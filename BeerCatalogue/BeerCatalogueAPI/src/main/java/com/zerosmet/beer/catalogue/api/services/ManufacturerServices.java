package com.zerosmet.beer.catalogue.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zerosmet.beer.catalogue.api.repository.ManufacturerRepository;
import com.zerosmet.beer.catalogue.commons.bean.ManufacturerBean;
import com.zerosmet.beer.catalogue.commons.exception.ItemNotFoundException;
import com.zerosmet.beer.catalogue.commons.exception.MissingParameterException;

@Service
public class ManufacturerServices {

	private final ManufacturerRepository manufacturerRepository;
	
	public ManufacturerServices(ManufacturerRepository manufacturerRepository) {
		this.manufacturerRepository = manufacturerRepository;
	}
	
	public List<ManufacturerBean> getAll(){
		List<ManufacturerBean> beers = new ArrayList<ManufacturerBean>();
		Iterable<ManufacturerBean> iter = manufacturerRepository.findAll();
		iter.forEach(beers::add);
		return beers;
	}
	
	public ManufacturerBean load(String id) throws ItemNotFoundException{
		Optional<ManufacturerBean> opt = manufacturerRepository.findById(id);
		if(opt.isEmpty()) {
			throw new ItemNotFoundException("The Manufacturer with id "+id+" was not found.");
		}else {
			return opt.get();
		}
	}
	
	public void save(ManufacturerBean bean) {
		bean.setId(null);
		manufacturerRepository.save(bean);
	}
	
	public void update(ManufacturerBean bean) throws MissingParameterException {
		if(bean.getId() == null) {
			throw new MissingParameterException("ID field is required to update the entry");
		}
		manufacturerRepository.save(bean);
	}
	
	public void delete(String id) {
		manufacturerRepository.deleteById(id);
	}
}
