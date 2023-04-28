package org.vitu.jpa.model.util;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum Civilite {
	
	MLLE("Mlle"),
	MME("Mme"),
	M("M");

	private String label;
	
	private Civilite(String label) {
		this.label = label;
	}
	
	public static Civilite of(String label) {
		
		return Arrays.stream(values())
				.filter(civilite -> civilite.label.equals(label))
				.findAny()
				.orElseThrow(() -> new NoSuchElementException("Pas de civilité pour : " + label));
		
//		for(Civilite civilite : values()) {
//			if(civilite.label.equals(label)) {
//				return civilite;
//			}
//		}
//		return null;
	}
}
