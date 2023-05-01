package org.vitu.jpa.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Departement implements Serializable {
	
	@Id @Column(length = 4)
	private String codeDepartement;
	
	@Column(length = 40, nullable = false)
	private String nom;
	
	@OneToMany(mappedBy = "departement")
	private List<Commune> communes = new ArrayList<>();
	
	public Departement() {
		super();
	}

	public Departement(String codeDepartement, String nom) {
		this.codeDepartement = codeDepartement;
		this.nom = nom;
	}

	public String getCodeDepartement() {
		return codeDepartement;
	}

	public void setCodeDepartement(String codeDepartement) {
		this.codeDepartement = codeDepartement;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void addCommune(Commune commune) {
		this.communes.add(commune);
		commune.setDepartement(this);
	}
	
	public List<Commune> getCommunes() {
		return new ArrayList<>(this.communes);
	}
	
	@Override
	public String toString() {
		return "Departement [codeDepartement=" + codeDepartement + ", nom=" + nom + "]";
	}
}
