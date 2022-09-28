package com.myspring.pro30.board.dao;

import java.util.List;
import java.util.Map;

import com.myspring.pro30.article.vo.ArticleVO;

public interface BoardDAO {

	public int insertNewArticle(Map articleMap);

	public List<ArticleVO> selectAllArticlesList();

}
