package com.oraclejava.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/fileupload")
public class FileUploadController {

	@RequestMapping(method=RequestMethod.GET)
	public void setUpForm(Model model) {
		FileUploadCommand command = new FileUploadCommand();
		for (int i=0; i<3; i++) {
			command.getUploadItems().add(new FileUploadItem());
		}
		model.addAttribute("uploadComponents", command);
	}
	
	//fileupload/single.html
	@RequestMapping(value="single", method=RequestMethod.POST)
	public ModelAndView single(@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
		String filename = file.getOriginalFilename();
		long size = file.getSize();
		String contentType = file.getContentType();
		file.transferTo(new File("C:/javaweb/workspace/webhello/upload/" + filename));
		InputStream is = null;
		try {
			is = file.getInputStream();
			//��ġó��
		} finally {
			is.close();
		}
		ModelAndView mav = new ModelAndView("upload/success");
		mav.addObject("filename", filename);
		mav.addObject("size", size);
		mav.addObject("contentType", contentType);
		
		return mav;
	}
	
	//fileupload/multi.html
		@RequestMapping(value="multi", method=RequestMethod.POST)
		public ModelAndView multi(@ModelAttribute FileUploadCommand command) throws IllegalStateException, IOException {
			for (FileUploadItem item : command.getUploadItems()) {
				MultipartFile file = item.getFile();
				if (file.isEmpty()) {
					continue;
				}
				String filename = file.getOriginalFilename();
				long size = file.getSize();
				String contentType = file.getContentType();
				file.transferTo(new File("C:/javaweb/workspace/webhello/upload/" + filename));
			}
			ModelAndView mav = new ModelAndView("upload/success");
			//mav.addObject("filename", filename);
			//mav.addObject("size", size);
			//mav.addObject("contentType", contentType);
			
			return mav;
		}
}
