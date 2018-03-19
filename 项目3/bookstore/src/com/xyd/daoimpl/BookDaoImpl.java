package com.xyd.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xyd.bean.Book;
import com.xyd.dao.BookDao;
import com.xyd.utils.JdbcUtils;

/**
 * book 的持久操作
 * @author Administrator
 *
 */
public class BookDaoImpl implements BookDao{

	private Connection conn;
	
	private PreparedStatement ps;
	
	private ResultSet rs = null;
	
	@Override
	public List<Book> getBook(int max,int min) {
		
		List<Book> list = new ArrayList<Book>();
		//分页查询 
		
		conn = JdbcUtils.getConn();
		
		String sql = "select * from (select rownum rn,b.* from bs_books b where rownum <= ? ) temp where temp.rn >= ?";

		try {
			ps = conn.prepareStatement(sql);
			
			//ps 给数据
			ps.setInt(1, max);
			ps.setInt(2, min);

			rs = ps.executeQuery();

			while (rs.next()) {
				// 获取结果集中的数据  添加到集合中
				 list.add(new Book(rs.getInt("bid"),rs.getString("bookname"), 
						 rs.getString("b_price"),  rs.getString("image"),  rs.getLong("stock")));
				
			}
			
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//释放资源
			JdbcUtils.coloseAll(rs,ps,conn);
		}
		return null;
	}

	/**
	 * 拿到书籍的数量 
	 */
	@Override
	public int getBookCount() {
		
//		conn = JdbcUtils.getConn();
//
//		try {
//			ps = conn.prepareStatement("select count(*) from bs_books");
//			
//			rs = ps.executeQuery();
//			
//			if(rs.next()){
//				return rs.getInt(1);
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		return getBookCount(null);
	}

	@Override
	public int getBookCount(String keyword) {
		
		conn = JdbcUtils.getConn();

		try {
			
			if (keyword != null && keyword.length() >0) {
				ps = conn.prepareStatement("select count(*) from bs_books where BOOKNAME like ?");
				ps.setString(1, "%"+ keyword +"%");
				rs = ps.executeQuery();
			}else{
				
				ps = conn.prepareStatement("select count(*) from bs_books");
				rs = ps.executeQuery();
			}
			
			if(rs.next()){
				return rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return 0;
		
	}

	@Override
	public List<Book> getBook(int max, int min, String keyword) {
		List<Book> list = new ArrayList<Book>();
		//分页查询 
		
		conn = JdbcUtils.getConn();
		
		String sql = "select * from (select rownum rn,b.* from bs_books b where bookname like ? and rownum <= ?) temp where temp.rn >= ?";

		try {
			ps = conn.prepareStatement(sql);
			
			//ps 给数据
			ps.setString(1, "%"+ keyword +"%");
			ps.setInt(2, max);
			ps.setInt(3, min);

			rs = ps.executeQuery();

			while (rs.next()) {
				// 获取结果集中的数据  添加到集合中
				 list.add(new Book(rs.getInt("bid"),rs.getString("bookname"), 
						 rs.getString("b_price"),  rs.getString("image"),  rs.getLong("stock")));
				
			}
			
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//释放资源
			JdbcUtils.coloseAll(rs,ps,conn);
		}
		return null;
	}
	

}
