package com.example.Myserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class MyserverApplication {
@RequestMapping(value="/")
public String index(){
	return "<h1> Hello world</h1>";
}
	public static void main(String[] args) {
		SpringApplication.run(MyserverApplication.class, args);
	}

}
