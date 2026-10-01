package com.huyanh.dao;

import java.util.List;
import com.huyanh.entity.Order_22145150;
import com.huyanh.entity.OrderStatus_22145150;

public interface IOrderDao_22145150 {
    void insert(Order_22145150 order);
    Order_22145150 findById(int orderId);
    List<Order_22145150> findByUser(String username, OrderStatus_22145150 status);
    OrderStatus_22145150 findStatus(int orderId); // đọc trạng thái mới nhất trực tiếp từ DB
}
