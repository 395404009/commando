package com.wl.service;

import java.util.ArrayList;

import com.wl.pojo.Queseval;

public interface QuesevalService {
    /**
     * ����ָ�����������������Ϣ
     * @param qid
     * @return
     */
	ArrayList<Queseval> UserComment(String qid);
    /**
     * ��������
     * @param qid
     * @param context
     * @param userid 
     * @return
     */
	int WriteComment(String qid, String context, String userid);

}
