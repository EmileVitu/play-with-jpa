package org.vitu.jpa.model;

import org.vitu.jpa.model.util.TypeInstrument;

public class Instrument {
	
	private String nom;
	private TypeInstrument type;

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

	@Override
	public String toString() {
		return "Instrument [nom=" + nom + ", type=" + type + "]";
	}
	
}
