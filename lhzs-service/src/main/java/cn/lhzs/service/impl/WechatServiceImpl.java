package cn.lhzs.service.impl;

import cn.lhzs.base.AbstractBaseService;
import cn.lhzs.common.constant.Constants;
import cn.lhzs.common.exception.WechatException;
import cn.lhzs.common.support.http.intf.IHttpClient;
import cn.lhzs.common.util.DateUtil;
import cn.lhzs.common.util.SecureUtil;
import cn.lhzs.common.util.StringUtil;
import cn.lhzs.common.util.WechatUtil;
import cn.lhzs.common.vo.WechatAccount;
import cn.lhzs.common.vo.WechatConfig;
import cn.lhzs.common.vo.WechatReply;
import cn.lhzs.common.vo.WechatTicket;
import cn.lhzs.common.vo.WechatToken;
import cn.lhzs.data.bean.Config;
import cn.lhzs.data.bean.WechatUser;
import cn.lhzs.data.dao.WechatUserMapper;
import cn.lhzs.service.intf.ConfigService;
import cn.lhzs.service.intf.WechatService;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by ZHX on 2017/12/21.
 */
@Service
public class WechatServiceImpl extends AbstractBaseService<WechatUser> implements WechatService {

    Logger logger = Logger.getLogger(WechatServiceImpl.class);

    @Resource
    public WechatUserMapper wechatUserMapper;

    @Resource
    public ConfigService configService;

    @Resource
    public IHttpClient httpClient;

    @Override
    public WechatAccount getAccount() {
        return JSONObject.parseObject(configService.getConfigById(Constants.WECHAT_ACCOUNT).getValue(), WechatAccount.class);
    }

    @Override
    public String reply(Map<String, String> paramFromRequest, Map<String, String> paramFromInputStream) {
        String MsgType = paramFromInputStream.get("MsgType");
        if (WechatUtil.TYPE_TEXT.equals(MsgType)) {
            String content = paramFromInputStream.get("Content");
            if (StringUtil.isNotEmpty(content)) {
                if ("测试".equals(content)) {
                    return WechatUtil.reply(new WechatReply() {{
                        setFromUser(paramFromInputStream.get("FromUserName"));
                        setToUser(paramFromInputStream.get("ToUserName"));
                        setType(WechatUtil.TYPE_TEXT);
                        setContent("关键字测试");
                    }});
                }
                return WechatUtil.reply(new WechatReply() {{
                    setFromUser(paramFromInputStream.get("FromUserName"));
                    setToUser(paramFromInputStream.get("ToUserName"));
                    setType(WechatUtil.TYPE_TEXT);
                    setContent("微信公众号测试");
                }});

            }
        } else if (WechatUtil.TYPE_EVENT.equals(MsgType)) {
            String event = paramFromInputStream.get("Event");
            if (event.equals("subscribe")) {
                return WechatUtil.reply(new WechatReply() {{
                    setFromUser(paramFromInputStream.get("FromUserName"));
                    setToUser(paramFromInputStream.get("ToUserName"));
                    setType(WechatUtil.TYPE_TEXT);
                    setContent("用户关注公众号");
                }});
            } else if (event.equals("unsubscribe")) {
                return WechatUtil.reply(new WechatReply() {{
                    setFromUser(paramFromInputStream.get("FromUserName"));
                    setToUser(paramFromInputStream.get("ToUserName"));
                    setType(WechatUtil.TYPE_TEXT);
                    setContent("用户取消公众号");
                }});
            } else if (event.equals("SCAN")) {
                return WechatUtil.reply(new WechatReply() {{
                    setFromUser(paramFromInputStream.get("FromUserName"));
                    setToUser(paramFromInputStream.get("ToUserName"));
                    setType(WechatUtil.TYPE_TEXT);
                    setContent("用户扫描公众号");
                }});
            }
        }
        return "";
    }

    @Override
    public String accessToken(String code) {
        try {
            String authorizeUrl = WechatUtil.geAccessTokenUrl(getAccount(), code);
            String result = httpClient.get(authorizeUrl, Constants.UTF8);
            WechatUtil.filterErrorMsg(result);
            return result;
        } catch (Exception e) {
            throw new WechatException("获取accessToken异常:" + e.getMessage());
        }
    }

    @Override
    public String getWechatUserInfo(WechatToken wechatToken) {
        try {
            String userInfo = WechatUtil.getUserInfoUrl(wechatToken);
            String result = httpClient.get(userInfo, Constants.UTF8);
            WechatUtil.filterErrorMsg(result);
            return result;
        } catch (Exception e) {
            throw new WechatException("获取WechatUserInfo异常:" + e.getMessage());
        }
    }

    @Override
    public WechatUser addWechatUser(WechatUser wechatUser) {
        WechatUser oldWechatUser = findBy("openId", wechatUser.getOpenId());
        if (oldWechatUser != null && oldWechatUser.getOpenId() != null && oldWechatUser.getOpenId().equals(wechatUser.getOpenId())) {
            return oldWechatUser;
        }
        return save(wechatUser);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public WechatConfig getConfig(String url) {
        try {
            String ticket = getJsApiTicket().getTicket();
            Long timestamp = DateUtil.getCurDate().getTime();
            String nonceStr = StringUtil.getRandomStringByLength(32);
            String signature = SecureUtil.sha1ToHex(WechatUtil.getConfigParam(ticket, timestamp, nonceStr, url).getBytes(Constants.UTF8));

            return new WechatConfig() {{
                setAppId(getAccount().getAppId());
                setNonceStr(nonceStr);
                setTimestamp(timestamp);
                setSignature(signature);
            }};

        } catch (UnsupportedEncodingException e) {
            throw new WechatException("获取Config异常");
        }
    }

    public String getAccessToken() {
        Config accssTockenConfig = configService.getConfigById(Constants.WECHAT_TEMP_ACCESS_TOKEN);
        if (accssTockenConfig == null || isTempAccessTokenExpired(accssTockenConfig)) {
            accssTockenConfig = updateTempAccessTokenConfig();
        }
        return JSONObject.parseObject(accssTockenConfig.getValue(), WechatToken.class).getAccess_token();
    }

    private boolean isTempAccessTokenExpired(Config accssTockenConfig) {
        return JSONObject.parseObject(accssTockenConfig.getValue(), WechatToken.class).getTimestamp() < DateUtil.getCurDate().getTime();
    }

    private Config updateTempAccessTokenConfig() {
        try {
            String tempAccessTokenUrl = WechatUtil.getTempAccessTokenUrl(getAccount());
            String result = httpClient.get(tempAccessTokenUrl, Constants.UTF8);
            WechatToken wechatToken = JSONObject.parseObject(result, WechatToken.class);
            wechatToken.setTimestamp(DateUtil.getCurDate().getTime() + 7000);
            return configService.updateConfigById(new Config() {{
                setId(Constants.WECHAT_TEMP_ACCESS_TOKEN);
                setValue(JSONObject.toJSONString(wechatToken));
            }});
        } catch (Exception e) {
            throw new WechatException("获取TempAccessToken异常");
        }
    }

    public WechatTicket getJsApiTicket() {
        try {
            String jsApiTicketUrl = WechatUtil.getJsApiTicketUrl(getAccessToken());
            String result = httpClient.get(jsApiTicketUrl, Constants.UTF8);
            WechatTicket wechatTicket = JSONObject.parseObject(result, WechatTicket.class);
            //TODO: 这里还需要判断ticket过期时间
            return wechatTicket;
        } catch (Exception e) {
            throw new WechatException("获取JsApiTicket异常");
        }
    }
}
