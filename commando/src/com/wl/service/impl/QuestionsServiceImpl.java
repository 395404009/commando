package com.wl.service.impl;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wl.mapper.QuestionsMapper;
import com.wl.pojo.Evaluates;
import com.wl.service.QuestionsService;

@Service
public class QuestionsServiceImpl implements QuestionsService{
    @Resource
	private QuestionsMapper questionMapper;
	@Override
	public int delUserQuestions(String qid) {
		int index=0;
		//ɾ�������(questions)
		index=questionMapper.delQuestionsByQid(qid);
		if(index==1){
			//ɾ���û�������Ĺ�����(userques)
			index+=questionMapper.delUserquesByQid(qid);
			if(index==2){
				//�������и����������
				 ArrayList<Evaluates> eval=questionMapper.selCommentByQid(qid);
				 //System.out.println(eval);
				 String eid=null;
				 for(Evaluates evaluates:eval){
					 //��ȡ���۱�eid
					 eid=evaluates.getEid();
					//ɾ���������������۱�(evaluates)
					 index+=questionMapper.delEvaluatesByEid(eid);
					//ɾ�����������۵Ĺ�����(queseval)
					 index+=questionMapper.delQuesevalByEid(eid);
				 }
				 return index;
			}
		}
		
		
		
		return 0;
	}

}
