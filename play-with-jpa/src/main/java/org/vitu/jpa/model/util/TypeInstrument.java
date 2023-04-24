package org.vitu.jpa.model.util;

import java.util.Arrays;

public enum TypeInstrument {
	CORDES("cordes"),
	VENTS("vent"),
	BOIS("bois"),
	CUIVRES("cuivre"),
	CORDES_PINCEES("cordes pincées"),
	CORDES_FRAPPEES("cordes frappées"),
	PERCUSSION("percussion");
	
	private String label;

	private TypeInstrument(String label) {
		this.label = label;
	}
	
	public static TypeInstrument of(String label) {
		
		return Arrays.stream(values())
			.filter(value -> value.label.equals(label))
			.findFirst()
			.orElseThrow();
		
//		for (TypeInstrument value : values()) {
//			if(value.label.equals(label)) {
//				return value;
//			}
//		}
//		return null;
	}
	
}
