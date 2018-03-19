package com.cxb.service.impl;

import java.util.List;

import com.cxb.dao.BookDao;
import com.cxb.dao.OrderDao;
import com.cxb.dao.impl.BookDaoImpl;
import com.cxb.dao.impl.OrderDaoImpl;
import com.cxb.pojo.Book;
import com.cxb.pojo.Item;
import com.cxb.service.OrderService;

public class OrderServiceImpl implements OrderService {
	private OrderDao orderDao = new OrderDaoImpl();
	private BookDao bookDao = new BookDaoImpl();
	@Override
	public int addOrder(String name, List<Item> items,double total_price) {
		return orderDao.addOrder(name, items,total_price);
	}

	@Override
	public List<Item> getItem(String name, Item item,int state) {
		return orderDao.getItem(name, item,state);
	}

	@Override
	public int updateItem(List<Item> items) {
		//遍历
		for(Item item:items) {
			//查询书籍信息
			Book book = bookDao.getBookById(item.getBid());
			//修改库存  库存-购买数量
			book.setStock(book.getStock()-item.getCount());
			bookDao.updateBook(book);
			//修改订单总价，状态，数量
			//订单细单价格
			item.setPrice(book.getBprice()*item.getCount());
			orderDao.updateItem(item);
		}
		//修改总价格
		return orderDao.updateTotalPrice();
	}

}
