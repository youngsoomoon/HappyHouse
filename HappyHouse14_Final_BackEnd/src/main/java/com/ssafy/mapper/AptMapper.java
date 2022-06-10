package com.ssafy.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vo.HouseDeal;
import com.ssafy.vo.HouseInfo;
import com.ssafy.vo.SidoGugunCode;

@Mapper
public interface AptMapper {

	ArrayList<HouseInfo> selectAllApt();
	ArrayList<HouseInfo> selectAllAptByName(String aptName);
	ArrayList<HouseDeal> selectAptDetail(String aptCode);
	ArrayList<SidoGugunCode> getSido();
	ArrayList<SidoGugunCode> getGugun(String sudio);
	ArrayList<HouseInfo> getDong(String gugun);
	ArrayList<HouseInfo> selectAptDong(String dong);

}
