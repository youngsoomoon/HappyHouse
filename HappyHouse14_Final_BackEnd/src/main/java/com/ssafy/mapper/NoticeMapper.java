package com.ssafy.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vo.Notice;

@Mapper
public interface NoticeMapper {

	ArrayList<Notice> noticeList();
	Notice selectNotice(String noticeNo);
	void noticeInsert(Notice n);
	void noticeUpdate(Notice n);
	void noticeDelete(String noticeNo);
	void updateHit(String noticeNo);

}
