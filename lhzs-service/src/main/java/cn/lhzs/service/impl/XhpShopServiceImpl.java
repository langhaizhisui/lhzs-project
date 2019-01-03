package cn.lhzs.service.impl;

import cn.lhzs.data.dao.XhpShopMapper;
import cn.lhzs.data.bean.XhpShop;
import cn.lhzs.service.intf.XhpShopService;
import cn.lhzs.base.AbstractBaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.CacheConfig;

import javax.annotation.Resource;


/**
 * Created by ZHX on 2019/01/03.
 */
@CacheConfig(cacheNames = "XhpShop")
@Service
@Transactional
public class XhpShopServiceImpl extends AbstractBaseService<XhpShop> implements XhpShopService {
    @Resource
    private XhpShopMapper xhpShopMapper;

}
