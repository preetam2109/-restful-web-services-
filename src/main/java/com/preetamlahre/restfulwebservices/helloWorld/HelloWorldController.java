package com.preetamlahre.restfulwebservices.helloWorld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@GetMapping(path="/hello-world")
	public String HelloWorld() {
	return "Hello World";	
	}
	
	
	
	@GetMapping(path="/hello-world-Bean")
	public HelloWorldBean HelloWorldBean() {
	return new HelloWorldBean("Hello Wolrd");	
	}
	
	@GetMapping(path="/hello-world-Bean-pathvariable/{name}")
	public HelloWorldBean HelloWorldBeanPathvariable(@PathVariable String name) {
	return new HelloWorldBean("Hello Wolrd " + name);	
	}
}
