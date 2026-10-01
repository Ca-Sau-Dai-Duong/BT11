package com.huyanh.dao;

import java.util.List;
import com.huyanh.entity.OrderItem_22145150;

public interface IOrderItemDao_22145150 {
    void insert(OrderItem_22145150 item);
    List<OrderItem_22145150> findByOrder(int orderId);
}
