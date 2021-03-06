package com.oraclejava.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.oraclejava.domain.FileUploadCommand;
import com.oraclejava.domain.FileUploadItem;
import com.oraclejava.domain.ZipCode;

@Controller
@RequestMapping("/fileupload")
public class FileUploadController {
	
	@RequestMapping(value="multiUpload.html", method=RequestMethod.GET)
	public void setupMultiForm(Model model){
		FileUploadCommand command = new FileUploadCommand();
		for(int i=0; i<3;i++){
			command.getUploadItems().add(new FileUploadItem());
		}
		model.addAttribute("uploadComponents", command);
	}

	// fileupload/multi.html
	@RequestMapping(value="multi", method=RequestMethod.POST)
	public ModelAndView multi(@ModelAttribute FileUploadCommand command)  throws IllegalStateException, IOException {
		System.out.println("!!!!");
		for(FileUploadItem item:command.getUploadItems()) {
			MultipartFile file = item.getFile();
			if(file.isEmpty()) continue;
			
			String filename = file.getOriginalFilename();
			long size = file.getSize();
			String contentType = file.getContentType();
			file.transferTo(new File("c:/work/upload/" + filename));
			
		}
		ModelAndView mav = new ModelAndView("fileupload/success");
//		mav.addObject("filename",filename);
//		mav.addObject("size",size);
//		mav.addObject("contentType",contentType);
		return mav;
	}
	
	@RequestMapping(value="singleUpload.html", method=RequestMethod.GET)
	public void setupForm(){
		
	}
	
	@Autowired
	private SqlSessionFactory factory;
	private SqlSession session;
	
	// fileupload/single.html
	@RequestMapping(value="single.html", method=RequestMethod.POST)
	public ModelAndView single(@RequestParam("file") MultipartFile file)  throws IllegalStateException, IOException {
		String filename = file.getOriginalFilename();
		long size = file.getSize();
		String contentType = file.getContentType();
		file.transferTo(new File("c:/work/upload/" + filename));
		
		ModelAndView mav = new ModelAndView("fileupload/success");
		mav.addObject("filename",filename);
		mav.addObject("size",size);
		mav.addObject("contentType",contentType);
		
		if(filename.equals("zipcode3.txt")){
			long start = System.currentTimeMillis();
			int cnt = readZipData2();
			long end = System.currentTimeMillis();
			mav.addObject("time", ( end - start )/1000.0 + " seconds" );
			mav.addObject("msg", cnt + " zip codes!");

		}
		
		return mav;
	}
	

	// mybatis 이용
	private int readZipData2(){
		session = factory.openSession(ExecutorType.BATCH );
		int totalCnt = 0;		
		
		List<ZipCode> args = new ArrayList<ZipCode>();
		
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("c:\\work\\upload\\zipcode3.txt"),"euc-kr"));
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
