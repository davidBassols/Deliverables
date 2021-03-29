package com.zerosmet.beer.catalogue.commons.bean;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity(name="manufacturer")
public class ManufacturerBean implements Serializable{

	private static final long serialVersionUID = 3188710788552010982L;
	
	@Id
	@GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
	private String id;
	private String name;
	private String nationality;
	@OneToMany(mappedBy="manufacturer")
	private Set<BeerBean> beers;
	
	public ManufacturerBean() {
		super();
	}

	public ManufacturerBean(String id, String name, String nationality, Set<BeerBean> beers) {
		super();
		this.id = id;
		this.name = name;
		this.nationality = nationality;
		this.beers = beers;
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

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Set<BeerBean> getBeers() {
		return beers;
	}

	public void setBeers(Set<BeerBean> beers) {
		this.beers = beers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((beers == null) ? 0 : beers.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((nationality == null) ? 0 : nationality.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ManufacturerBean other = (ManufacturerBean) obj;
		if (beers == null) {
			if (other.beers != null)
				return false;
		} else if (!beers.equals(other.beers))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nationality == null) {
			if (other.nationality != null)
				return false;
		} else if (!nationality.equals(other.nationality))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ManufacturerBean [id=" + id + ", name=" + name + ", nationality=" + nationality + ", beers=" + beers
				+ "]";
	}
	
	
	
	
}
