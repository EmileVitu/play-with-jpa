package org.vitu.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.vitu.jpa.model.util.TypeInstrument;


// @Entity
public class Instrument implements Serializable {
	
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	@Column(length=80, nullable = false)
	private String nom;
	
	@Column(length=20)
	@Enumerated(EnumType.STRING)
	private TypeInstrument type;
	
	public Instrument() {
	}

	public Instrument(String nom, TypeInstrument type) {
		this.nom = nom;
		this.type = type;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public TypeInstrument getType() {
		return type;
	}
	
	public void setType(TypeInstrument type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Instrument [id=" + id + ", nom=" + nom + ", type=" + type + "]";
	}
	
}
