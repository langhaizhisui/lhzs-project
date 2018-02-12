package cn.lhzs.common.vo;

/**
 * Created by ZHX on 2018/2/12.
 */
public class WechatTicket {

    private Integer errorcode;

    private String errmsg;

    private String ticket;

    private Integer expire_in;

    public Integer getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(Integer errorcode) {
        this.errorcode = errorcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Integer getExpire_in() {
        return expire_in;
    }

    public void setExpire_in(Integer expire_in) {
        this.expire_in = expire_in;
    }
}
