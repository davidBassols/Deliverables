package com.zerosmet.beer.catalogue.rest.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zerosmet.beer.catalogue.api.services.BeerServices;
import com.zerosmet.beer.catalogue.commons.bean.BeerBean;
import com.zerosmet.beer.catalogue.commons.exception.ItemNotFoundException;
import com.zerosmet.beer.catalogue.commons.exception.MissingParameterException;

@Controller
@RequestMapping("/beer")
public class BeerController {
	private static Logger logger = LoggerFactory.getLogger(BeerController.class);
	
	private final BeerServices beerServices;

	public BeerController(BeerServices beerServices) {
		this.beerServices = beerServices;
	}

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BeerBean>> getAllBeers() {
		logger.debug("GET /beer");
		List<BeerBean> result = beerServices.getAll();
		return new ResponseEntity<List<BeerBean>>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BeerBean> getBeersById(@PathVariable String id) {
		logger.debug("GET /beer/"+id);
		try {
			BeerBean result = beerServices.load(id);
			return new ResponseEntity<>(result, HttpStatus.OK);
		}catch(ItemNotFoundException e) {
			logger.error(e.getMessage(),e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "")
	public ResponseEntity<BeerBean> save(@RequestBody BeerBean bean) {
		logger.debug("POST /beer");
		beerServices.save(bean);
		return new ResponseEntity<>(HttpStatus.OK);
	} 

	@PutMapping(value = "")
	@ExceptionHandler(MissingParameterException.class)
	public ResponseEntity<BeerBean> update(@RequestBody BeerBean bean) {
		logger.debug("PUT /beer");
		try {
			beerServices.update(bean);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(MissingParameterException e) {
			logger.error(e.getMessage(),e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}


	@DeleteMapping(value = "/{id}")
	public ResponseEntity<BeerBean> delete(@PathVariable String id) {
		logger.debug("DELETE /beer/"+id);
		beerServices.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	} 
}
