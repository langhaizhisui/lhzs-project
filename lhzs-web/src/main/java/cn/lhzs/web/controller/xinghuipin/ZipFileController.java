package cn.lhzs.web.controller.xinghuipin;

/**
 * Created by ZHX on 2018/12/3.
 */

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ZipFileController {

    public static void main(String[] args) throws IOException {
        String source = "D:\\aaa.zip";
        String dest = "D:\\bbb";
        String password = "password";
        try {
            ZipFile zFile = new ZipFile(new File(source));
            zFile.setFileNameCharset("GBK");
            if (zFile.isEncrypted()) {
                zFile.setPassword(password.toCharArray());// 设置密码   
            }
            zFile.extractAll(dest);// 将文件抽出到解压目录(解压)   

            List<FileHeader> headerList = zFile.getFileHeaders();
            for (FileHeader fileHeader : headerList) {
                if (!fileHeader.isDirectory()) {
                    File file = new File(dest, fileHeader.getFileName());
                    System.out.println(file.getAbsolutePath());
                }
            }

        } catch (ZipException e) {

        }
    }
}
