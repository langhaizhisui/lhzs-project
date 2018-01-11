package cn.lhzs.common.vo;

import java.util.Date;

public class ProductSearchCondition extends BaseCondition {

    private Long prodId;

    private String name;

    private String category;

    private String platform;

    private Date expirationStart;

    private Date expirationEnd;

    private Date createTimeStart;

    private Date createTimeEnd;

    public Long getProdId() {
        return prodId;
    }

    public void setProdId(Long prodId) {
        this.prodId = prodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Date getExpirationStart() {
        return expirationStart;
    }

    public void setExpirationStart(Date expirationStart) {
        this.expirationStart = expirationStart;
    }

    public Date getExpirationEnd() {
        return expirationEnd;
    }

    public void setExpirationEnd(Date expirationEnd) {
        this.expirationEnd = expirationEnd;
    }

    public Date getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(Date createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public Date getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(Date createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

}