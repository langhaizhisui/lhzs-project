package cn.lhzs.web.controller;

import cn.lhzs.common.vo.ProductSearchCondition;
import cn.lhzs.common.result.ResponseResult;
import cn.lhzs.service.intf.ProductService;
import cn.lhzs.service.intf.ShopService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static cn.lhzs.common.result.ResponseResultGenerator.generatorSuccessResult;

/**
 * Created by ZHX on 2017/4/27.
 */
@Controller
@RequestMapping("/prod")
public class ProductController {

    @Autowired
    public ProductService productService;

    @Autowired
    public ShopService shopService;

    @RequestMapping(value = "/getProduct", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult getProduct(Long prodId) {
        return generatorSuccessResult(productService.getProductByProdId(prodId));
    }

    @RequestMapping(value = "/getList")
    @ResponseBody
    public ResponseResult getProductList(@RequestBody ProductSearchCondition productSearchCondition) {
        return generatorSuccessResult(new PageInfo(productService.getProdList(productSearchCondition)));
    }

    @RequestMapping("/search")
    @ResponseBody
    public ResponseResult search(@RequestBody ProductSearchCondition productSearchCondition) {
        return generatorSuccessResult(new PageInfo(productService.searchProduct(productSearchCondition)));
    }

}
