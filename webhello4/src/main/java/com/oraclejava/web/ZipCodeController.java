package com.oraclejava.web;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
import com.oraclejava.domain.Post;

@Controller
public class ZipCodeController {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SqlSession session;
	
	@RequestMapping("/createZip")
	public ModelAndView create() {
		//���۽ð� ����
		long s_time = System.currentTimeMillis();
		ModelAndView mav = new ModelAndView("zipcode/createSuccess");
		int cnt = createZip2();
		mav.addObject("msg", cnt + "�� �Է� ����!");
		long e_time = System.currentTimeMillis();
		//����ð� ����
		//�ѽð�(��) = ����ð� - ���۽ð�
		mav.addObject("t_time", ((e_time - s_time) / 1000.0) + "��");
		return mav;
	}
	
	private int createZip() {
		int totalCnt = 0;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("c:\\work\\zipcode3.txt"), "utf-8"));
			String s = "";
			List<Object[]> args = new ArrayList<Object[]>();
			while ((s = br.readLine()) != null) {
				totalCnt++;
				String[] arr = s.split("\\|");
				int seq = totalCnt;
				String zipcode = arr[0];
				String sido = arr[1];
				String gugun = arr[2];
				String dong = arr[3];
				String ri = arr[4];
				String stBunji = arr[5];
				String edBunji = arr[6];
				args.add(new Object[]{seq, zipcode, sido, gugun, dong, ri, stBunji, edBunji});
			}
			br.close();
			//jdbcTemplate.update("DELETE FROM POST");
			int[] num = jdbcTemplate.batchUpdate("INSERT INTO POST(SEQ, ZIPCODE, SIDO, GUGUN, DONG, RI, ST_BUNJI, ED_BUNJI) VALUES (?,?,?,?,?,?,?,?)", args);
		} catch(IOException e) {
			e.printStackTrace();
		} 
		return totalCnt;
	}
	
	private int createZip2() {
		int totalCnt = 0;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("c:\\javaweb\\zipcode3.txt"), "utf-8"));
			String s = "";
			List<Post> args = new ArrayList<Post>();
			while ((s = br.readLine()) != null) {
				totalCnt++;
				String[] arr = s.split("\\|");
				int seq = totalCnt;
				String zipcode = arr[0];
				String sido = arr[1];
				String gugun = arr[2];
				String dong = arr[3];
				String ri = arr[4];
				String stBunji = arr[5];
				String edBunji = arr[6];
				args.add(new Post(seq, zipcode, sido, gugun, dong, ri, stBunji, edBunji));
			}
			br.close();
			//jdbcTemplate.update("DELETE FROM POST");
			//int[] num = jdbcTemplate.batchUpdate("INSERT INTO POST(SEQ, ZIPCODE, SIDO, GUGUN, DONG, RI, ST_BUNJI, ED_BUNJI) VALUES (?,?,?,?,?,?,?,?)", args);
			for (Post post : args) {
				session.insert("mybatis.sql.Post.createZip", post);
			}
		} catch(IOException e) {
			e.printStackTrace();
		} 
		return totalCnt;
	}
}
