package com.oraclejava.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.oraclejava.domain.Customer;

@Controller
public class XmlController {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SqlSession session;
	
	//@RequestMapping(value="/ajax/jsonOut1", headers="Accept=application/json;charset=utf-8")
	@RequestMapping(value="/ajax/xmlOut1.xml", produces="application/xml;charset=utf-8")  //3.1�̻�
	@ResponseBody
	public CustomerJaxb xmlOut() {
		CustomerJaxb outData = new CustomerJaxb();
		outData.setCustId(1);
		outData.setCustName("홍길동");
		outData.setCustAddr("");
		outData.setCustEmail("hong@hanyang.com");
		return outData;
	}

	@RequestMapping(value="/ajax/xmlListOut1", produces="application/xml;charset=utf-8")  //3.1�̻�
	@ResponseBody
	public CustomerListJaxb xmlListOut() {
		List<Customer> list = getCustList2();
		CustomerListJaxb listJaxb = new CustomerListJaxb(list);
		return listJaxb;
	}
	
	@RequestMapping(value="/ajax/xmlIn1", consumes="application/xml")
	public ModelAndView xmlIn(@RequestBody CustomerJaxb command) {
		ModelAndView mav = new ModelAndView("ajax/jsonIn");
		mav.addObject("data", command);
		return mav;
	}

//	@RequestMapping(value="/ajax/xmlIn2", consumes="application/xml", produces="application/xml;charset=utf-8")
//	@ResponseBody
//	public CustomerJaxb xmlIn2(@RequestBody CustomerJaxb command) {
//
//		return command;
//	}
	
	@RequestMapping(value="/ajax/xmlIn2", consumes="application/xml", produces="application/xml;charset=utf-8")
	@ResponseBody
	public Callable<CustomerJaxb> xmlIn2(@RequestBody final CustomerJaxb command) {
		return new Callable<CustomerJaxb>(){

			@Override
			public CustomerJaxb call() throws Exception{
				try{
					Thread.sleep(5000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				command.setCustName(command.getCustName()+"씨");
				return command;
			}
		};

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
