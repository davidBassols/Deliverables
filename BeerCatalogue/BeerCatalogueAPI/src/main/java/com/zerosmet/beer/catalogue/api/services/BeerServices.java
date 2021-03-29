package com.zerosmet.beer.catalogue.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zerosmet.beer.catalogue.api.repository.BeerRepository;
import com.zerosmet.beer.catalogue.commons.bean.BeerBean;
import com.zerosmet.beer.catalogue.commons.exception.ItemNotFoundException;
import com.zerosmet.beer.catalogue.commons.exception.MissingParameterException;

@Service
public class BeerServices {

	private final BeerRepository beerRepository;
	
	public BeerServices(BeerRepository beerRepository) {
		this.beerRepository = beerRepository;
	}
	
	public List<BeerBean> getAll(){
		List<BeerBean> beers = new ArrayList<BeerBean>();
		Iterable<BeerBean> iter = beerRepository.findAll();
		iter.forEach(beers::add);
		return beers;
	}
	
	public BeerBean load(String id) throws ItemNotFoundException{
		Optional<BeerBean> opt = beerRepository.findById(id);
		if(opt.isEmpty()) {
			throw new ItemNotFoundException("The Beer with id "+id+" was not found.");
		}else {
			return opt.get();
		}
	}
	
	public void save(BeerBean bean) {
		bean.setId(null);
		beerRepository.save(bean);
	}
	
	public void update(BeerBean bean) throws MissingParameterException {
		if(bean.getId() == null) {
			throw new MissingParameterException("ID field is required to update the entry");
		}
		beerRepository.save(bean);
	}
	
	public void delete(String id) {
		beerRepository.deleteById(id);
	}
}
