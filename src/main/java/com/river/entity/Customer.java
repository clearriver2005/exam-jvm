package com.river.entity;

/****
 * @desc 客户实体类
 * @author wuqinghe
 * @date 205-09-01
 **/
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * •客户信息
 * */
public class Customer implements java.io.Serializable,RowMapper<Customer>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7822046848694555873L;
	/**
	 * 客户 ID
	 * */
	private Long cid;
	/**
	 * 客户姓名
	 * */
	private String name;
	public Long getCid() {
		return cid;
	}
	/**
	 * 客户 ID
	 * */
	public void setCid(Long cid) {
		this.cid = cid;
	}
	public String getName() {
		return name;
	}
	/**
	 * 客户姓名
	 * */
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Customer c=new Customer();
		c.setCid(rs.getLong("cid"));
		c.setName(rs.getString("name"));
		return c;
	}
}
