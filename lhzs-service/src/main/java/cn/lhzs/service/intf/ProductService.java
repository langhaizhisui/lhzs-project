package cn.lhzs.service.intf;

import cn.lhzs.base.IBaseService;
import cn.lhzs.data.bean.Product;
import cn.lhzs.common.vo.ProductSearchCondition;

import java.util.List;

/**
 * Created by ZHX on 2017/4/27.
 */
public interface ProductService extends IBaseService<Product> {

    List<Product> getProdList(ProductSearchCondition productSearchCondition);

    Product getProductByProdId(Long id);

    void addProd(Product product);

    void deleteProdByProdId(Long id);

    void updateProd(Product product);

    List<Product> searchProduct(ProductSearchCondition productSearchCondition);

    void deleteTable();

    void timerDeleteTask(String expiration);

    void batchDeleteProduct(ProductSearchCondition productSearchCondition);

    List<Product> getAllProduct();
}
