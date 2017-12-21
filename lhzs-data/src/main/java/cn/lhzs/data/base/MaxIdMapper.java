package cn.lhzs.data.base;

import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.SelectProvider;

/**
 * @author ZHX on 2017/11/10.
 * @descp 查询实体类当前最大id
 */
public interface MaxIdMapper<T> {

    @SelectProvider(type = MaxIdProvider.class, method = "dynamicSQL")
    @ResultType(Long.class)
    Long selectMaxId();
}
