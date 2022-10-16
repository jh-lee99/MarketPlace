package com.PROJECT_ONE.member.controller;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.PROJECT_ONE.member.service.MemberService;
import com.PROJECT_ONE.member.vo.MemberVO;


@Controller("memberController")
@RequestMapping(value="/member")
public class MemberControllerImpl implements MemberController{
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberVO memberVO;
	
	/*회원가입*/
	@Override
	@RequestMapping(value = "/addMember.do", method = RequestMethod.POST)
	public ResponseEntity addMember(@ModelAttribute("memberVO") MemberVO _memberVO,	//새로운 회원 정보가 들어있는 _memberVO객체
			HttpServletRequest request, HttpServletResponse response)throws Exception{
			response.setContentType("text/html; charset=UTF-8");
			String message = null;
			ResponseEntity resEntity = null;
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add("Content-Type", "text/html; charset=utf-8");	//헤더에 값 추가
			
			try {
			    memberService.addMember(_memberVO);								//회원 정보를 SQL문으로 전달
			    message  = "<script>";
			    message +=" alert('성공적으로 가입이 완료되었습니다.');";
			    message += " location.href='"+request.getContextPath()+"/member/loginForm.do';";	//로그인화면으로 이동
			    message += " </script>";
			    
			}catch(Exception e) {												//만약 회원가입이 정상적으로 이루어지지 않았다면
				message  = "<script>";
				message +=" alert('회원 가입 도중 오류가 발생했습니다.');";
			    message += " location.href='"+request.getContextPath()+"/member/memberForm.do';";	//회원가입화면으로 이동
			    message += " </script>";
				e.printStackTrace();
			}
			resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
			return resEntity;
	}
	
	@Override
	@RequestMapping(value="/overlapped.do" ,method = RequestMethod.POST)			//id 중복 확인
	public ResponseEntity overlapped(@RequestParam("id") String id,HttpServletRequest request, HttpServletResponse response) throws Exception{
		ResponseEntity resEntity = null;
		String result = memberService.overlapped(id);
		resEntity =new ResponseEntity(result, HttpStatus.OK);
		return resEntity;
	}
}
