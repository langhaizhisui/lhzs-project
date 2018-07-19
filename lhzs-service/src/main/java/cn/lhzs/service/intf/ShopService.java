package cn.lhzs.service.intf;

import cn.lhzs.common.vo.BasePageList;
import cn.lhzs.data.bean.Shop;
import cn.lhzs.common.vo.ShopSearchCondition;

import java.util.List;

/**
 * Created by ZHX on 2017/4/27.
 */
public interface ShopService {

    List<Shop> getShopList(Shop shop);

    Shop getShopByShopId(Long shopId);

    void addShop(Shop shop);

    void addBatchShop(List<Shop> shops);

    void deleteShopByShopId(Long shopId);

    void updateShop(Shop shop);

    BasePageList<Shop> searchShop(ShopSearchCondition shopSearchCondition);

    BasePageList<Shop> getShops(Shop shop);

    void deleteTable();

    List<Shop> getAllShop();
}
