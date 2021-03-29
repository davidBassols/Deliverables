package com.zerosmet.beer.catalogue.commons.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="beer")
public class BeerBean implements Serializable{
	private static final long serialVersionUID = -8613284394241220396L;
	
	@Id
	@GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
	private String id;
	private String name;
	private String description;
	private float graduation;
	private String beerType;
	@ManyToOne
    @JoinColumn(name = "manufacturer_id", insertable = false, updatable = false)
	@JsonIgnore
	private ManufacturerBean manufacturer;
	
	
	public BeerBean() {
		super();
	}

	public BeerBean(String id, String name, String description, float graduation, String beerType,
			ManufacturerBean manufacturer) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.graduation = graduation;
		this.beerType = beerType;
		this.manufacturer = manufacturer;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getGraduation() {
		return graduation;
	}
	public void setGraduation(float graduation) {
		this.graduation = graduation;
	}
	public String getType() {
		return beerType;
	}
	public void setType(String beerType) {
		this.beerType = beerType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ManufacturerBean getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(ManufacturerBean manufacturer) {
		this.manufacturer = manufacturer;
	}
	@Override
	public String toString() {
		return "BeerBean [id=" + id + ", name=" + name + ", graduation=" + graduation + ", beerType=" + beerType
				+ ", description=" + description + ", manufacturer=" + manufacturer + "]";
	}
	
	
	
}
