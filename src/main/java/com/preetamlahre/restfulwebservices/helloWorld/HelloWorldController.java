package com.preetamlahre.restfulwebservices.helloWorld;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	private MessageSource messagesource;
	public HelloWorldController(MessageSource messagesource){
		this.messagesource=messagesource;
	}
	@GetMapping(path="/hello-world")
	public String HelloWorld() {
	return "Hello World";	
	}
	
	
	
	@GetMapping(path="/hello-world-Bean")
	public HelloWorldBean HelloWorldBean() {
	return new HelloWorldBean("Hello Wolrd");	
	}
	
	@GetMapping(path="/hello-world-Bean-path-variable/{name}")
	public HelloWorldBean HelloWorldBeanPathvariable(@PathVariable String name) {
	return new HelloWorldBean("Hello Wolrd " + name);	
	}
	
	
	@GetMapping(path = "/hello-world-internationalized")
	public String helloWorldInternationalized() {
		Locale locale = LocaleContextHolder.getLocale();
		return messagesource.getMessage("good.morning.message", null, "Default Message", locale );
}
}
