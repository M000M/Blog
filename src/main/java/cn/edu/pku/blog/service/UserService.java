package cn.edu.pku.blog.service;

import cn.edu.pku.blog.po.User;

public interface UserService {

    User checkUser(String username, String password);
}
