package com.oraclejava.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import com.oraclejava.domain.ChatMessage;
import com.oraclejava.service.ChatDeferredResultManager;

@Controller
@RequestMapping("/chat")
public class ChatController {
	private final List<DeferredResult<ChatMessage>> chatResultData = 
			new ArrayList<DeferredResult<ChatMessage>>();

	@Autowired
	private ChatDeferredResultManager chatDeferredResultManager;
	
	@RequestMapping(value="/polling",method=RequestMethod.GET)
	@ResponseBody
	public DeferredResult<ChatMessage> getMessage(){
		return chatDeferredResultManager.createDeferredResult();
	}

	// treat message (sent by browser)
	@RequestMapping(value="/push",method=RequestMethod.POST)
	@ResponseBody
	public void pushMessage(@RequestParam String name, @RequestParam String message){
		this.chatDeferredResultManager.propagateAllDeferredResult(new ChatMessage(name,message));
	}
}
