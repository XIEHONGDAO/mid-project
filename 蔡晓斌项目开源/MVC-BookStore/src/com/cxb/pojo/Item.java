package com.cxb.pojo;

import java.util.Date;

public class Item {
	private long iid;
	private long oid;
	private int bid;
	private Date createDate;
	private int count;
	private double price;
	private int state;
	private double total_price;
	private Book book;
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public long getIid() {
		return iid;
	}
	public void setIid(long iid) {
		this.iid = iid;
	}
	public long getOid() {
		return oid;
	}
	public void setOid(long oid) {
		this.oid = oid;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public double getTotal_price() {
		return total_price;
	}
	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}
	
	public Item(long iid, int bid, int count, double total_price) {
		super();
		this.iid = iid;
		this.bid = bid;
		this.count = count;
		this.total_price = total_price;
	}
	public Item(long iid, long oid, int bid, Date createDate, int count, double price, int state, double total_price,
			Book book) {
		super();
		this.iid = iid;
		this.oid = oid;
		this.bid = bid;
		this.createDate = createDate;
		this.count = count;
		this.price = price;
		this.state = state;
		this.total_price = total_price;
		this.book = book;
	}
	public Item(int bid, int count, double price, double total_price, Book book) {
		super();
		this.bid = bid;
		this.count = count;
		this.price = price;
		this.total_price = total_price;
		this.book = book;
	}
	public Item() {
		super();
	}
	@Override
	public String toString() {
		return "Item [iid=" + iid + ", oid=" + oid + ", bid=" + bid + ", createDate=" + createDate + ", count=" + count
				+ ", price=" + price + ", state=" + state + ", total_price=" + total_price + ", book=" + book + "]";
	}
	
	
}
