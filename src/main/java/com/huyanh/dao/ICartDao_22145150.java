package com.huyanh.dao;

import java.util.List;
import com.huyanh.entity.CartItem_22145150;

public interface ICartDao_22145150 {
    List<CartItem_22145150> findByUser(String username);
    CartItem_22145150 findByUserAndVideo(String username, String videoId);
    CartItem_22145150 findById(int cartItemId);
    void insert(CartItem_22145150 item);
    void update(CartItem_22145150 item);
    void delete(int cartItemId);
    void deleteAllByUser(String username);
    long countItems(String username);
}
