package com.oraclejava.web;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.oraclejava.domain.Customer;
import com.oraclejava.domain.CustomerCommand;

@Controller
public class JsonController {
	
	@Autowired
	private SqlSession session;

	
	@RequestMapping(value="/ajax/jsonOut1", headers="Accept=application/json")
	//@RequestMapping(value="/ajax/jsonOut1", produces="Accept=application/json")
	@ResponseBody
	public List<Customer> jsonOut(){
		List<Customer> list = getCustomerList3();
		return list;
	}

	// mybatis �댁슜
	private List<Customer> getCustomerList3() {
		HashMap<String,String> hm = new HashMap<String, String>();
		//hm.put("fname", "��);
		//hm.put("paddr", "援�");
		List<Customer> customerList = session.selectList("mybatis.sql.Customer.selectCustomerList",hm);
		return customerList;
	}

	@RequestMapping(value="/ajax/jsonIn1", consumes="application/json")
	public ModelAndView jsonIn(@RequestBody CustomerCommand command){
		System.out.println("11");
		ModelAndView mav = new ModelAndView("ajax/jsonIn");
		mav.addObject("data",command);
		return mav;
	}
}
