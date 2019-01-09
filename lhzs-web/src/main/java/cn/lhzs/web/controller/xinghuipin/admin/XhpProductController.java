package cn.lhzs.web.controller.xinghuipin.admin;

import cn.lhzs.common.result.ResponseResult;
import cn.lhzs.data.bean.XhpProduct;
import cn.lhzs.service.intf.XhpProductService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static cn.lhzs.common.result.ResponseResultGenerator.generatorFailResult;
import static cn.lhzs.common.result.ResponseResultGenerator.generatorSuccessResult;

/**
 * Created by ZHX on 2019/01/03.
 */
@Controller
@RequestMapping("/xhp/admin/product")
public class XhpProductController {

    @Autowired
    private XhpProductService xhpProductService;

    @RequestMapping(value = "/add")
    @ResponseBody
    public ResponseResult addProduct(@RequestBody XhpProduct product) {
        boolean result = xhpProductService.addProduct(product);
        if (result) {
            return generatorSuccessResult();
        }
        return generatorFailResult();
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public ResponseResult getProductList(@RequestBody XhpProduct product) {
        return generatorSuccessResult(new PageInfo(xhpProductService.getProductList(product)));
    }
}
