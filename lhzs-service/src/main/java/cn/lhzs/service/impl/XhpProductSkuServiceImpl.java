package cn.lhzs.service.impl;

import cn.lhzs.data.dao.XhpProductSkuMapper;
import cn.lhzs.data.bean.XhpProductSku;
import cn.lhzs.service.intf.XhpProductSkuService;
import cn.lhzs.base.AbstractBaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.CacheConfig;

import javax.annotation.Resource;


/**
 * Created by ZHX on 2019/01/03.
 */
@CacheConfig(cacheNames = "XhpProductSku")
@Service
@Transactional
public class XhpProductSkuServiceImpl extends AbstractBaseService<XhpProductSku> implements XhpProductSkuService {
    @Resource
    private XhpProductSkuMapper xhpProductSkuMapper;

}
