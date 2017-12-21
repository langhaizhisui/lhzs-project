package cn.lhzs.web.controller;

import cn.lhzs.common.result.ResponseResult;
import cn.lhzs.service.intf.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static cn.lhzs.common.result.ResponseResultGenerator.generatorSuccessResult;

/**
 * Created by ZHX on 2017/5/7.
 */
@Controller
@RequestMapping("/catalog")
public class CatalogController {

    @Autowired
    public CatalogService catalogService;

    @RequestMapping("/cataList")
    @ResponseBody
    public ResponseResult getCatalogList(){
        return generatorSuccessResult(catalogService.getCatalogList());
    }
}
