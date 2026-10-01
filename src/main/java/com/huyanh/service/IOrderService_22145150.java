package com.huyanh.service;

import java.util.List;
import com.huyanh.entity.Order_22145150;
import com.huyanh.entity.OrderStatus_22145150;

public interface IOrderService_22145150 {
    /** Đặt hàng COD từ toàn bộ giỏ hàng hiện tại của user. Trả về Order vừa tạo, hoặc null nếu giỏ hàng rỗng */
    Order_22145150 checkoutCOD(String username, String receiverName, String receiverPhone,
                               String receiverAddress, String note);
    List<Order_22145150> getHistory(String username, OrderStatus_22145150 status);
    Order_22145150 getDetail(int orderId, String username);
    /** Đọc trạng thái mới nhất trực tiếp từ DB (không qua cache) để phục vụ yêu cầu xem trạng thái đổi theo SQL */
    OrderStatus_22145150 getLiveStatus(int orderId);
}
