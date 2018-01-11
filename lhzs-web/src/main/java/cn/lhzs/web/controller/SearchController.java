package cn.lhzs.web.controller;

import cn.lhzs.common.result.ResponseResult;
import cn.lhzs.common.vo.ProductSearchCondition;
import cn.lhzs.common.vo.ShopSearchCondition;
import cn.lhzs.service.intf.SearchService;
import com.github.pagehelper.PageInfo;
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

    @RequestMapping("/product")
    @ResponseBody
    public ResponseResult searchProductList(@RequestBody ProductSearchCondition productSearchCondition) {
        return generatorSuccessResult(new PageInfo(searchService.searchProductList(productSearchCondition)));
    }

    @RequestMapping("/shop")
    @ResponseBody
    public ResponseResult searchShopList(@RequestBody ShopSearchCondition shopSearchCondition) {
        return generatorSuccessResult(new PageInfo(searchService.searchShopList(shopSearchCondition)));
    }
}
