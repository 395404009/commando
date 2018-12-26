package com.wl.service.impl;

import java.util.ArrayList;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wl.mapper.UserquesMapper;
import com.wl.pojo.Questions;
import com.wl.pojo.Userques;
import com.wl.pojo.Users;
import com.wl.service.UserquesService;

@Service
public class UserquesServiceImpl implements UserquesService{
    @Resource
	private UserquesMapper userquesMapper;
	@Override
	public ArrayList<Userques> AllQuestions() {
		//��������û�������
		ArrayList<Userques> list = userquesMapper.selAllQuestions();
		return list;
	}
	@Override
	public int RelQuestion(Users users, String question, String answer) {
		//�õ��û�id
		String userid = users.getUserid();
		//������������qid
		ArrayList<Questions> ques= userquesMapper.selAllQid();
		
		int qid=0;
		//���
		int falg=1;
		
		while(true){
			//�������һ��[0,10000]���������userid
			Random r = new Random();
			qid=r.nextInt(10000);
			for(Questions questions:ques){
				//���жԱ�
				if(questions.getQid().equals(qid)){
					falg=0;
					break;
				}
			}
			if(falg==1){
				break;
			}
		}
		String qidStr=qid+"";
		
		//��ʼindex
		int index=0;
		//���������(questions)
		index=userquesMapper.insQuestion(qidStr,question,answer);
		if(index==1){
			//�����û�������Ĺ�����(userques)
			index+=userquesMapper.insUserques(userid,qidStr);
			return index;
		}
		
		return 0;
	}
	@Override
	public ArrayList<Userques> myQuestion(Users users) {
		ArrayList<Userques> userques = userquesMapper.selAllMyQuestion(users.getUserid());
		return userques;
	}

}
