package cn.lhzs.service.impl;

import cn.lhzs.base.AbstractBaseService;
import cn.lhzs.data.bean.Article;
import cn.lhzs.data.bean.Meta;
import cn.lhzs.data.dao.MetaMapper;
import cn.lhzs.service.intf.MetaService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by ZHX on 2017/5/10.
 */
@Service
@Transactional(readOnly = true)
public class MetaServiceImpl  extends AbstractBaseService<Meta> implements MetaService{

    Logger logger = Logger.getLogger(MetaServiceImpl.class);

    @Resource
    public MetaMapper metaMapper;

    @Override
    public Meta getMeta(Long id) {
        return findById(id);
    }
}
