package cn.lhzs.common.vo;

/**
 * Created by ZHX on 2018/6/14.
 */
public class WechatCondition extends BaseCondition{

    private String appId;

    private String url;

    private String count;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
