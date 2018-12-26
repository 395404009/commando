package com.wl.service;

import java.util.ArrayList;

import com.wl.pojo.Userques;
import com.wl.pojo.Users;

public interface UserquesService {
    /**
     * 查询所有用户的问题
     * @return
     */
	ArrayList<Userques> AllQuestions();
	/**
	 * 发布问答
	 * @param users
	 * @param question
	 * @param answer
	 * @return
	 */
	int RelQuestion(Users users, String question, String answer);
	/**
	 * 查看我的所有信息
	 * @param users
	 * @return
	 */
	ArrayList<Userques> myQuestion(Users users);

}
