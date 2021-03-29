package com.zerosmet.beer.catalogue.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.zerosmet.beer.catalogue.commons.bean.ManufacturerBean;

@Component
public interface ManufacturerRepository extends CrudRepository<ManufacturerBean, String>{

}
