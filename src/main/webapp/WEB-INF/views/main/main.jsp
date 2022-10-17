<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"	isELIgnored="false"
	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
  request.setCharacterEncoding("UTF-8");
%>  

<div id="ad_main_banner">
	<ul class="bjqs">	 	
	  <li><img width="775" height="145" src="${contextPath}/resources/image/main_banner01.jpg"></li>
		<li><img width="775" height="145" src="${contextPath}/resources/image/main_banner02.jpg"></li>
		<li><img width="775" height="145" src="${contextPath}/resources/image/main_banner03.jpg"></li> 
	</ul>
</div>
<div class="main_book">
   <c:set  var="goods_count" value="0" />
	<h3>베스트셀러</h3>
	<c:forEach var="item" items="${goodsMap.bestseller }"><%--베스트 셀러 표시--%>
	   <c:set  var="goods_count" value="${goods_count+1 }" />
		<div class="book">
			<a href="${contextPath}/goods/goodsDetail.do?goods_id=${item.goods_id }"><%--이미지클릭시 상품 페이지로 이동--%>
			<img class="link"  src="${contextPath}/resources/image/1px.gif"> 
			</a> 
				<img width="121" height="154" <%-- 썸네일 표시 --%>
				     src="${contextPath}/thumbnails.do?goods_id=${item.goods_id}&fileName=${item.goods_fileName}">

			<div class="title">${item.goods_title }</div>
			<div class="price">
		  	   <fmt:formatNumber  value="${item.goods_price}" type="number" var="goods_price" />
		          ${goods_price}원
			</div>
		</div>
	   <c:if test="${goods_count==15   }">
         <div class="book">
           <font size=20> <a href="#">more</a></font>
         </div>
     </c:if>
  </c:forEach>
</div>
<div class="clear"></div>
<div id="ad_sub_banner">
	<img width="770" height="117" src="${contextPath}/resources/image/sub_banner01.jpg">
</div>
<div class="main_book" >
<c:set  var="goods_count" value="0" />
	<h3>새로 출판된 책</h3>
	<c:forEach var="item" items="${goodsMap.newbook }" ><%--신간 표시--%>
	   <c:set  var="goods_count" value="${goods_count+1 }" />
		<div class="book">
		  <a href="${contextPath}/goods/goodsDetail.do?goods_id=${item.goods_id }"><%--이미지클릭시 상품 페이지로 이동--%>
	       <img class="link"  src="${contextPath}/resources/image/1px.gif"> 
	      </a>
		 <img width="121" height="154" 
				src="${contextPath}/thumbnails.do?goods_id=${item.goods_id}&fileName=${item.goods_fileName}">
		<div class="title">${item.goods_title }</div>
		<div class="price">
		    <fmt:formatNumber  value="${item.goods_price}" type="number" var="goods_price" />
		       ${goods_price}원
		  </div>
	</div>
	 <c:if test="${goods_count==15   }">
     <div class="book">
       <font size=20> <a href="#">more</a></font>
     </div>
   </c:if>
	</c:forEach>
</div>

<div class="clear"></div>
<div id="ad_sub_banner">
	<img width="770" height="117" src="${contextPath}/resources/image/sub_banner02.jpg">
</div>


<div class="main_book" >
<c:set  var="goods_count" value="0" />
	<h3>스테디셀러</h3>
	<c:forEach var="item" items="${goodsMap.steadyseller }" ><%-- 스테디 셀러 표시 --%>
	   <c:set  var="goods_count" value="${goods_count+1 }" />
		<div class="book">
		  <a href="${contextPath}/goods/goodsDetail.do?goods_id=${item.goods_id }"><%-- 이미지클릭시 상품 페이지로 이동 --%>
	       <img class="link"  src="${contextPath}/resources/image/1px.gif"> 
	      </a>
		 <img width="121" height="154" 
				src="${contextPath}/thumbnails.do?goods_id=${item.goods_id}&fileName=${item.goods_fileName}">
		<div class="title">${item.goods_title }</div>
		<div class="price">
		    <fmt:formatNumber  value="${item.goods_price}" type="number" var="goods_price" />
		       ${goods_price}원
		  </div>
	</div>
	 <c:if test="${goods_count==15   }"><%--상품 15개 이상이면 more이미지 표시--%>
     <div class="book">
       <font size=20> <a href="#">more</a></font>
     </div>
   </c:if>
	</c:forEach>
</div>

   
   