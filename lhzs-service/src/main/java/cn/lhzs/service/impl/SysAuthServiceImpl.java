package cn.lhzs.service.impl;

import cn.lhzs.base.AbstractBaseService;
import cn.lhzs.data.bean.SysAuth;
import cn.lhzs.data.dao.SysAuthMapper;
import cn.lhzs.service.intf.SysAuthService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZHX on 2017/10/18.
 */
@Service
public class SysAuthServiceImpl extends AbstractBaseService<SysAuth> implements SysAuthService {

    Logger logger = Logger.getLogger(SysAuthServiceImpl.class);

    @Resource
    public SysAuthMapper sysAuthMapper;

    @Override
    public List<SysAuth> getUserAuthList(Long uid) {
        return sysAuthMapper.getUserAuthList(uid);
    }

}
