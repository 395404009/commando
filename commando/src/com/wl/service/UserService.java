package com.wl.service;

import java.util.ArrayList;

import com.wl.pojo.Userques;
import com.wl.pojo.Users;

public interface UserService {
	/**
	 * 用户登录
	 * @param username
	 * @param pwd
	 * @return
	 */
    Users UserLogin(String username,String pwd);
    /**
     * 用户注册
     * @param users
     * @return
     */
	int UserRegister(Users users);
    
}
