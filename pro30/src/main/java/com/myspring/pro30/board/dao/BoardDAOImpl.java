package com.myspring.pro30.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.pro30.article.vo.ArticleVO;

/**
 * 
 * @author jh-lee99
 *
 */
@Repository
public class BoardDAOImpl implements BoardDAO {
	@Autowired
	private SqlSession sqlSession;
	
	/** 
	 * ... DB에 등록된 모든 글에 대한 정보를 list형태로 가져온다.
	 * @return articleList 관계된 모든 레코드를 읽어 반환한다.
	 */
	@Override
	public List selectAllArticlesList() throws DataAccessException {
		List<ArticleVO> articlesList = articlesList = sqlSession.selectList("mapper.board.selectAllArticlesList");
		return articlesList;
	}

	/**
	 * @param articleMap 추가할 글의 정보를 담고있는 Map
	 * @return 가장 최근에 추가된 글의 번호를 반환한다.
	 */
	@Override
	public int insertNewArticle(Map articleMap) throws DataAccessException {
		int articleNO = selectNewArticleNO();
		articleMap.put("articleNO", articleNO);
		sqlSession.insert("mapper.board.insertNewArticle",articleMap);
		return articleNO;
	}
	
	/**
	 * @return 가장 최근에 추가된 글 번호를 가져오는 sql문
	 * @throws DataAccessException
	 */
	private int selectNewArticleNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.board.selectNewArticleNO");
	}
}
