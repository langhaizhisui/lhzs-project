package cn.lhzs.service.impl;

import cn.lhzs.common.constant.Constants;
import cn.lhzs.common.util.StringUtil;
import cn.lhzs.common.util.WechatUtil;
import cn.lhzs.common.vo.WechatAccount;
import cn.lhzs.common.vo.WechatReply;
import cn.lhzs.service.intf.ConfigService;
import cn.lhzs.service.intf.WechatService;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by ZHX on 2017/12/21.
 */
@Service
public class WechatServiceImpl implements WechatService {

    Logger logger = Logger.getLogger(WechatServiceImpl.class);

    @Resource
    public ConfigService configService;

    public WechatAccount getAccount() {
        return JSONObject.parseObject(configService.getConfigById(Constants.WECHAT_ACCOUNT).getValue(), WechatAccount.class);
    }

    @Override
    public String reply(Map<String, String> paramFromRequest, Map<String, String> paramFromInputStream) {

        String MsgType = paramFromInputStream.get("MsgType");
        if (WechatUtil.TYPE_TEXT.equals(MsgType)) {
            String content = paramFromInputStream.get("Content");
            if (StringUtil.isNotEmpty(content)) {
                if("测试".equals(content)){
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
            if(event.equals("subscribe") ){
                return WechatUtil.reply(new WechatReply() {{
                    setFromUser(paramFromInputStream.get("FromUserName"));
                    setToUser(paramFromInputStream.get("ToUserName"));
                    setType(WechatUtil.TYPE_TEXT);
                    setContent("用户关注公众号");
                }});
            }else if(event.equals("unsubscribe")){
                return WechatUtil.reply(new WechatReply() {{
                    setFromUser(paramFromInputStream.get("FromUserName"));
                    setToUser(paramFromInputStream.get("ToUserName"));
                    setType(WechatUtil.TYPE_TEXT);
                    setContent("用户取消公众号");
                }});
            } else if(event.equals("SCAN")){
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
}
