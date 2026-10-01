package com.huyanh.service;

import com.huyanh.entity.Users_22145150;

public interface IUserService_22145150 {
    Users_22145150 login(String username, String password);
    boolean exists(String username);
    void register(Users_22145150 u);
    String generateOtp();
}
