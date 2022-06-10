package com.ssafy.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vo.QnaComment;

@Mapper
public interface QnaCommentMapper {

	ArrayList<QnaComment> commentList(String qnaNo);

	QnaComment commentSelect(String qnaNo, String commentNo);

	void commentInsert(QnaComment qc);

	void commentUpdate(QnaComment qc);

	void commentDelete(String qnaNo, String commentNo);

	void commentDeleteAll(String qnaNo);

}
