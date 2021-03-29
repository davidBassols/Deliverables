package com.zerosmet.beer.catalogue.commons.util;

import org.springframework.data.domain.Sort.Direction;

import com.zerosmet.beer.catalogue.commons.bean.SortPaginationBean;

public class PaginationUtils {

	private PaginationUtils() {}
	public static SortPaginationBean clearPagination(SortPaginationBean bean) {
		if(bean == null) {
			bean = new SortPaginationBean();
		}
		if(bean.getSortColumn() == null) {
			bean.setSortColumn("id");
		}
		if(bean.getSortType() == null) {
			bean.setSortType(Direction.ASC);
		}
		if(bean.getPage()<0) {
			bean.setPage(0);
		}
		if(bean.getItemsXPage()<=0 || bean.getItemsXPage() > 100) {
			bean.setItemsXPage(100);
		}
		return bean;
	}
}
