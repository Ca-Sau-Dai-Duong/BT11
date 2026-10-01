package com.huyanh.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

@Entity(name = "OrderItems")
@Table(name = "OrderItems")
public class OrderItem_22145150 implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderItemId") private Integer orderItemId;

    @ManyToOne @JoinColumn(name = "OrderId") private Order_22145150 order;
    @ManyToOne @JoinColumn(name = "VideoId") private Videos_22145150 video;

    @Column(name = "Title", length = 200) private String title; // chốt tên tại thời điểm đặt
    @Column(name = "Price") private BigDecimal price;            // chốt giá tại thời điểm đặt
    @Column(name = "Quantity") private Integer quantity;

    public BigDecimal getLineTotal() {
        if (price == null || quantity == null) return BigDecimal.ZERO;
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    public Integer getOrderItemId() { return orderItemId; }
    public void setOrderItemId(Integer orderItemId) { this.orderItemId = orderItemId; }
    public Order_22145150 getOrder() { return order; }
    public void setOrder(Order_22145150 order) { this.order = order; }
    public Videos_22145150 getVideo() { return video; }
    public void setVideo(Videos_22145150 video) { this.video = video; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
