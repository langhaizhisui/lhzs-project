package cn.lhzs.web.controller.admin;

import cn.lhzs.common.result.ResponseResult;
import cn.lhzs.common.util.WebUtil;
import cn.lhzs.data.bean.SysAuth;
import cn.lhzs.data.bean.SysAuthMenu;
import cn.lhzs.service.intf.SysAuthService;
import cn.lhzs.service.intf.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.Set;

import static cn.lhzs.common.result.ResponseResultGenerator.generatorLoginResult;
import static cn.lhzs.common.result.ResponseResultGenerator.generatorSuccessResult;

/**
 * Created by ZHX on 2017/12/27.
 */
@Controller
@RequestMapping("/admin/user")
public class AdminUserController {

    @Autowired
    SysUserService sysUserService;

    @RequestMapping("/menu")
    @ResponseBody
    public ResponseResult getNavigation() {
        return generatorSuccessResult(sysUserService.getMenu());
    }
}
