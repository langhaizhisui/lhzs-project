package cn.lhzs.data.bean;

import cn.lhzs.data.base.BaseModel;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_auth")
public class SysAuth extends BaseModel {

    private Long amid;

    /**
     * 列名称
     */
    private String name;

    /**
     * 页面url链接
     */
    private String url;

    /**
     * 创建人
     */
    @Column(name = "create_by")
    private Long createBy;

    /**
     * 更新人
     */
    @Column(name = "update_by")
    private Long updateBy;

    /**
     * @return amid
     */
    public Long getAmid() {
        return amid;
    }

    /**
     * @param amid
     */
    public void setAmid(Long amid) {
        this.amid = amid;
    }

    /**
     * 获取列名称
     *
     * @return name - 列名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置列名称
     *
     * @param name 列名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取页面url链接
     *
     * @return url - 页面url链接
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置页面url链接
     *
     * @param url 页面url链接
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取创建人
     *
     * @return create_by - 创建人
     */
    public Long getCreateBy() {
        return createBy;
    }

    /**
     * 设置创建人
     *
     * @param createBy 创建人
     */
    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    /**
     * 获取更新人
     *
     * @return update_by - 更新人
     */
    public Long getUpdateBy() {
        return updateBy;
    }

    /**
     * 设置更新人
     *
     * @param updateBy 更新人
     */
    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }
}