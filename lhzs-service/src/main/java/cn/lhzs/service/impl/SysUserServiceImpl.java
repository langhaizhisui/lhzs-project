package cn.lhzs.service.impl;

import cn.lhzs.base.AbstractBaseService;
import cn.lhzs.common.util.WebUtil;
import cn.lhzs.data.bean.SysAuth;
import cn.lhzs.data.bean.SysAuthMenu;
import cn.lhzs.data.bean.SysUser;
import cn.lhzs.data.dao.SysUserMapper;
import cn.lhzs.service.intf.SysAuthMenuService;
import cn.lhzs.service.intf.SysAuthService;
import cn.lhzs.service.intf.SysUserService;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
        Session session = SecurityUtils.getSubject().getSession();
        Object userMenu = session.getAttribute("userMenu");
        if (userMenu == null) {
            session.setAttribute("userMenu", getSubMenu());
        }
        return (Set<SysAuthMenu>) session.getAttribute("userMenu");
    }

    private Set<SysAuthMenu> getSubMenu() {
        Map<Long, Set<SysAuthMenu>> sysAuthMenuMap = new HashMap<>();
        sysAuthService.getUserAuthList(WebUtil.getCurrentUser()).forEach(sysAuth -> setSysAuthMenuData(sysAuthMenuMap, sysAuth));
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
