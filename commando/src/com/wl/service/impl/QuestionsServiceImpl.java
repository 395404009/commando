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
		//删除问题表(questions)
		index=questionMapper.delQuestionsByQid(qid);
		if(index==1){
			//删除用户与问题的关联表(userques)
			index+=questionMapper.delUserquesByQid(qid);
			if(index==2){
				//查找所有该问题的评价
				 ArrayList<Evaluates> eval=questionMapper.selCommentByQid(qid);
				 //System.out.println(eval);
				 String eid=null;
				 for(Evaluates evaluates:eval){
					 //获取评价表eid
					 eid=evaluates.getEid();
					//删除该问题所有评价表(evaluates)
					 index+=questionMapper.delEvaluatesByEid(eid);
					//删除问题与评价的关联表(queseval)
					 index+=questionMapper.delQuesevalByEid(eid);
				 }
				 return index;
			}
		}
		
		
		
		return 0;
	}

}
