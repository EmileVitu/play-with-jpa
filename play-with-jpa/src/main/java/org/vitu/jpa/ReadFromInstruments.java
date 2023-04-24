package org.vitu.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.vitu.jpa.model.Instrument;

public class ReadFromInstruments {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("play-with-jpa");
		System.out.println("EMF = " + emf);
		
		EntityManager entityManager = emf.createEntityManager();
		
		Instrument inst1 = entityManager.find(Instrument.class, 1);
		Instrument inst2 = entityManager.find(Instrument.class, 2);

		System.out.println("Instrument 1 = " + inst1);
		System.out.println("Instrument 2 = " + inst2);
	}

}
