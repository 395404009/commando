package com.wl.service;

import java.util.ArrayList;

import com.wl.pojo.Userques;
import com.wl.pojo.Users;

public interface UserquesService {
    /**
     * ��ѯ�����û�������
     * @return
     */
	ArrayList<Userques> AllQuestions();
	/**
	 * �����ʴ�
	 * @param users
	 * @param question
	 * @param answer
	 * @return
	 */
	int RelQuestion(Users users, String question, String answer);
	/**
	 * �鿴�ҵ�������Ϣ
	 * @param users
	 * @return
	 */
	ArrayList<Userques> myQuestion(Users users);

}
