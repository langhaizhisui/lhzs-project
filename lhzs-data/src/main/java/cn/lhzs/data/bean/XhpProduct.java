package cn.lhzs.data.bean;

import cn.lhzs.data.base.BaseModel;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;

@Table(name = "xhp_product")
public class XhpProduct extends BaseModel {

    /**
     * 商品标题
     */
    private String title;

    /**
     * 商品主图
     */
    private String banner;

    /**
     * 商品详情图
     */
    @Column(name = "detaiBanners")
    private String detaibanners;

    /**
     * 商品是否为sku
     */
    @Column(name = "isSku")
    private Integer issku;

    /**
     * 商品配置信息
     */
    private String config;

    @Transient
    private List<XhpProductSku> skuList;

    @Transient
    private List<XhpFileUpload> mainBannerList;

    @Transient
    private List<XhpFileUpload> detailBannerList;

    /**
     * 获取商品标题
     *
     * @return title - 商品标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置商品标题
     *
     * @param title 商品标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取商品主图
     *
     * @return banner - 商品主图
     */
    public String getBanner() {
        return banner;
    }

    /**
     * 设置商品主图
     *
     * @param banner 商品主图
     */
    public void setBanner(String banner) {
        this.banner = banner;
    }

    /**
     * 获取商品详情图
     *
     * @return detaiBanners - 商品详情图
     */
    public String getDetaibanners() {
        return detaibanners;
    }

    /**
     * 设置商品详情图
     *
     * @param detaibanners 商品详情图
     */
    public void setDetaibanners(String detaibanners) {
        this.detaibanners = detaibanners;
    }

    /**
     * 获取商品是否为sku
     *
     * @return isSku - 商品是否为sku
     */
    public Integer getIssku() {
        return issku;
    }

    /**
     * 设置商品是否为sku
     *
     * @param issku 商品是否为sku
     */
    public void setIssku(Integer issku) {
        this.issku = issku;
    }

    /**
     * 获取商品配置信息
     *
     * @return config - 商品配置信息
     */
    public String getConfig() {
        return config;
    }

    /**
     * 设置商品配置信息
     *
     * @param config 商品配置信息
     */
    public void setConfig(String config) {
        this.config = config;
    }

    public List<XhpProductSku> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<XhpProductSku> skuList) {
        this.skuList = skuList;
    }

    public List<XhpFileUpload> getMainBannerList() {
        return mainBannerList;
    }

    public void setMainBannerList(List<XhpFileUpload> mainBannerList) {
        this.mainBannerList = mainBannerList;
    }

    public List<XhpFileUpload> getDetailBannerList() {
        return detailBannerList;
    }

    public void setDetailBannerList(List<XhpFileUpload> detailBannerList) {
        this.detailBannerList = detailBannerList;
    }
}