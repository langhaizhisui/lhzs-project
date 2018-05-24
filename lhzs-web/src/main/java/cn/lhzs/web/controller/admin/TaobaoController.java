package cn.lhzs.web.controller.admin;

import cn.lhzs.common.exception.TaobaoException;
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
import com.taobao.api.request.TbkItemInfoGetRequest;
import com.taobao.api.request.TbkShopGetRequest;
import com.taobao.api.response.TbkDgItemCouponGetResponse;
import com.taobao.api.response.TbkItemInfoGetResponse;
import com.taobao.api.response.TbkShopGetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

import static cn.lhzs.common.result.ResponseResultGenerator.generatorSuccessResult;
import static cn.lhzs.common.result.ResponseResultGenerator.getResponseResultByPage;
import static java.lang.Thread.sleep;

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
//            req.setPageSize(1L);
//            req.setPageNo(1L);
//            req.setQ("鞋子 男");
//            TbkDgItemCouponGetResponse tbkDgItemCouponGetResponse = client.execute(req);
//            System.out.println(tbkDgItemCouponGetResponse.getBody());
//            ResponseResult responseResult = generatorSuccessResult(getResponseResultByPage(Integer.parseInt(tbkDgItemCouponGetResponse.getTotalResults() + ""), 1, tbkDgItemCouponGetResponse.getResults()));
//            System.out.println(responseResult);


//            TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23825857", "58a2fff3c1c40fcd0293adac41aedfaa");
//            TbkItemInfoGetRequest tbkItemInfoGetRequest = new TbkItemInfoGetRequest();
//            tbkItemInfoGetRequest.setFields("num_iid,title,pict_url,small_images,reserve_price,zk_final_price,user_type,provcity,item_url");
//            tbkItemInfoGetRequest.setNumIids("567957108381");
//            TbkItemInfoGetResponse execute = client.execute(tbkItemInfoGetRequest);
//            System.out.println(execute);

//            TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23825857", "58a2fff3c1c40fcd0293adac41aedfaa");
//            TbkShopGetRequest req = new TbkShopGetRequest();
//            req.setFields("user_id,shop_title,shop_type,seller_nick,pict_url,shop_url");
//            req.setQ("女装");
//            req.setIsTmall(false);
//            TbkShopGetResponse response = client.execute(req);
//            System.out.println(response.getBody());


            TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23825857", "58a2fff3c1c40fcd0293adac41aedfaa");
            TbkDgItemCouponGetRequest tbkDgItemCouponGetRequest = new TbkDgItemCouponGetRequest();
            tbkDgItemCouponGetRequest.setAdzoneId(106100645L);
            tbkDgItemCouponGetRequest.setPageSize(5L);
            tbkDgItemCouponGetRequest.setPageNo(5L);
            tbkDgItemCouponGetRequest.setQ("男鞋");
            TbkDgItemCouponGetResponse execute;
            for (int i = 0; i < 100; i++) {
                System.out.println("执行前："+ new Date().getTime());
                execute = client.execute(tbkDgItemCouponGetRequest);
                System.out.println(execute.getBody());
                System.out.println("执行后："+ new Date().getTime());
                try {
                    sleep(2000);
                } catch (InterruptedException e) {

                }
            }


        } catch (ApiException e) {

        }
    }

}
