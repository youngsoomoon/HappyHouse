package com.ssafy.service;

import java.util.ArrayList;

import com.ssafy.vo.HouseDeal;
import com.ssafy.vo.HouseInfo;
import com.ssafy.vo.SidoGugunCode;

public interface AptService {

	ArrayList<HouseInfo> selectAllApt();
	ArrayList<HouseInfo> selectAllAptByName(String aptName);
	ArrayList<HouseDeal> selectAptDetail(String aptCode);
	ArrayList<SidoGugunCode> getSido();
	ArrayList<SidoGugunCode> getGugun(String sido);
	ArrayList<HouseInfo> getDong(String gugun);
	ArrayList<HouseInfo> selectAptDong(String dong);

}
