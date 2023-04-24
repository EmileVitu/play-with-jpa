package org.vitu.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.vitu.jpa.model.Instrument;
import org.vitu.jpa.model.util.TypeInstrument;

public class PlayWithMusiciens {

	public static void main(String[] args) {
		
		Instrument instrument = new Instrument("Violon", TypeInstrument.CORDES);
		
		System.out.println("Instrument = " + instrument);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("play-with-jpa");
		System.out.println("EMF = " + emf);
		
		EntityManager entityManager = emf.createEntityManager();
		
		entityManager.persist(instrument);

	}
}