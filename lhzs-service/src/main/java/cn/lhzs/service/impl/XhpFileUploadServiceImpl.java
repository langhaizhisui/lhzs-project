package cn.lhzs.service.impl;

import cn.lhzs.data.dao.XhpFileUploadMapper;
import cn.lhzs.data.bean.XhpFileUpload;
import cn.lhzs.service.intf.XhpFileUploadService;
import cn.lhzs.base.AbstractBaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.CacheConfig;

import javax.annotation.Resource;


/**
 * Created by ZHX on 2019/01/03.
 */
@CacheConfig(cacheNames = "XhpFileUpload")
@Service
@Transactional
public class XhpFileUploadServiceImpl extends AbstractBaseService<XhpFileUpload> implements XhpFileUploadService {
    @Resource
    private XhpFileUploadMapper xhpFileUploadMapper;

}
