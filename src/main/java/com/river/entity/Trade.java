package com.river.entity;
/****
 * @desc 交易记录实体类
 * @author wuqinghe
 * @date 205-09-01
 **/
import java.util.Date;

public class Trade implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7556002683797125796L;
	/**
	 * 交易 ID
	 * */
	private Long tid;
	/**
	 * 创建时间
	 * */
	private Date createDate;
	/**
	 * 交易状态（例如是否交易成功）1 是  0 否
	 * */
	private Integer status;
	/**
	 * 交易类型（例如支付还是退款）1支付2退款
	 * */
	private Integer tradeType;
	/**
	 * 交易金额
	 * */
	private Double amount;
	/**
	 * 交易币种（例如美元还是人民币）1人民币2美元
	 * */
	private Integer moneyType;
	/**
	 * 客户信息
	 * */
	private Customer customer;
	public Long getTid() {
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * 创建时间
	 * */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getStatus() {
		return status;
	}
	/**
	 * 交易状态（例如是否交易成功）1 是  0 否
	 * */
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getTradeType() {
		return tradeType;
	}
	/**
	 * 交易类型（例如支付还是退款）1支付2退款
	 * */
	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}
	public Double getAmount() {
		return amount;
	}
	/**
	 * 交易金额
	 * */
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Integer getMoneyType() {
		return moneyType;
	}
	/**
	 * 交易币种（例如美元还是人民币）1人民币2美元
	 * */
	public void setMoneyType(Integer moneyType) {
		this.moneyType = moneyType;
	}
	public Customer getCustomer() {
		return customer;
	}
	/**
	 * 客户信息
	 * */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
