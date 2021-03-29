package com.zerosmet.beer.catalogue.commons.bean;

import org.springframework.data.domain.Sort.Direction;

public class SortPaginationBean {

	private String sortColumn;
	private Direction sortType;
	private int page;
	private int itemsXPage;
	
	public String getSortColumn() {
		return sortColumn;
	}
	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}
	public Direction getSortType() {
		return sortType;
	}
	public void setSortType(Direction sortType) {
		this.sortType = sortType;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getItemsXPage() {
		return itemsXPage;
	}
	public void setItemsXPage(int itemsXPage) {
		this.itemsXPage = itemsXPage;
	}
	@Override
	public String toString() {
		return "SortPaginationBean [sortColumn=" + sortColumn + ", sortType=" + sortType + ", page=" + page
				+ ", itemsXPage=" + itemsXPage + "]";
	}
	
}
