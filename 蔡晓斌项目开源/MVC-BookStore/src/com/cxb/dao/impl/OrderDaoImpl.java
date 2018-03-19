package com.cxb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cxb.dao.OrderDao;
import com.cxb.pojo.Book;
import com.cxb.pojo.Item;
import com.cxb.utils.DBUtils;

public class OrderDaoImpl implements OrderDao {
	private Connection conn = null;
	private PreparedStatement pstat = null;
	private ResultSet rs = null;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	@Override
	public int addOrder(String name, List<Item> items, double total_price) {
		try {
			conn = DBUtils.getConn();
			String sql = "insert into bs_orders(oid,username) values(order_seq.nextval,?)";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, name);
			int i = pstat.executeUpdate();
			if (i > 0) {
				sql = "select max(oid) from bs_orders";
				pstat = conn.prepareStatement(sql);
				rs = pstat.executeQuery();
				long oid = 0;
				if (rs.next()) {
					oid = rs.getLong(1);
				}
				for (Item item : items) {
					sql = "insert into bs_items values(order_seq.nextval,?,?,?,?,?,?,?)";
					pstat = conn.prepareStatement(sql);
					pstat.setLong(1, oid);
					pstat.setInt(2, item.getBid());
					// pstat.setDate(3, new java.sql.Date(new
					// Date().getTime()));
					pstat.setString(3, sdf.format(new Date()));
					pstat.setInt(4, item.getCount());
					pstat.setDouble(5, item.getPrice());
					pstat.setInt(6, 0);
					pstat.setDouble(7, total_price);

					pstat.executeUpdate();
				}
			}
			return i;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeResource(conn, pstat, rs);
		}
		return 0;
	}

	@Override
	public List<Item> getItem(String name, Item item, int st) {
		List<Item> items = new ArrayList<Item>();
		try {
			conn = DBUtils.getConn();
			StringBuffer sql = new StringBuffer(
					"select i.*,b.* from bs_orders o join bs_items i on o.oid=i.oid");
			sql.append(" join bs_books b on b.bid=i.bid ");
			sql.append(" where o.username = ? and i.state=?");
			if (item != null) {
				long oid = item.getOid();
				Book book = item.getBook();
				if (book != null) {
					sql.append(" and b.bookname like '%" + book.getBname()
							+ "%'");
				} else {
					sql.append(" and o.oid=" + oid);
				}
			}
			pstat = conn.prepareStatement(sql.toString());
			pstat.setString(1, name);
			pstat.setInt(2, st);
			System.out.println(sql);
			rs = pstat.executeQuery();
			while (rs.next()) {
				long iid = rs.getLong("iid");
				long oid = rs.getLong("oid");
				int bid = rs.getInt(3);
				String createDate = rs.getString("createdate");
				// System.out.println(createDate+"*************");
				int count = rs.getInt("count");
				double price = rs.getDouble(6);
				int state = rs.getInt(7);
				double total_price = rs.getDouble(8);
				String bname = rs.getString("bookname");
				double bprice = rs.getDouble("b_price");
				String images = rs.getString("image");
				int stock = rs.getInt("stock");

				Date date = null;
				try {
					date = sdf.parse(createDate);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				Item order = new Item(iid, oid, bid, date, count, price, state,
						total_price, new Book(null, bname, bprice, images,
								stock));
				items.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeResource(conn, pstat, rs);
		}
		return items;
	}

	@Override
	public int updateItem(Item item) {
		try {
			conn = DBUtils.getConn();
			String sql = "update bs_items set count=?,price=?,state=1,total_price=? where iid=?";
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, item.getCount());
			pstat.setDouble(2, item.getPrice());
			pstat.setDouble(3, item.getTotal_price());
			pstat.setLong(4, item.getIid());
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
	public int updateTotalPrice() {
		try {
			conn = DBUtils.getConn();
			String sql = "update bs_items i set i.total_price=(select sum(t.price) "
					+ "from bs_items t where i.oid=t.oid group by t.oid)";
			pstat = conn.prepareStatement(sql);
			int i = pstat.executeUpdate();
			return i;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeResource(conn, pstat, null);
		}
		return 0;
	}

}
