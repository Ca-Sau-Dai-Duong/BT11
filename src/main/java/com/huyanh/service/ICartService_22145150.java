package com.huyanh.service;

import java.util.List;
import com.huyanh.entity.CartItem_22145150;

public interface ICartService_22145150 {
    List<CartItem_22145150> getCart(String username);
    long countItems(String username);
    /** Thêm video vào giỏ; nếu đã có thì cộng dồn số lượng. Trả về thông báo lỗi, hoặc null nếu thành công */
    String addToCart(String username, String videoId, int quantity);
    /** Đổi số lượng 1 dòng giỏ hàng, giới hạn 1..99. Trả về thông báo lỗi, hoặc null nếu thành công */
    String updateQuantity(String username, int cartItemId, int quantity);
    void remove(String username, int cartItemId);
    void clear(String username);
}
