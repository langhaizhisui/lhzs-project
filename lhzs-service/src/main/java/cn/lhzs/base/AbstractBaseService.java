package cn.lhzs.base;

import cn.lhzs.common.util.SecureUtil;
import cn.lhzs.common.vo.BasePageList;
import cn.lhzs.data.base.BaseModel;
import cn.lhzs.data.base.BaseRedis;
import cn.lhzs.data.base.ExampleCondition;
import cn.lhzs.data.base.Mapper;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public abstract class AbstractBaseService<T extends BaseModel> extends BaseRedis<String, T> implements IBaseService<T> {

    public static final Long INIT_ID = 10000L;

    private Class<T> modelClass;

    @Autowired
    protected Mapper<T> mapper;

    public AbstractBaseService() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        modelClass = (Class<T>) pt.getActualTypeArguments()[0];
    }

    @Override
    public T save(T model) {
        return update(model, true, 0L);
    }

    @Override
    public T save(T model, boolean isUseRedis, Long expire) {
        return update(model, isUseRedis, expire);
    }

    @Override
    public void save(List<T> models) {
        Assert.notNull(models, "MODEL不能为空");
        models.forEach(item -> update(item, true, 0L));
//        mapper.insertList(models);
    }

    @Override
    public void deleteById(Long id) {
        Assert.notNull(id, "ID不能为空");
//        deleteRedisId(id);
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteRedisId(Long id) {
        srem(getRedisSetClassIdKey(), getRedisClassIdKey(id.toString()));
        delete(getRedisClassIdKey(id.toString()));
        deleteRedisPage();
    }

    @Override
    public void deleteByIds(String ids) {
        Assert.notNull(ids, "ID不能为空");
//        deleteRedisIds(ids);
        mapper.deleteByIds(ids);
    }

    @Override
    public void deleteRedisIds(String ids) {
        deleteRedisList(Arrays.asList(ids.split(",")));
        deleteRedisPage();
    }

    @Override
    public T update(T model, boolean isUseRedis, Long expire) {
        Assert.notNull(model, "model不能为空");
        try {
            model.setUpdateTime(new Date());
            if (model.getId() == null) {
                add(model, isUseRedis, expire);
            } else {
                updateInfo(model, isUseRedis, expire);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return model;
    }

    @Override
    public T findById(Long id) {
        Assert.notNull(id, "ID不能为空");
        try {
//            T model = get(getRedisClassIdKey(id.toString()), modelClass);
//            if (model == null) {
//                model = mapper.selectByPrimaryKey(id);
//                if (model != null) {
//                    addRedisId(id, model);
//                }
//            }
//            return model;

            return mapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public void addRedisId(Long id, T model) {
        sadd(getRedisSetClassIdKey(), getRedisClassIdKey(id.toString()));
        set(getRedisClassIdKey(id.toString()), model, 0L);
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
//        String[] idArr = ids.split(",");
//        List<T> list = getRedisIds(idArr);
//        if (list.size() == idArr.length) {
//            return list;
//        }
        return mapper.selectByIds(ids);
    }

    @Override
    public List<T> getRedisIds(String[] idArr) {
        List<T> list = new ArrayList<T>();
        for (int i = 0; i < idArr.length; i++) {
            list.add((T) get(getRedisClassIdKey(idArr[i])));
        }
        return list;
    }

    @Override
    public BasePageList<T> findByCondition(ExampleCondition condition) {
        BasePageList<T> basePageList = new BasePageList<>();
//        String pageKey = getPageKey(getAttrValForRedisKey(condition, false), condition.getPage(), condition.getSize());
//        List<T> list = hget(getRedisSetPageKey(), pageKey, ArrayList.class);
//        if (list == null) {
//            list = mapper.selectByExample(condition);
//            if (list != null) {
//                basePageList.setList(list);
//                basePageList.setTotalPage(10L);
//                addPageRedis(pageKey, basePageList);
//            }
//        }
        List<T> list = mapper.selectByExample(condition);
        basePageList.setList(list);
        return basePageList;
    }

    @Override
    public void addPageRedis(String pageKey, BasePageList basePageList) {
        hset(getRedisSetPageKey(), pageKey, (Serializable) basePageList, 10L);
    }

    @Override
    public List<T> getPageRedis(T model) {
        String pageKey = getPageKey(getAttrValForRedisKey(model, false), model.getPage(), model.getSize());
        return hget(getRedisSetPageKey(), pageKey, ArrayList.class);
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

    private String getRedisClassIdKey(String id) {
        return new StringBuilder(modelClass.getSimpleName()).append(":").append(id).toString();
    }

    private String getRedisSetClassIdKey() {
        return new StringBuffer("SET:").append(modelClass.getSimpleName()).toString();
    }

    private String getRedisSetPageKey() {
        return new StringBuffer("SET:").append("PAGE").append(modelClass.getSimpleName()).toString();
    }

    private void deleteRedisPage() {
        List<String> listKey = (List<String>) smembers(getRedisSetPageKey());
        listKey.add(getRedisSetPageKey());
        if (listKey.size() > 0) {
            delete(listKey);
        }
    }

    private void deleteRedisList(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            srem(getRedisSetClassIdKey(), getRedisClassIdKey(id));
            delete(getRedisClassIdKey(id));
        }
    }

    private void updateInfo(T model, boolean isUseRedis, Long expire) {
        mapper.updateByPrimaryKey(model);
//        if (isUseRedis) {
//            String classKey = modelClass.getSimpleName() + 120000;
//            set(classKey, model, expire == null ? 0L : expire);
//            deleteRedisPage();
//        }
    }

    private void add(T model, boolean isUseRedis, Long expire) {
        model.setId(getMaxId() + 1);
        model.setState(1);
        model.setCreateTime(new Date());
        mapper.insert(model);
//        if (isUseRedis) {
//            String classKey = new StringBuilder(modelClass.getSimpleName()).append(":").append("12000").toString();
//            sadd(getRedisSetClassIdKey(), classKey);
//            set(classKey, model, expire == null ? 0L : expire);
//            deleteRedisPage();
//        }
    }

    private String getPageKey(String pageFieldKey, Integer page, Integer size) {
        return new StringBuilder(pageFieldKey).append(":").append(page).append(":").append(size).toString();
    }

    public String getAttrValForRedisKey(Example t, boolean bWithSupperFields) {
        StringBuffer sb = new StringBuffer();
        try {
            Field[] fields = t.getClass().getDeclaredFields();
            Field[] father;
            getPageFieldKey(t, sb, fields);
            if (bWithSupperFields) {
                father = t.getClass().getSuperclass().getDeclaredFields();
                getPageFieldKey(t, sb, father);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        String val = SecureUtil.md5X16Str(sb.toString(), "utf-8");
        sb = new StringBuffer(t.getClass().getSimpleName()).append(":total:").append(val);
        return sb.toString();
    }

    @Override
    public String getAttrValForRedisKey(T t, boolean bWithSupperFields) {
        StringBuffer sb = new StringBuffer();
        try {
            Field[] fields = t.getClass().getDeclaredFields();
            Field[] father;
            getPageFieldKey(t, sb, fields);
            if (bWithSupperFields) {
                father = t.getClass().getSuperclass().getDeclaredFields();
                getPageFieldKey(t, sb, father);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        String val = SecureUtil.md5X16Str(sb.toString(), "utf-8");
        sb = new StringBuffer(t.getClass().getSimpleName()).append(":total:").append(val);
        return sb.toString();
    }

    private static <T> void getPageFieldKey(T t, StringBuffer sb, Field[] fields) throws IllegalAccessException {
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            if (field.getName().equals("page") || field.getName().equals("size")) {
                continue;

            }
            field.setAccessible(true);
            Object val = field.get(t);
            if (val != null) {
                sb.append(field.getName()).append(":").append(val);
            }
        }
    }
}
