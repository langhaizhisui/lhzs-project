package cn.lhzs.service.intf;

import cn.lhzs.base.IBaseService;
import cn.lhzs.data.bean.SysAuth;

import java.util.List;

/**
 * Created by ZHX on 2017/10/18.
 */
public interface SysAuthService extends IBaseService<SysAuth> {

    List<SysAuth> getUserAuthList(Long uid);

}
