package cn.lhzs.service.impl;

import cn.lhzs.base.AbstractBaseService;
import cn.lhzs.data.bean.Catalog;
import cn.lhzs.data.dao.CatalogMapper;
import cn.lhzs.service.intf.CatalogService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/5/7.
 */
@Service
@Transactional(readOnly = true)
public class CatalogServiceImpl extends AbstractBaseService<Catalog> implements CatalogService{

    Logger logger = Logger.getLogger(CatalogServiceImpl.class);

    @Resource
    public CatalogMapper catalogMapper;

    @Override
    public List<Catalog> getCatalogList() {
        return findAll();
    }
}
