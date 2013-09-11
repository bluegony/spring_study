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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.oraclejava.domain.Customer;

@Controller
public class HelloController {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SqlSession session;
	
	@RequestMapping("/hello")
	public ModelAndView hello() {
		ModelAndView mav = new ModelAndView("hello");
		mav.addObject("msg", "Hello World!");
		mav.addObject("custCnt", getCustCnt3());
		mav.addObject("custInfo", getCustInfo2(2));
		mav.addObject("custList", getCustList2());
		return mav;
	}
	@RequestMapping("/hello2")
	public String hello2(Model model) {
		model.addAttribute("msg", "hello?");
		return "hello2";
	}
	
	@RequestMapping(value="/customer", method=RequestMethod.GET)
	public String getCustomer(Model model, @RequestParam int custId) {
		model.addAttribute("custInfo", getCustInfo2(custId));
		return "customer";
	}
	
	private List<Customer> getCustList2() {
		HashMap<String, String> hm = new HashMap<String, String>();
		//hm.put("sung", "��");
		//hm.put("addr", "��");
		List<Customer> custList = session.selectList(
				"mybatis.sql.Customer.selectCustList", hm);
		return custList;
	}
	
	private List<Customer> getCustList() {
		List<Customer> custList = jdbcTemplate.query("SELECT * FROM CUSTOMER"
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
				});
		return custList;
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
	
	private int getCustCnt3() {
		int count = (Integer)session.selectOne("mybatis.sql.Customer.selectCustCnt");
		return count;
	}
	
	private int getCustCnt2() {
		int count = jdbcTemplate.queryForInt("SELECT COUNT(*) FROM CUSTOMER");
		return count;
	}
	
	private int getCustCnt() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int cnt = -1;
		 try {
			 conn = dataSource.getConnection();
			 ps = conn.prepareStatement("SELECT COUNT(*) FROM CUSTOMER");
			 rs = ps.executeQuery();
			 if (rs.next()) {
				 cnt = rs.getInt(1);
			 }
		 } catch (SQLException sqle) {
			 sqle.printStackTrace();
		 } finally {
			 try {
				 if (rs != null) {
					 rs.close();
				 }
			 } catch (Exception e) {
				 e.printStackTrace();
			 }
			 try {
				 if (ps != null) {
					 ps.close();
				 }
			 } catch (Exception e) {
				 e.printStackTrace();
			 }
			 try {
				 if (conn != null) {
					 conn.close();
				 }
			 } catch (Exception e) {
				 e.printStackTrace();
			 }
		 }
		 return cnt;
	}
}
