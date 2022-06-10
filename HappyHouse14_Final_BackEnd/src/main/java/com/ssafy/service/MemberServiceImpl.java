package com.ssafy.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mapper.MemberMapper;
import com.ssafy.vo.Interest;
import com.ssafy.vo.Member;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	MemberMapper memberMapper;
	
	@Override
	public ArrayList<Member> memberSelect() {
		return memberMapper.memberSelect();
	}
	
	@Override
	public Member selectOneMember(String id) {
		return memberMapper.selectOneMember(id);
	}
	
	@Override
	public Member login(String id, String pwd) {
		Member m = new Member(id,pwd,"","","");
		return memberMapper.login(m);
	}

	@Override
	public void memberRegister(Member m) {
		memberMapper.memberRegister(m);
	}

	@Override
	public void memberUpdate(Member m) {
		memberMapper.memberUpdate(m);
	}

	@Override
	public void memberDelete(String id) {
		memberMapper.memberDelete(id);
	}

	@Override
	public ArrayList<Interest> interestSelectAll(String id) {
		return memberMapper.interestSelectAll(id);
	}

	@Override
	public void interestInsert(Interest i) {
		memberMapper.interestInsert(i);
	}

	@Override
	public void interestDeleteAll(String id) {
		memberMapper.interestDeleteAll(id);
	}

	@Override
	public void interestDelete(Interest i) {
		memberMapper.interestDelete(i);
	}



	
}

