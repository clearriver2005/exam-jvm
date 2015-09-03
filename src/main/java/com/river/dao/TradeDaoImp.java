package com.river.dao;
/****
 * @desc 交易DAO
 * @author wuqinghe
 * @date 205-09-02
 **/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.river.entity.Customer;
import com.river.entity.Trade;
@Repository
public class TradeDaoImp implements ITradeDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	/****
	 * @desc 保存交易记录
	 * @author wuqinghe
	 * @date 205-09-02
	 **/
	@Override
	public Trade save(Trade trade){
		if(trade.getTid()==null){
			try {
				trade.setCreateDate(new Date());
				insert(trade);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			update(trade);
		}
		return trade;
	}
	/****
	 * @desc 按交易状态查询交易信息
	 * @author wuqinghe
	 * @date 205-09-02
	 **/
	@Override
	public List<Trade> query(Integer status){
		return jdbcTemplate.query("SELECT TID,AMOUNT,CREATE_DATE,T.CID,MONEY_TYPE,STATUS,TRADE_TYPE,C.NAME FROM R_TRADE T,R_CUSTOMER C WHERE T.CID=C.CID AND T.STATUS=?"
				,new Object[]{status},new RowMapper<Trade>(){
					@Override
					public Trade mapRow(ResultSet rs, int rowNum) throws SQLException {
						Trade trade = new Trade();
						trade.setTid(rs.getLong("tid"));
						trade.setAmount(rs.getDouble("amount"));
						trade.setCreateDate(rs.getTimestamp("create_date"));
						trade.setMoneyType(rs.getInt("money_type"));
						trade.setStatus(rs.getInt("status"));
						trade.setTradeType(rs.getInt("trade_type"));
						Customer c=new Customer();
						c.setCid(rs.getLong("cid"));
						c.setName(rs.getString("name"));
						trade.setCustomer(c);
				        return trade;
					}
				});
	}
	/****
	 * @desc 查询所有交易信息
	 * @author wuqinghe
	 * @date 205-09-02
	 **/
	@Override
	public List<Trade> query(){
		List<Trade> tlist=jdbcTemplate.query("SELECT TID,AMOUNT,CREATE_DATE,T.CID,MONEY_TYPE,STATUS,TRADE_TYPE,C.NAME FROM R_TRADE T,R_CUSTOMER C WHERE T.CID=C.CID"
				,new RowMapper<Trade>(){
					@Override
					public Trade mapRow(ResultSet rs, int rowNum) throws SQLException {
						Trade trade = new Trade();
						trade.setTid(rs.getLong("tid"));
						trade.setAmount(rs.getDouble("amount"));
						trade.setCreateDate(rs.getTimestamp("create_date"));
						trade.setMoneyType(rs.getInt("money_type"));
						trade.setStatus(rs.getInt("status"));
						trade.setTradeType(rs.getInt("trade_type"));
						Customer c=new Customer();
						c.setCid(rs.getLong("cid"));
						c.setName(rs.getString("name"));
						trade.setCustomer(c);
				        return trade;
					}
				});
		return tlist;
	}
	/****
	 * @desc 插入交易记录
	 * @author wuqinghe
	 * @date 205-09-02
	 **/
	private void insert(final Trade trade){
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update( new PreparedStatementCreator(){
            @Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException{
            	StringBuffer sql1=new StringBuffer("insert into R_TRADE(AMOUNT,CID");
            	StringBuffer sql2=new StringBuffer(") values(?,?");
            	if(trade.getMoneyType()!=null){
            		sql1.append(",MONEY_TYPE");
            		sql2.append(",?");
            	}
            	if(trade.getStatus()!=null){
            		sql1.append(",STATUS");
            		sql2.append(",?");
            	}
            	if(trade.getTradeType()!=null){
            		sql1.append(",TRADE_TYPE");
            		sql2.append(",?");
            	}
                PreparedStatement ps = conn.prepareStatement(sql1.append(sql2).append(")").toString()
                		, new String[]{"TID"}); 
                int i = 0;
                ps.setDouble(++i,trade.getAmount());
                ps.setLong(++i,trade.getCustomer().getCid());
                if(trade.getMoneyType()!=null){
                	ps.setInt(++i,trade.getMoneyType());
            	}
            	if(trade.getStatus()!=null){
                	ps.setInt(++i,trade.getStatus());
            	}
            	if(trade.getTradeType()!=null){
                	ps.setInt(++i,trade.getTradeType());
            	}
                return ps;
            }
        },
        keyHolder);
		trade.setTid(keyHolder.getKey().longValue());
	}
	/****
	 * @desc 更新交易记录
	 * @author wuqinghe
	 * @date 205-09-02
	 **/
	private void update(final Trade trade){
		StringBuffer sql=new StringBuffer("UPDATE R_TRADE SET ");
		if(trade.getAmount()!=null){
			sql.append("AMOUNT=").append(trade.getAmount()).append(",");
		}
		if(trade.getCustomer()!=null&&trade.getCustomer().getCid()!=null){
			sql.append("CID=").append(trade.getCustomer().getCid()).append(",");
		}
		if(trade.getMoneyType()!=null){
			sql.append("MONEY_TYPE=").append(trade.getMoneyType()).append(",");
		}
		if(trade.getStatus()!=null){
			sql.append("STATUS=").append(trade.getStatus()).append(",");
		}
		if(trade.getTradeType()!=null){
			sql.append("TRADE_TYPE=").append(trade.getTradeType()).append(",");
		}
		sql.deleteCharAt(sql.length()-1);
		sql.append(" WHERE TID=").append(trade.getTid());
		jdbcTemplate.execute(sql.toString());
	}
	/****
	 * @desc 按ID删除交易记录
	 * @author wuqinghe
	 * @date 205-09-02
	 **/
	@Override
	public void del(Long tid) {
		jdbcTemplate.execute("delete from R_TRADE where tid="+tid);
	}
}
