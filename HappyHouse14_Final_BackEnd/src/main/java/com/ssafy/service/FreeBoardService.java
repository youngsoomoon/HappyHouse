package com.ssafy.service;

import java.util.ArrayList;

import com.ssafy.vo.FreeBoard;

public interface FreeBoardService {

	ArrayList<FreeBoard> freeboardList();

	FreeBoard selectFreeboard(String freeboardNo);

	void freeboardInsert(FreeBoard f);

	void freeboardUpdate(FreeBoard f);

	void freeboardDelete(String freeboardNo);

}
