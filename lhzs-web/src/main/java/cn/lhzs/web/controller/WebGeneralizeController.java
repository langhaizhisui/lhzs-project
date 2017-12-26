package cn.lhzs.web.controller;

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

import static cn.lhzs.common.result.ResponseResultGenerator.generatorSuccessResult;

/**
* Created by ZHX on 2017/11/10.
*/
@Controller
@RequestMapping("/web/generalize")
public class WebGeneralizeController {

    @Autowired
    public ConfigService configService;

    @RequestMapping("/search")
    @ResponseBody
    public ResponseResult getWebGeneralize(@RequestBody WebGeneralizeCondition webGeneralize) {
        return generatorSuccessResult(new PageInfo(configService.getWebGeneralizeList(webGeneralize)));
    }

    @RequestMapping("/detail")
    @ResponseBody
    public ResponseResult getWebGeneralizeDetail(@RequestParam Integer id) {
        return generatorSuccessResult(configService.getWebGeneralizeDetail(id));
    }

}
