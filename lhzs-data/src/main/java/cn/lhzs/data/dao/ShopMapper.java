package cn.lhzs.data.dao;

import cn.lhzs.data.base.Mapper;
import cn.lhzs.data.bean.Shop;

public interface ShopMapper  extends Mapper<Shop> {

    void deleteTable();
}