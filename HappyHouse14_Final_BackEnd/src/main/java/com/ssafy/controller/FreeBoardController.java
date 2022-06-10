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

import com.ssafy.service.FreeBoardService;
import com.ssafy.service.QnaService;
import com.ssafy.vo.FreeBoard;
import com.ssafy.vo.Notice;
import com.ssafy.vo.Qna;

@RestController
@CrossOrigin("*")
public class FreeBoardController {

	@Autowired
	private FreeBoardService freeboardService;
	
	@GetMapping("/freeboard")
	public ArrayList<FreeBoard> freeboardList(){
		return freeboardService.freeboardList();
	}
	
	@GetMapping("/freeboard/{freeboardNo}")
	public FreeBoard selectFreeboard(@PathVariable String freeboardNo){
		return freeboardService.selectFreeboard(freeboardNo);
	}

	@PostMapping("/freeboard")
	public void freeboardInsert(@RequestBody FreeBoard f) {
		freeboardService.freeboardInsert(f);
	}

	@PutMapping("/freeboard/{freeboardNo}")
	public void freeboardUpdate(@RequestBody FreeBoard f, @PathVariable String freeboardNo) {
		f.setFreeboardNo(freeboardNo);
		freeboardService.freeboardUpdate(f);
	}

	// 삭제
	@DeleteMapping("/freeboard/{freeboardNo}")
	public void freeboardDelete(@PathVariable String freeboardNo) {
		freeboardService.freeboardDelete(freeboardNo);
	}
	
	
}
