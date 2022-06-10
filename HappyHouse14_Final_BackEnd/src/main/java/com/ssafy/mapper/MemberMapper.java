package com.ssafy.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vo.Interest;
import com.ssafy.vo.Member;

@Mapper
public interface MemberMapper {

	ArrayList<Member> memberSelect();
	Member selectOneMember(String id);
	Member login(Member m);
	void memberRegister(Member m);
	void memberUpdate(Member m);
	void memberDelete(String id);
	// 관심 지역
	ArrayList<Interest> interestSelectAll(String id);
	void interestInsert(Interest i);
	void interestDeleteAll(String id);
	void interestDelete(Interest i);

}
