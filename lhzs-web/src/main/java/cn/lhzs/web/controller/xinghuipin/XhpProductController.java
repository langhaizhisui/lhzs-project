package cn.lhzs.web.controller.xinghuipin;

import cn.lhzs.common.result.ResponseResult;
import cn.lhzs.data.bean.XhpProduct;
import cn.lhzs.data.bean.XhpProductSku;
import cn.lhzs.service.intf.XhpProductService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static cn.lhzs.common.result.ResponseResultGenerator.generatorFailResult;
import static cn.lhzs.common.result.ResponseResultGenerator.generatorSuccessResult;

/**
 * Created by ZHX on 2019/01/03.
 */
@Controller
@RequestMapping("/xhp/prod")
public class XhpProductController {

    @Autowired
    private XhpProductService xhpProductService;

    @RequestMapping(value = "/list")
    @ResponseBody
    public ResponseResult getProductList(@RequestBody XhpProduct product) {
        return generatorSuccessResult(new PageInfo(xhpProductService.getProductList(product)));
    }

    @RequestMapping(value = "/catalog")
    @ResponseBody
    public ResponseResult getCatalogList(@RequestBody XhpProductSku productSku) {
        return generatorSuccessResult(new PageInfo(xhpProductService.getProductSku(productSku)));
    }

    @RequestMapping(value = "/detail")
    @ResponseBody
    public ResponseResult getProduct(@RequestBody XhpProduct product) {
        return generatorSuccessResult(xhpProductService.getProduct(product));
    }

}
