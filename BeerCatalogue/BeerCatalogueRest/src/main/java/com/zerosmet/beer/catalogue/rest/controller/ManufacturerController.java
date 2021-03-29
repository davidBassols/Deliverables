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

import com.zerosmet.beer.catalogue.api.services.ManufacturerServices;
import com.zerosmet.beer.catalogue.commons.bean.ManufacturerBean;
import com.zerosmet.beer.catalogue.commons.bean.SortPaginationBean;
import com.zerosmet.beer.catalogue.commons.exception.ItemNotFoundException;
import com.zerosmet.beer.catalogue.commons.exception.MissingParameterException;

@Controller
@RequestMapping("/manufacturer")
public class ManufacturerController {
	private static Logger logger = LoggerFactory.getLogger(ManufacturerController.class);
	
	private final ManufacturerServices manufacturerServices;

	public ManufacturerController(ManufacturerServices manufacturerServices) {
		this.manufacturerServices = manufacturerServices;
	}

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ManufacturerBean>> getAllBeers(@RequestBody(required=false) SortPaginationBean sortPaginationBean) {
		logger.debug("GET /manufacturer");
		List<ManufacturerBean> result = manufacturerServices.getAll(sortPaginationBean);
		return new ResponseEntity<List<ManufacturerBean>>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ManufacturerBean> getBeersById(@PathVariable String id) {
		logger.debug("GET /manufacturer/"+id);
		try {
			ManufacturerBean result = manufacturerServices.load(id);
			return new ResponseEntity<>(result, HttpStatus.OK);
		}catch(ItemNotFoundException e) {
			logger.error(e.getMessage(),e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "")
	public ResponseEntity<ManufacturerBean> save(@RequestBody ManufacturerBean bean) {
		logger.debug("POST /manufacturer");
		manufacturerServices.save(bean);
		return new ResponseEntity<>(HttpStatus.OK);
	} 

	@PutMapping(value = "")
	@ExceptionHandler(MissingParameterException.class)
	public ResponseEntity<ManufacturerBean> update(@RequestBody ManufacturerBean bean) {
		logger.debug("PUT /manufacturer");
		try {
			manufacturerServices.update(bean);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(MissingParameterException e) {
			logger.error(e.getMessage(),e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}


	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ManufacturerBean> delete(@PathVariable String id) {
		logger.debug("DELETE /manufacturer/"+id);
		manufacturerServices.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	} 
}
