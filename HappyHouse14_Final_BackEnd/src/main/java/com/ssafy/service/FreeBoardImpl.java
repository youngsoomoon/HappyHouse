package com.ssafy.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mapper.FreeBoardMapper;
import com.ssafy.mapper.QnaCommentMapper;
import com.ssafy.vo.FreeBoard;
import com.ssafy.vo.Qna;

@Service
public class FreeBoardImpl implements FreeBoardService {

	@Autowired
	private FreeBoardMapper freeboardMapper;

	@Override
	public ArrayList<FreeBoard> freeboardList() {
		return freeboardMapper.freeboardList();
	}

	@Override
	public FreeBoard selectFreeboard(String freeboardNo) {
		freeboardMapper.updateHit(freeboardNo);
		return freeboardMapper.selectFreeboard(freeboardNo);
	}

	@Override
	public void freeboardInsert(FreeBoard f) {
		freeboardMapper.freeboardInsert(f);
	}

	@Override
	public void freeboardUpdate(FreeBoard f) {
		freeboardMapper.freeboardUpdate(f);
	}

	@Override
	public void freeboardDelete(String freeboardNo) {
		freeboardMapper.freeboardDelete(freeboardNo);
	}

}
