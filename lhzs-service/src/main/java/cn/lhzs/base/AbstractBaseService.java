package cn.lhzs.base;

import cn.lhzs.data.base.BaseModel;
import cn.lhzs.data.base.Mapper;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

/**
 * 基于通用MyBatis Mapper插件的Service接口的实现
 * 业务逻辑层基类<
 *
 * @author ZHX
 */
public abstract class AbstractBaseService<T extends BaseModel> implements IBaseService<T> {

    public static final Long INIT_ID = 10000L;

    @Autowired
    protected Mapper<T> mapper;

    private Class<T> modelClass;    // 当前泛型真实类型的Class

    public AbstractBaseService() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        modelClass = (Class<T>) pt.getActualTypeArguments()[0];
    }

    @Override
    public T save(T model) {
        Assert.notNull(model, "MODEL不能为空");
        return update(model);
    }

    public void save(List<T> models) {
        Assert.notNull(models, "MODEL不能为空");
        models.forEach(this::update);
//        mapper.insertList(models);
    }

    @Override
    public void deleteById(Long id) {
        Assert.notNull(id, "ID不能为空");
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByIds(String ids) {
        Assert.notNull(ids, "ID不能为空");
        mapper.deleteByIds(ids);
    }

    @Override
    public T update(T model) {
        Assert.notNull(model, "ID不能为空");
        try {
            model.setUpdateTime(new Date());
            if (model.getId() == null) {
//                Long generateId = getMaxId() + 1;
//                model.setId(generateId);
                if (model.getCreateTime() == null) {
                    model.setCreateTime(new Date());
                }
                mapper.insert(model);
            } else {
                mapper.updateByPrimaryKey(model);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return model;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T findById(Long id) {
        Assert.notNull(id, "ID不能为空");
        try {
            return mapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public T findBy(String fieldName, Object value) throws TooManyResultsException {
        try {
            T model = modelClass.newInstance();
            Field field = modelClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(model, value);
            return mapper.selectOne(model);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public List<T> findByIds(String ids) {
        Assert.notNull(ids, "ID不能为空");
        return mapper.selectByIds(ids);
    }

    @Override
    public List<T> findByCondition(Example example) {
        return mapper.selectByExample(example);
    }

    @Override
    public List<T> findAll() {
        return mapper.selectAll();
    }

    @Override
    public List<T> select(T t) {
        return mapper.select(t);
    }

    @Override
    public Long getMaxId() {
        Long maxId = mapper.selectMaxId();
        if (maxId == null || maxId == 0L) {
            maxId = INIT_ID;
        }
        return maxId;
    }

}
