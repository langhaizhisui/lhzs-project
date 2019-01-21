package cn.lhzs.web.shiro;

import cn.lhzs.common.exception.LoginException;
import cn.lhzs.common.util.StringUtil;
import cn.lhzs.common.util.WebUtil;
import cn.lhzs.data.bean.SysAuth;
import cn.lhzs.data.bean.SysUser;
import cn.lhzs.service.intf.SysAuthService;
import cn.lhzs.service.intf.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShiroRealm extends AuthorizingRealm {


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Object user = SecurityUtils.getSubject().getSession().getAttribute("user");
        if (!super.getAvailablePrincipal(principals).equals(user)) {
            throw new LoginException("用户名或密码错误");
        }
        return getUserAuth();
    }

    private AuthorizationInfo getUserAuth() {
        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
        Subject subject = SecurityUtils.getSubject();
        if (subject == null) {
            throw new LoginException("Session超时，请重新登录");
        }
        List<SysAuth> userAuthList = (List<SysAuth>) subject.getSession().getAttribute("userAuth");
        userAuthList.forEach(userAuth -> simpleAuthorInfo.addStringPermission(userAuth.getId() + ""));
        return simpleAuthorInfo;
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        return new SimpleAuthenticationInfo(token.getUsername(), token.getPassword(), getName());
    }

}