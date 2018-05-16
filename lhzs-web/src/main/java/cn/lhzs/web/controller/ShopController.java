package cn.lhzs.web.controller;

import cn.lhzs.common.result.ResponseResult;
import cn.lhzs.common.vo.ShopSearchCondition;
import cn.lhzs.data.bean.Shop;
import cn.lhzs.data.bean.Taobao;
import cn.lhzs.service.intf.ShopService;
import cn.lhzs.service.intf.TaobaoService;
import com.github.pagehelper.PageInfo;
import com.taobao.api.response.TbkShopGetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static cn.lhzs.common.result.ResponseResultGenerator.generatorSuccessResult;
import static cn.lhzs.common.result.ResponseResultGenerator.getResponseResultByPage;

/**
 * Created by ZHX on 2017/4/27.
 */
@Controller
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    public ShopService shopService;

    @Autowired
    public TaobaoService taobaoService;

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
    public ResponseResult getShopList(@RequestBody Taobao taobao){
        TbkShopGetResponse tbkShopGetResponse = taobaoService.searchShop(taobao);
        return generatorSuccessResult(getResponseResultByPage(Integer.parseInt(tbkShopGetResponse.getTotalResults() + ""), taobao.getPage(), tbkShopGetResponse.getResults()));
    }

}
