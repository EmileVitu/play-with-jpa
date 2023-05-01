package org.vitu.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.vitu.jpa.model.Commune;
import org.vitu.jpa.model.Departement;
import org.vitu.jpa.model.Maire;

public class FunWithCommunes {

	public static void main(String[] args) {
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("play-with-jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		Commune commune = entityManager.find(Commune.class, "01007");
		
		System.out.println(commune);
		
		Maire maire = commune.getMaire();
		Departement departement = commune.getDepartement();
		
		List<Commune> communes = departement.getCommunes();
		
		System.out.println("Commune = " + commune);
		System.out.println("Maire = " + maire);
		System.out.println("# Commune = " + communes.size());
	}

}
