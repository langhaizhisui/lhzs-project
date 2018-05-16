package cn.lhzs.common.result;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by ZHX on 2018/5/16.
 */
public class ResponseResultByPage {

    private Integer pages;

    private Integer pageNum;

    private Object data;

    public Integer getPages() {
        return pages;
    }

    public ResponseResultByPage setPages(Integer pages) {
        this.pages = pages;
        return this;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public ResponseResultByPage setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
        return this;
    }

    public Object getData() {
        return data;
    }

    public ResponseResultByPage setData(Object data) {
        this.data = data;
        return this;
    }

    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
