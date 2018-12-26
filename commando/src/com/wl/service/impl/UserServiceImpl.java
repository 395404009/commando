package com.wl.service.impl;

import java.util.ArrayList;
import java.util.Random;

import javax.annotation.Resource;




import org.springframework.stereotype.Service;

import com.wl.mapper.UsersMapper;
import com.wl.pojo.Questions;
import com.wl.pojo.Users;
import com.wl.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Resource
	private UsersMapper usersMapper;
	
	@Override
	public Users UserLogin(String username, String pwd) {
		
		return usersMapper.selUser(username, pwd);
	}

	@Override
	public int UserRegister(Users users) {
		//通过用户名查找，看该用户名是否已经注册
		Users user = usersMapper.selUserByUserName(users.getUsername());
		if(user==null||user.equals("")){
			//查找所有已注册用户
			ArrayList<Users> list = usersMapper.selAllUser();
	
			//随机产生一个userid
			int userid=0;
			//标记
			int falg=1;
			
			while(true){
				//随机产生一个[0,10000]的随机生成userid
				Random r = new Random();
				userid=r.nextInt(10000);
				for(Users us:list){
					//进行对比
					if(us.getUserid().equals(userid)){
						falg=0;
						break;
					}
				}
				if(falg==1){
					break;
				}
			}
			String useridStr=userid+"";
			users.setUserid(useridStr);
			
			//进行注册
			int index = usersMapper.insByUser(users);
			return index;
		}else{
			return 0;
		}
		
	}

}