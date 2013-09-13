package com.oraclejava.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import com.oraclejava.domain.ChatMessage;

//@Service
public class ChatDeferredResultManager {
	private final List<DeferredResult<ChatMessage>> chatResultData = 
			new ArrayList<DeferredResult<ChatMessage>>();
	
	public DeferredResult<ChatMessage> createDeferredResult(){
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
	
	
	public void propagateAllDeferredResult(ChatMessage message){
		for(DeferredResult<ChatMessage> item: this.chatResultData){
			System.out.println("setResult...");
			item.setResult(new ChatMessage(message.getName(),message.getMessage()));
		}
	}
	
	
}
