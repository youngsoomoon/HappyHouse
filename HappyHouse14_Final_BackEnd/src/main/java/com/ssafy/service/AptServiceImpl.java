package com.ssafy.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mapper.AptMapper;
import com.ssafy.vo.HouseDeal;
import com.ssafy.vo.HouseInfo;
import com.ssafy.vo.SidoGugunCode;

@Service
public class AptServiceImpl implements AptService{

	@Autowired
	AptMapper aptMapper;
	
	@Override
	public ArrayList<HouseInfo> selectAllApt() {
		return aptMapper.selectAllApt();
	}

	@Override
	public ArrayList<HouseInfo> selectAllAptByName(String aptName) {
		return aptMapper.selectAllAptByName(aptName);
	}
	
	@Override
	public ArrayList<HouseDeal> selectAptDetail(String aptCode) {
		return aptMapper.selectAptDetail(aptCode);
	}
	
	@Override
	public ArrayList<SidoGugunCode> getSido() {
		return aptMapper.getSido();
	}
	
	@Override
	public ArrayList<SidoGugunCode> getGugun(String sido) {
		return aptMapper.getGugun(sido);
	}
	
	@Override
	public ArrayList<HouseInfo> getDong(String gugun) {
		return aptMapper.getDong(gugun);
	}
	
	@Override
	public ArrayList<HouseInfo> selectAptDong(String dong) {
		return aptMapper.selectAptDong(dong);
	}

}
