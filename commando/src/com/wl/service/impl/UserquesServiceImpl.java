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
		//获得所有用户的问题
		ArrayList<Userques> list = userquesMapper.selAllQuestions();
		return list;
	}
	@Override
	public int RelQuestion(Users users, String question, String answer) {
		//得到用户id
		String userid = users.getUserid();
		//查找所有问题qid
		ArrayList<Questions> ques= userquesMapper.selAllQid();
		
		int qid=0;
		//标记
		int falg=1;
		
		while(true){
			//随机产生一个[0,10000]的随机生成userid
			Random r = new Random();
			qid=r.nextInt(10000);
			for(Questions questions:ques){
				//进行对比
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
		
		//初始index
		int index=0;
		//插入问题表(questions)
		index=userquesMapper.insQuestion(qidStr,question,answer);
		if(index==1){
			//插入用户与问题的关联表(userques)
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
