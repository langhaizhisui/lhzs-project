package cn.lhzs.data.bean;

import cn.lhzs.data.base.BaseModel;
import java.util.Date;
import javax.persistence.*;

@Table(name = "xhp_file_upload")
public class XhpFileUpload extends BaseModel {

    /**
     * 存储目录
     */
    private String dir;

    /**
     * 原文件名称
     */
    private String name;

    /**
     * 文件别名
     */
    private String alia;

    /**
     * 文件大小
     */
    @Column(name = "file_size")
    private Double fileSize;

    /**
     * 获取存储目录
     *
     * @return dir - 存储目录
     */
    public String getDir() {
        return dir;
    }

    /**
     * 设置存储目录
     *
     * @param dir 存储目录
     */
    public void setDir(String dir) {
        this.dir = dir;
    }

    /**
     * 获取原文件名称
     *
     * @return name - 原文件名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置原文件名称
     *
     * @param name 原文件名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取文件别名
     *
     * @return alia - 文件别名
     */
    public String getAlia() {
        return alia;
    }

    /**
     * 设置文件别名
     *
     * @param alia 文件别名
     */
    public void setAlia(String alia) {
        this.alia = alia;
    }

    /**
     * 获取文件大小
     *
     * @return file_size - 文件大小
     */
    public Double getFileSize() {
        return fileSize;
    }

    /**
     * 设置文件大小
     *
     * @param fileSize 文件大小
     */
    public void setFileSize(Double fileSize) {
        this.fileSize = fileSize;
    }

}