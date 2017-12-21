package cn.lhzs.service.impl;

import cn.lhzs.base.AbstractBaseService;
import cn.lhzs.data.bean.SysRoleUser;
import cn.lhzs.data.dao.SysRoleUserMapper;
import cn.lhzs.service.intf.SysRoleUserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by ZHX on 2017/10/18.
 */
@Service
public class SysRoleUserServiceImpl extends AbstractBaseService<SysRoleUser> implements SysRoleUserService {

    Logger logger = Logger.getLogger(SysRoleUserServiceImpl.class);

    @Resource
    public SysRoleUserMapper sysRoleUserMapper;

}
