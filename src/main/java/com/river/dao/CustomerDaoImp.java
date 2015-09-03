package com.river.dao;
/****
 * @desc 用户DAO
 * @author wuqinghe
 * @date 205-09-02
 **/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.river.entity.Customer;
import com.river.util.Tool;
@Repository
public class CustomerDaoImp implements ICustomerDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	/****
	 * @desc 交易信息保存
	 * @author wuqinghe
	 * @date 205-09-02
	 **/
	@Override
	public Customer save(Customer customer){
		if(customer.getCid()==null){
			insert(customer);
		}else{
			update(customer);
		}
		return customer;
	}
	/****
	 * @desc 查询所有交易信息
	 * @author wuqinghe
	 * @date 205-09-02
	 **/
	@Override
	public List<Customer> query() {
		return jdbcTemplate.query("SELECT CID,NAME FROM R_CUSTOMER"
				,new RowMapper<Customer>(){
					@Override
					public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
						Customer c=new Customer();
						c.setCid(rs.getLong("cid"));
						c.setName(rs.getString("name"));
				        return c;
					}
				});
	}
	/****
	 * @desc 初始化数据表结构
	 * @author wuqinghe
	 * @date 205-09-02
	 **/
	@Override
	public void init() {
		Tool.createTable(jdbcTemplate);
	}
	/****
	 * @desc 初始化数据表结构
	 * @author wuqinghe
	 * @date 205-09-02
	 **/
	@Override
	public void initIfNotExists() {
		if(!Tool.existTableName(jdbcTemplate, "R_CUSTOMER")) {
			init();
			Customer customer=new Customer();
			customer.setName("张三");
			insert(customer);
		}
	}
	/****
	 * @desc 插入用户信息
	 * @author wuqinghe
	 * @date 205-09-02
	 **/
	private void insert(final Customer customer){
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update( new PreparedStatementCreator(){
            @Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException{
                PreparedStatement ps = conn.prepareStatement("insert into R_CUSTOMER(NAME) values(?)"
                		, new String[]{"CID"}); 
                ps.setString(1,customer.getName());
                return ps;
            }
        },
        keyHolder);
		customer.setCid(keyHolder.getKey().longValue());
	}
	/****
	 * @desc 更新用户信息
	 * @author wuqinghe
	 * @date 205-09-02
	 **/
	private void update(final Customer customer){
		StringBuffer sql=new StringBuffer("UPDATE R_CUSTOMER SET ");
		sql.append("NAME=").append(customer.getName());
		sql.append(" WHERE CID=").append(customer.getCid());
		jdbcTemplate.execute(sql.toString());
	}
}
