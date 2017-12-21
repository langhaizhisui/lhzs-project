package cn.lhzs.data.bean;

import cn.lhzs.data.base.BaseModel;

import javax.persistence.Column;
import javax.persistence.Transient;

public class Shop extends BaseModel {

    @Column(name = "web_shop")
    private String webShop;

    private String site;

    private String type;

    @Column(name = "sell_name")
    private String sellName;

    @Column(name = "brand_name")
    private String brandName;

    @Column(name = "sell_prod")
    private String sellProd;

    @Column(name = "web_url")
    private String webUrl;

    @Column(name = "web_generalize")
    private String webGeneralize;

    @Column(name = "mobile_url")
    private String mobileUrl;

    @Column(name = "mobile_generalize")
    private String mobileGeneralize;

    @Column(name = "shop_addr")
    private String shopAddr;

    private String banner;

    @Transient
    private String key;

    public String getWebShop() {
        return webShop;
    }

    public void setWebShop(String webShop) {
        this.webShop = webShop == null ? null : webShop.trim();
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site == null ? null : site.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getSellName() {
        return sellName;
    }

    public void setSellName(String sellName) {
        this.sellName = sellName == null ? null : sellName.trim();
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

    public String getSellProd() {
        return sellProd;
    }

    public void setSellProd(String sellProd) {
        this.sellProd = sellProd == null ? null : sellProd.trim();
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl == null ? null : webUrl.trim();
    }

    public String getWebGeneralize() {
        return webGeneralize;
    }

    public void setWebGeneralize(String webGeneralize) {
        this.webGeneralize = webGeneralize == null ? null : webGeneralize.trim();
    }

    public String getMobileUrl() {
        return mobileUrl;
    }

    public void setMobileUrl(String mobileUrl) {
        this.mobileUrl = mobileUrl == null ? null : mobileUrl.trim();
    }

    public String getMobileGeneralize() {
        return mobileGeneralize;
    }

    public void setMobileGeneralize(String mobileGeneralize) {
        this.mobileGeneralize = mobileGeneralize == null ? null : mobileGeneralize.trim();
    }

    public String getShopAddr() {
        return shopAddr;
    }

    public void setShopAddr(String shopAddr) {
        this.shopAddr = shopAddr == null ? null : shopAddr.trim();
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner == null ? null : banner.trim();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}