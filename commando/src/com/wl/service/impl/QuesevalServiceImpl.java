package com.wl.service.impl;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wl.mapper.EvaluatesMapper;
import com.wl.mapper.QuesevalMapper;
import com.wl.pojo.Evaluates;
import com.wl.pojo.Queseval;
import com.wl.pojo.Userques;
import com.wl.service.QuesevalService;

@Service
public class QuesevalServiceImpl implements QuesevalService{
    @Resource
	private QuesevalMapper quesevalMapper;
    @Resource
    private EvaluatesMapper evaluatesMapper;
    /**
     * 查找所有评价
     */
	@Override
	public ArrayList<Queseval> UserComment(String qid) {
		ArrayList<Queseval> queseval = quesevalMapper.selCommentByQid(qid);
		return queseval;
	}
	/**
	 * 进行评价
	 */
	@Override
	public int WriteComment(String qid, String context,String userid) {
		//查找所有的eid
		ArrayList<Evaluates> eval=evaluatesMapper.selAllEval();
		//标记
		int falg=1;
		//初始eid
		int eid=0;
		while(true){
			//随机产生一个[0,10000]的eid
			Random r = new Random();
			eid=r.nextInt(10000);
			//变量evaluates
			for(Evaluates evaluates :eval){
				//随机生成eid与已知eid对比
				if(evaluates.getEid().equals(eid)){
					falg=0;
					break;
				}
			}
			if(falg==1){
				break;
			}
		}
		//强转从String
		String eidStr=eid+"";
		//获取评论时间
		Date date=new Date();
		DateFormat mediumFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM);
		String time=mediumFormat.format(date);
		//初始index
		int index=0;
		//将评价插入评价表中
		index=evaluatesMapper.insByEidAndContext(eidStr,context,time);
		if(index==1){
			//插入问题与评价的关联表(queseval)
			index+=evaluatesMapper.insByQidAndEid(eidStr,qid);
			if(index==2){
				//插入用户与评价的关联表(usereval)
				index+=evaluatesMapper.insByUseridAndEid(userid,eidStr);
				return index;
			}
		}
		
		
		return 0;
	}

}
