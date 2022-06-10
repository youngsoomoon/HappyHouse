package com.ssafy.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vo.Qna;

@Mapper
public interface QnaMapper {

	ArrayList<Qna> qnaList();

	Qna selectQna(String qnaNo);

	void qnaInsert(Qna q);

	void qnaUpdate(Qna q);

	void updateHit(String qnaNo);

	void qnaDelete(String qnaNo);

}
