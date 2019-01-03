package cn.lhzs.data.bean;

import cn.lhzs.data.base.BaseModel;
import java.util.Date;
import javax.persistence.*;

@Table(name = "xhp_product_sku")
public class XhpProductSku extends BaseModel {

    /**
     * 商品标识
     */
    @Column(name = "prodId")
    private Long prodid;

    /**
     * 商品Sku标题
     */
    private String title;

    /**
     * 商品Sku价格
     */
    private Float price;

    /**
     * 商品Sku库存
     */
    private Integer stock;

    /**
     * 获取商品标识
     *
     * @return prodId - 商品标识
     */
    public Long getProdid() {
        return prodid;
    }

    /**
     * 设置商品标识
     *
     * @param prodid 商品标识
     */
    public void setProdid(Long prodid) {
        this.prodid = prodid;
    }

    /**
     * 获取商品Sku标题
     *
     * @return title - 商品Sku标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置商品Sku标题
     *
     * @param title 商品Sku标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取商品Sku价格
     *
     * @return price - 商品Sku价格
     */
    public Float getPrice() {
        return price;
    }

    /**
     * 设置商品Sku价格
     *
     * @param price 商品Sku价格
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     * 获取商品Sku库存
     *
     * @return stock - 商品Sku库存
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * 设置商品Sku库存
     *
     * @param stock 商品Sku库存
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

}