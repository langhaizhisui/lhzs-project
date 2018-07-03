package cn.lhzs.service.impl;

import cn.lhzs.base.AbstractBaseService;
import cn.lhzs.data.base.ExampleCondition;
import cn.lhzs.data.bean.Product;
import cn.lhzs.data.dao.ProductMapper;
import cn.lhzs.common.vo.ProductSearchCondition;
import cn.lhzs.service.intf.ProductService;
import cn.lhzs.common.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

import static com.github.pagehelper.PageHelper.startPage;

/**
 * Created by ZHX on 2017/4/27.
 */
@Service
public class ProductServiceImpl extends AbstractBaseService<Product> implements ProductService {

    Logger logger = Logger.getLogger(ProductServiceImpl.class);

    @Resource
    public ProductMapper productMapper;

    @Override
    public List<Product> getProdList(ProductSearchCondition productSearchCondition) {
        startPage(productSearchCondition.getPage(), productSearchCondition.getSize());
        return findByCondition(getProductSearchExample(productSearchCondition));
    }

    @Override
    public Product getProductByProdId(Long id) {
        return findById(id);
    }

    @Override
    public void addProd(Product product) {
        product.setScanNum(0);
        save(product);
    }

    @Override
    public void deleteProdByProdId(Long id) {
        deleteById(id);
    }

    @Override
    public void updateProd(Product product) {
        save(product);
    }

    @Override
    public List<Product> searchProduct(ProductSearchCondition productSearchCondition) {
        startPage(productSearchCondition.getPage(), productSearchCondition.getSize());
        return findByCondition(getProductSearchExample(productSearchCondition));
    }

    private ExampleCondition getProductSearchExample(ProductSearchCondition productSearchCondition) {
        ExampleCondition condition = new ExampleCondition(Product.class);
        condition.setPage(productSearchCondition.getPage());
        condition.setSize(productSearchCondition.getSize());
        Example.Criteria criteria = condition.createCriteria();
        if (StringUtil.isNotEmpty(productSearchCondition.getProdId() + "")) {
            criteria.andEqualTo("id", productSearchCondition.getProdId());
        }
        if (StringUtil.isNotEmpty(productSearchCondition.getCategory())) {
            criteria.andEqualTo("category", productSearchCondition.getCategory());
        }
        if (StringUtil.isNotEmpty(productSearchCondition.getPlatform())) {
            criteria.andEqualTo("platform", productSearchCondition.getPlatform());
        }
        if (StringUtil.isNotEmpty(productSearchCondition.getExpirationStart() + "")
                && StringUtil.isNotEmpty(productSearchCondition.getExpirationEnd() + "")) {
            criteria.andGreaterThanOrEqualTo("expiration", productSearchCondition.getExpirationStart())
                    .andLessThanOrEqualTo("expiration", productSearchCondition.getExpirationEnd());
        }
        if (StringUtil.isNotEmpty(productSearchCondition.getCreateTimeStart() + "")
                && StringUtil.isNotEmpty(productSearchCondition.getCreateTimeEnd() + "")) {
            criteria.andGreaterThanOrEqualTo("createTime", productSearchCondition.getCreateTimeStart())
                    .andLessThanOrEqualTo("createTime", productSearchCondition.getCreateTimeEnd());
        }
        return condition;
    }

    @Override
    public void deleteTable() {
        productMapper.deleteTable();
    }

    @Override
    public void timerDeleteTask(String expiration) {
        Example example = new Example(Product.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andGreaterThanOrEqualTo("expiration", expiration);
        productMapper.deleteByExample(example);
    }

    @Override
    public void batchDeleteProduct(ProductSearchCondition productSearchCondition) {
        searchProduct(productSearchCondition).forEach(e -> deleteProdByProdId(e.getId()));
    }

    @Override
    public List<Product> getAllProduct(){
        return productMapper.selectAll();
    }

}
