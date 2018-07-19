package cn.lhzs.common.vo;

import java.io.Serializable;
import java.util.List;

public class BasePageList<T> implements Serializable{

    private List<T> list;

    private Long totalPage;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }
}