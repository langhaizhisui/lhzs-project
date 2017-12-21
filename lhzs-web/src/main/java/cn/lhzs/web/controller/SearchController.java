package cn.lhzs.web.controller;

import cn.lhzs.data.bean.Product;
import cn.lhzs.data.bean.Shop;
import cn.lhzs.common.result.ResponseResult;
import cn.lhzs.service.intf.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static cn.lhzs.common.result.ResponseResultGenerator.generatorSuccessResult;

/**
 * Created by ZHX on 2017/11/23.
 */
@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping("product")
    @ResponseBody
    public ResponseResult searchProductList(@RequestBody Product product) {
        return generatorSuccessResult(searchService.searchProductList(product.getName()));
    }

    @RequestMapping("shop")
    @ResponseBody
    public ResponseResult searchShopList(@RequestBody Shop shop) {
        return generatorSuccessResult(searchService.searchShopList(shop.getWebShop()));
    }
}
