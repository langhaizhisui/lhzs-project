package cn.lhzs.data.bean;

import cn.lhzs.data.base.BaseModel;
import java.util.Date;
import javax.persistence.*;

@Table(name = "xhp_shop")
public class XhpShop extends BaseModel {
    /**
     * 用户标识
     */
    private Long uid;

    /**
     * 店铺名称
     */
    @Column(name = "shopName")
    private String shopname;

    /**
     * 用户别名
     */
    private String name;

    /**
     * 微信
     */
    private String wechat;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 获取用户标识
     *
     * @return uid - 用户标识
     */
    public Long getUid() {
        return uid;
    }

    /**
     * 设置用户标识
     *
     * @param uid 用户标识
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * 获取店铺名称
     *
     * @return shopName - 店铺名称
     */
    public String getShopname() {
        return shopname;
    }

    /**
     * 设置店铺名称
     *
     * @param shopname 店铺名称
     */
    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    /**
     * 获取用户别名
     *
     * @return name - 用户别名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置用户别名
     *
     * @param name 用户别名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取微信
     *
     * @return wechat - 微信
     */
    public String getWechat() {
        return wechat;
    }

    /**
     * 设置微信
     *
     * @param wechat 微信
     */
    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    /**
     * 获取手机号码
     *
     * @return mobile - 手机号码
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号码
     *
     * @param mobile 手机号码
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取性别
     *
     * @return sex - 性别
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置性别
     *
     * @param sex 性别
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }
}