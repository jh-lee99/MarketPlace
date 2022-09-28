package com.myspring.pro30.board.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myspring.pro30.board.vo.ArticleVO;

/**
 * 
 * @version 1.0.0 2022년 9월 28일
 * @author 김민석
 *
 */
@Repository("boardDAO")
public class BoardDAOImpl implements BoardDAO {
	@Autowired
	// SQL 구문 실행을 위한 SqlSession 객체 선언
	private SqlSession sqlSession;

	// 모든 글을 List 형식으로 불러옴
	@Override
	public List selectAllArticlesList() throws Exception {
		List<ArticleVO> articlesList = sqlSession.selectList("mapper.board.selectAllArticlesList");
		return articlesList;
	}

	// articleNO에 해당하는 번호의 글을 불러옴
	@Override
	public ArticleVO selectArticle(int articleNO) throws DataAccessException {
		return sqlSession.selectOne("mapper.board.selectArticle", articleNO);
	}
}
