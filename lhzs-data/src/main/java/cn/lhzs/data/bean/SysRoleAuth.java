package cn.lhzs.data.bean;

import cn.lhzs.data.base.BaseModel;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_role_auth")
public class SysRoleAuth extends BaseModel {

    /**
     * 角色标识
     */
    private Long rid;

    /**
     * 权限标识
     */
    private Long aid;

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
     * 获取角色标识
     *
     * @return rid - 角色标识
     */
    public Long getRid() {
        return rid;
    }

    /**
     * 设置角色标识
     *
     * @param rid 角色标识
     */
    public void setRid(Long rid) {
        this.rid = rid;
    }

    /**
     * 获取权限标识
     *
     * @return aid - 权限标识
     */
    public Long getAid() {
        return aid;
    }

    /**
     * 设置权限标识
     *
     * @param aid 权限标识
     */
    public void setAid(Long aid) {
        this.aid = aid;
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