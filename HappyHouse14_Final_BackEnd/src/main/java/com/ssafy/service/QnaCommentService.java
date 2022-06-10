package com.ssafy.service;

import java.util.ArrayList;

import com.ssafy.vo.QnaComment;

public interface QnaCommentService {

	ArrayList<QnaComment> commentList(String qnaNo);

	QnaComment commentSelect(String qnaNo, String commentNo);

	void commentInsert(QnaComment qc);

	void commentUpdate(QnaComment qc);

	void commentDelete(String qnaNo, String commentNo);

	void commentDeleteAll(String qnaNo);

}
