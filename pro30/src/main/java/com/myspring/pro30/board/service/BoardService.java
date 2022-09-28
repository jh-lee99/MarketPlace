package com.myspring.pro30.board.service;

import java.util.List;
import java.util.Map;

public interface BoardService {

	public int addNewArticle(Map<String, Object> articleMap) throws Exception;

	public List listArticles() throws Exception;

}
