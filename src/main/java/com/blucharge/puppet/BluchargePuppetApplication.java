package com.blucharge.puppet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.blucharge.core","com.blucharge.puppet"})
public class BluchargePuppetApplication {
	public static void main(String[] args) {
		SpringApplication.run(BluchargePuppetApplication.class, args);
	}

}
