package cn.lhzs.service.intf;

import cn.lhzs.common.vo.ProductSearchCondition;
import cn.lhzs.common.vo.ShopSearchCondition;
import cn.lhzs.data.bean.Product;

import java.util.List;

/**
 * Created by ZHX on 2017/11/23.
 */
public interface SearchService {

    <T> void builderSearchIndex(List<T> list,String index, String type);

    List<Product> searchProductList(ProductSearchCondition product);

    List<Product> searchShopList(ShopSearchCondition shop);
}