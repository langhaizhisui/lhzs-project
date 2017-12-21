package cn.lhzs.web.controller;

import cn.lhzs.common.result.ResponseResult;
import cn.lhzs.service.intf.MetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static cn.lhzs.common.result.ResponseResultGenerator.generatorSuccessResult;

/**
 * Created by ZHX on 2017/5/7.
 */
@Controller
@RequestMapping("/meta")
public class MetaController {

    @Autowired
    public MetaService metaService;

    @RequestMapping("/getMeta")
    @ResponseBody
    public ResponseResult getMeta(Long id){
        return generatorSuccessResult(metaService.getMeta(id));
    }
}
