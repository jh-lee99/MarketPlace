/*
 * @(#)BoardControllerImpl.java	
 */
package com.myspring.pro30.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.pro30.board.service.BoardService;
import com.myspring.pro30.board.vo.ArticleVO;

/**
 * 
 * @version 1.0.0 2022년 9월 28일
 * @author 김민석,방세현
 *
 */
@Controller("boardController")
public class BoardControllerImpl implements BoardController {
	// 이미지가 저장되어 있는 파일 경로
	private static final String CURR_IMAGE_REPO_PATH = "C:\\board\\article_image";

	@Autowired
	/***/
	private BoardService boardService;

	@Autowired
	// 글 객체
	private ArticleVO articleVO;

	/**
	 * 
	 */
	@Override
	@RequestMapping(value = "/board/listArticles.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView listArticles(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		List articlesList = boardService.listArticles();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("articlesList", articlesList);
		return mav;
	}

	/**
	 * 
	 * @param articleNO 글 번호
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	//한개의 이미지 보여주기
	@RequestMapping(value = "/board/viewArticle.do", method = RequestMethod.GET)
	public ModelAndView viewArticle(@RequestParam("articleNO") int articleNO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		articleVO = boardService.viewArticle(articleNO);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("article", articleVO);
		return mav;
	}
	//한 개 이미지 수정 기능
  @RequestMapping(value="/board/modArticle.do" ,method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity modArticle(MultipartHttpServletRequest multipartRequest,  
    HttpServletResponse response) throws Exception{
    multipartRequest.setCharacterEncoding("utf-8");
	Map<String,Object> articleMap = new HashMap<String, Object>();
	Enumeration enu=multipartRequest.getParameterNames();
	while(enu.hasMoreElements()){
		String name=(String)enu.nextElement();
		String value=multipartRequest.getParameter(name);
		articleMap.put(name,value);
	}
	
	//String imageFileName= upload(multipartRequest);
	String imageFileName= null;
	articleMap.put("imageFileName", imageFileName);
	
	String articleNO=(String)articleMap.get("articleNO");
	String message;
	ResponseEntity resEnt=null;
	HttpHeaders responseHeaders = new HttpHeaders();
	responseHeaders.add("Content-Type", "text/html; charset=utf-8");
    try {
       boardService.modArticle(articleMap);
       if(imageFileName!=null && imageFileName.length()!=0) {
         File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
         File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+articleNO);
         FileUtils.moveFileToDirectory(srcFile, destDir, true);
         
         String originalFileName = (String)articleMap.get("originalFileName");
         File oldFile = new File(ARTICLE_IMAGE_REPO+"\\"+articleNO+"\\"+originalFileName);
         oldFile.delete();
       }	
       message = "<script>";
	   message += " alert('글을 수정했습니다.');";
	   message += " location.href='"+multipartRequest.getContextPath()+"/board/viewArticle.do?articleNO="+articleNO+"';";
	   message +=" </script>";
       resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
    }catch(Exception e) {
      File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
      srcFile.delete();
      message = "<script>";
	  message += " alert('오류가 발생했습니다.다시 수정해주세요');";
	  message += " location.href='"+multipartRequest.getContextPath()+"/board/viewArticle.do?articleNO="+articleNO+"';";
	  message +=" </script>";
      resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
    }
    return resEnt;
  }
  
  @Override
  @RequestMapping(value="/board/removeArticle.do" ,method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity  removeArticle(@RequestParam("articleNO") int articleNO,
                              HttpServletRequest request, HttpServletResponse response) throws Exception{
	response.setContentType("text/html; charset=UTF-8");
	String message;
	ResponseEntity resEnt=null;
	HttpHeaders responseHeaders = new HttpHeaders();
	responseHeaders.add("Content-Type", "text/html; charset=utf-8");
	try {
		boardService.removeArticle(articleNO);
		File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+articleNO);
		FileUtils.deleteDirectory(destDir);
		
		message = "<script>";
		message += " alert('글을 삭제했습니다.');";
		message += " location.href='"+request.getContextPath()+"/board/listArticles.do';";
		message +=" </script>";
	    resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
	       
	}catch(Exception e) {
		message = "<script>";
		message += " alert('작업중 오류가 발생했습니다.다시 시도해 주세요.');";
		message += " location.href='"+request.getContextPath()+"/board/listArticles.do';";
		message +=" </script>";
	    resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
	    e.printStackTrace();
	}
	return resEnt;
  }  
  
  /* 다중 이미지 글 추가하기 */
  @Override
  @RequestMapping(value="/board/addNewArticle.do" ,method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity  addNewArticle(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception {
	multipartRequest.setCharacterEncoding("utf-8");
	String imageFileName=null;
	
	/* 글 정보를 HashMap에 저장 */
	Map articleMap = new HashMap();
	Enumeration enu=multipartRequest.getParameterNames();
	while(enu.hasMoreElements()){
		String name=(String)enu.nextElement();
		String value=multipartRequest.getParameter(name);
		articleMap.put(name,value);
	}
	
	//로그인 시 세션에 저장된 회원 정보에서 글쓴이 아이디를 얻어와서 Map에 저장합니다.
	HttpSession session = multipartRequest.getSession();
	MemberVO memberVO = (MemberVO) session.getAttribute("member");
	String id = memberVO.getId();
	articleMap.put("id",id);
	articleMap.put("parentNO", 0);
	
	List<String> fileList =upload(multipartRequest);		//첨부한 이미지 이름을 fileList로 반환
	List<ImageVO> imageFileList = new ArrayList<ImageVO>();
	
	/* 만일 첨부된 파일이 있으면  ImageVO객체 생성 후 파일 이름을 imageFileName에 저장, ImageVO객체를 List<ImageVO> imageFileList에 저장*/
	if(fileList!= null && fileList.size()!=0) {
		for(String fileName : fileList) {
			ImageVO imageVO = new ImageVO();
			imageVO.setImageFileName(fileName);
			imageFileList.add(imageVO);
		}
		articleMap.put("imageFileList", imageFileList); 		//articleMap에 imageFileList 저장
	}
	String message;
	ResponseEntity resEnt=null;
	HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.add("Content-Type", "text/html; charset=utf-8");
	try {
		int articleNO = boardService.addNewArticle(articleMap);	//boardService클래스의 addNewArticle메소드에 articleMap전달, 이미지가 몇번째 이미지인지 리턴
		if(imageFileList!=null && imageFileList.size()!=0) {
			for(ImageVO  imageVO:imageFileList) {				//articleMap에 있는 이미지들의 경로를 temp/imageFileName에서 articleNo로 변경
				imageFileName = imageVO.getImageFileName();
				File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
				File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+articleNO);
				//destDir.mkdirs();
				FileUtils.moveFileToDirectory(srcFile, destDir,true);
			}
		}
		    
		message = "<script>";
		message += " alert('새글을 추가했습니다.');";
		message += " location.href='"+multipartRequest.getContextPath()+"/board/listArticles.do'; ";
		message +=" </script>";
	    resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
	    
		 
	}catch(Exception e) {										//오류 발생시
		if(imageFileList!=null && imageFileList.size()!=0) {
		  for(ImageVO  imageVO:imageFileList) {					//temp폴더에 있는 이미지를 for문 돌면서 삭제
		  	imageFileName = imageVO.getImageFileName();
			File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
		 	srcFile.delete();
		  }
		}

		
		message = " <script>";
		message +=" alert('오류가 발생했습니다. 다시 시도해 주세요');');";
		message +=" location.href='"+multipartRequest.getContextPath()+"/board/articleForm.do'; ";
		message +=" </script>";
		resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		e.printStackTrace();
	}
	return resEnt;
  }
	


	

	@RequestMapping(value = "/board/*Form.do", method =  RequestMethod.GET)
	private ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
		//다중 이미지 업로드하기
	private List<String> upload(MultipartHttpServletRequest multipartRequest) throws Exception{
		List<String> fileList= new ArrayList<String>();
		Iterator<String> fileNames = multipartRequest.getFileNames();
		while(fileNames.hasNext()){
			String fileName = fileNames.next();
			MultipartFile mFile = multipartRequest.getFile(fileName);
			String originalFileName=mFile.getOriginalFilename();
			fileList.add(originalFileName);																//첨부한 이미지의 이름을 fileList에 저장합니다.
			File file = new File(ARTICLE_IMAGE_REPO +"\\"+"temp"+"\\" + fileName);
			if(mFile.getSize()!=0){ 																	//File Null Check
				if(!file.exists()){ 																	//경로상에 파일이 존재하지 않을 경우
					file.getParentFile().mkdirs();  													//경로에 해당하는 디렉토리들을 생성
					mFile.transferTo(new File(ARTICLE_IMAGE_REPO +"\\"+"temp"+ "\\"+originalFileName)); //임시로 저장된 multipartFile을 실제 파일로 전송
				}
			}
		}
		return fileList;
	}
	}
}
