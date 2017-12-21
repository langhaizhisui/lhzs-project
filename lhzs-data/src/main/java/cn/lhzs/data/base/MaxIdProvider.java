package cn.lhzs.data.base;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;

/**
 * @author ZHX on 2017/11/10.
 * @descp 继承tk.mybatis MapperTemplate可以动态获取表名
 * SelectProvider注解是mybatis3提供开发者进行拼接SQL语句
 */
public class MaxIdProvider extends MapperTemplate {


    public MaxIdProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    public String selectMaxId(MappedStatement ms) {
        Class<?> entityClass = getEntityClass(ms);
        String tableName = this.tableName(entityClass);
       return "SELECT MAX(id) FROM " + tableName;
    }
}
