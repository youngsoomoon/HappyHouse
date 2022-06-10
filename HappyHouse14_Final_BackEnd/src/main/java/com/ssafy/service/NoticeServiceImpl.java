package com.ssafy.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mapper.NoticeMapper;
import com.ssafy.vo.Notice;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeMapper noticeMapper;

	@Override
	public ArrayList<Notice> noticeList() {
		return noticeMapper.noticeList();
	}

	@Override
	public Notice selectNotice(String noticeNo) {
		noticeMapper.updateHit(noticeNo);
		return noticeMapper.selectNotice(noticeNo);
	}

	@Override
	public void noticeInsert(Notice n) {
		noticeMapper.noticeInsert(n);
	}

	@Override
	public void noticeUpdate(Notice n) {
		noticeMapper.noticeUpdate(n);
	}

	@Override
	public void noticeDelete(String noticeNo) {
		noticeMapper.noticeDelete(noticeNo);
	}

}
