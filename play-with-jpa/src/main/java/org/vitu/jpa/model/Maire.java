package org.vitu.jpa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

import org.eclipse.persistence.internal.jpa.parsing.TemporalLiteralNode.TemporalType;
import org.vitu.jpa.model.util.Civilite;

@Entity
public class Maire implements Serializable {
	
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	@Column(nullable = false, length = 80)
	private String nom;
	
	@Column(length = 80)
	private String prenom;
	
	@Column(length = 5)
	@Enumerated(EnumType.STRING)
	private Civilite civilite;
	
	@OneToOne(cascade = CascadeType.PERSIST, mappedBy = "maire")
	private Commune commune;
	
//	@Temporal(TemporalType.DATE)
	private Date dateDeNaissance;

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(
			name="address",
			column=@Column(name="address_libelle")
		)
	})
	@AssociationOverrides(
		@AssociationOverride(
			name = "commune",
			joinColumns = @JoinColumn(name = "address_commune_codePostal")
		)
	)
	private Address address;
	
	public Maire() {
	}

	public Maire(String nom, String prenom, Civilite civilite, Date dateDeNaissance) {
		this.nom = nom;
		this.prenom = prenom;
		this.civilite = civilite;
		this.dateDeNaissance = dateDeNaissance;
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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Civilite getCivilite() {
		return civilite;
	}

	public void setCivilite(Civilite civilite) {
		this.civilite = civilite;
	}

	public Date getDateDeNaissance() {
		return new Date(dateDeNaissance.getTime());
	}

	public void setDateDeNaissance(Date dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}

	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "Maire [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", civilite=" + civilite
				+ ", dateDeNaissance=" + dateDeNaissance + "]";
	}
}
