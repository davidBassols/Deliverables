package com.zerosmet.beer.catalogue.commons.bean;

import java.util.List;

public class SearchBean {

	private List<SearchItemBean> name;
	private List<SearchItemBean> description;
	private List<SearchItemBean> graduation;
	private List<SearchItemBean> beerType;
	private SortPaginationBean sortPaginationBean;
	
	public List<SearchItemBean> getName() {
		return name;
	}
	public void setName(List<SearchItemBean> name) {
		this.name = name;
	}
	public List<SearchItemBean> getDescription() {
		return description;
	}
	public void setDescription(List<SearchItemBean> description) {
		this.description = description;
	}
	public List<SearchItemBean> getGraduation() {
		return graduation;
	}
	public void setGraduation(List<SearchItemBean> graduation) {
		this.graduation = graduation;
	}
	public List<SearchItemBean> getBeerType() {
		return beerType;
	}
	public void setBeerType(List<SearchItemBean> beerType) {
		this.beerType = beerType;
	}
	public SortPaginationBean getSortPaginationBean() {
		return sortPaginationBean;
	}
	public void setSortPaginationBean(SortPaginationBean sortPaginationBean) {
		this.sortPaginationBean = sortPaginationBean;
	}
	
	@Override
	public String toString() {
		return "SearchBean [name=" + name + ", description=" + description + ", graduation=" + graduation
				+ ", beerType=" + beerType + ", sortPaginationBean=" + sortPaginationBean + "]";
	}
	
}
