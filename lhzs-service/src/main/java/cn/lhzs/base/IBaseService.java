package cn.lhzs.base;

import cn.lhzs.data.base.BaseModel;
import org.apache.ibatis.exceptions.TooManyResultsException;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author ZHX
 * @decp 基础服务类接口
 */
public interface IBaseService<T extends BaseModel> {
    /**
     * 保存记录
     *
     * @param model 实体
     */
    T save(T model);

    /**
     * 批量持久化
     *
     * @param models 实体列表
     */
    void save(List<T> models);

    /**
     * 通过主鍵刪除
     *
     * @param id 主键
     */
    void deleteById(Long id);

    /**
     * 批量刪除 eg：ids -> “1,2,3,4”
     *
     * @param ids
     */
    void deleteByIds(String ids);

    /**
     * 更新记录
     *
     * @param model 实体
     * @return 更换的实体
     */
    T update(T model);

    /**
     * 通过ID查找
     *
     * @param id 主键
     * @return
     */
    T findById(Long id);

    /**
     * 根据条件查询
     * 通过Model中某个成员变量名称（非数据表中column的名称）查找,value需符合unique约束
     * @param fieldName 字段
     * @param value     字段值
     * @return
     * @throws TooManyResultsException
     */
    T findBy(String fieldName, Object value) throws TooManyResultsException;

    /**
     * 通过多个ID查找//eg：ids -> “1,2,3,4”
     *
     * @param ids
     * @return
     */
    List<T> findByIds(String ids);

    /**
     * 根据条件查找
     *
     * @param example
     * @return
     */
    List<T> findByCondition(Example example);

    /**
     * 获取所有
     *
     * @return
     */
    List<T> findAll();

    /**
     * 根据实体类属性查询列表
     * @param t 实体类
     * @return  符合查询条件的实体类列表
     */
    List<T> select(T t);

    /**
     * 查询当前表中最大id值
     * @return 最大id值
     */
    Long getMaxId();
}
