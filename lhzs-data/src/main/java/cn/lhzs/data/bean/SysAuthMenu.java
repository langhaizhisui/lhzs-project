package cn.lhzs.data.bean;

import cn.lhzs.data.base.BaseModel;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

@Table(name = "sys_auth_menu")
public class SysAuthMenu extends BaseModel {

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单权重
     */
    private Integer weight;

    /**
     * 菜单所关联页面
     */
    private String url;

    /**
     * 父级菜单标识（0--第一层）
     */
    @Column(name = "p_amid")
    private Long pAmid;

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
     * 直接下级菜单
     */
    @Transient
    private Set<SysAuthMenu> subSysAuthMenuList;

    /**
     * 获取菜单名称
     *
     * @return name - 菜单名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置菜单名称
     *
     * @param name 菜单名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取菜单权重
     *
     * @return weight - 菜单权重
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * 设置菜单权重
     *
     * @param weight 菜单权重
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    /**
     * 获取菜单所关联页面
     *
     * @return url - 菜单所关联页面
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置菜单所关联页面
     *
     * @param url 菜单所关联页面
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取父级菜单标识（0--第一层）
     *
     * @return p_amid - 父级菜单标识（0--第一层）
     */
    public Long getpAmid() {
        return pAmid;
    }

    /**
     * 设置父级菜单标识（0--第一层）
     *
     * @param pAmid 父级菜单标识（0--第一层）
     */
    public void setpAmid(Long pAmid) {
        this.pAmid = pAmid;
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

    public Set<SysAuthMenu> getSubSysAuthMenuList() {
        return subSysAuthMenuList;
    }

    public void setSubSysAuthMenuList(Set<SysAuthMenu> subSysAuthMenuList) {
        this.subSysAuthMenuList = subSysAuthMenuList;
    }

    @Override
    public int hashCode()
    {
        return getId().hashCode();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o.getClass() == SysAuthMenu.class) {
            SysAuthMenu sysAuthMenu = (SysAuthMenu)o;
            return sysAuthMenu.getId().equals(getId());
        }

        return false;
    }
}