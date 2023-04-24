package org.vitu.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.vitu.jpa.model.Instrument;
import org.vitu.jpa.model.util.TypeInstrument;

public class CreateInstruments {

	public static void main(String[] args) {
		
		Instrument violon = new Instrument("Violon", TypeInstrument.CORDES);
		Instrument violoncelle = new Instrument("Violoncelle", TypeInstrument.CORDES);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("play-with-jpa");
		System.out.println("EMF = " + emf);
		
		EntityManager entityManager = emf.createEntityManager();
		
		EntityTransaction transaction = entityManager.getTransaction();
		
		System.out.println("Avant transaction :");
		System.out.println("Violon = " + violon);
		System.out.println("Violoncelle = " + violoncelle);
		
		transaction.begin();
		entityManager.persist(violon);
		entityManager.persist(violoncelle);
		transaction.commit();
		
		System.out.println("Après transaction :");		
		System.out.println("Violon = " + violon);
		System.out.println("Violoncelle = " + violoncelle);
	}
}