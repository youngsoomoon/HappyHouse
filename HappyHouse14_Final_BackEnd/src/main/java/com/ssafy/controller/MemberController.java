package com.ssafy.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.service.MemberService;
import com.ssafy.vo.Interest;
import com.ssafy.vo.Member;

@RestController
@CrossOrigin("*")
public class MemberController {

	@Autowired
	private MemberService memberService;

	// 회원정보 관련

	@GetMapping("/member")
	public ArrayList<Member> select() {
		return memberService.memberSelect();
	}

	@GetMapping("/member/{id}")
	public Member selectOneMember(@PathVariable String id) {
		return memberService.selectOneMember(id);
	}

	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody Member m) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		Member loginUser = memberService.login(m.getId(), m.getPwd());
		if (loginUser != null) {
			resultMap.put("loginUser", loginUser);
			resultMap.put("message", "SUCCESS");
			status = HttpStatus.ACCEPTED;
		} else {
			resultMap.put("message", "FAIL");
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/logout/{id}")
	public void logout(@PathVariable String id, HttpSession session) {
		session.removeAttribute("id");
	}

	@PostMapping("/register")
	public void register(@RequestBody Member m) {
		memberService.memberRegister(m);
	}

	@PutMapping("/memberUpdate/{id}")
	public void memberUpdate(@PathVariable String id, @RequestBody Member m) {
		m.setId(id);
		memberService.memberUpdate(m);
	}

	@DeleteMapping("/memberDelete/{id}")
	public void memberDelete(@PathVariable String id) {
		memberService.memberDelete(id);
	}

	@GetMapping("/member/{id}/interest")
	public ArrayList<Interest> interestSelectAll(@PathVariable String id) {
		return memberService.interestSelectAll(id);
	}

	@PostMapping("/member/{id}/interest")
	public void interestInsert(@PathVariable String id, @RequestBody Interest i) {
		memberService.interestInsert(i);
	}

	@DeleteMapping("/member/{id}/interest")
	public void interestDeleteAll(@PathVariable String id) {
		memberService.interestDeleteAll(id);
	}

	@DeleteMapping("/member/{id}/interest/{dongCode}")
	public void interestDelete(@PathVariable String id, @PathVariable String dongCode) {
		Interest i = new Interest();
		i.setId(id);
		i.setDongCode(dongCode);
		memberService.interestDelete(i);
	}

}
