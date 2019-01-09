package cn.lhzs.service.impl;

import cn.lhzs.common.util.StringUtil;
import cn.lhzs.data.base.ExampleCondition;
import cn.lhzs.data.bean.XhpProductSku;
import cn.lhzs.data.dao.XhpProductMapper;
import cn.lhzs.data.bean.XhpProduct;
import cn.lhzs.service.intf.XhpProductService;
import cn.lhzs.base.AbstractBaseService;
import cn.lhzs.service.intf.XhpProductSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.CacheConfig;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

import java.util.List;

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

    @Resource
    private XhpProductMapper xhpProductMapper;

    @Override
    public List<XhpProduct> getProductList(XhpProduct xhpProduct) {
        startPage(xhpProduct.getPage(), xhpProduct.getSize());
        return findByExampleCondition(getProductExample(xhpProduct));
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
