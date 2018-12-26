package com.wl.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.wl.pojo.Evaluates;

public interface QuestionsMapper {

    /**
     * ���Ҹ��������������
     * @param qid
     * @return
     */
	@Select("select * from evaluates where eid in(select eid from queseval where qid=#{qid})")
	ArrayList<Evaluates> selCommentByQid(@Param("qid") String qid);
    /**
     * ɾ��������
     * @param qid
     * @return
     */
	@Delete("delete from questions where qid=#{qid}")
	int delQuestionsByQid(@Param("qid") String qid);
	/**
	 * �û�������Ĺ�����(userques)
	 * @param qid
	 * @return
	 */
	@Delete("delete from userques where qid=#{qid}")
	int delUserquesByQid(@Param("qid") String qid);
	/**
	 * ɾ���������µ���������
	 * @param eid
	 * @return
	 */
	@Delete("delete from evaluates where eid=#{eid}")
	int delEvaluatesByEid(@Param("eid") String eid);
	/**
	 * ɾ�����������۵Ĺ�����(queseval)
	 * @param eid
	 * @return
	 */
	@Delete("delete from queseval where eid=#{eid}")
	int delQuesevalByEid(@Param("eid") String eid);

}
