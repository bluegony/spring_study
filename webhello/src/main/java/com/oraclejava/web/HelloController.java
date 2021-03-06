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
	public ModelAndView hello(){
		ModelAndView mav = new ModelAndView("hello");
		mav.addObject("msg", "Hello, World!");
		mav.addObject("ccount", getCustomerCount3());
		mav.addObject("cinfo", getCustomerInfo3(2));
		mav.addObject("customerList", getCustomerList3());
		return mav;
	}
	@RequestMapping(value="/customer",method=RequestMethod.GET)
	public String getCustomerInfo4(Model model, @RequestParam int customerId) {
		model.addAttribute("custInfo",getCustomerInfo(customerId));
		return "customer";
	}
	
	
	@RequestMapping("/hello2")
	public String hello2(Model model){
		model.addAttribute("msg","dkssudgktpdyu");
		return "hello222";	
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
	
	// JdbcTemplate 이용
	private int getCustomerCount2(){
		int count=jdbcTemplate.queryForInt("SELECT COUNT(*) FROM CUSTOMER");
		System.out.println("getCustomerCount2 = " + count);
		return count;
	}
	private List<Customer> getCustomerList() {
		List<Customer> customerList = jdbcTemplate.query("SELECT * FROM CUSTOMER"
				, new RowMapper<Customer>(){
					public Customer mapRow(ResultSet rs, int rowNum) throws SQLException{
						Customer customer = new Customer();
						customer.setCustId(rs.getInt("id"));
						customer.setCustName(rs.getString("name"));
						customer.setCustAddr(rs.getString("addr"));
						customer.setCustEmail(rs.getString("email"));
						return customer;
					}
		});
				return customerList;
	}
	private Customer getCustomerInfo(int id){
		Customer customer = jdbcTemplate.queryForObject("SELECT * FROM CUSTOMER where id=?"
				, new RowMapper<Customer>(){
					public Customer mapRow(ResultSet rs, int rowNum) throws SQLException{
						Customer customer = new Customer();
						customer.setCustId(rs.getInt("id"));
						customer.setCustName(rs.getString("name"));
						customer.setCustAddr(rs.getString("addr"));
						customer.setCustEmail(rs.getString("email"));
						return customer;
					}
		}, id);
		return customer;
	}
	
	
	// DataSource 직접이용
	private int getCustomerCount(){
		Connection conn = null;
		PreparedStatement ps=null;
		ResultSet rs= null;
		int cnt = -1;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("SELECT COUNT(*) FROM CUSTOMER");
			rs = ps.executeQuery();
			if(rs.next()){
				cnt = rs.getInt(1);
				System.out.println("cnt="+cnt);
			}
		} catch(SQLException sqle){
			sqle.printStackTrace();
		} finally{
			try{
				if(rs!=null){
					rs.close();
				}
			} catch(Exception e){
				e.printStackTrace();
			}
			try{
				if(ps!=null){
					ps.close();
				}
			} catch(Exception e){
				e.printStackTrace();
			}
			try{
				if(conn!=null){
					conn.close();
				}
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		return cnt;
	}
	
	
	
}
