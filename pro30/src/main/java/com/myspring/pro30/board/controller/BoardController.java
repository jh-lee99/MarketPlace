package com.myspring.pro30.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @version 1.0.0 2022년 9월 28일
 * @author 김민석
 *
 */
public interface BoardController {
	/**
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView listArticles(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
