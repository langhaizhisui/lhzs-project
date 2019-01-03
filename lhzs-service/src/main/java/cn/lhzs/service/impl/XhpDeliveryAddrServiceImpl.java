package cn.lhzs.service.impl;

import cn.lhzs.data.dao.XhpDeliveryAddrMapper;
import cn.lhzs.data.bean.XhpDeliveryAddr;
import cn.lhzs.service.intf.XhpDeliveryAddrService;
import cn.lhzs.base.AbstractBaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.CacheConfig;

import javax.annotation.Resource;


/**
 * Created by ZHX on 2019/01/03.
 */
@CacheConfig(cacheNames = "XhpDeliveryAddr")
@Service
@Transactional
public class XhpDeliveryAddrServiceImpl extends AbstractBaseService<XhpDeliveryAddr> implements XhpDeliveryAddrService {
    @Resource
    private XhpDeliveryAddrMapper xhpDeliveryAddrMapper;

}
