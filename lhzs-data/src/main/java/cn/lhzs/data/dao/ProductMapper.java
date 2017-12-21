package cn.lhzs.data.dao;

import cn.lhzs.data.base.Mapper;
import cn.lhzs.data.bean.Product;

import java.util.List;


public interface ProductMapper extends Mapper<Product> {

    void deleteTable();

    void batchInsert(List<Product> products);
}