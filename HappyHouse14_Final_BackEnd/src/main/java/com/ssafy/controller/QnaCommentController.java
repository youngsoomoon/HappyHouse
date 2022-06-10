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

import com.ssafy.service.QnaCommentService;
import com.ssafy.vo.QnaComment;

@RestController
@CrossOrigin("*")
public class QnaCommentController {

	@Autowired
	private QnaCommentService qnaCommentService;

	@GetMapping("/qna/{qnaNo}/comment")
	public ArrayList<QnaComment> noticeList(@PathVariable String qnaNo) {
		return qnaCommentService.commentList(qnaNo);
	}

	@GetMapping("/qna/{qnaNo}/comment/{commentNo}")
	public QnaComment selectQna(@PathVariable String qnaNo, @PathVariable String commentNo) {
		return qnaCommentService.commentSelect(qnaNo, commentNo);
	}

	@PostMapping("/qna/{qnaNo}")
	public void qnaInsert(@RequestBody QnaComment qc) {
		qnaCommentService.commentInsert(qc);
	}

	@PutMapping("/qna/{qnaNo}/comment/{commentNo}")
	public void qnaUpdate(@RequestBody QnaComment qc) {
		qnaCommentService.commentUpdate(qc);
	}

	// 삭제
	@DeleteMapping("/qna/{qnaNo}/comment/{commentNo}")
	public void noticeDelete(@PathVariable String qnaNo, @PathVariable String commentNo) {
		qnaCommentService.commentDelete(qnaNo, commentNo);
	}

	@DeleteMapping("/qna/{qnaNo}/comment")
	public void noticeDeleteAll(@PathVariable String qnaNo) {
		qnaCommentService.commentDeleteAll(qnaNo);
	}

}
