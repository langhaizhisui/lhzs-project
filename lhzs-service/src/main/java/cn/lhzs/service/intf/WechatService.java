package cn.lhzs.service.intf;

import java.util.Map;

/**
 * Created by ZHX on 2017/12/21.
 */
public interface WechatService {

    String reply(Map<String, String> paramFromRequest, Map<String, String> paramFromInputStream);
}
