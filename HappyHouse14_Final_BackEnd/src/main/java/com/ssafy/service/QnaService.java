package com.ssafy.service;

import java.util.ArrayList;

import com.ssafy.vo.Qna;

public interface QnaService {

	ArrayList<Qna> qnaList();

	Qna selectQna(String qnaNo);

	void qnaInsert(Qna q);

	void qnaUpdate(Qna q);

	void qnaDelete(String qnaNo);

}
