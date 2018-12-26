package com.wl.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.wl.pojo.Evaluates;

public interface QuestionsMapper {

    /**
     * 查找该问题的所有评价
     * @param qid
     * @return
     */
	@Select("select * from evaluates where eid in(select eid from queseval where qid=#{qid})")
	ArrayList<Evaluates> selCommentByQid(@Param("qid") String qid);
    /**
     * 删除该问题
     * @param qid
     * @return
     */
	@Delete("delete from questions where qid=#{qid}")
	int delQuestionsByQid(@Param("qid") String qid);
	/**
	 * 用户与问题的关联表(userques)
	 * @param qid
	 * @return
	 */
	@Delete("delete from userques where qid=#{qid}")
	int delUserquesByQid(@Param("qid") String qid);
	/**
	 * 删除该问题下的所有评论
	 * @param eid
	 * @return
	 */
	@Delete("delete from evaluates where eid=#{eid}")
	int delEvaluatesByEid(@Param("eid") String eid);
	/**
	 * 删除问题与评价的关联表(queseval)
	 * @param eid
	 * @return
	 */
	@Delete("delete from queseval where eid=#{eid}")
	int delQuesevalByEid(@Param("eid") String eid);

}
