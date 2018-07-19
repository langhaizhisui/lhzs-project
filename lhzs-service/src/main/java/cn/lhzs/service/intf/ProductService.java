package cn.lhzs.service.intf;

import cn.lhzs.base.IBaseService;
import cn.lhzs.common.vo.BasePageList;
import cn.lhzs.data.bean.Product;
import cn.lhzs.common.vo.ProductSearchCondition;

import java.util.List;

/**
 * Created by ZHX on 2017/4/27.
 */
public interface ProductService extends IBaseService<Product> {

    BasePageList<Product> getProdList(ProductSearchCondition productSearchCondition);

    Product getProductByProdId(Long id);

    void addProd(Product product);

    void deleteProdByProdId(Long id);

    void updateProd(Product product);

    BasePageList<Product> searchProduct(ProductSearchCondition productSearchCondition);

    void deleteTable();

    void timerDeleteTask(String expiration);

    void batchDeleteProduct(ProductSearchCondition productSearchCondition);

    List<Product> getAllProduct();
}
