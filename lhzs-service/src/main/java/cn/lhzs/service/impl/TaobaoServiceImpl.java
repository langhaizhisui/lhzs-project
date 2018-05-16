package cn.lhzs.service.impl;

import cn.lhzs.common.exception.TaobaoException;
import cn.lhzs.data.bean.Taobao;
import cn.lhzs.service.intf.TaobaoService;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkDgItemCouponGetRequest;
import com.taobao.api.request.TbkItemInfoGetRequest;
import com.taobao.api.request.TbkShopGetRequest;
import com.taobao.api.response.TbkDgItemCouponGetResponse;
import com.taobao.api.response.TbkItemInfoGetResponse;
import com.taobao.api.response.TbkShopGetResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.github.pagehelper.PageHelper.startPage;

/**
 * Created by ZHX on 2018/5/15.
 */
@Service
@Transactional
public class TaobaoServiceImpl implements TaobaoService {

    private TaobaoClient client;

    Logger logger = Logger.getLogger(TaobaoServiceImpl.class);

    @Override
    public TbkDgItemCouponGetResponse searchProduct(Taobao taobao) {
        try {
            return getTaobaoClient().execute(new TbkDgItemCouponGetRequest(){{
                setAdzoneId(106100645L);
                setPageSize(Long.parseLong(taobao.getPage().toString()));
                setPageNo(Long.parseLong(taobao.getSize().toString()));
                setQ(taobao.getQ());
            }});
        } catch (ApiException e) {
            logger.error("好券清单API【导购】异常",e);
            throw new TaobaoException("好券清单API【导购】异常");
        }
    }

    @Override
    public TbkItemInfoGetResponse getProductByNumIids(String numIids){
        try {
            return getTaobaoClient().execute(new TbkItemInfoGetRequest(){{
                setFields("num_iid,title,pict_url,small_images,reserve_price,zk_final_price,user_type,provcity,item_url");
                setNumIids(numIids);
            }});
        } catch (ApiException e) {
            logger.error("淘宝客商品详情异常",e);
            throw new TaobaoException("淘宝客商品详情异常");
        }
    }

    @Override
    public TbkShopGetResponse searchShop(Taobao taobao){
        try {
            return getTaobaoClient().execute(new TbkShopGetRequest(){{
                setFields("user_id,shop_title,shop_type,seller_nick,pict_url,shop_url");
                setQ("女装");
                setIsTmall(true);
                setStartCredit(taobao.getStartCredit());
                setEndCredit(taobao.getEndCredit());
                setStartCommissionRate(taobao.getStartCommissionRate());
                setEndCommissionRate(taobao.getEndCommissionRate());
                setStartTotalAction(taobao.getStartTotalAction());
                setEndTotalAction(taobao.getEndTotalAction());
                setStartAuctionCount(taobao.getStartAuctionCount());
                setEndAuctionCount(taobao.getEndAuctionCount());
            }});
        } catch (ApiException e) {
            logger.error("淘宝客店铺查询异常",e);
            throw new TaobaoException("淘宝客店铺查询异常");
        }
    }

    private TaobaoClient getTaobaoClient(){
        if(client == null){
            client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23825857", "58a2fff3c1c40fcd0293adac41aedfaa");
        }
        return client;
    }
}
