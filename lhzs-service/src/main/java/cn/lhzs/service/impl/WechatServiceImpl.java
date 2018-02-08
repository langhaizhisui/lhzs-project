package cn.lhzs.service.impl;

import cn.lhzs.common.constant.Constants;
import cn.lhzs.common.util.DateUtil;
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
            String inputText = paramFromInputStream.get("Content");
            if (StringUtil.isNotEmpty(inputText)) {
                return WechatUtil.reply(new WechatReply() {{
                    setFromUser(paramFromInputStream.get("FromUserName"));
                    setToUser(paramFromInputStream.get("ToUserName"));
                    setType(WechatUtil.TYPE_TEXT);
                    setContent("微信公众号测试");
                }});
            }
        } else if (WechatUtil.TYPE_EVENT.equals(MsgType)) {
            return WechatUtil.reply(new WechatReply() {{
                setFromUser(paramFromInputStream.get("FromUserName"));
                setToUser(paramFromInputStream.get("ToUserName"));
                setType(WechatUtil.TYPE_TEXT);
                setContent("www.baidu.com");
            }});
        }
        return "";
    }
}
