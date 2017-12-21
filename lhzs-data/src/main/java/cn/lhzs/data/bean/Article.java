package cn.lhzs.data.bean;

import cn.lhzs.data.base.BaseModel;

import javax.persistence.Column;

public class Article extends BaseModel {

    /**
     * 页面metaTitle
     */
    @Column(name = "meta_title")
    private String metaTitle;

    /**
     * 页面meta关键字
     */
    @Column(name = "meta_key")
    private String metaKey;

    /**
     * 页面meta描述
     */
    @Column(name = "meta_descp")
    private String metaDescp;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章封面
     */
    private String picture;

    /**
     * 文章作者
     */
    private String author;

    /**
     * 文章链接
     */
    private String src;

    /**
     * 文章权重
     */
    private Integer weight;

    /**
     * 文章类型
     */
    private String type;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 内容描述，用于列表的显示
     */
    @Column(name = "content_descp")
    private String contentDescp;

    /**
     * 获取页面metaTitle
     *
     * @return meta_title - 页面metaTitle
     */
    public String getMetaTitle() {
        return metaTitle;
    }

    /**
     * 设置页面metaTitle
     *
     * @param metaTitle 页面metaTitle
     */
    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    /**
     * 获取页面meta关键字
     *
     * @return meta_key - 页面meta关键字
     */
    public String getMetaKey() {
        return metaKey;
    }

    /**
     * 设置页面meta关键字
     *
     * @param metaKey 页面meta关键字
     */
    public void setMetaKey(String metaKey) {
        this.metaKey = metaKey;
    }

    /**
     * 获取页面meta描述
     *
     * @return meta_descp - 页面meta描述
     */
    public String getMetaDescp() {
        return metaDescp;
    }

    /**
     * 设置页面meta描述
     *
     * @param metaDescp 页面meta描述
     */
    public void setMetaDescp(String metaDescp) {
        this.metaDescp = metaDescp;
    }

    /**
     * 获取文章标题
     *
     * @return title - 文章标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置文章标题
     *
     * @param title 文章标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取文章封面
     *
     * @return picture - 文章封面
     */
    public String getPicture() {
        return picture;
    }

    /**
     * 设置文章封面
     *
     * @param picture 文章封面
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * 获取文章作者
     *
     * @return author - 文章作者
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置文章作者
     *
     * @param author 文章作者
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 获取文章链接
     *
     * @return src - 文章链接
     */
    public String getSrc() {
        return src;
    }

    /**
     * 设置文章链接
     *
     * @param src 文章链接
     */
    public void setSrc(String src) {
        this.src = src;
    }

    /**
     * 获取文章权重
     *
     * @return weight - 文章权重
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * 设置文章权重
     *
     * @param weight 文章权重
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    /**
     * 获取文章类型
     *
     * @return type - 文章类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置文章类型
     *
     * @param type 文章类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取文章内容
     *
     * @return content - 文章内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置文章内容
     *
     * @param content 文章内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 内容描述，用于列表的显示
     *
     * @return content - 内容描述
     */
    public String getContentDescp() {
        return contentDescp;
    }

    /**
     * 内容描述，用于列表的显示
     *
     * @return content - 内容描述
     */
    public void setContentDescp(String contentDescp) {
        this.contentDescp = contentDescp;
    }
}