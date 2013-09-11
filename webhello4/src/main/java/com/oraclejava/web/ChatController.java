package com.oraclejava.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import com.oraclejava.domain.ChatMessage;

@Controller
@RequestMapping("/chat")
public class ChatController {
	private final List<DeferredResult<ChatMessage>> chatResultData = 
			new ArrayList<DeferredResult<ChatMessage>>();

	@RequestMapping(value="/polling",method=RequestMethod.GET)
	@ResponseBody
	public DeferredResult<ChatMessage> getMessage(){
		final DeferredResult<ChatMessage> deferredResult = 
				new DeferredResult<ChatMessage>(1000*30L,new ChatMessage("",""));
		this.chatResultData.add(deferredResult);
		deferredResult.onCompletion(new Runnable(){
			@Override
			public void run(){
				System.out.println("completed...");
				chatResultData.remove(deferredResult);
			}
		});
		return deferredResult;
	}

	@RequestMapping(value="/push",method=RequestMethod.POST)
	@ResponseBody
	public void pushMessage(@RequestParam String name, @RequestParam String message){
		for(DeferredResult<ChatMessage> item: this.chatResultData){
			System.out.println("setResult...");
			item.setResult(new ChatMessage(name,message));
		}
	}
}
