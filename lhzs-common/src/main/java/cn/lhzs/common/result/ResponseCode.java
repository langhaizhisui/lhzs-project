package cn.lhzs.common.result;

/**
 * Created by ZHX on 2017/9/15.
 */
public enum ResponseCode {

    OK(200, "请求成功"),
    BAD_REQUEST(400,"请求参数出错"),
    NOT_FOUND(404, "找不到页面"),
    FAIL(500, "请求失败"),
    INTERNAL_SERVER_ERROR(501, "服务器出错"),
    UNLOGIN(600, "未登录"),
    LOGIN(601, "登录成功"),
    LOGIN_FAIL(602, "登录失败"),
    WECHAT_ERROR(603, "微信接口错误");

    private Integer code;
    private String descp;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescp() {
        return descp;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }

    ResponseCode(Integer code, String descp) {
        this.code = code;
        this.descp = descp;
    }

    public static ResponseCode get(Integer code) {
        ResponseCode[] responseCodes = values();
        for (int i = 0; i < responseCodes.length; i++) {
            ResponseCode responseCode = responseCodes[i];
            if (responseCode.getCode().equals(code)) {
                return responseCode;
            }
        }
        return null;
    }
}
