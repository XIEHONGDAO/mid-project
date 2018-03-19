package com.xyd.bean;

/**
 * 书的 javaBean
 * @author Administrator
 *
 */
public class Book {

	private Integer bid;  //书的id
	private String bookname;//书名
	private String b_price; //书的价格
	private String image;   //图片
	private long stock;     //库存
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getB_price() {
		return b_price;
	}
	public void setB_price(String b_price) {
		this.b_price = b_price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public long getStock() {
		return stock;
	}
	public void setStock(long stock) {
		this.stock = stock;
	}
	
	public Book() {
		// TODO Auto-generated constructor stub
	}
	
	public Book(Integer bid, String bookname, String b_price, String image, long stock) {
		this.bid = bid;
		this.bookname = bookname;
		this.b_price = b_price;
		this.image = image;
		this.stock = stock;
	}
	@Override
	public String toString() {
		return "Book [bid=" + bid + ", bookname=" + bookname + ", b_price=" + b_price + ", image=" + image + ", stock="
				+ stock + "]";
	}
	
	
}
