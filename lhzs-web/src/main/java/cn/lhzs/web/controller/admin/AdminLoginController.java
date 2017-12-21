package cn.lhzs.web.controller.admin;

import cn.lhzs.common.exception.LoginException;
import cn.lhzs.common.vo.LoginCondition;
import cn.lhzs.common.result.ResponseResult;
import cn.lhzs.service.intf.ArticleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static cn.lhzs.common.result.ResponseResultGenerator.generatorLoginResult;
import static cn.lhzs.common.result.ResponseResultGenerator.generatorUnLoginResult;

/**
 * Created by ZHX on 2017/5/7.
 */
@Controller
public class AdminLoginController {

    @Autowired
    public ArticleService articleService;

    @RequestMapping("/login")
    @ResponseBody
    public ResponseResult login(@RequestBody LoginCondition loginCondition) {

        UsernamePasswordToken token = new UsernamePasswordToken(loginCondition.getUserName(), loginCondition.getPassword());
        try {
            token.setRememberMe(true);
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.login(token);
            if (!currentUser.isAuthenticated()) {
                token.clear();
            }
            return generatorLoginResult();
        } catch (UnknownAccountException e) {
            throw new LoginException("未知账户" + token.getPrincipal());
        } catch (IncorrectCredentialsException e) {
            throw new LoginException("凭证错误" + token.getPrincipal());
        } catch (LockedAccountException e) {
            throw new LoginException("账户已锁定" + token.getPrincipal());
        } catch (ExcessiveAttemptsException e) {
            throw new LoginException("错误次数过多" + token.getPrincipal());
        } catch (AuthenticationException e) {
            throw new LoginException(e.getCause().getMessage());
        }
    }

    @RequestMapping("/unLogin")
    @ResponseBody
    public ResponseResult unLogin() {
        return generatorUnLoginResult();
    }

    @RequestMapping("/forbidden")
    @ResponseBody
    public ResponseResult forbidden() {
        return generatorUnLoginResult();
    }
}
