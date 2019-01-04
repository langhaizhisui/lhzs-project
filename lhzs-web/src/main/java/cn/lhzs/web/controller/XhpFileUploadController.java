package cn.lhzs.web.controller;

import cn.lhzs.common.result.ResponseResult;
import cn.lhzs.common.util.DateUtil;
import cn.lhzs.common.util.StringUtil;
import cn.lhzs.common.uuid.UUIDUtil;
import cn.lhzs.data.bean.XhpFileUpload;
import cn.lhzs.service.intf.XhpFileUploadService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import static cn.lhzs.common.result.ResponseResultGenerator.generatorSuccessResult;

/**
 * Created by ZHX on 2019/01/03.
 */
@Controller
@RequestMapping("/xhp/file/upload")
public class XhpFileUploadController {

    @Autowired
    private XhpFileUploadService xhpFileUploadService;

    @RequestMapping("/deleteFile")
    @ResponseBody
    public ResponseResult deleteFile(@RequestBody XhpFileUpload fileUpload) {
        fileUpload.setState(2);
        xhpFileUploadService.save(fileUpload);
        return generatorSuccessResult();
    }

    @RequestMapping("/fileUpload")
    @ResponseBody
    public ResponseResult fileUpload(MultipartFile file, Long userId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String method = request.getParameter("method");
        String attach = UUIDUtil.generateUUID() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        XhpFileUpload fileUpload = new XhpFileUpload();
        if (file != null && StringUtil.equals("fileupload", method)) {
            fileUpload.setUid(userId);
            fileUpload.setDir(createUploadDir(file, attach));
            fileUpload.setAlia(attach);
            fileUpload.setName(file.getOriginalFilename());
            fileUpload.setFileSize(file.getSize() / (1024 * 1024 * 1.0));
            xhpFileUploadService.save(fileUpload);
        }
        return generatorSuccessResult(fileUpload);
    }

    /**
     * 创建附件上传目录
     *
     * @return 文件上传目录绝对路径
     */
    public String createUploadDir(MultipartFile file, String attach) {
        try {
            String basePath = "D:" + File.separator + "attachment";
            String uploadDir = basePath + File.separator + DateUtil.formatDate(new Date(), "yyyyMM") + File.separator;// 按月分目录存储
            File savePath = new File(uploadDir, attach);
            if (!savePath.exists()) {
                savePath.mkdirs();
            }
            file.transferTo(savePath);
            return uploadDir;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 附件下载
     *
     * @return
     * @throws IOException
     */
    @RequestMapping("/fileDownload")
    public void fileDownload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        OutputStream os = response.getOutputStream();
        FileInputStream fis = null;
        try {
            String filename = "3e6794b7a1224f0f97f72825cfd947da.jpg";
            fis = new java.io.FileInputStream("D:\\attachment\\201901\\3e6794b7a1224f0f97f72825cfd947da.jpg");
            response.setContentType("application/octet-stream; charset=UTF-8");
            response.setHeader("Content-Disposition",
                    "attachment; filename=\"" + filename.toString() + "\"");

            byte[] b = new byte[1024];
            int length = 0;
            while ((length = fis.read(b)) != -1) {
                os.write(b, 0, length);
            }
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null)
                os.close();
            if (fis != null)
                fis.close();
        }
    }

}
