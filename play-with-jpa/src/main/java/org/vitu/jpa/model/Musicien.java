package org.vitu.jpa.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.GenerationType;

// @Entity
public class Musicien implements Serializable {
	
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	@Column(length = 80, nullable = false)
	private String nom;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<Instrument> instruments = new HashSet<>();
	
	public Musicien() {
	}

	public boolean addInstrument(Instrument instrument) {
		return this.instruments.add(instrument);
	}
	
	public Musicien(String nom) {
		this.nom = nom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "Musicien [id=" + id + ", nom=" + nom + ", instruments=" + instruments + "]";
	}
	
}
