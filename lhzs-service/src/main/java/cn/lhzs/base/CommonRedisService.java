package cn.lhzs.base;

import cn.lhzs.common.util.SecureUtil;
import cn.lhzs.data.base.BaseRedis;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class CommonRedisService<T> extends BaseRedis<String, T> {

    private Class<T> modelClass;

    public CommonRedisService() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        modelClass = (Class<T>) pt.getActualTypeArguments()[0];
    }

    public void deleteRedisId(Long id) {
        srem(getRedisSetClassIdKey(), getRedisClassIdKey(id.toString()));
        delete(getRedisClassIdKey(id.toString()));
        deleteRedisPage();
    }

    public void deleteRedisIds(String ids) {
        deleteRedisList(Arrays.asList(ids.split(",")));
        deleteRedisPage();
    }

    public void addRedisId(Long id, T model) {
        sadd(getRedisSetClassIdKey(), getRedisClassIdKey(id.toString()));
        set(getRedisClassIdKey(id.toString()), (Serializable) model, 0L);
    }

    public List<T> getRedisIds(String[] idArr) {
        List<T> list = new ArrayList<T>();
        for (int i = 0; i < idArr.length; i++) {
            list.add((T) get(getRedisClassIdKey(idArr[i])));
        }
        return list;
    }

    public void addPageRedis(String pageKey, List<T> list) {
        hset(getRedisSetPageKey(), pageKey, (Serializable) list, 10L);
    }

    public List<T> getPageRedis(T model, Integer page, Integer size) {
        String pageKey = getPageKey(getAttrValForRedisKey(model, false), page, size);
        return hget(getRedisSetPageKey(), pageKey, ArrayList.class);
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

    private String getPageKey(String pageFieldKey, Integer page, Integer size) {
        return new StringBuilder(pageFieldKey).append(":").append(page).append(":").append(size).toString();
    }

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
