package cn.lhzs.data.base;

import tk.mybatis.mapper.entity.Example;

/**
 * Created by ZHX on 2018/07/02.
 */
public class ExampleCondition extends Example {

    private Integer page;

    private Integer size;

    public ExampleCondition(Class<?> entityClass) {
        super(entityClass);
    }

    public ExampleCondition(Class<?> entityClass, boolean exists) {
        super(entityClass, exists);
    }

    public ExampleCondition(Class<?> entityClass, boolean exists, boolean notNull) {
        super(entityClass, exists, notNull);
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
