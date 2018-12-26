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
     * ������������
     */
	@Override
	public ArrayList<Queseval> UserComment(String qid) {
		ArrayList<Queseval> queseval = quesevalMapper.selCommentByQid(qid);
		return queseval;
	}
	/**
	 * ��������
	 */
	@Override
	public int WriteComment(String qid, String context,String userid) {
		//�������е�eid
		ArrayList<Evaluates> eval=evaluatesMapper.selAllEval();
		//���
		int falg=1;
		//��ʼeid
		int eid=0;
		while(true){
			//�������һ��[0,10000]��eid
			Random r = new Random();
			eid=r.nextInt(10000);
			//����evaluates
			for(Evaluates evaluates :eval){
				//�������eid����֪eid�Ա�
				if(evaluates.getEid().equals(eid)){
					falg=0;
					break;
				}
			}
			if(falg==1){
				break;
			}
		}
		//ǿת��String
		String eidStr=eid+"";
		//��ȡ����ʱ��
		Date date=new Date();
		DateFormat mediumFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM);
		String time=mediumFormat.format(date);
		//��ʼindex
		int index=0;
		//�����۲������۱���
		index=evaluatesMapper.insByEidAndContext(eidStr,context,time);
		if(index==1){
			//�������������۵Ĺ�����(queseval)
			index+=evaluatesMapper.insByQidAndEid(eidStr,qid);
			if(index==2){
				//�����û������۵Ĺ�����(usereval)
				index+=evaluatesMapper.insByUseridAndEid(userid,eidStr);
				return index;
			}
		}
		
		
		return 0;
	}

}
