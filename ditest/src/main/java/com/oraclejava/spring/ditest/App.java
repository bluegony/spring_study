package com.oraclejava.spring.ditest;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.oraclejava.spring.service.HelloService;
import com.oraclejava.spring.service.impl.HelloServiceImpl;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	//2 xml에서 bean 생성 이용
       // BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext.xml");
       // HelloService helloService = (HelloService)factory.getBean("helloService");
    	
    	//1 그냥 java로 생성
    	//HelloService helloService = new HelloServiceImpl();
    	
    	//3 annotation 이용 @Component("aaa")이렇게 이름을 써주면 쓸때도 getBean("aaa")처럼 이름을 써야.
    	BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext.xml");
    	HelloService helloService = factory.getBean(HelloService.class);
        helloService.hello();
    }
}
