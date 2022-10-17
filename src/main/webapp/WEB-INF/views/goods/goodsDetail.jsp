<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"
  isELIgnored="false"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="goods" value="${goodsMap.goodsVO}" />
<c:set var="imageList" value="${goodsMap.imageList }" />
<%
//pageContext.setAttribute("crcn", "\r\n"); //개행문자
pageContext.setAttribute("crcn", "\n"); //Ajax로 변경 시 개행 문자 
pageContext.setAttribute("br", "<br/>"); //br 태그
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function fn_order_each_goods(goods_id, goods_title, goods_sales_price, fileName) {
		var _isLogOn = document.getElementById("isLogOn")
		var isLogOn = _isLogOn.value;
		
		if(isLogOn == "false" || isLogOn == '') {
			alert("로그인 후 주문이 가능합니다.")
		}
		var total_price, final_total_price;
		var order_goods_qty = document.getElementById("order_goods_qty");
		
		var formObj = document.createElement("form");
		
		var i_goods_id = document.createElement("input");
		var i_goods_title = document.createElement("input");
		var i_goods_sales_price = document.createElement("input")
		var i_fileName = document.createElement("input")
		var i_order_goods_qty = document.createElement("input");

		i_goods_id.name = "goods_id";
		i_goods_title.name = "goods_title";
		i_goods_sales_price.name = "goods_sales_price";
		i_fileName.name = "goods_fileName";
		i_order_goods_qty.name = "order_goods_qty";

		i_goods_id.value = goods_id;
		i_order_goods_qty.value = order_goods_qty.value;
		i_goods_title.value = goods_title;
		i_goods_sales_price.value = goods_sales_price;
		i_fileName.value = fileName;

		formObj.appendChild(i_goods_id);
		formObj.appendChild(i_goods_title);
		formObj.appendChild(i_goods_sales_price);
		formObj.appendChild(i_fileName);
		formObj.appendChild(i_order_goods_qty);

		document.body.appendChild(fromObj);
		formObj.method = "post";

		formObj.action = "${contextPath}/order/orderEachGoods.do";
		formObj.submit();
	}
</script>
</head>
<body>
	<div id="detail_table">
		<table>
			<tbody>
				<tr>
					<td class="fixed">수량</td>
					<td class="fixed">
						<select style="width: 60px;" id="order_goods_qty">
							<option>1</option>
							<option>2</option>
							<option>3</option>
							<option>4</option>
							<option>5</option>
						</select>
					</td>
				</tr>
			</tbody>
		</table>
		
		<ul>
			<li><a class="buy" href="javascript:fn_order_each_goods('${goods.goods_id}', '${goods.goods_title}', '${goods.goods_sales_price}', '${goods.goods_fileName}');">
				구매하기</a></li>
			<li><a class="cart" href="javascript:add_cart('${goods.goods_id}')">장바구니</a></li>
			<li><a class="wish" href="#">위시리스트</a></li>
		</ul>
	
</body>
</html>
<input type="hidden" name="isLogOn" id="isLogOn" value="${isLogOn}" />