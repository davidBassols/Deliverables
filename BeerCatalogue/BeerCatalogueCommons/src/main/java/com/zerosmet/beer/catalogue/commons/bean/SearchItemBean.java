package com.zerosmet.beer.catalogue.commons.bean;

import com.zerosmet.beer.catalogue.commons.enumeration.Comparisson;

public class SearchItemBean {

	private Comparisson comp;
	private String value;
	
	public Comparisson getComp() {
		return comp;
	}
	public void setComp(Comparisson comp) {
		this.comp = comp;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "SearchItemBean [comp=" + comp + ", value=" + value + "]";
	}
}
