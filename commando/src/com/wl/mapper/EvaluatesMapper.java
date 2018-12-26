package com.wl.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.wl.pojo.Evaluates;

public interface EvaluatesMapper {
	/**
	 * 查找所有评价
	 * @return
	 */
	@Select("select * from evaluates")
	ArrayList<Evaluates> selAllEval();
    /**
     * 插入到评价表(evaluates)中
     * @param eid
     * @param context
     * @param time
     * @return
     */
	@Insert("insert into evaluates values(#{eid},#{context},#{time})")
	int insByEidAndContext(@Param("eid") String eid,@Param("context") String context,@Param("time") String time);
	/**
	 * 插入到问题与评价的关联表(queseval)
	 * @param eid
	 * @param qid
	 * @return
	 */
	@Insert("insert into queseval values(#{qid},#{eid})")
	int insByQidAndEid(@Param("eid") String eid,@Param("qid") String qid);
	/**
	 * 插入到用户与评价的关联表(usereval)
	 * @param userid
	 * @param eid
	 * @return
	 */
	@Insert("insert into usereval values(#{userid},#{eid})")
	int insByUseridAndEid(@Param("userid") String userid,@Param("eid") String eid);
}
