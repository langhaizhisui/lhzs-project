package cn.lhzs.data.bean;

public class Taobao{

    /**
     * 查询词
     */
    private String q;

    /**
     * 排序 _des（降序），_asc（升序），佣金比率（commission_rate），商品数量（auction_count）,销售总数量（total_auction）
     */
    private String sort;

    /**
     * 淘宝商城店铺（true），默认为false
     */
    private Boolean isTmall;

    /**
     * 信用等级下限（1-20）
     */
    private Long startCredit;

    /**
     * 信用等级上限（1-20）
     */
    private Long endCredit;

    /**
     * 淘宝佣金比率下限（1-10000）
     */
    private Long startCommissionRate;

    /**
     * 淘宝佣金比率上限（1-10000）
     */
    private Long endCommissionRate;

    /**
     * 店铺商品总数下限
     */
    private Long startTotalAction;

    /**
     * 店铺商品总数上限
     */
    private Long endTotalAction;

    /**
     * 累计推广商品下限
     */
    private Long startAuctionCount;

    /**
     * 累计推广商品上限
     */
    private Long endAuctionCount;

    private Integer page;

    private Integer size;

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Boolean getTmall() {
        return isTmall;
    }

    public void setTmall(Boolean tmall) {
        isTmall = tmall;
    }

    public Long getStartCredit() {
        return startCredit;
    }

    public void setStartCredit(Long startCredit) {
        this.startCredit = startCredit;
    }

    public Long getEndCredit() {
        return endCredit;
    }

    public void setEndCredit(Long endCredit) {
        this.endCredit = endCredit;
    }

    public Long getStartCommissionRate() {
        return startCommissionRate;
    }

    public void setStartCommissionRate(Long startCommissionRate) {
        this.startCommissionRate = startCommissionRate;
    }

    public Long getEndCommissionRate() {
        return endCommissionRate;
    }

    public void setEndCommissionRate(Long endCommissionRate) {
        this.endCommissionRate = endCommissionRate;
    }

    public Long getStartTotalAction() {
        return startTotalAction;
    }

    public void setStartTotalAction(Long startTotalAction) {
        this.startTotalAction = startTotalAction;
    }

    public Long getEndTotalAction() {
        return endTotalAction;
    }

    public void setEndTotalAction(Long endTotalAction) {
        this.endTotalAction = endTotalAction;
    }

    public Long getStartAuctionCount() {
        return startAuctionCount;
    }

    public void setStartAuctionCount(Long startAuctionCount) {
        this.startAuctionCount = startAuctionCount;
    }

    public Long getEndAuctionCount() {
        return endAuctionCount;
    }

    public void setEndAuctionCount(Long endAuctionCount) {
        this.endAuctionCount = endAuctionCount;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

}