/*
 * @(#)BoardService.java		
 * 
 * 2022-09-28
 * 
 */
package com.myspring.pro30.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myspring.pro30.board.dao.BoardDAO;
import com.myspring.pro30.board.vo.ArticleVO;
import com.myspring.pro30.board.vo.ImageVO;

/**
*
*
* @version          1.00 2022년 09월 28일
* @author           방세현
*/

@Service("boardService")
@Transactional(propagation = Propagation.REQUIRED)
public class BoardServiceImpl  implements BoardService{
	@Autowired
	BoardDAO boardDAO;
	
	public List<ArticleVO> listArticles() throws Exception{
		List<ArticleVO> articlesList =  boardDAO.selectAllArticlesList();
        return articlesList;
	}

	
	//단일 이미지 추가하기
//	@Override
//	public int addNewArticle(Map articleMap) throws Exception{
//		return boardDAO.insertNewArticle(articleMap);
//	}
	

	//다중 이미지 추가하기
	@Override
	public int addNewArticle(Map articleMap) throws Exception{
		int articleNO = boardDAO.insertNewArticle(articleMap);	// 글 정보를 저장한 후 글 번호를 가져옵니다.
		articleMap.put("articleNO", articleNO);					// 글 번호를 acticleMap에 저장
		boardDAO.insertNewImage(articleMap);					// acticleMap을 boardDAO클래스의 insertNewImage메소드로 전달
		return articleNO;
	}

	/*
	//다중 파일 보이기
	@Override
	public Map viewArticle(int articleNO) throws Exception {
		Map articleMap = new HashMap();
		ArticleVO articleVO = boardDAO.selectArticle(articleNO);
		List<ImageVO> imageFileList = boardDAO.selectImageFileList(articleNO);
		articleMap.put("article", articleVO);
		articleMap.put("imageFileList", imageFileList);
		return articleMap;
	}
   */
	
	
	 //단일 파일 보이기
	@Override
	public ArticleVO viewArticle(int articleNO) throws Exception {
		ArticleVO articleVO = boardDAO.selectArticle(articleNO);
		return articleVO;
	}
	
	
	@Override
	public void modArticle(Map articleMap) throws Exception {
		boardDAO.updateArticle(articleMap);
	}
	
	@Override
	public void removeArticle(int articleNO) throws Exception {
		boardDAO.deleteArticle(articleNO);
	}
	

	
}
