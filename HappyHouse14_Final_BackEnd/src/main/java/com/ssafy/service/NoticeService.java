package com.ssafy.service;

import java.util.ArrayList;

import com.ssafy.vo.Notice;

public interface NoticeService {

	ArrayList<Notice> noticeList();
	Notice selectNotice(String noticeNo);
	void noticeInsert(Notice n);
	void noticeUpdate(Notice n);
	void noticeDelete(String noticeNo);

}
