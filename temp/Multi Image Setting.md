여러 이미지를 저장하는 기능 수행시 기존 파일에서 수정할것
===========================


BoardServiceImpl.java
----------------------

기존의 addNewArticle 주석처리후

	@Override
	public int addNewArticle(Map articleMap) throws Exception{
		int articleNO = boardDAO.insertNewArticle(articleMap);
		articleMap.put("articleNO", articleNO);
		boardDAO.insertNewImage(articleMap);
		return articleNO;
	}
  추가


BoardDAOImpl.java
-----------------

	@Override
	public void insertNewImage(Map articleMap) throws DataAccessException {
		List<ImageVO> imageFileList = (ArrayList)articleMap.get("imageFileList");
		int articleNO = (Integer)articleMap.get("articleNO");
		int imageFileNO = selectNewImageFileNO();
		for(ImageVO imageVO : imageFileList){
			imageVO.setImageFileNO(++imageFileNO);
			imageVO.setArticleNO(articleNO);
		}
		sqlSession.insert("mapper.board.insertNewImage",imageFileList);
	}
추가

BoardDAO.java
--------------------

public void insertNewImage(Map articleMap) throws DataAccessException;	추가
vo에 ImageVO.java 추가

board.xml
-------------------------------

board.xml에 insert id = "insertNewArticle" 쿼리문 주석 처리 후

<insert id="insertNewArticle"  parameterType="java.util.Map">
    <![CDATA[
      insert into t_board(articleNO, id, title, content, imageFileName)
      values(#{articleNO}, #{id}, #{title}, #{content}, null)
    ]]>      
  </insert>

 
   <insert id="insertNewImage"  parameterType="java.util.Map">
    <foreach item="item"  collection="list"   open="INSERT ALL" 
                  separator=" " close="SELECT * FROM DUAL" >
      INTO t_imageFile(imageFileNO, imageFileName, articleNO, regDate)
      VALUES (#{item.imageFileNO}, #{item.imageFileName}, #{item.articleNO}, sysdate)
    </foreach>
  </insert>
추가

view/board/articleForm.jsp
------------------------------
이미지파일 첨부 태그를
	  <td align="right">이미지파일 첨부</td>
		<td align="left"> <input type="button" value="파일 추가" onClick="fn_addFile()"/></td>로 수정
