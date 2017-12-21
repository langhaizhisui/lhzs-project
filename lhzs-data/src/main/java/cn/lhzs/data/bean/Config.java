package cn.lhzs.data.bean;

import cn.lhzs.data.base.BaseModel;
import java.util.Date;
import javax.persistence.*;

public class Config extends BaseModel {

    /**
     * 配置数据
     */
    private String value;

    /**
     * 更新人id
     */
    @Column(name = "update_uid")
    private Integer updateUid;

    /**
     * 配置标注
     */
    private String remark;

    /**
     * 获取配置数据
     *
     * @return value - 配置数据
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置配置数据
     *
     * @param value 配置数据
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 获取更新人id
     *
     * @return update_uid - 更新人id
     */
    public Integer getUpdateUid() {
        return updateUid;
    }

    /**
     * 设置更新人id
     *
     * @param updateUid 更新人id
     */
    public void setUpdateUid(Integer updateUid) {
        this.updateUid = updateUid;
    }

    /**
     * 获取配置标注
     *
     * @return remark - 配置标注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置配置标注
     *
     * @param remark 配置标注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

}