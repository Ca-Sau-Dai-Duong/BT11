package com.huyanh.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity(name = "Orders")
@Table(name = "Orders")
public class Order_22145150 implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderId") private Integer orderId;

    @ManyToOne @JoinColumn(name = "Username") private Users_22145150 user;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "OrderDate") private Date orderDate;

    @Column(name = "ReceiverName", length = 100) private String receiverName;
    @Column(name = "ReceiverPhone", length = 15) private String receiverPhone;
    @Column(name = "ReceiverAddress", length = 300) private String receiverAddress;
    @Column(name = "PaymentMethod", length = 20) private String paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status", length = 20) private OrderStatus_22145150 status;

    @Column(name = "TotalAmount") private BigDecimal totalAmount;
    @Column(name = "Note", length = 500) private String note;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderItem_22145150> items = new ArrayList<>();

    public Integer getOrderId() { return orderId; }
    public void setOrderId(Integer orderId) { this.orderId = orderId; }
    public Users_22145150 getUser() { return user; }
    public void setUser(Users_22145150 user) { this.user = user; }
    public Date getOrderDate() { return orderDate; }
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }
    public String getReceiverName() { return receiverName; }
    public void setReceiverName(String receiverName) { this.receiverName = receiverName; }
    public String getReceiverPhone() { return receiverPhone; }
    public void setReceiverPhone(String receiverPhone) { this.receiverPhone = receiverPhone; }
    public String getReceiverAddress() { return receiverAddress; }
    public void setReceiverAddress(String receiverAddress) { this.receiverAddress = receiverAddress; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public OrderStatus_22145150 getStatus() { return status; }
    public void setStatus(OrderStatus_22145150 status) { this.status = status; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
    public List<OrderItem_22145150> getItems() { return items; }
    public void setItems(List<OrderItem_22145150> items) { this.items = items; }
}
