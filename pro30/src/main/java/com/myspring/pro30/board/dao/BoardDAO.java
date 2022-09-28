package com.myspring.pro30.board.dao;

import java.util.List;

import com.myspring.pro30.board.vo.ArticleVO;

/**
 * 
 * @version 1.0.0 2022년 9월 28일
 * @author 김민석
 *
 */
public interface BoardDAO {

	/**
	 * 
	 * @return List
	 * @throws Exception
	 */
	List selectAllArticlesList() throws Exception;

	/**
	 * 
	 * @param articleNO 글 번호
	 * @return ArticleVO
	 * @throws DataAccessException
	 */
	ArticleVO selectArticle(int articleNO) throws DataAccessException;

}
