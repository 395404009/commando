package com.wl.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.wl.pojo.Queseval;

public interface QuesevalMapper {

	ArrayList<Queseval> selCommentByQid(@Param("qid") String qid);

}
