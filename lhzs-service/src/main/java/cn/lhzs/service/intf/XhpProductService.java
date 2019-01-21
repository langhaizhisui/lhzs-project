package cn.lhzs.service.intf;

import cn.lhzs.base.IBaseService;
import cn.lhzs.data.bean.XhpProduct;
import cn.lhzs.data.bean.XhpProductSku;

import java.util.List;


/**
 * Created by ZHX on 2019/01/03.
 */
public interface XhpProductService extends IBaseService<XhpProduct> {

    List<XhpProduct> getProductList(XhpProduct xhpProduct);

    boolean addProduct(XhpProduct product);

    List<XhpProductSku> getProductSku(XhpProductSku xhpProductSku);

    XhpProduct getProduct(XhpProduct product);

    boolean deleteProductSku(XhpProductSku productSku);
}
