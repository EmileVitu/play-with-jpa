package org.vitu.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class PlayWithNativeQueries {
	
	public static void main(String[] args) {
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("play-with-jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		String sql00 = "select count(*) from Commune";
		Query query00 = entityManager.createNativeQuery(sql00);
		Object singleResult00 = query00.getSingleResult();
		Long count00 = (Long)query00.getSingleResult();
		System.out.println("Count = " + count00);
		
		String sql01 = "select count(*), max(population) count from Commune";
		Query query01 = entityManager.createNativeQuery(sql01);
		Object[] result01 = (Object[])query01.getSingleResult();
		Long count01 = (Long)result01[0];
		Integer max01 = (Integer)result01[1];
		System.out.println("Class = " + result01[0].getClass());
		System.out.println("Class = " + result01[1].getClass());
		System.out.println("Count = " + count01);
		System.out.println("Max = " + max01);
		
		String sql02 = "select nom, population from Commune where population > 250000";
		
		Query query02 = entityManager.createNativeQuery(sql02);
		List result02 = query02.getResultList();
		Object object = result02.get(0);
		
		System.out.println("# resultats : " + result02.size());
		System.out.println("Class = " + object.getClass());
		
		for (Object[] line : (List<Object[]>)result02) {
//			System.out.println("0 : " + line[0].getClass());
//			System.out.println("1 : " + line[1].getClass());
			String nom = (String)line[0];
			int population = (int)line[1];
			System.out.println("Nom : " + nom + " - " + population + " habitants.");
		}
		
		String sql03 = "select nom, population from Commune where population > ?1";
		Query query03 = entityManager.createNativeQuery(sql03);
		query03.setParameter(1, 250000);
		List result03 = query03.getResultList();
		
		System.out.println("# resultats : " + result03.size());
		
		for (Object[] line : (List<Object[]>)result03) {
//			System.out.println("0 : " + line[0].getClass());
//			System.out.println("1 : " + line[1].getClass());
			String nom = (String)line[0];
			int population = (int)line[1];
			System.out.println("Nom : " + nom + " - " + population + " habitants.");
		}
		
		String sql04 = "select d.nom, sum(c.population) from Departement d join Commune c on d.codeDepartement = c.departement_codeDepartement group by d.nom";
		Query query04 = entityManager.createNativeQuery(sql04);
		List<Object[]> resultList = query04.getResultList();
		resultList.forEach(tab -> System.out.println(tab[0] + " -> " + tab[1]));
		
//		int count = (int)query00.getSingleResult();
//		Class class1 = singleResult00.getClass();
//		System.out.println("Class = " + class1);
	}
}
