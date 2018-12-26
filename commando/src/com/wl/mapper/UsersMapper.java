package com.wl.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.wl.pojo.Users;

public interface UsersMapper {
	/**
	 * 登录
	 * @param username
	 * @param pwd
	 * @return
	 */
	@Select("select * from users where  pwd=#{pwd} and username=#{username}")
    Users selUser(@Param("username") String username,@Param("pwd")String pwd) ;
	/**
	 * 查看该用户名是否已经被注册
	 * @param username
	 * @return
	 */
    @Select("select * from users where username=#{username}")
	Users selUserByUserName(@Param("username") String username);
    /**
     * 查找所有已注册用户
     * @return
     */
    @Select("select * from users")
	ArrayList<Users> selAllUser();
    /**
     *注册
     * @param users
     * @return
     */
    @Insert("insert into users values(#{userid},#{username},#{pwd}，#{usex},#{photofile})")
	int insByUser(Users users);
}
