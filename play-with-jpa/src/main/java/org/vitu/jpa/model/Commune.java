package org.vitu.jpa.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(name = "Commune.byPopulationMin",
				query = "select c.nom, c.population from Commune c where c.population > :pop_min"
	)
})
@Entity(name = "Commune")
@Table(name = "Commune")
public class Commune implements Serializable {

	@Id@Column(length = 8)
	private String codePostal;
	@Column(length = 80)
	private String nom;

	// @OneToOne(cascade = CascadeType.PERSIST, mappedBy = "commune")
	@OneToOne(cascade = CascadeType.PERSIST)
	private Maire maire;
	
	@ManyToOne
	private Departement departement;
	
	private int population;

	public Commune() {
	}

	public Commune(String nom, String codePostal, int population) {
		this.nom = nom;
		this.codePostal = codePostal;
		this.population = population;
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
	
	public Departement getDepartement() {
		return departement;
	}

	public void setDepartement(Departement departement) {
		this.departement = departement;
	}

	public String getCodeDepartement() {
		return codePostal.substring(0, 2);
	}
	
	
	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	@Override
	public String toString() {
		return "Commune [codePostal=" + codePostal + ", nom=" + nom + ", maire=" + maire + ", departement="
				+ departement + ", population=" + population + "]";
	}

 
}
