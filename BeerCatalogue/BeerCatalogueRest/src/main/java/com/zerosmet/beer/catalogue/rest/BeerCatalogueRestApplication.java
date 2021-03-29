package com.zerosmet.beer.catalogue.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.zerosmet.beer.catalogue.api.repository")
@EntityScan("com.zerosmet.beer.catalogue.commons")
@SpringBootApplication(scanBasePackages = {"com.zerosmet.beer.catalogue"})
public class BeerCatalogueRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeerCatalogueRestApplication.class, args);
	}

}
