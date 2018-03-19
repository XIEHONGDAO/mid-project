package com.cxb.pojo;

public class Book {
	private Integer bid;
	private String bname;
	private double bprice;
	private String images;
	private Integer stock;
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public double getBprice() {
		return bprice;
	}
	public void setBprice(double bprice) {
		this.bprice = bprice;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	public Book() {
		super();
	}
	public Book(Integer bid, String bname, double bprice, String images, Integer stock) {
		super();
		this.bid = bid;
		this.bname = bname;
		this.bprice = bprice;
		this.images = images;
		this.stock = stock;
	}
	@Override
	public String toString() {
		return "Book [bid=" + bid + ", bname=" + bname + ", bprice=" + bprice + ", images=" + images + ", stock="
				+ stock + "]";
	}
	
}
