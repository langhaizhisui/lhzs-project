package cn.lhzs.web.controller.admin;

import cn.lhzs.common.aop.log.OpLog;
import cn.lhzs.common.vo.WebGeneralizeCondition;
import cn.lhzs.common.result.ResponseResult;
import cn.lhzs.service.intf.ConfigService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static cn.lhzs.common.aop.log.OpEnum.ADD;
import static cn.lhzs.common.aop.log.OpEnum.DEL;
import static cn.lhzs.common.aop.log.OpEnum.EDIT;
import static cn.lhzs.common.result.ResponseResultGenerator.generatorSuccessResult;

/**
* Created by ZHX on 2017/12/6.
*/
@Controller
@RequestMapping("/admin/web/generalize")
public class AdminWebGeneralizeController {

    @Autowired
    public ConfigService configService;

    @RequestMapping("/search")
    @ResponseBody
    public ResponseResult getWebGeneralize(@RequestBody WebGeneralizeCondition webGeneralize) {
        return generatorSuccessResult(new PageInfo(configService.getWebGeneralizeList(webGeneralize)));
    }

    @OpLog(type = ADD, descp = "增加推荐商城")
    @RequestMapping("/add")
    @ResponseBody
    public ResponseResult addWebGeneralize(@RequestBody WebGeneralizeCondition webGeneralize) {
        configService.addWebGeneralize(webGeneralize);
        return generatorSuccessResult();
    }

    @OpLog(type = DEL, descp = "删除推荐商城")
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseResult deleteWebGeneralize(Integer id) {
        configService.deleteWebGeneralize(id);
        return generatorSuccessResult();
    }

    @OpLog(type = EDIT, descp = "修改推荐商城")
    @RequestMapping("/update")
    @ResponseBody
    public ResponseResult updateWebGeneralize(@RequestBody WebGeneralizeCondition webGeneralize) {
        configService.updateWebGeneralize(webGeneralize);
        return generatorSuccessResult();
    }

    @RequestMapping("/detail")
    @ResponseBody
    public ResponseResult getWebGeneralizeDetail(@RequestParam Integer id) {
        return generatorSuccessResult(configService.getWebGeneralizeDetail(id));
    }

}
