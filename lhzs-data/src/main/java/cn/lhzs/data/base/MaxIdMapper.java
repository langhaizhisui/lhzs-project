package cn.lhzs.data.base;

import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

@RegisterMapper
public interface MaxIdMapper<T> {

    @SelectProvider(type = MaxIdProvider.class, method = "dynamicSQL")
    @ResultType(Long.class)
    Long selectMaxId();
}
