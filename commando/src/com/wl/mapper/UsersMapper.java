package com.wl.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.wl.pojo.Users;

public interface UsersMapper {
	/**
	 * ��¼
	 * @param username
	 * @param pwd
	 * @return
	 */
	@Select("select * from users where  pwd=#{pwd} and username=#{username}")
    Users selUser(@Param("username") String username,@Param("pwd")String pwd) ;
	/**
	 * �鿴���û����Ƿ��Ѿ���ע��
	 * @param username
	 * @return
	 */
    @Select("select * from users where username=#{username}")
	Users selUserByUserName(@Param("username") String username);
    /**
     * ����������ע���û�
     * @return
     */
    @Select("select * from users")
	ArrayList<Users> selAllUser();
    /**
     *ע��
     * @param users
     * @return
     */
    @Insert("insert into users values(#{userid},#{username},#{pwd}��#{usex},#{photofile})")
	int insByUser(Users users);
}
