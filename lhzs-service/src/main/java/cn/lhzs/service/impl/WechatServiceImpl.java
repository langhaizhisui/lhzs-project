package cn.lhzs.service.impl;

import cn.lhzs.common.constant.Constants;
import cn.lhzs.data.bean.WechatAccount;
import cn.lhzs.data.bean.WechatAuthorize;
import cn.lhzs.service.intf.ConfigService;
import cn.lhzs.service.intf.WechatService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by ZHX on 2017/12/21.
 */
@Service
public class WechatServiceImpl implements WechatService {

    Logger logger = Logger.getLogger(WechatServiceImpl.class);

    @Resource
    public ConfigService configService;

    public String generatorAuthorizeUrl(WechatAuthorize wechatAuthorize) {
        StringBuffer url = new StringBuffer();
        try {
            url.append("https://open.weixin.qq.com/connect/oauth2/authorize");
            url.append("?appid=").append(getWechatAccount().getAppId());
            url.append("&redirect_uri=").append(URLEncoder.encode(wechatAuthorize.getRedirectUri(), Constants.UTF8));
            url.append("&response_type=code&scope=snsapi_userinfo&state=1&connect_redirect=1#wechat_redirect");
        } catch (UnsupportedEncodingException e) {

        }
        return url.toString();
    }

    private WechatAccount getWechatAccount() {
        return JSONObject.parseObject(configService.getConfigById(Constants.WECHAT_ACCOUNT).getValue(), WechatAccount.class);
    }
}
