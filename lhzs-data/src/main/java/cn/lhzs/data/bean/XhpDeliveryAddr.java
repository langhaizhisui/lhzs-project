package cn.lhzs.data.bean;

import cn.lhzs.data.base.BaseModel;
import java.util.Date;
import javax.persistence.*;

@Table(name = "xhp_delivery_addr")
public class XhpDeliveryAddr extends BaseModel {

    /**
     * 用户标识
     */
    private Long uid;

    /**
     * 收货人
     */
    private String name;

    /**
     * 联系电话
     */
    private String contact;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String district;

    /**
     * 详细地址
     */
    private String descp;

    /**
     * 默认地址
     */
    @Column(name = "default_addr")
    private Integer defaultAddr;

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
     * 获取收货人
     *
     * @return name - 收货人
     */
    public String getName() {
        return name;
    }

    /**
     * 设置收货人
     *
     * @param name 收货人
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取联系电话
     *
     * @return contact - 联系电话
     */
    public String getContact() {
        return contact;
    }

    /**
     * 设置联系电话
     *
     * @param contact 联系电话
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * 获取省
     *
     * @return province - 省
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置省
     *
     * @param province 省
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取市
     *
     * @return city - 市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置市
     *
     * @param city 市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取区
     *
     * @return district - 区
     */
    public String getDistrict() {
        return district;
    }

    /**
     * 设置区
     *
     * @param district 区
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * 获取详细地址
     *
     * @return descp - 详细地址
     */
    public String getDescp() {
        return descp;
    }

    /**
     * 设置详细地址
     *
     * @param descp 详细地址
     */
    public void setDescp(String descp) {
        this.descp = descp;
    }

    /**
     * 获取默认地址
     *
     * @return default_addr - 默认地址
     */
    public Integer getDefaultAddr() {
        return defaultAddr;
    }

    /**
     * 设置默认地址
     *
     * @param defaultAddr 默认地址
     */
    public void setDefaultAddr(Integer defaultAddr) {
        this.defaultAddr = defaultAddr;
    }
}