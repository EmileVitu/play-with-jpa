package org.vitu.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class PlayWithQueries {
	
	public static void main(String[] args) {
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("play-with-jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		String sql00 = "select count(*) from Commune";
		
		Query query00 = entityManager.createNativeQuery(sql00);

		Object singleResult00 = query00.getSingleResult();

		Long count = (Long)query00.getSingleResult();
		System.out.println("Count = " + count);
		
		String sql01 = "select count(*), max(population) count from Commune";
		Query query01 = entityManager.createNativeQuery(sql01);
		
		Object[] result01 = (Object[])query01.getSingleResult();
		Long count01 = (Long)result01[0];
		Integer max01 = (Integer)result01[1];
		
		System.out.println("Class = " + result01[0].getClass());
		System.out.println("Class = " + result01[1].getClass());
		System.out.println("Count = " + count01);
		System.out.println("Max = " + max01);

//		int count = (int)query00.getSingleResult();
//		Class class1 = singleResult00.getClass();
//		System.out.println("Class = " + class1);
	}
}
