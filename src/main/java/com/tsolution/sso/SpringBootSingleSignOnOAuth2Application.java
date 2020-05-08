package com.tsolution.sso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableResourceServer
@ComponentScan("com.tsolution.sso.*")
public class SpringBootSingleSignOnOAuth2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSingleSignOnOAuth2Application.class, args);
	}

}
