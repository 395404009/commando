package com.wl.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.wl.pojo.Questions;
import com.wl.pojo.Userques;

public interface UserquesMapper {
	/**
	 * 查找所有问题
	 * @return
	 */
	ArrayList<Userques> selAllQuestions();

	/**
	 * 查找所有问题
	 * @return
	 */
	@Select("select * from questions")
	ArrayList<Questions> selAllQid();
   /**
    * 插入问题表(questions)
    * @param qid
    * @param question
    * @param answer
    */
	@Insert("insert into questions values(#{qid},#{question},#{answer})")
    int insQuestion(@Param("qid") String qid,@Param("question") String question,@Param("answer") String answer);
   /**
    * 插入用户与问题的关联表(userques)
    * @param userid
    * @param qid
    * @return
    */
	@Insert("insert into userques values(#{userid},#{qid})")
   int insUserques(@Param("userid")String userid,@Param("qid") String qid);
   /**
    * 查找登入用户的所有信息
    * @param userid
    * @return
    */
   ArrayList<Userques> selAllMyQuestion(@Param("userid")String userid);

	
}
