package com.ssafy.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mapper.QnaMapper;
import com.ssafy.vo.Qna;

@Service
public class QnaServiceImpl implements QnaService {

	@Autowired
	private QnaMapper qnaMapper;

	@Override
	public ArrayList<Qna> qnaList() {
		return qnaMapper.qnaList();
	}

	@Override
	public Qna selectQna(String qnaNo) {
		qnaMapper.updateHit(qnaNo);
		return qnaMapper.selectQna(qnaNo);
	}

	@Override
	public void qnaInsert(Qna q) {
		qnaMapper.qnaInsert(q);
	}

	@Override
	public void qnaUpdate(Qna q) {
		qnaMapper.qnaUpdate(q);
	}

	@Override
	public void qnaDelete(String qnaNo) {
		qnaMapper.qnaDelete(qnaNo);
	}

}
