package org.vitu.jpa;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.vitu.jpa.model.Maire;
import org.vitu.jpa.model.util.Civilite;

public class FunWithMaire {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("play-with-jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		Date date = new Date(1952, 8, 4);
		Maire maire = new Maire("Chirac", "Jacques", Civilite.M, date);
		
		entityManager.getTransaction().begin();
		entityManager.persist(maire);
		entityManager.getTransaction().commit();
		
		System.out.println("Maire = " + maire);
		
		date.setMonth(7);
		
		System.out.println("Maire = " + maire);
	}

}
