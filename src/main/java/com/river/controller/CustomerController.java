package com.river.controller;
/****
 * @desc 用户
 * @author wuqinghe
 * @date 205-09-02
 **/
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.river.entity.Customer;
import com.river.service.ICustomerService;
import com.river.util.Tool;
@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private ICustomerService customerService;
	/****
	 * @desc 用户保存
	 * @author wuqinghe
	 * @date 205-09-02
	 **/
	@RequestMapping(value="/save",method={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView save(Customer customer){
		ModelAndView mav=new ModelAndView(new MappingJackson2JsonView());
		if(customer.getName()!=null&&!StringUtils.isBlank(customer.getName())){
			customerService.save(customer);
	        mav.addObject("customer",Tool.mapper(objectMapper, customer));
	        mav.addObject("success",true);
		}else {
			mav.addObject("success",false);
			mav.addObject("msg","名字不能为空!");
		}
        return mav;
    }
	/****
	 * @desc 查询所有用户
	 * @author wuqinghe
	 * @date 205-09-02
	 **/
	@RequestMapping(value="/query",method={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView query(){
        ModelAndView mav=new ModelAndView(new MappingJackson2JsonView());
        mav.addObject("customerlist",Tool.mapper(objectMapper,customerService.query()));
        return mav;
    }
	/****
	 * @desc 数据初始化
	 * @author wuqinghe
	 * @date 205-09-02
	 **/
	@RequestMapping(value="/init",method={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView init(){
		customerService.init();
        ModelAndView mav=new ModelAndView(new MappingJackson2JsonView());
		mav.addObject("success",true);
		mav.addObject("msg","数据表初始化完成!");
        return mav;
    }
}
