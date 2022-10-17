<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"
  isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function fn_order_all_cart_goods() {
		var order_goods_qty;
		var order_goods_id;
		var objForm = document.frm_order_all_cart;
		var cart_goods_qty = objForm.cart_goods_qty;
		var h_order_each_goods_qty = objForm.h_order_each_goods_qty;
		var checked_goods = objForm.checked_goods;
		var length = checked_goods.length;

		if (length > 1) {
			for (var i = 0; i < length; i++) {
				if (checked_goods[i].checked == true) {
					order_goods_id = checked_goods[i].value;
					order_goods_qty = cart_goods_qty[i].value;
					cart_goods_qty[i].value = "";
					cart_goods_qty[i].value = order_goods_id + ":"
							+ order_goods_qty;
					console.log(cart_goods_qty[i].value);
				}
			}
		} else {
			order_goods_id = checked_goods.value;
			order_goods_qty = cart_goods_qty.value;
			cart_goods_qty.value = order_goods_id + ":" + order_goods_qty;
		}

		objForm.method = "post";
		objForm.action = "${contextPath}/order/orderAllCartGoods.do";
		objForm.submit();
	}
</script>
</head>
<body>

</body>
</html>