package cn.lhzs.web.controller;

import cn.lhzs.data.bean.Shop;
import cn.lhzs.common.vo.ShopSearchCondition;
import cn.lhzs.common.result.ResponseResult;
import cn.lhzs.service.intf.SearchService;
import cn.lhzs.service.intf.ShopService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static cn.lhzs.common.result.ResponseResultGenerator.generatorSuccessResult;

/**
 * Created by ZHX on 2017/4/27.
 */
@Controller
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    public ShopService shopService;

    @Autowired
    public SearchService searchService;

    @RequestMapping("/getShop")
    @ResponseBody
    public ResponseResult getShop(Long shopId) {
        return generatorSuccessResult(shopService.getShopByShopId(shopId));
    }

    @RequestMapping("/getSiteList")
    @ResponseBody
    public ResponseResult getShopListBySite(@RequestBody Shop shop) {
        return generatorSuccessResult(shopService.getShopList(shop));
    }

    @RequestMapping("/search")
    @ResponseBody
    public ResponseResult getShopList(@RequestBody ShopSearchCondition shopSearchCondition){
        return generatorSuccessResult(new PageInfo(shopService.searchShop(shopSearchCondition)));
    }

}