package com.odos.smartaqua.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	@Bean
	public Docket postApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("public-api").apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.odos.smartaqua.controller")).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("smartaqua-services").description("").version("1.0")
				.termsOfServiceUrl("smartaqua").license("Developed By smartaqua.co.in")
				.contact(new Contact(null, null, null)).build();
	}

}
