package com.PROJECT_ONE.member.dao;

import com.PROJECT_ONE.member.vo.MemberVO;

public interface MemberDAO{
	void insertNewMember(MemberVO memverVO) throws Exception;

	String selectOverlappedID(String id);
}