package com.river.test;

import java.util.ArrayList;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.ModelAndView;

import com.river.controller.CustomerController;
import com.river.controller.TradeController;
import com.river.entity.Customer;
import com.river.entity.Trade;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:conf/spring/spring-servlet.xml",
		"classpath:conf/spring/spring-context.xml" })
public class TradeUnit {
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private TradeController tradeController;
	@Autowired
	private CustomerController customerController;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {

	}
	@Test
	public void test(){
		init();
	}
	private void init(){
		System.out.println("-----初始化信息开始...");
		//初始化数据表
		customerController.init();
		//初始化用户
		Customer customer=new Customer();
		customer.setName("张三");
		customerController.save(customer);
		try {
			ModelAndView cmav=customerController.query();
			Object json=cmav.getModel().get("customerlist");
			System.out.println("用户信息:"+json);
			JavaType javaType= objectMapper.getTypeFactory().constructParametricType(ArrayList.class, Customer.class);
			ArrayList<Customer> customerlist=objectMapper.readValue(json.toString(), javaType);
			System.out.println("用户信息size:"+customerlist.size());
			customer=customerlist.get(0);
		}catch (Exception e) {			
			e.printStackTrace();
		}
		System.out.println("----用户信息初始化完成.----");
		/**
		 * 1.使用用户张三创建一条86元5角人民币的交易记录，验证交易状态为等待付款，并且交易的金额是正确的
		 * */
		//初始化交易信息
		Trade trade=new Trade();
		trade.setAmount(86.5);
		trade.setMoneyType(1);//1.人民币 2.美元
		//trade.setStatus(null);	//(是否交易成功）1 是 0 否	
		trade.setCustomer(customer);
		tradeController.save(trade);
		try {
			ModelAndView tmav=tradeController.query();
			Object json=tmav.getModel().get("tradelist");
			System.out.println("交易信息:\n"+json);
			JavaType javaType= objectMapper.getTypeFactory().constructParametricType(ArrayList.class, Trade.class);
			ArrayList<Trade> tradelist=objectMapper.readValue(json.toString(), javaType);
			trade=tradelist.get(0);
			System.out.println("交易状态:"+trade.getStatus()+"  交易的金额:"+trade.getAmount()+"  币种:"+trade.getMoneyType());
		}catch (Exception e){		
			e.printStackTrace();
		}
		//清除本条记录
		tradeController.del(trade.getTid());
		/**
		 * 2. 初始化3条交易成功信息，2条交易失败信息。验证查询接口按全部状态查询共5条交易，只查询成功的交易是3条，只查询失败的交易是2条
		 * */
		//初始化3条交易成功信息
		for(int i=0;i<3;i++){
			Trade t=new Trade();
			t.setAmount(86.5+i+1);
			t.setMoneyType(1);//1.人民币 2.美元
			t.setStatus(1);	//(是否交易成功）1 是 0 否	
			t.setCustomer(customer);
			tradeController.save(t);
		}
		//初始化2条交易失败信息
		for(int i=0;i<2;i++){
			Trade t=new Trade();
			t.setAmount(96.5+i+1);
			t.setMoneyType(1);//1.人民币 2.美元
			t.setStatus(0);	//(是否交易成功）1 是 0 否	
			t.setCustomer(customer);
			tradeController.save(t);
		}
		//按全部状态查询共5条交易
		try {
			ModelAndView mavquery=tradeController.query();//查询所有记录
			Object json=mavquery.getModel().get("tradelist");
			JavaType javaType= objectMapper.getTypeFactory().constructParametricType(ArrayList.class, Trade.class);   
			ArrayList<Trade> tradelist=objectMapper.readValue(json.toString(),javaType);
			System.out.println("交易信息列表:\n"+json);
			System.out.println("交易信息列表size:"+tradelist.size());
		}catch (Exception e){		
			e.printStackTrace();
		}
		//只查询成功的交易是3条
		try {
			ModelAndView mavquery=tradeController.queryByStatus(1);//只查询成功的交易
			Object json=mavquery.getModel().get("tradelist");
			JavaType javaType= objectMapper.getTypeFactory().constructParametricType(ArrayList.class, Trade.class);   
			ArrayList<Trade> tradelist=objectMapper.readValue(json.toString(),javaType);
			System.out.println("交易信息列表:\n"+json);
			System.out.println("交易信息列表size:"+tradelist.size());
		}catch (Exception e){		
			e.printStackTrace();
		}
		//只查询失败的交易是2条
		try {
			ModelAndView mavquery=tradeController.queryByStatus(0);//只查询失败的交易
			Object json=mavquery.getModel().get("tradelist");
			JavaType javaType= objectMapper.getTypeFactory().constructParametricType(ArrayList.class, Trade.class);   
			ArrayList<Trade> tradelist=objectMapper.readValue(json.toString(),javaType);
			System.out.println("交易信息列表:\n"+json);
			System.out.println("交易信息列表size:"+tradelist.size());
		}catch (Exception e){		
			e.printStackTrace();
		}
	}
}
