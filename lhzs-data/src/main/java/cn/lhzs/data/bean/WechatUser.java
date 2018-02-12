package cn.lhzs.data.bean;

import cn.lhzs.data.base.BaseModel;
import java.util.Date;
import javax.persistence.*;

@Table(name = "wechat_user")
public class WechatUser extends BaseModel {
    /**
     * 微信用户openId
     */
    @Column(name = "open_id")
    private String openId;

    /**
     * 性别
     */
    private Byte sex;

    /**
     * 省份
     */
    private String province;

    /**
     * 市区
     */
    private String city;

    /**
     * 县
     */
    private String country;

    /**
     * 微信头像
     */
    private String headimgurl;

    /**
     * 特权
     */
    private String privilege;

    /**
     * 微信关联Id
     */
    @Column(name = "union_id")
    private Integer unionId;

    /**
     * 用户Id
     */
    private Integer uid;

    /**
     * 获取微信用户openId
     *
     * @return open_id - 微信用户openId
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * 设置微信用户openId
     *
     * @param openId 微信用户openId
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * 获取性别
     *
     * @return sex - 性别
     */
    public Byte getSex() {
        return sex;
    }

    /**
     * 设置性别
     *
     * @param sex 性别
     */
    public void setSex(Byte sex) {
        this.sex = sex;
    }

    /**
     * 获取省份
     *
     * @return province - 省份
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置省份
     *
     * @param province 省份
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取市区
     *
     * @return city - 市区
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置市区
     *
     * @param city 市区
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取县
     *
     * @return country - 县
     */
    public String getCountry() {
        return country;
    }

    /**
     * 设置县
     *
     * @param country 县
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 获取微信头像
     *
     * @return headimgurl - 微信头像
     */
    public String getHeadimgurl() {
        return headimgurl;
    }

    /**
     * 设置微信头像
     *
     * @param headimgurl 微信头像
     */
    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    /**
     * 获取特权
     *
     * @return privilege - 特权
     */
    public String getPrivilege() {
        return privilege;
    }

    /**
     * 设置特权
     *
     * @param privilege 特权
     */
    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    /**
     * 获取微信关联Id
     *
     * @return union_id - 微信关联Id
     */
    public Integer getUnionId() {
        return unionId;
    }

    /**
     * 设置微信关联Id
     *
     * @param unionId 微信关联Id
     */
    public void setUnionId(Integer unionId) {
        this.unionId = unionId;
    }

    /**
     * 获取用户Id
     *
     * @return uid - 用户Id
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 设置用户Id
     *
     * @param uid 用户Id
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

}