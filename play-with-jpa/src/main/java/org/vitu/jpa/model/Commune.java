package org.vitu.jpa.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Commune implements Serializable {

	@Id@Column(length = 8)
	private String codePostal;
	@Column(length = 80)
	private String nom;

	@OneToOne(cascade = CascadeType.PERSIST)
	private Maire maire;

	public Commune() {
	}

	public Commune(String nom, String codePostal) {
		this.nom = nom;
		this.codePostal = codePostal;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	
	public Maire getMaire() {
		return maire;
	}

	public void setMaire(Maire maire) {
		this.maire = maire;
	}

	@Override
	public String toString() {
		return "Commune [nom=" + nom + ", codePostal=" + codePostal + ", maire=" + maire + "]";
	}
}
