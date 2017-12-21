package cn.lhzs.web.shiro;


import cn.lhzs.data.bean.SysAuth;
import cn.lhzs.service.intf.SysAuthService;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by ZHX on 2017/11/30.
 */
public class ShiroPermissionManage extends ShiroFilterFactoryBean {

    @Autowired
    private SysAuthService sysAuthService;

    public boolean updateFilter() {
        Map<String, String> permissionMap = getPermissionMap();
        try {
            AbstractShiroFilter shiroFilter = (AbstractShiroFilter) getObject();
            PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
            DefaultFilterChainManager filterManager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();
            filterManager.getFilterChains().clear();

            getFilterChainDefinitionMap().clear();
            Map<String, String> chains = getFilterChainDefinitionMap();
            chains.putAll(permissionMap);
            for (Map.Entry<String, String> entry : permissionMap.entrySet()) {
                String url = entry.getKey();
                String chainDefinition = entry.getValue();
                filterManager.createChain(url, chainDefinition);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Map<String, String> getPermissionMap() {
        Map<String, String> filterChainDefinitionManager = new LinkedHashMap<>();
        sysAuthService.findAll().forEach(sysAuth -> splitUrl(sysAuth, filterChainDefinitionManager));
        return filterChainDefinitionManager;
    }

    private void splitUrl(SysAuth sysAuth, Map<String, String> filterChainDefinitionManager) {
        Arrays.asList(sysAuth.getUrl().split(",")).forEach(url -> {
            if (url.endsWith("/")) {
                url = url + "**";
            }
            filterChainDefinitionManager.put(url, "authc,perms[" + sysAuth.getId() + "]");
        });
//        filterChainDefinitionManager.put("/article/**","authc,perms[root]");
    }

}
