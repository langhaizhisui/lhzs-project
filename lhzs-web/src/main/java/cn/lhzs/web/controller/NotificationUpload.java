package cn.lhzs.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 附件上传处理
 */
@Controller
@RequestMapping("/notificationUpload")
public class NotificationUpload {

    /**
     * 删除文件
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "deleteFile", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String deleteFile(String id) {
        return id;
    }

    /**
     * 修改默认文件名称
     */
    class MyFileRenamePolicy {
        public String rename(MultipartFile file) {
            return "fileName";
        }
    }

    @RequestMapping(value = "fileUpload", produces = "text/html;charset=UTF-8")
    public void fileUpload(MultipartFile file, Long userId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(file.getName());
    }

    /**
     * 创建附件上传目录
     *
     * @return 文件上传目录绝对路径
     */
    public String createUploadDir(MultipartFile file, String attach) {
        return "";
    }

    public void printInfo(HttpServletResponse response, String err, String newFileName) throws IOException {

    }

    /**
     * 附件下载
     *
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/fileDownload", produces = "application/json;charset=UTF-8")
    public void fileDownload(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }
}
