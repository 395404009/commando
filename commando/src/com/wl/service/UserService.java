package com.wl.service;

import java.util.ArrayList;

import com.wl.pojo.Userques;
import com.wl.pojo.Users;

public interface UserService {
	/**
	 * �û���¼
	 * @param username
	 * @param pwd
	 * @return
	 */
    Users UserLogin(String username,String pwd);
    /**
     * �û�ע��
     * @param users
     * @return
     */
	int UserRegister(Users users);
    
}
