package com.PROJECT_ONE.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import com.PROJECT_ONE.member.vo.MemberVO;

public interface MemberController{

	ResponseEntity addMember(MemberVO _memberVO, HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	ResponseEntity overlapped(String id, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}