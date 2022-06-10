package com.ssafy.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.service.QnaService;
import com.ssafy.vo.Qna;

@RestController
@CrossOrigin("*")
public class QnaController {

	@Autowired
	private QnaService qnaService;

	@GetMapping("/qna")
	public ArrayList<Qna> noticeList() {
		return qnaService.qnaList();
	}

	@GetMapping("/qna/{qnaNo}")
	public Qna selectQna(@PathVariable String qnaNo) {
		return qnaService.selectQna(qnaNo);
	}

	@PostMapping("/qna")
	public void qnaInsert(@RequestBody Qna q) {
		qnaService.qnaInsert(q);
	}

	@PutMapping("/qna/{qnaNo}")
	public void qnaUpdate(@RequestBody Qna q, @PathVariable String qnaNo) {
		q.setQnaNo(Integer.parseInt(qnaNo));
		qnaService.qnaUpdate(q);
	}

	// 삭제
	@DeleteMapping("/qna/{qnaNo}")
	public void noticeDelete(@PathVariable String qnaNo) {
		qnaService.qnaDelete(qnaNo);
	}

}
