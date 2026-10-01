package com.huyanh.service.impl;

import com.huyanh.dao.IUserDao_22145150;
import com.huyanh.dao.impl.UserDaoImpl_22145150;
import com.huyanh.service.IUserService_22145150;
import java.security.SecureRandom;
import com.huyanh.entity.Users_22145150;

public class UserServiceImpl_22145150 implements IUserService_22145150 {
    private final IUserDao_22145150 dao = new UserDaoImpl_22145150();

    /** Trả về user nếu đăng nhập đúng và tài khoản đang Active, ngược lại null */
    @Override
    public Users_22145150 login(String username, String password) {
        if (username == null || password == null) return null;
        Users_22145150 u = dao.findById(username);
        if (u != null && password.equals(u.getPassword()) && Boolean.TRUE.equals(u.getActive())) return u;
        return null;
    }
    @Override
    public boolean exists(String username) { return dao.findById(username) != null; }
    @Override
    public void register(Users_22145150 u) { dao.insert(u); }
    @Override
    public String generateOtp() { return String.format("%06d", new SecureRandom().nextInt(1_000_000)); }
}
