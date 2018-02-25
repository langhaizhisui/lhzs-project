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

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysAuthService sysAuthService;

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
        SysUser sysUser = sysUserService.findBy("account", token.getUsername());
        if (!checkUser(sysUser, token)) {
            throw new LoginException("用户名或密码错误");
        }
        saveUserInfo(SecurityUtils.getSubject(), sysUser);
        return new SimpleAuthenticationInfo(token.getUsername(), token.getPassword(), getName());
    }

    private void saveUserInfo(Subject subject, SysUser sysUser) {
        Session session = subject.getSession();
        session.setAttribute("user", sysUser.getAccount());
        session.setAttribute("userAuth", sysAuthService.getUserAuthList(sysUser.getId()));
        session.setTimeout(1000 * 60 * 60 * 2);
        WebUtil.saveCurrentUser(sysUser.getId());
    }

    private boolean checkUser(SysUser sysUser, UsernamePasswordToken token) {
        String password = getTokenPassword(token.getPassword());
        if (sysUser != null && StringUtil.isNotEmpty(password) && password.equals(sysUser.getPassword())) {
            return true;
        }
        return false;
    }

    private String getTokenPassword(char[] password) {
        StringBuilder builder = new StringBuilder();
        if (password != null) {
            for (int i = 0; i < password.length; i++) {
                builder.append(password[i]);
            }
        }
        return builder.toString();
    }

}