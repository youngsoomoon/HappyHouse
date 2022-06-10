package com.ssafy.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.service.AptService;
import com.ssafy.vo.HouseDeal;
import com.ssafy.vo.HouseInfo;
import com.ssafy.vo.SidoGugunCode;

@RestController
@CrossOrigin("*")
public class AptController {

	@Autowired
	private AptService aptService;

	// 아파트 매매가 관련
	@GetMapping("/aptList")
	public ArrayList<HouseInfo> selectAllApt() {
		return aptService.selectAllApt();
	}

	@GetMapping("/aptList/detail/{aptCode}")
	public ArrayList<HouseDeal> selectAptDetail(@PathVariable String aptCode) {
		return aptService.selectAptDetail(aptCode);
	}

	@GetMapping("/sido")
	public ArrayList<SidoGugunCode> sido() {
		return aptService.getSido();
	}

	@GetMapping("/gugun")
	public ArrayList<SidoGugunCode> gugun(@RequestParam("sido") String sido) {
		return aptService.getGugun(sido);
	}

	@GetMapping("/dong")
	public ArrayList<HouseInfo> dong(@RequestParam("gugun") String gugun) {
		return aptService.getDong(gugun);
	}

	@GetMapping("/apt")
	public ArrayList<HouseInfo> selectAptDong(@RequestParam("dong") String dong) {
		return aptService.selectAptDong(dong);
	}

	// 아파트 이름 검색
	@GetMapping("/apt/{aptName}")
	public ArrayList<HouseInfo> selectAllAptByName(@PathVariable String aptName) {
		return aptService.selectAllAptByName(aptName);
	}

}
