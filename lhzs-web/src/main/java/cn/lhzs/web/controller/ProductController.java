package cn.lhzs.web.controller;

import cn.lhzs.common.result.ResponseResult;
import cn.lhzs.common.vo.ProductSearchCondition;
import cn.lhzs.service.intf.ProductService;
import cn.lhzs.service.intf.SearchService;
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

    @Autowired
    public SearchService searchService;

    @RequestMapping(value = "/getProduct", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult getProduct(Long id) {
        return generatorSuccessResult(productService.getProductByProdId(id));
    }

    @RequestMapping(value = "/getList")
    @ResponseBody
    public ResponseResult getProductList(@RequestBody ProductSearchCondition productSearchCondition) {
        return generatorSuccessResult(new PageInfo(productService.getProdList(productSearchCondition)));
    }

}
