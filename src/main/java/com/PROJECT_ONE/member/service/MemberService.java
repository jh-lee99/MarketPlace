package com.PROJECT_ONE.member.service;

import com.PROJECT_ONE.member.vo.MemberVO;


public interface MemberService {

	void addMember(MemberVO memberVO) throws Exception;

	String overlapped(String id) throws Exception;


}
