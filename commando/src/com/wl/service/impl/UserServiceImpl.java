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
		//ͨ���û������ң������û����Ƿ��Ѿ�ע��
		Users user = usersMapper.selUserByUserName(users.getUsername());
		if(user==null||user.equals("")){
			//����������ע���û�
			ArrayList<Users> list = usersMapper.selAllUser();
	
			//�������һ��userid
			int userid=0;
			//���
			int falg=1;
			
			while(true){
				//�������һ��[0,10000]���������userid
				Random r = new Random();
				userid=r.nextInt(10000);
				for(Users us:list){
					//���жԱ�
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
			
			//����ע��
			int index = usersMapper.insByUser(users);
			return index;
		}else{
			return 0;
		}
		
	}

}