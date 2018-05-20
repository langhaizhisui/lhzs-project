package cn.lhzs.web.controller;

import cn.lhzs.common.result.ResponseResult;
import cn.lhzs.common.util.StringUtil;
import cn.lhzs.common.vo.ProductSearchCondition;
import cn.lhzs.common.vo.ShopSearchCondition;
import cn.lhzs.data.bean.Taobao;
import cn.lhzs.service.intf.SearchService;
import cn.lhzs.service.intf.TaobaoService;
import com.github.pagehelper.PageInfo;
import com.taobao.api.response.TbkDgItemCouponGetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static cn.lhzs.common.result.ResponseResultGenerator.generatorFailResult;
import static cn.lhzs.common.result.ResponseResultGenerator.generatorSuccessResult;
import static cn.lhzs.common.result.ResponseResultGenerator.getResponseResultByPage;

/**
 * Created by ZHX on 2017/11/23.
 */
@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @Autowired
    public TaobaoService taobaoService;

    @RequestMapping("/product")
    @ResponseBody
    public ResponseResult searchProductList(@RequestBody Taobao taobao) {
        TbkDgItemCouponGetResponse tbkDgItemCouponGetResponse = taobaoService.searchProduct(taobao);
        if (StringUtil.isNotEmpty(tbkDgItemCouponGetResponse.getErrorCode())) {
            return generatorFailResult(tbkDgItemCouponGetResponse.getMsg() + "," + tbkDgItemCouponGetResponse.getSubMsg());
        }
        return generatorSuccessResult(getResponseResultByPage(Integer.parseInt(tbkDgItemCouponGetResponse.getTotalResults() + ""), taobao.getPage(), tbkDgItemCouponGetResponse.getResults()));
    }

    @RequestMapping("/shop")
    @ResponseBody
    public ResponseResult searchShopList(@RequestBody ShopSearchCondition shopSearchCondition) {
        return generatorSuccessResult(new PageInfo(searchService.searchShopList(shopSearchCondition)));
    }
}
