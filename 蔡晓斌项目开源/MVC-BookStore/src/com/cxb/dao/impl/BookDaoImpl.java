package com.cxb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cxb.dao.BookDao;
import com.cxb.pojo.Book;
import com.cxb.utils.DBUtils;

public class BookDaoImpl implements BookDao {
	private Connection conn = null;
	private PreparedStatement pstat = null;
	private ResultSet rs = null;
	@Override
	public int updateBook(Book book) {
		try {
			//获取数据库连接
			conn = DBUtils.getConn();
			//准备SQL语句
			String sql = "update bs_books set bookname=?,b_price=?,image=?,stock=? where bid=?";
			//把SQL语句传入PrepareStatement预编译
			pstat = conn.prepareStatement(sql);
			//填充占位符
			pstat.setString(1, book.getBname());
			pstat.setDouble(2, book.getBprice());
			pstat.setString(3, book.getImages());
			pstat.setInt(4, book.getStock());
			pstat.setInt(5, book.getBid());
			System.out.println(sql);
			//执行SQL语句
			int i = pstat.executeUpdate();
			return i;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeResource(conn, pstat, null);
		}
		return 0;
	}

	@Override
	public List<Book> getBooksByBookName(String bname,int min,int max) {
		List<Book> list = new ArrayList<Book>();
		try {
			conn = DBUtils.getConn();
			String sql = "select t.* from (select b.*,rownum rw from bs_books b where b.bookname  like ? " + 
					"and rownum<=?) t where rw>=?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, "%"+bname+"%");
			pstat.setInt(2, max);
			pstat.setInt(3, min);
			System.out.println(sql);
			rs = pstat.executeQuery();
			while(rs.next()) {
				Book book = new Book();
				book.setBid(rs.getInt("bid"));
				book.setBname(rs.getString("bookname"));
				book.setBprice(rs.getDouble("b_price"));
				book.setImages(rs.getString("image"));
				book.setStock(rs.getInt("stock"));
				list.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeResource(conn, pstat, rs);
		}
		return list;
	}

	@Override
	public List<Book> getBooks(int min, int max) {
		List<Book> list = new ArrayList<Book>();
		try {
			conn = DBUtils.getConn();
			String sql = "select bs.* from (select t.*,rownum rw from bs_books t" + 
					"       where rownum<=?) bs where rw>=?";
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, max);
			pstat.setInt(2, min);
			System.out.println(sql);
			rs = pstat.executeQuery();
			while(rs.next()) {
				Book book = new Book();
				book.setBid(rs.getInt("bid"));
				book.setBname(rs.getString("bookname"));
				book.setBprice(rs.getDouble("b_price"));
				book.setImages(rs.getString("image"));
				book.setStock(rs.getInt("stock"));
				list.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeResource(conn, pstat, rs);
		}
		return list;
	}

	@Override
	public int getBooksNumber(String bname) {
		try {
			conn = DBUtils.getConn();
			String sql  ="select count(*) from bs_books where 1=1 ";
			if(bname!=null && !"".equals(bname)) {
				sql+= " and bookname like ?";
			}
			pstat = conn.prepareStatement(sql);
			if(bname!=null && !"".equals(bname)) {
				pstat.setString(1, "%"+bname+"%");
			}
			rs = pstat.executeQuery();
			if(rs.next()) {
				int count = rs.getInt(1);
				return count;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeResource(conn, pstat, rs);
		}
		return 0;
	}

	@Override
	public Book getBookById(int bid) {
		try {
			conn = DBUtils.getConn();
			String sql = "select * from bs_books where bid=?";
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, bid);
			rs = pstat.executeQuery();
			if(rs.next()) {
				Book book = new Book(bid, rs.getString("bookname"), rs.getDouble("b_price"),
						rs.getString("image"), rs.getInt("stock"));
				return book;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeResource(conn, pstat, rs);
		}
		return null;
	}

}
