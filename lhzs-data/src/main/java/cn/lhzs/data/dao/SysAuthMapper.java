package cn.lhzs.data.dao;

import cn.lhzs.data.base.Mapper;
import cn.lhzs.data.bean.SysAuth;

import java.util.List;

public interface SysAuthMapper extends Mapper<SysAuth> {

    List<SysAuth> getUserAuthList(Long uid);
}