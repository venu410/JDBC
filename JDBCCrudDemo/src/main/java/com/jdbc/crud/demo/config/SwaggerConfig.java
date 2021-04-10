package com.jdbc.crud.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	
	@Bean
	public Docket getApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("EMPLOYEE-DETAILS")
				.description("Employee Details API reference")
				.termsOfServiceUrl("")
				.contact("vkumar.maddela@gmail.com").license("Venu Kumar License")
				.licenseUrl("https://github.com/venu410").version("1.0").build();
	}

}
