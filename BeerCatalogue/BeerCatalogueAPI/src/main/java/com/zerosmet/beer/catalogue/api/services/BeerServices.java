package com.zerosmet.beer.catalogue.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.zerosmet.beer.catalogue.api.repository.BeerRepository;
import com.zerosmet.beer.catalogue.commons.bean.BeerBean;
import com.zerosmet.beer.catalogue.commons.bean.SearchBean;
import com.zerosmet.beer.catalogue.commons.bean.SearchItemBean;
import com.zerosmet.beer.catalogue.commons.bean.SortPaginationBean;
import com.zerosmet.beer.catalogue.commons.enumeration.Comparisson;
import com.zerosmet.beer.catalogue.commons.exception.EmptySearchException;
import com.zerosmet.beer.catalogue.commons.exception.ItemNotFoundException;
import com.zerosmet.beer.catalogue.commons.exception.MissingParameterException;
import com.zerosmet.beer.catalogue.commons.exception.WrongComparissonException;
import com.zerosmet.beer.catalogue.commons.exception.WrongSearchValueException;
import com.zerosmet.beer.catalogue.commons.util.PaginationUtils;

@Service
public class BeerServices {

	private final BeerRepository beerRepository;
	private EntityManager em;
	
	public BeerServices(BeerRepository beerRepository, EntityManager em) {
		this.beerRepository = beerRepository;
		this.em = em;
	}
	
	public List<BeerBean> getAll(SortPaginationBean sortPaginationBean){
		sortPaginationBean = PaginationUtils.clearPagination(sortPaginationBean);
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(sortPaginationBean.getSortType(), sortPaginationBean.getSortColumn()));
		Pageable paging = PageRequest.of(sortPaginationBean.getPage(), sortPaginationBean.getItemsXPage(), Sort.by(orders));
		
		List<BeerBean> beers = new ArrayList<>();
		Iterable<BeerBean> iter = beerRepository.findAll(paging);
		iter.forEach(beers::add);
		return beers;
	}
	
	public List<BeerBean> search(SearchBean searchBean) throws WrongComparissonException, EmptySearchException, WrongSearchValueException{
		SortPaginationBean sortPaginationBean = PaginationUtils.clearPagination(searchBean.getSortPaginationBean());
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaQuery<BeerBean> cq = cb.createQuery(BeerBean.class);

	    Root<BeerBean> root = cq.from(BeerBean.class);
	    List<Predicate> predicates = new ArrayList<>();
	    
    	if(searchBean.getName() != null && !searchBean.getName().isEmpty()) {
    		for(SearchItemBean item: searchBean.getName()) {
    			if(Comparisson.EQUAL.equals(item.getComp())) {
    				predicates.add(cb.equal(cb.lower(root.get("name")), item.getValue().toLowerCase()));
    			}else if(Comparisson.LIKE.equals(item.getComp())) {
    				predicates.add(cb.like(cb.lower(root.get("name")), "%"+item.getValue().toLowerCase()+"%"));
    			}else {
    				throw new WrongComparissonException("Comparisson "+item.getComp()+" is invalid for 'String' type. Use 'EQUAL' or 'LIKE' instead. ");
    			}
    		}
    	}
    	if(searchBean.getDescription() != null && !searchBean.getDescription().isEmpty()) {
    		for(SearchItemBean item: searchBean.getDescription()) {
    			if(Comparisson.EQUAL.equals(item.getComp())) {
    				predicates.add(cb.equal(cb.lower(root.get("description")), item.getValue().toLowerCase()));
    			}else if(Comparisson.LIKE.equals(item.getComp())) {
    				predicates.add(cb.like(cb.lower(root.get("description")), "%"+item.getValue().toLowerCase()+"%"));
    			}else {
    				throw new WrongComparissonException("Comparisson "+item.getComp()+" is invalid for 'String' type. Use 'EQUAL' or 'LIKE' instead. ");
    			}
    		}
    	}
    	if(searchBean.getBeerType() != null && !searchBean.getBeerType().isEmpty()) {
    		for(SearchItemBean item: searchBean.getBeerType()) {
    			if(Comparisson.EQUAL.equals(item.getComp())) {
    				predicates.add(cb.equal(cb.lower(root.get("beer_type")), item.getValue().toLowerCase()));
    			}else if(Comparisson.LIKE.equals(item.getComp())) {
    				predicates.add(cb.like(cb.lower(root.get("beer_type")), "%"+item.getValue().toLowerCase()+"%"));
    			}else {
    				throw new WrongComparissonException("Comparisson "+item.getComp()+" is invalid for 'String' type. Use 'EQUAL' or 'LIKE' instead. ");
    			}
    		}
    	}
    	if(searchBean.getGraduation() != null && !searchBean.getGraduation().isEmpty()) {
    		for(SearchItemBean item: searchBean.getGraduation()) {
    			try {
    				Float.parseFloat(item.getValue());
    			}catch(NumberFormatException e) {
    				throw new WrongSearchValueException("Value of 'gradutaion' must be a positive number");
    			}
    			
    			if(Comparisson.EQUAL.equals(item.getComp())) {
    				predicates.add(cb.equal(root.get("graduation"), item.getValue()));
    			}else if(Comparisson.GREATER_THAN.equals(item.getComp())) {
    				predicates.add(cb.greaterThan(root.get("graduation"), item.getValue()));
    			}else if(Comparisson.GREATER_OR_EQUAL.equals(item.getComp())) {
    				predicates.add(cb.greaterThanOrEqualTo(root.get("graduation"), item.getValue()));
    			}else if(Comparisson.LESS_OR_EQUAL.equals(item.getComp())) {
    				predicates.add(cb.lessThanOrEqualTo(root.get("graduation"), item.getValue()));
    			}else if(Comparisson.LESS_THAN.equals(item.getComp())) {
    				predicates.add(cb.lessThan(root.get("graduation"), item.getValue()));
    			}else {
    				throw new WrongComparissonException("Comparisson "+item.getComp()+" is invalid for 'Float' type. Use 'EQUAL', 'GREATER_THAN','GREATER_OR_EQUAL','LESS_OR_EQUAL', 'LESS_THAN' or 'LIKE' instead. ");
    			}
    		}
    	}
    	
    	if(predicates.isEmpty()) {
    		throw new EmptySearchException("No criteria selected for the search");
    	}
	    cq.where(predicates.toArray(new Predicate[predicates.size()]));
	    if(Direction.ASC.equals(sortPaginationBean.getSortType())) {
	    	cq.orderBy(cb.asc(root.get(sortPaginationBean.getSortColumn())));
	    }
	    
	    TypedQuery<BeerBean> tq = em.createQuery(cq);
	    tq.setFirstResult(sortPaginationBean.getPage()*sortPaginationBean.getItemsXPage());
	    tq.setMaxResults(sortPaginationBean.getItemsXPage());

	    return tq.getResultList();
	    
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
