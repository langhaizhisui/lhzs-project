package cn.lhzs.data.bean;

import cn.lhzs.data.base.BaseModel;
import java.util.Date;
import javax.persistence.*;

public class Product extends BaseModel {

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品主图链接
     */
    private String banner;

    /**
     * 商品详情页链接
     */
    private String detail;

    /**
     * 商品类目
     */
    private String category;

    /**
     * 商品价格
     */
    private Double price;

    /**
     * 优惠价格
     */
    @Column(name = "discount_price")
    private Double discountPrice;

    /**
     * 优惠文字描述
     */
    @Column(name = "discount_desc")
    private String discountDesc;

    /**
     * 平台
     */
    private String platform;

    @Column(name = "save_price")
    private Double savePrice;

    /**
     * 商品推广链接
     */
    @Column(name = "prod_generalize")
    private String prodGeneralize;

    private String expiration;

    /**
     * 浏览数
     */
    @Column(name = "scan_num")
    private Integer scanNum;

    /**
     * 获取商品名称
     *
     * @return name - 商品名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置商品名称
     *
     * @param name 商品名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取商品主图链接
     *
     * @return banner - 商品主图链接
     */
    public String getBanner() {
        return banner;
    }

    /**
     * 设置商品主图链接
     *
     * @param banner 商品主图链接
     */
    public void setBanner(String banner) {
        this.banner = banner;
    }

    /**
     * 获取商品详情页链接
     *
     * @return detail - 商品详情页链接
     */
    public String getDetail() {
        return detail;
    }

    /**
     * 设置商品详情页链接
     *
     * @param detail 商品详情页链接
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * 获取商品类目
     *
     * @return category - 商品类目
     */
    public String getCategory() {
        return category;
    }

    /**
     * 设置商品类目
     *
     * @param category 商品类目
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * 获取商品价格
     *
     * @return price - 商品价格
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 设置商品价格
     *
     * @param price 商品价格
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 获取优惠价格
     *
     * @return discount_price - 优惠价格
     */
    public Double getDiscountPrice() {
        return discountPrice;
    }

    /**
     * 设置优惠价格
     *
     * @param discountPrice 优惠价格
     */
    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    /**
     * 获取优惠文字描述
     *
     * @return discount_desc - 优惠文字描述
     */
    public String getDiscountDesc() {
        return discountDesc;
    }

    /**
     * 设置优惠文字描述
     *
     * @param discountDesc 优惠文字描述
     */
    public void setDiscountDesc(String discountDesc) {
        this.discountDesc = discountDesc;
    }

    /**
     * 获取平台
     *
     * @return platform - 平台
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * 设置平台
     *
     * @param platform 平台
     */
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    /**
     * @return save_price
     */
    public Double getSavePrice() {
        return savePrice;
    }

    /**
     * @param savePrice
     */
    public void setSavePrice(Double savePrice) {
        this.savePrice = savePrice;
    }

    /**
     * 获取商品推广链接
     *
     * @return prod_generalize - 商品推广链接
     */
    public String getProdGeneralize() {
        return prodGeneralize;
    }

    /**
     * 设置商品推广链接
     *
     * @param prodGeneralize 商品推广链接
     */
    public void setProdGeneralize(String prodGeneralize) {
        this.prodGeneralize = prodGeneralize;
    }

    /**
     * @return expiration
     */
    public String getExpiration() {
        return expiration;
    }

    /**
     * @param expiration
     */
    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    /**
     * 获取浏览数
     *
     * @return scan_num - 浏览数
     */
    public Integer getScanNum() {
        return scanNum;
    }

    /**
     * 设置浏览数
     *
     * @param scanNum 浏览数
     */
    public void setScanNum(Integer scanNum) {
        this.scanNum = scanNum;
    }

}