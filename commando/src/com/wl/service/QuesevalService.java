package com.wl.service;

import java.util.ArrayList;

import com.wl.pojo.Queseval;

public interface QuesevalService {
    /**
     * 查找指定问题的所有评论信息
     * @param qid
     * @return
     */
	ArrayList<Queseval> UserComment(String qid);
    /**
     * 进行评价
     * @param qid
     * @param context
     * @param userid 
     * @return
     */
	int WriteComment(String qid, String context, String userid);

}
