package cn.lhzs.service.impl;

import cn.lhzs.base.AbstractBaseService;
import cn.lhzs.data.bean.SysAuth;
import cn.lhzs.data.bean.SysAuthMenu;
import cn.lhzs.data.bean.SysUser;
import cn.lhzs.data.dao.SysUserMapper;
import cn.lhzs.service.intf.SysAuthMenuService;
import cn.lhzs.service.intf.SysAuthService;
import cn.lhzs.service.intf.SysUserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//import cn.lhzs.common.util.WebUtil;

/**
 * Created by ZHX on 2017/10/18.
 */
@Service
public class SysUserServiceImpl extends AbstractBaseService<SysUser> implements SysUserService {

    Logger logger = Logger.getLogger(SysUserServiceImpl.class);

    @Resource
    public SysUserMapper sysUserMapper;

    @Resource
    public SysAuthService sysAuthService;

    @Resource
    public SysAuthMenuService sysAuthMenuService;

    @Override
    public Set<SysAuthMenu> getMenu() {
        Map<Long, Set<SysAuthMenu>> sysAuthMenuMap = new HashMap<>();
        setSysAuthMenuMapData(sysAuthMenuMap);
        return getSubMenu(sysAuthMenuMap);
    }

    private Set<SysAuthMenu> getSubMenu(Map<Long, Set<SysAuthMenu>> sysAuthMenuMap) {
        Set<SysAuthMenu> sysAuthMenuList = sysAuthMenuMap.get(0L);
        setFirstStageSubMenu(sysAuthMenuMap, sysAuthMenuList);
        return sysAuthMenuList;
    }

    private void setFirstStageSubMenu(Map<Long, Set<SysAuthMenu>> sysAuthMenuMap, Set<SysAuthMenu> sysAuthMenuList) {
        if (sysAuthMenuList != null) {
            sysAuthMenuList.forEach(firstStageSubMenu -> {
                Set<SysAuthMenu> subSysAuthMenuList = sysAuthMenuMap.get(firstStageSubMenu.getId());
                firstStageSubMenu.setSubSysAuthMenuList(subSysAuthMenuList);
                setSecondStageSubMenu(sysAuthMenuMap, subSysAuthMenuList);
            });
        }
    }


    private void setSecondStageSubMenu(Map<Long, Set<SysAuthMenu>> sysAuthMenuMap, Set<SysAuthMenu> secondStageSysAuthMenuList) {
        if (secondStageSysAuthMenuList != null) {
            secondStageSysAuthMenuList.forEach(secondStageSysAuthMenu -> {
                secondStageSysAuthMenu.setSubSysAuthMenuList(sysAuthMenuMap.get(secondStageSysAuthMenu.getId()));
            });
        }
    }

    private void setSysAuthMenuMapData(Map<Long, Set<SysAuthMenu>> sysAuthMenuMap) {
        sysAuthService.getUserAuthList(10000L).forEach(sysAuth -> setSysAuthMenuData(sysAuthMenuMap, sysAuth));
    }

    private void setSysAuthMenuData(Map<Long, Set<SysAuthMenu>> sysAuthMenuMap, SysAuth sysAuth) {
        SysAuthMenu sysAuthMenu = sysAuthMenuService.findById(sysAuth.getAmid());
        while (sysAuthMenu != null) {
            Set<SysAuthMenu> sysAuthMenuList = sysAuthMenuMap.get(sysAuthMenu.getpAmid());
            if (sysAuthMenuList == null) {
                sysAuthMenuList = new HashSet<>();
                sysAuthMenuMap.put(sysAuthMenu.getpAmid(), sysAuthMenuList);
            }
            sysAuthMenuList.add(sysAuthMenu);
            sysAuthMenu = sysAuthMenuService.findById(sysAuthMenu.getpAmid());
        }
    }

}
