package com.ssafy.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mapper.QnaCommentMapper;
import com.ssafy.vo.QnaComment;

@Service
public class QnaCommentServiceImpl implements QnaCommentService {

	@Autowired
	private QnaCommentMapper qnaCommentMapper;

	@Override
	public ArrayList<QnaComment> commentList(String qnaNo) {
		return qnaCommentMapper.commentList(qnaNo);
	};

	@Override
	public QnaComment commentSelect(String qnaNo, String commentNo) {
		return qnaCommentMapper.commentSelect(qnaNo, commentNo);
	};

	@Override
	public void commentInsert(QnaComment qc) {
		qnaCommentMapper.commentInsert(qc);
	};

	@Override
	public void commentUpdate(QnaComment qc) {
		qnaCommentMapper.commentUpdate(qc);
	};

	@Override
	public void commentDelete(String qnaNo, String commentNo) {
		qnaCommentMapper.commentDelete(qnaNo, commentNo);
	};

	@Override
	public void commentDeleteAll(String qnaNo) {
		qnaCommentMapper.commentDeleteAll(qnaNo);
	};

}
