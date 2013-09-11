package com.oraclejava.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.oraclejava.domain.Customer;

@Controller
@RequestMapping("/custedit")
public class CustUpdController {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SqlSession session;
	
	//초기값 셋팅
	@RequestMapping(method=RequestMethod.GET)
	public void setupForm(@RequestParam int custId, Model model) {
		model.addAttribute("custInfo", getCustInfo2(custId));
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String doAction(@ModelAttribute CustomerCommand command) {
		Customer customer = new Customer(command);
		update(customer);
		//update처리
		//ModelAndView mav = new ModelAndView("redirect:/hello.html");
		return "redirect:/hello.html";
	}

	private void update(Customer customer) {
		session.update("mybatis.sql.Customer.updateCustomer", customer);
	}
	
	
	
	private Customer getCustInfo2(int id) {
		Customer customer = (Customer)session.selectOne("mybatis.sql.Customer.selectCustInfo", id);
		return customer;
	}
	
	private Customer getCustInfo(int id) {
		Customer customer = jdbcTemplate.queryForObject("SELECT * FROM CUSTOMER WHERE CUST_ID=?"
				, new RowMapper<Customer>() {

					public Customer mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Customer customer = new Customer();
						customer.setCustId(rs.getInt("CUST_ID"));
						customer.setCustName(rs.getString("CUST_NAME"));
						customer.setCustAddr(rs.getString("CUST_ADDR"));
						customer.setCustEmail(rs.getString("CUST_EMAIL"));
						return customer;
					}
					
				}, id);
		return customer;
	}
	
	
}
