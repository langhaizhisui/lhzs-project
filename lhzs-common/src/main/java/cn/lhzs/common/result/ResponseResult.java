package cn.lhzs.common.result;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by ZHX on 2017/1/5.
 */
public class ResponseResult {

    private Integer code;
    private String msg;
    private Object data;

    public Integer getCode() {
        return code;
    }

    public ResponseResult setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResponseResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public ResponseResult setData(Object data) {
        this.data = data;
        return this;
    }

    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
