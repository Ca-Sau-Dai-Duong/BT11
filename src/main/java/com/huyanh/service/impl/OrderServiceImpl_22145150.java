package com.huyanh.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.huyanh.dao.ICartDao_22145150;
import com.huyanh.dao.IOrderDao_22145150;
import com.huyanh.dao.IOrderItemDao_22145150;
import com.huyanh.dao.impl.CartDaoImpl_22145150;
import com.huyanh.dao.impl.OrderDaoImpl_22145150;
import com.huyanh.dao.impl.OrderItemDaoImpl_22145150;
import com.huyanh.entity.CartItem_22145150;
import com.huyanh.entity.Order_22145150;
import com.huyanh.entity.OrderItem_22145150;
import com.huyanh.entity.OrderStatus_22145150;
import com.huyanh.entity.Users_22145150;
import com.huyanh.service.IOrderService_22145150;

public class OrderServiceImpl_22145150 implements IOrderService_22145150 {
    private final ICartDao_22145150 cartDao = new CartDaoImpl_22145150();
    private final IOrderDao_22145150 orderDao = new OrderDaoImpl_22145150();
    private final IOrderItemDao_22145150 orderItemDao = new OrderItemDaoImpl_22145150();

    @Override
    public Order_22145150 checkoutCOD(String username, String receiverName, String receiverPhone,
                                      String receiverAddress, String note) {
        List<CartItem_22145150> cart = cartDao.findByUser(username);
        if (cart.isEmpty()) return null;

        // 1) Tạo đơn hàng
        Order_22145150 order = new Order_22145150();
        Users_22145150 u = new Users_22145150();
        u.setUsername(username);
        order.setUser(u);
        order.setOrderDate(new Date());
        order.setReceiverName(receiverName);
        order.setReceiverPhone(receiverPhone);
        order.setReceiverAddress(receiverAddress);
        order.setPaymentMethod("COD");
        order.setStatus(OrderStatus_22145150.MOI); // trạng thái ban đầu: Đơn hàng mới
        order.setNote(note);

        BigDecimal total = BigDecimal.ZERO;
        for (CartItem_22145150 ci : cart) {
            total = total.add(ci.getLineTotal());
        }
        order.setTotalAmount(total);
        orderDao.insert(order); // sau dòng này order.getOrderId() đã có giá trị

        // 2) Chốt chi tiết đơn hàng (title/price tại thời điểm đặt)
        for (CartItem_22145150 ci : cart) {
            OrderItem_22145150 oi = new OrderItem_22145150();
            oi.setOrder(order);
            oi.setVideo(ci.getVideo());
            oi.setTitle(ci.getVideo().getTitle());
            oi.setPrice(ci.getVideo().getPrice());
            oi.setQuantity(ci.getQuantity());
            orderItemDao.insert(oi);
        }

        // 3) Xóa giỏ hàng sau khi đặt thành công
        cartDao.deleteAllByUser(username);

        return order;
    }

    @Override
    public List<Order_22145150> getHistory(String username, OrderStatus_22145150 status) {
        return orderDao.findByUser(username, status);
    }

    @Override
    public Order_22145150 getDetail(int orderId, String username) {
        Order_22145150 o = orderDao.findById(orderId);
        if (o == null || !o.getUser().getUsername().equals(username)) return null;
        o.setItems(orderItemDao.findByOrder(orderId));
        return o;
    }

    @Override
    public OrderStatus_22145150 getLiveStatus(int orderId) {
        return orderDao.findStatus(orderId);
    }
}
