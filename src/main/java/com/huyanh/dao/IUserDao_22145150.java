package com.huyanh.dao;

import com.huyanh.entity.Users_22145150;

public interface IUserDao_22145150 {
    Users_22145150 findById(String username);
    void insert(Users_22145150 u);
}
