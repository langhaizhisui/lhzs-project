package cn.lhzs.service.intf;

import cn.lhzs.base.IBaseService;
import cn.lhzs.data.bean.SysAuthMenu;
import cn.lhzs.data.bean.SysUser;

import java.util.Set;

/**
 * Created by ZHX on 2017/10/18.
 */
public interface SysUserService extends IBaseService<SysUser> {

    Set<SysAuthMenu> getMenu();
}
