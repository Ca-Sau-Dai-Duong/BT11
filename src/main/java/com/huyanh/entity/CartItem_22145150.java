package com.huyanh.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Entity(name = "CartItems")
@Table(name = "CartItems")
public class CartItem_22145150 implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CartItemId") private Integer cartItemId;

    @ManyToOne @JoinColumn(name = "Username") private Users_22145150 user;
    @ManyToOne @JoinColumn(name = "VideoId") private Videos_22145150 video;

    @Column(name = "Quantity") private Integer quantity;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreatedAt") private Date createdAt;

    /** Thành tiền của dòng giỏ hàng này = giá * số lượng */
    public BigDecimal getLineTotal() {
        if (video == null || video.getPrice() == null || quantity == null) return BigDecimal.ZERO;
        return video.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    public Integer getCartItemId() { return cartItemId; }
    public void setCartItemId(Integer cartItemId) { this.cartItemId = cartItemId; }
    public Users_22145150 getUser() { return user; }
    public void setUser(Users_22145150 user) { this.user = user; }
    public Videos_22145150 getVideo() { return video; }
    public void setVideo(Videos_22145150 video) { this.video = video; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}
