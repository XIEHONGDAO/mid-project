package com.cxb.service;

import java.util.List;

import com.cxb.pojo.Item;

public interface OrderService {
	/**
	 * 创建订单
	 * @param items
	 * @return
	 */
	int addOrder(String name,List<Item> items,double total_price);
	/**
	 * 查询订单
	 * @param name 用户名
	 * @param item 订单信息
	 * @return
	 */
	List<Item> getItem(String name,Item item,int state);
	
	/**
	 * 修改订单列表
	 * @param items
	 * @return
	 */
	int updateItem(List<Item> items);
}
