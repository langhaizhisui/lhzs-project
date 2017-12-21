package cn.lhzs.service.impl;

import cn.lhzs.base.AbstractBaseService;
import cn.lhzs.data.bean.SysRoleAuth;
import cn.lhzs.data.dao.SysRoleAuthMapper;
import cn.lhzs.service.intf.SysRoleAuthService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by ZHX on 2017/10/18.
 */
@Service
public class SysRoleAuthServiceImpl extends AbstractBaseService<SysRoleAuth> implements SysRoleAuthService {

    Logger logger = Logger.getLogger(SysRoleAuthServiceImpl.class);

    @Resource
    public SysRoleAuthMapper sysRoleAuthMapper;

}
