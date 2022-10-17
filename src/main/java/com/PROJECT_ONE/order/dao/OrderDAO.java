package com.PROJECT_ONE.order.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.PROJECT_ONE.order.vo.OrderVO;

public interface OrderDAO {
	public List<OrderVO> listMyOrderGoods(OrderVO orderBean) throws DataAccessException;

	public void insertNewOrder(List<OrderVO> myOrderList) throws DataAccessException;

	public OrderVO findMyOrder(String order_id) throws DataAccessException;

	public void removeGoodsFromCart(List<OrderVO> myOrderList) throws DataAccessException;
}
