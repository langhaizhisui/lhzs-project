package cn.lhzs.service.impl;


import cn.lhzs.base.AbstractBaseService;
import cn.lhzs.data.bean.SysRole;
import cn.lhzs.data.dao.SysRoleMapper;
import cn.lhzs.service.intf.SysAuthService;
import cn.lhzs.service.intf.SysRoleService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by ZHX on 2017/10/18.
 */
@Service
public class SysRoleServiceImpl extends AbstractBaseService<SysRole> implements SysRoleService {

    Logger logger = Logger.getLogger(SysRoleServiceImpl.class);

    @Resource
    public SysRoleMapper sysRoleMapper;

}
