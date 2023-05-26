package org.vitu.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class PlayWithJPQLQueries {
	
	public static void main(String[] args) {
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("play-with-jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		String jpql00 = "select count(*) from Commune";
		Query query00 = entityManager.createNativeQuery(jpql00);

		Long count00 = (Long)query00.getSingleResult();
		System.out.println("Count = " + count00);
		
	}
}
