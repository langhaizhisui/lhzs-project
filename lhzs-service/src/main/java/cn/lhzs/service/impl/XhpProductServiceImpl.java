package cn.lhzs.service.impl;

import cn.lhzs.base.AbstractBaseService;
import cn.lhzs.common.util.StringUtil;
import cn.lhzs.data.base.ExampleCondition;
import cn.lhzs.data.bean.XhpFileUpload;
import cn.lhzs.data.bean.XhpProduct;
import cn.lhzs.data.bean.XhpProductSku;
import cn.lhzs.data.dao.XhpProductMapper;
import cn.lhzs.service.intf.XhpFileUploadService;
import cn.lhzs.service.intf.XhpProductService;
import cn.lhzs.service.intf.XhpProductSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.pagehelper.PageHelper.startPage;


/**
 * Created by ZHX on 2019/01/03.
 */
@CacheConfig(cacheNames = "XhpProduct")
@Service
@Transactional
public class XhpProductServiceImpl extends AbstractBaseService<XhpProduct> implements XhpProductService {

    @Autowired
    private XhpProductSkuService xhpProductSkuService;

    @Autowired
    private XhpFileUploadService xhpFileUploadService;

    @Resource
    private XhpProductMapper xhpProductMapper;

    @Override
    public List<XhpProduct> getProductList(XhpProduct xhpProduct) {
        startPage(xhpProduct.getPage(), xhpProduct.getSize());
        return findByExampleCondition(getProductExample(xhpProduct)).parallelStream().map(product -> {
            XhpFileUpload bannerFileUpload = xhpFileUploadService.findById(Long.parseLong(product.getBanner()));
            if (bannerFileUpload != null) {
                product.setBanner(bannerFileUpload.getName());
            }
            List<XhpFileUpload> detaibannersFileUpload = xhpFileUploadService.findByIds(product.getDetaibanners());
            if (detaibannersFileUpload != null) {
                List<String> detaibannersList = new ArrayList<>();
                detaibannersFileUpload.forEach(detaibanner -> {
                    detaibannersList.add(detaibanner.getName());
                });
                product.setDetaibanners(StringUtil.join(detaibannersList.toArray(), ","));
            }
            return product;
        }).collect(Collectors.toList());
    }

    @Override
    public boolean addProduct(XhpProduct product) {
        List<XhpProductSku> skuList = product.getSkuList();
        product.setSkuList(null);
        XhpProduct xhpProduct = save(product);
        if (xhpProduct != null) {
            skuList.forEach(x -> {
                x.setProdid(xhpProduct.getId());
            });
            xhpProductSkuService.save(skuList);
            return true;
        }
        return false;
    }

    @Override
    public List<XhpProductSku> getProductSku(XhpProductSku xhpProductSku) {
        startPage(xhpProductSku.getPage(), xhpProductSku.getSize());
        return xhpProductSkuService.select(xhpProductSku);
    }

    @Override
    public XhpProduct getProduct(XhpProduct product) {
        XhpProduct xhpProduct = findById(product.getId());
        xhpProduct.setMainBannerList(xhpFileUploadService.findByIds(xhpProduct.getBanner()));
        xhpProduct.setDetailBannerList(xhpFileUploadService.findByIds(xhpProduct.getDetaibanners()));
        xhpProduct.setSkuList(xhpProductSkuService.select(new XhpProductSku(){{setProdid(product.getId());}}));
        return xhpProduct;
    }

    @Override
    public boolean deleteProductSku(XhpProductSku productSku) {
        xhpProductSkuService.deleteById(productSku.getId());
        return true;
    }

    private ExampleCondition getProductExample(XhpProduct xhpProduct) {
        ExampleCondition condition = new ExampleCondition(XhpProduct.class);
        condition.setPage(xhpProduct.getPage());
        condition.setSize(xhpProduct.getSize());
        Example.Criteria criteria = condition.createCriteria();
        if (StringUtil.isNotEmpty(xhpProduct.getId() + "")) {
            criteria.andEqualTo("id", xhpProduct.getId());
        }
        if (StringUtil.isNotEmpty(xhpProduct.getTitle())) {
            criteria.andLike("title", "%" + xhpProduct.getTitle() + "%");
        }
        return condition;
    }
}
