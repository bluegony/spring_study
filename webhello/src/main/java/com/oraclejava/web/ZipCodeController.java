package com.oraclejava.web;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oraclejava.domain.ZipCode;

@Controller
public class ZipCodeController {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//@Autowired
	private SqlSession session;

	@Autowired
	private SqlSessionFactory factory;
	
	@RequestMapping("/createZip")
	public ModelAndView create(){
		ModelAndView mav = new ModelAndView("zipcode/createSuccess");
		
		long start = System.currentTimeMillis();
		int cnt = readZipData2();
		long end = System.currentTimeMillis();

		mav.addObject("time", ( end - start )/1000.0 + " seconds" );
		mav.addObject("msg", cnt + " zip codes!");
		
		return mav;
	}
	
	
	// JdbcTemplate 이용
	private int readZipData(){
		int totalCnt = 0;
		
		List<Object[]> args = new ArrayList<Object[]>();
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("c:\\work\\zipcode3.txt"),"euc-kr"));
			String s = "";
			while((s=br.readLine())!=null){
				totalCnt++;
				String[] arr = s.split("\\|");
				
			//	ZipCode zipCode = new ZipCode(totalCnt,arr);
			//	args.add(zipCode);

				int seq = totalCnt;
				String zipcode = arr[0];  //우편번호
				String sido = arr[1];		//시도
				String gugun = arr[2];	//구군
				String dong = arr[3];	//동
				String ri = arr[4];	//리, 건물명
				String st_bunji = arr[5];	//시작번지
				String ed_bunji = arr[6];	//끝번지
				args.add(new Object[]{seq, zipcode, sido, gugun, dong, ri, st_bunji, ed_bunji});
			}
			br.close();
			try{
				int[] num = jdbcTemplate.batchUpdate("INSERT INTO POST(SEQ,ZIPCODE,SIDO,GUGUN,DONG,RI,ST_BUNJI,ED_BUNJI)  VALUES(?,?,?,?,?,?,?,?)", args);
				
			} catch(Exception e){
				e.printStackTrace();
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return totalCnt;
	}
	
	
	// mybatis 이용
	private int readZipData2(){
		session = factory.openSession(ExecutorType.BATCH );
		int totalCnt = 0;		
		
		List<ZipCode> args = new ArrayList<ZipCode>();
		
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("c:\\work\\zipcode3.txt"),"euc-kr"));
			String s = "";
			while((s=br.readLine())!=null){
				totalCnt++;
				String[] arr = s.split("\\|");
				
				ZipCode zipCode = new ZipCode(totalCnt,arr);
				args.add(zipCode);

			}
			br.close();
			for (ZipCode zipcode : args){
				session.insert("mybatis.sql.ZipCode.insertZipCode",zipcode);
			}
//			try{
//				int[] num = jdbcTemplate.batchUpdate("INSERT INTO POST(SEQ,ZIPCODE,SIDO,GUGUN,DONG,RI,ST_BUNJI,ED_BUNJI)  VALUES(?,?,?,?,?,?,?,?)", args);
//				
//			} catch(Exception e){
//				e.printStackTrace();
//				
//			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return totalCnt;
	}
		
}
