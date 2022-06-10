package com.ssafy.service;

import java.util.ArrayList;

import com.ssafy.vo.Interest;
import com.ssafy.vo.Member;

public interface MemberService {

	ArrayList<Member> memberSelect();
	Member selectOneMember(String id);
	Member login(String id, String pwd);
	void memberRegister(Member m);
	void memberUpdate(Member m);
	void memberDelete(String id);
	//관심지역
	ArrayList<Interest> interestSelectAll(String id);
	void interestInsert(Interest i);
	void interestDeleteAll(String id);
	void interestDelete(Interest i);

}
