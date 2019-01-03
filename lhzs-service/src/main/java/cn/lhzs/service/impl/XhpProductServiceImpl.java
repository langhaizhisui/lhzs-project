package cn.lhzs.service.impl;

import cn.lhzs.data.dao.XhpProductMapper;
import cn.lhzs.data.bean.XhpProduct;
import cn.lhzs.service.intf.XhpProductService;
import cn.lhzs.base.AbstractBaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.CacheConfig;

import javax.annotation.Resource;


/**
 * Created by ZHX on 2019/01/03.
 */
@CacheConfig(cacheNames = "XhpProduct")
@Service
@Transactional
public class XhpProductServiceImpl extends AbstractBaseService<XhpProduct> implements XhpProductService {
    @Resource
    private XhpProductMapper xhpProductMapper;

}
