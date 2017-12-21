package cn.lhzs.web.controller.admin;

import cn.lhzs.common.aop.log.OpLog;
import cn.lhzs.data.bean.SlideShowPicture;
import cn.lhzs.data.bean.Upload;
import cn.lhzs.common.result.ResponseResult;
import cn.lhzs.service.impl.UploadServiceImpl;
import cn.lhzs.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static cn.lhzs.common.aop.log.OpEnum.ADD;
import static cn.lhzs.common.result.ResponseResultGenerator.generatorFailResult;
import static cn.lhzs.common.result.ResponseResultGenerator.generatorSuccessResult;

/**
 * Created by ZHX on 2017/12/6.
 */
@Controller
@RequestMapping("/admin/upload")
public class AdminUploadController {

    @Autowired
    public UploadServiceImpl uploadService;

    @OpLog(type = ADD, descp = "批量增加商品或店铺")
    @RequestMapping("/excel")
    @ResponseBody
    public ResponseResult getExcel(@RequestParam(value = "file") MultipartFile file, @RequestParam(value = "type") String type) {
        try {
            String fileName = file.getOriginalFilename();
            InputStream inputStream = file.getInputStream();
            String flag = uploadService.getExcell(fileName, inputStream, type);

            if (Upload.EXCEL_TEMPLATE_ERROR.equals(flag)) {
                return generatorFailResult("模板格式错误");
            }
            return generatorSuccessResult();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/slideShowPicture")
    @ResponseBody
    public ResponseResult saveSlideShowPicture(@RequestParam(value = "file") MultipartFile file,
                                               @RequestParam(value = "toUrl") String toUrl,
                                               HttpServletRequest request) {
        try {
            String slideShowPicturePath = request.getSession().getServletContext().getRealPath("/slideShowPicture");
            String fileName = file.getOriginalFilename();
            String pictureName = DateUtil.getNowTimeStampStr() + fileName.substring(fileName.indexOf('.'));
            file.transferTo(new File(slideShowPicturePath, pictureName));

            SlideShowPicture slideShowPicture = new SlideShowPicture();
            slideShowPicture.setUrl("/slideShowPicture/" + pictureName);
            slideShowPicture.setToUrl(toUrl);
            slideShowPicture.setWeight(1);
            uploadService.saveImageInfo(slideShowPicture);
            return generatorSuccessResult();
        } catch (IOException e) {
            return generatorFailResult("上传失败");
        }
    }
}
