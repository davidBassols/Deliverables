package com.zerosmet.beer.catalogue.api.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import com.zerosmet.beer.catalogue.commons.bean.BeerBean;

@Component
public interface BeerRepository extends PagingAndSortingRepository<BeerBean, String>{

}
