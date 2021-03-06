package com.oraclejava.web;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.oraclejava.domain.Customer;
import com.oraclejava.domain.CustomerCommand;

@Controller
@RequestMapping("/editCustomer")
public class CustomerController {

	@Autowired
	private SqlSession session;
	
//	@RequestMapping("/hello")
//	public ModelAndView hello(){
//		ModelAndView mav = new ModelAndView("hello");
//		mav.addObject("msg", "Hello, World!");
//		mav.addObject("ccount", getCustomerCount3());
//		mav.addObject("cinfo", getCustomerInfo3(2));
//		mav.addObject("customerList", getCustomerList3());
//		return mav;
//	}
//	@RequestMapping(value="/customer",method=RequestMethod.GET)
//	public String getCustomerInfo4(Model model, @RequestParam int customerId) {
//		model.addAttribute("custInfo",getCustomerInfo(customerId));
//		return "customer";
//	}

	@RequestMapping(method=RequestMethod.GET)
	public void setupForm( Model model, @RequestParam int customerId) {		// void일때는 class에 mapping된 주소의 jsp file을 쓴다.
		System.out.println("!");
		model.addAttribute("custInfo", getCustomerInfo3(customerId));
	}

//	@RequestMapping(value="/customer",method=RequestMethod.GET)
//	public String getCustomerInfo4(Model model, @RequestParam int customerId) {
//		model.addAttribute("custInfo",getCustomerInfo(customerId));
//		return "customer";
//	}
//	

	@RequestMapping(method=RequestMethod.POST)
	public String doAction(@ModelAttribute CustomerCommand command){
		Customer customer = new Customer(command);
		update(customer);
		//update 처리
		//ModelAndView mav = new ModelAndView("redirect:/hello.html");
		return "redirect:/hello.html";
	}
	
	private void update(Customer customer){
		session.update("mybatis.sql.Customer.updateCustomer",customer);
	}

	// mybatis 이용
	private int getCustomerCount3(){
		int count = (Integer)session.selectOne("mybatis.sql.Customer.selectCustCnt");
		return count;
	}
	private Customer getCustomerInfo3(int id){
		Customer customer=(Customer)session.selectOne("mybatis.sql.Customer.selectCustomer", id);
		return customer;
	} 
	private List<Customer> getCustomerList3() {
		HashMap<String,String> hm = new HashMap<String, String>();
		//hm.put("fname", "이");
		//hm.put("paddr", "구2");
		List<Customer> customerList = session.selectList("mybatis.sql.Customer.selectCustomerList",hm);
		return customerList;
	}
}
