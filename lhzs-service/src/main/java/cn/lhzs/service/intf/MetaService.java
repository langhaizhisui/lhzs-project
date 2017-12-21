package cn.lhzs.service.intf;

import cn.lhzs.base.IBaseService;
import cn.lhzs.data.bean.Meta;

/**
 * Created by ZHX on 2017/5/10.
 */
public interface MetaService  extends IBaseService<Meta> {

    Meta getMeta(Long id);
}
