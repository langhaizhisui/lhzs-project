package cn.lhzs.web.controller.admin;

import cn.lhzs.common.result.ResponseResult;
import cn.lhzs.data.bean.Article;
import cn.lhzs.service.intf.ArticleService;
import cn.lhzs.service.intf.ConfigService;
import cn.lhzs.service.intf.TaobaoService;
import com.github.pagehelper.PageInfo;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkDgItemCouponGetRequest;
import com.taobao.api.request.TbkShopGetRequest;
import com.taobao.api.response.TbkDgItemCouponGetResponse;
import com.taobao.api.response.TbkShopGetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static cn.lhzs.common.result.ResponseResultGenerator.generatorSuccessResult;

/**
* Created by ZHX on 2018/5/15.
*/
@Controller
@RequestMapping("/taobao")
public class TaobaoController {

    @Autowired
    public TaobaoService taobaoService;

    public static void main(String[] args) {
        try {
//            TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23825857", "58a2fff3c1c40fcd0293adac41aedfaa");
//            TbkDgItemCouponGetRequest req = new TbkDgItemCouponGetRequest();
//            req.setAdzoneId(106100645L);
////            req.setPageSize(200L);
////            req.setPageNo(1L);
//            req.setQ("鞋子 男");
//            TbkDgItemCouponGetResponse response = client.execute(req);
//            System.out.println(response.getBody());

            TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23825857", "58a2fff3c1c40fcd0293adac41aedfaa");
            TbkShopGetRequest req = new TbkShopGetRequest();
            req.setFields("user_id,shop_title,shop_type,seller_nick,pict_url,shop_url");
            req.setQ("女装");
            req.setIsTmall(true);
            TbkShopGetResponse response = client.execute(req);
            System.out.println(response.getBody());
        } catch (ApiException e) {

        }
    }

}
