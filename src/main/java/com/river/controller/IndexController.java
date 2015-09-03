package com.river.controller;
/****
 * @desc 首页
 * @author wuqinghe
 * @date 205-09-02
 **/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.river.service.ICustomerService;

@Controller
@RequestMapping("/")
public class IndexController {
	/****
	 * @desc 首页测试
	 * @author wuqinghe
	 * @date 205-09-02
	 **/
	@Autowired
	private ICustomerService customerService;
	@RequestMapping(value="/index",method={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView query(){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("index");
        return mav;
    }
	/****
	 * @desc 交易列表首页
	 * @author wuqinghe
	 * @date 205-09-02
	 **/
	@RequestMapping(value="/list",method={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView list(){
		customerService.initIfNotExists();
        ModelAndView mav=new ModelAndView();
        mav.setViewName("list");
        return mav;
    }
	/****
	 * @desc 交易添加页面
	 * @author wuqinghe
	 * @date 205-09-02
	 **/
	@RequestMapping(value="/add",method={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView add(){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("add");
        return mav;
    }
}
