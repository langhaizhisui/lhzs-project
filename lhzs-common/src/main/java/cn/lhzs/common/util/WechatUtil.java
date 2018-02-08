package cn.lhzs.common.util;

import cn.lhzs.common.constant.Constants;
import cn.lhzs.common.exception.WechatException;
import cn.lhzs.common.support.http.intf.IHttpClient;
import cn.lhzs.common.vo.WechatAccount;
import cn.lhzs.common.vo.WechatAuthorize;
import cn.lhzs.common.vo.WechatErrorMsg;
import cn.lhzs.common.vo.WechatReply;
import cn.lhzs.common.vo.WechatToken;
import com.alibaba.fastjson.JSONObject;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by ZHX on 2017/12/22.
 */
public class WechatUtil {

    @Resource
    private static IHttpClient httpClient;

    private String token;
    public static final String TYPE_TEXT = "text";
    public static final String TYPE_EVENT = "event";

    public static String reply(WechatReply wechatReply) {
        return new StringBuilder().append("<xml><ToUserName><![CDATA[").append(wechatReply.getFromUser()).append("]]></ToUserName>")
                .append("<FromUserName><![CDATA[").append(wechatReply.getToUser()).append("]]></FromUserName>")
                .append("<CreateTime>").append(DateUtil.getNowTimeStampStr()).append("</CreateTime>")
                .append("<MsgType><![CDATA[").append(wechatReply.getType()).append("]]></MsgType>")
                .append("<Content><![CDATA[").append(wechatReply.getContent()).append("]]></Content></xml>").toString();
    }

    public static String generatorAuthorizeUrl(WechatAuthorize wechatAuthorize) {
        try {
            String url = new StringBuilder().append("https://open.weixin.qq.com/connect/oauth2/authorize")
                    .append("?appid=").append(wechatAuthorize.getAppId())
                    .append("&redirect_uri=").append(URLEncoder.encode(wechatAuthorize.getRedirectUri(), Constants.UTF8))
                    .append("&response_type=code&scope=snsapi_userinfo&state=1&connect_redirect=1#wechat_redirect").toString();

            filterErrorMsg(url);
            return url;
        } catch (UnsupportedEncodingException e) {
            throw new WechatException("UnsupportedEncodingException异常");
        }
    }

    public static String accessToken(WechatAccount account, String code) {
        try {
            String url = new StringBuilder("https://api.weixin.qq.com/sns/oauth2/access_token")
                    .append("?appid=").append(account.getAppId())
                    .append("&secret=").append(account.getAppsecret())
                    .append("&code=").append(code)
                    .append("&grant_type=authorization_code").toString();
            String result = httpClient.get(url, Constants.UTF8);
            filterErrorMsg(result);
            return result;
        } catch (Exception e) {
            throw new WechatException("UnsupportedEncodingException异常");
        }
    }

    public static String getUserInfo(WechatToken wechatToken) {
        try {
            String url = new StringBuilder("https://api.weixin.qq.com/sns/userinfo")
                    .append("?access_token=").append(wechatToken.getAccess_token())
                    .append("&openid=").append(wechatToken.getOpenid())
                    .append("&lang=zh_CN").toString();
            String result = httpClient.get(url, Constants.UTF8);
            filterErrorMsg(result);
            return result;
        } catch (Exception e) {
            throw new WechatException("获取信息异常");
        }
    }

    private static void filterErrorMsg(String result) {
        WechatErrorMsg wechatErrorMsg = JSONObject.parseObject(result, WechatErrorMsg.class);
        if (wechatErrorMsg.getErrcode() != null) {
            throw new WechatException(wechatErrorMsg.getErrmsg());
        }
    }

    public static boolean checkSign(Map<String, String> paramFromRequest) {
        try {
            String signature = paramFromRequest.get("signature");
            String timestamp = paramFromRequest.get("timestamp");
            String nonce = paramFromRequest.get("nonce");
//            String token = paramFromRequest.get("token");
            String token = "qbtest";

            String[] ArrTmp = {token, timestamp, nonce};
            Arrays.sort(ArrTmp);
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < ArrTmp.length; i++) {
                sb.append(ArrTmp[i]);
            }

            if (SecureUtil.sha1ToHex(sb.toString().getBytes(Constants.UTF8)).equals(signature)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}
