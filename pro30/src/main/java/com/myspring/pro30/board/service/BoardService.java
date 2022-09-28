package com.myspring.pro30.board.service;

import java.util.List;

import com.myspring.pro30.board.vo.ArticleVO;

/**
 * 
 * @version 1.0.0 2022년 9월 28일
 * @author 김민석
 *
 */
public interface BoardService {
	/**
	 * 
	 * @return List
	 * @throws Exception
	 */
	public List<ArticleVO> listArticles() throws Exception;

	/**
	 * 
	 * @param articleNO 글 번호
	 * @return ArticleVO
	 * @throws Exception
	 */
	public ArticleVO viewArticle(int articleNO) throws Exception;
}
