package cn.lhzs.service.intf;

import cn.lhzs.data.bean.Taobao;
import com.taobao.api.response.TbkDgItemCouponGetResponse;
import com.taobao.api.response.TbkItemInfoGetResponse;
import com.taobao.api.response.TbkShopGetResponse;

/**
 * Created by ZHX on 2018/5/15.
 */
public interface TaobaoService {

    TbkDgItemCouponGetResponse searchProduct(Taobao taobao);

    TbkItemInfoGetResponse getProductByNumIids(String numIids);

    TbkShopGetResponse searchShop(Taobao taobao);

}
