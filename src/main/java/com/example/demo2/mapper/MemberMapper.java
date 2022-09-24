package com.example.demo2.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo2.dto.MemberDTO;
@Mapper
public interface MemberMapper {
	List<MemberDTO> selectMemberList();
	MemberDTO selectMemberDetail(@Param("paramId") String id); // paramId랑 쿼리문에 있는거랑 매치시켜야됨!!
	void insertMember(MemberDTO memberDTO);
	void deleteMember(@Param("paramId") String id);
}