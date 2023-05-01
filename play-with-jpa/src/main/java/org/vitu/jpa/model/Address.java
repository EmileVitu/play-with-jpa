package org.vitu.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class Address implements Serializable {

	@Column(length = 80)
	private String libelle;
	
	@ManyToOne
	private Commune commune;
	
	public Address() {
	}
	
	public Address(String libelle, Commune commune) {
		this.libelle = libelle;
		this.commune = commune;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Commune getCommune() {
		return commune;
	}

	public void setCommune(Commune commune) {
		this.commune = commune;
	}

	@Override
	public String toString() {
		return "Address [libelle=" + libelle + ", commune=" + commune + "]";
	}	
}
