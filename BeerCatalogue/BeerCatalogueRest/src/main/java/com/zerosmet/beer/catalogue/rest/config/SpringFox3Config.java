package com.zerosmet.beer.catalogue.rest.config;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.HttpAuthenticationScheme;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFox3Config {
	
	@Value("${application.title}")
	private String title;
	
	@Value("${application.description}")
	private String description;
	
	@Value("${application.version}")
	private String version;
	
	
	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.OAS_30)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.paths(Predicate.not(PathSelectors.regex("/error.*")))
				.build()
				.apiInfo(getApiInfo())
				.securityContexts(Arrays.asList(securityContext()))
				.securitySchemes(Arrays.asList(jwtScheme()))
				.useDefaultResponseMessages(false)
				;
				
	}
	
	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder()
				.title(title)
				.description(description)
				.version(version)
				.build();
	}
	
	private SecurityScheme jwtScheme() {
		return HttpAuthenticationScheme.JWT_BEARER_BUILDER
				.name("JWT")
				.build();
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder()
				.securityReferences(List.of(defaultAuth()))
				.operationSelector(o -> o.requestMappingPattern().matches("/.*"))
				.build();
	}

	private SecurityReference defaultAuth() {
		return SecurityReference.builder()
				.scopes(new AuthorizationScope[0])
				.reference("JWT")
				.build();
	}
}

