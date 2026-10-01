package com.huyanh.service.impl;

import java.util.List;
import com.huyanh.dao.ICartDao_22145150;
import com.huyanh.dao.IVideoDao_22145150;
import com.huyanh.dao.impl.CartDaoImpl_22145150;
import com.huyanh.dao.impl.VideoDaoImpl_22145150;
import com.huyanh.entity.CartItem_22145150;
import com.huyanh.entity.Users_22145150;
import com.huyanh.entity.Videos_22145150;
import com.huyanh.service.ICartService_22145150;

public class CartServiceImpl_22145150 implements ICartService_22145150 {
    private static final int MIN_QTY = 1;
    private static final int MAX_QTY = 99; // giới hạn số lượng tối đa mỗi dòng giỏ hàng

    private final ICartDao_22145150 cartDao = new CartDaoImpl_22145150();
    private final IVideoDao_22145150 videoDao = new VideoDaoImpl_22145150();

    @Override
    public List<CartItem_22145150> getCart(String username) {
        return cartDao.findByUser(username);
    }

    @Override
    public long countItems(String username) {
        return cartDao.countItems(username);
    }

    @Override
    public String addToCart(String username, String videoId, int quantity) {
        if (quantity < MIN_QTY) quantity = MIN_QTY;
        Videos_22145150 video = videoDao.findById(videoId);
        if (video == null || !Boolean.TRUE.equals(video.getActive())) {
            return "Video không tồn tại hoặc đã ngừng kinh doanh!";
        }
        CartItem_22145150 existing = cartDao.findByUserAndVideo(username, videoId);
        if (existing != null) {
            int newQty = existing.getQuantity() + quantity;
            if (newQty > MAX_QTY) newQty = MAX_QTY;
            existing.setQuantity(newQty);
            cartDao.update(existing);
        } else {
            if (quantity > MAX_QTY) quantity = MAX_QTY;
            CartItem_22145150 item = new CartItem_22145150();
            Users_22145150 u = new Users_22145150();
            u.setUsername(username);
            item.setUser(u);
            item.setVideo(video);
            item.setQuantity(quantity);
            item.setCreatedAt(new java.util.Date());
            cartDao.insert(item);
        }
        return null;
    }

    @Override
    public String updateQuantity(String username, int cartItemId, int quantity) {
        CartItem_22145150 item = cartDao.findById(cartItemId);
        if (item == null || !item.getUser().getUsername().equals(username)) {
            return "Dòng giỏ hàng không hợp lệ!";
        }
        if (quantity < MIN_QTY) {
            return "Số lượng tối thiểu là " + MIN_QTY + "!";
        }
        if (quantity > MAX_QTY) {
            return "Số lượng tối đa là " + MAX_QTY + "!";
        }
        item.setQuantity(quantity);
        cartDao.update(item);
        return null;
    }

    @Override
    public void remove(String username, int cartItemId) {
        CartItem_22145150 item = cartDao.findById(cartItemId);
        if (item != null && item.getUser().getUsername().equals(username)) {
            cartDao.delete(cartItemId);
        }
    }

    @Override
    public void clear(String username) {
        cartDao.deleteAllByUser(username);
    }
}
