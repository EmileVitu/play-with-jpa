package org.vitu.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.GenerationType;

@Entity
public class Musicien implements Serializable {
	
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	@Column(length = 80, nullable = false)
	private String nom;

	
	
	public Musicien() {
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
		return "Musicien [id=" + id + ", nom=" + nom + "]";
	}
	
}
