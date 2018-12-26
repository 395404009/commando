package com.wl.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.wl.pojo.Questions;
import com.wl.pojo.Userques;

public interface UserquesMapper {
	/**
	 * ������������
	 * @return
	 */
	ArrayList<Userques> selAllQuestions();

	/**
	 * ������������
	 * @return
	 */
	@Select("select * from questions")
	ArrayList<Questions> selAllQid();
   /**
    * ���������(questions)
    * @param qid
    * @param question
    * @param answer
    */
	@Insert("insert into questions values(#{qid},#{question},#{answer})")
    int insQuestion(@Param("qid") String qid,@Param("question") String question,@Param("answer") String answer);
   /**
    * �����û�������Ĺ�����(userques)
    * @param userid
    * @param qid
    * @return
    */
	@Insert("insert into userques values(#{userid},#{qid})")
   int insUserques(@Param("userid")String userid,@Param("qid") String qid);
   /**
    * ���ҵ����û���������Ϣ
    * @param userid
    * @return
    */
   ArrayList<Userques> selAllMyQuestion(@Param("userid")String userid);

	
}
