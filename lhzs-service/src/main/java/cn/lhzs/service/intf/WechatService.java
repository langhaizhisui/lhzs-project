package cn.lhzs.service.intf;

import cn.lhzs.common.vo.WechatAccount;
import cn.lhzs.common.vo.WechatToken;

import java.util.Map;

/**
 * Created by ZHX on 2017/12/21.
 */
public interface WechatService {

    WechatAccount getAccount();

    String reply(Map<String, String> paramFromRequest, Map<String, String> paramFromInputStream);

    String accessToken(String code);

    String getWechatUserInfo(WechatToken wechatToken);
}
