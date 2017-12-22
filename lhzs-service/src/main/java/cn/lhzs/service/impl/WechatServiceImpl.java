package cn.lhzs.service.impl;

import cn.lhzs.common.constant.Constants;
import cn.lhzs.common.vo.WechatAccount;
import cn.lhzs.service.intf.ConfigService;
import cn.lhzs.service.intf.WechatService;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

}
