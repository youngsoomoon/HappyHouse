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

import com.ssafy.service.NoticeService;
import com.ssafy.vo.Notice;

@RestController
@CrossOrigin("*")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@GetMapping("/notice")
	public ArrayList<Notice> noticeList(){
		return noticeService.noticeList();
	}
	
	@GetMapping("/notice/{noticeNo}")
	public Notice selectNotice(@PathVariable String noticeNo){
		return noticeService.selectNotice(noticeNo);
	}

	@PostMapping("/notice")
	public void noticeInsert(@RequestBody Notice n) {
		noticeService.noticeInsert(n);
	}

	@PutMapping("/notice/{noticeNo}")
	public void noticeUpdate(@RequestBody Notice n, @PathVariable String noticeNo) {
		n.setNoticeNo(Integer.parseInt(noticeNo));
		noticeService.noticeUpdate(n);
	}

	// 삭제
	@DeleteMapping("/notice/{noticeNo}")
	public void noticeDelete(@PathVariable String noticeNo) {
		noticeService.noticeDelete(noticeNo);
	}
	
	
}
