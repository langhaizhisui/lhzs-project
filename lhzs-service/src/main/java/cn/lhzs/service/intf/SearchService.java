package cn.lhzs.service.intf;

import cn.lhzs.data.bean.Product;

import java.util.List;

/**
 * Created by ZHX on 2017/11/23.
 */
public interface SearchService {

    <T> void builderSearchIndex(List<T> list,String index, String type);

    List<Product> searchProductList(String param);

    List<Product> searchShopList(String param);
}