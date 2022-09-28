package com.myspring.pro30.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myspring.pro30.article.vo.ArticleVO;
import com.myspring.pro30.board.dao.BoardDAO;

@Service("boardService")
@Transactional(propagation = Propagation.REQUIRED)
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDAO boardDAO;

	/** 
	 * ... DB에 저장된 모든 board 레코드를 가져온다.
	 */
	public List<ArticleVO> listArticles() throws Exception{
		List<ArticleVO> articlesList =  boardDAO.selectAllArticlesList();
        return articlesList;
	}
	
	/**
	 *  ... 매개변수로 map을 받아 DB에 저장한다.
	 * @param articleMap 저장될 레코드의 정보를 담고 있는 map
	 * @return 가장 최근에 추가된 글 번호 반환
	 */
	@Override
	public int addNewArticle(Map articleMap) throws Exception{
		return boardDAO.insertNewArticle(articleMap);
	}
}
