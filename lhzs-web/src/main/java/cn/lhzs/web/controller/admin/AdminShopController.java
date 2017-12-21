package cn.lhzs.web.controller.admin;

import cn.lhzs.common.aop.log.OpLog;
import cn.lhzs.data.bean.Shop;
import cn.lhzs.common.vo.ShopSearchCondition;
import cn.lhzs.common.result.ResponseResult;
import cn.lhzs.service.intf.ShopService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static cn.lhzs.common.aop.log.OpEnum.ADD;
import static cn.lhzs.common.aop.log.OpEnum.DEL;
import static cn.lhzs.common.aop.log.OpEnum.EDIT;
import static cn.lhzs.common.result.ResponseResultGenerator.generatorSuccessResult;

/**
 * Created by ZHX on 2017/12/6.
 */
@Controller
@RequestMapping("/admin/shop")
public class AdminShopController {

    @Autowired
    public ShopService shopService;

    @RequestMapping("/getShop")
    @ResponseBody
    public ResponseResult getShop(Long shopId) {
        return generatorSuccessResult(shopService.getShopByShopId(shopId));
    }

    @OpLog(type = ADD, descp = "增加店铺")
    @RequestMapping("/add")
    @ResponseBody
    public ResponseResult addShop(@RequestBody Shop shop) {
        shopService.addShop(shop);
        return generatorSuccessResult();
    }

    @OpLog(type = DEL, descp = "删除店铺")
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseResult deleteShop(Long shopId) {
        shopService.deleteShopByShopId(shopId);
        return generatorSuccessResult();
    }

    @OpLog(type = EDIT, descp = "更新店铺")
    @RequestMapping("/update")
    @ResponseBody
    public ResponseResult updateShop(@RequestBody Shop shop) {
        shopService.updateShop(shop);
        return generatorSuccessResult();
    }

    @RequestMapping("/search")
    @ResponseBody
    public ResponseResult searchShop(@RequestBody ShopSearchCondition shopSearchCondition) {
        return generatorSuccessResult(new PageInfo(shopService.searchShop(shopSearchCondition)));
    }

    @RequestMapping("/getSiteList")
    @ResponseBody
    public ResponseResult getShopListBySite(@RequestBody Shop shop) {
        return generatorSuccessResult(shopService.getShopList(shop));
    }

    @OpLog(type = DEL, descp = "删除所有店铺")
    @RequestMapping("/all/delete")
    @ResponseBody
    public ResponseResult deleteTable() {
        shopService.deleteTable();
        return generatorSuccessResult();
    }

    @OpLog(type = ADD, descp = "批量删除店铺")
    @RequestMapping("/batch/delete")
    @ResponseBody
    public ResponseResult batchDelete(@RequestBody ShopSearchCondition shopSearchCondition) {
        shopService.searchShop(shopSearchCondition).forEach(shop -> shopService.deleteShopByShopId(shop.getId()));
        return generatorSuccessResult();
    }
}
