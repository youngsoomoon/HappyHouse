package com.ssafy.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vo.FreeBoard;
import com.ssafy.vo.Notice;

@Mapper
public interface FreeBoardMapper {
	ArrayList<FreeBoard> freeboardList();
	FreeBoard selectFreeboard(String freeboardNo);
	void updateHit(String freeboardNo);
	void freeboardInsert(FreeBoard f);
	void freeboardUpdate(FreeBoard f);
	void freeboardDelete(String freeboardNo);

}
