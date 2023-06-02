package org.vitu.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.vitu.jpa.model.Commune;

public class PlayWithJPQLQueries {
	
	public static void main(String[] args) {
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("play-with-jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		String jpql00 = "select count(*) from Commune";
		Query query00 = entityManager.createNativeQuery(jpql00);

		long count00 = (long)query00.getSingleResult();
		System.out.println("Count = " + count00);
		
		String jpql01 = "select count(*), max(c.population) from Commune c";
		Query query01 = entityManager.createQuery(jpql01);
		
		Object[] result01 = (Object[])query01.getSingleResult();
		long count01 = (long)result01[0];
		int max01 = (int)result01[1];
		System.out.println("Class = " + result01[0].getClass());
		System.out.println("Class = " + result01[1].getClass());
		System.out.println("Count = " + count01);
		System.out.println("Max = " + max01);
		
		String jpql02 = "select c.nom, c.population from Commune c where c.population > 250000";
		
		Query query02 = entityManager.createQuery(jpql02);
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
		
		String jpql03 = "select c.nom, c.population from Commune c where c.population > ?1";
		
		Query query03 = entityManager.createQuery(jpql03);
		query03.setParameter(1,200_000);
		List result03 = query03.getResultList();
		
		System.out.println("# resultats : " + result03.size());
		
		for (Object[] line : (List<Object[]>)result03) {
//			System.out.println("0 : " + line[0].getClass());
//			System.out.println("1 : " + line[1].getClass());
			String nom = (String)line[0];
			int population = (int)line[1];
			System.out.println("Nom : " + nom + " - " + population + " habitants.");
		}
		
		String jpql04 = "select c.nom, c.population from Commune c where c.population > :pop_min";
		
		Query query04 = entityManager.createQuery(jpql04);
		query04.setParameter("pop_min",250_000);
		List result04 = query04.getResultList();
		
		System.out.println("# resultats : " + result04.size());
		
		for (Object[] line : (List<Object[]>)result04) {
			String nom = (String)line[0];
			int population = (int)line[1];
			System.out.println("Nom : " + nom + " - " + population + " habitants.");
		}
		
		Query query05 = entityManager.createNamedQuery("Commune.byPopulationMin");
		query05.setParameter("pop_min",230_000);
		List result05 = query05.getResultList();
		
		System.out.println("# resultats : " + result05.size());
		
		for (Object[] line : (List<Object[]>)result05) {
			String nom = (String)line[0];
			int population = (int)line[1];
			System.out.println("Nom : " + nom + " - " + population + " habitants.");
		}
		
		// Commune la plus peuplée
		String jpql06 = "select c from Commune c where c.population = (select max(c.population) from Commune c)";
		Query query06 = entityManager.createQuery(jpql06);
		Commune commune06 = (Commune)query06.getSingleResult();
		System.out.println("Commune la plus peuplée = " + commune06);
		
		// Nombre de Communes qui ont moins de 1000 habitants
		String jpql07 = "select count(c) from Commune c where c.population < 1000";
		Query query07 = entityManager.createQuery(jpql07);
		long count07 = (long)query07.getSingleResult();
		System.out.println("# communes de moins de 1000 habitants = " + count07);
		
		// Table nom du département : population du département
		String jpql08 = "select d.nom, sum(c.population) from Departement d, in(d.communes) c group by d.nom";
		Query query08 = entityManager.createQuery(jpql08);
		List<Object[]> result08 = query08.getResultList();
		System.out.println("result08 = " + result08.size());
		result08.forEach(tab -> System.out.println(tab[0] + " - " + tab[1]));
		
		String jpql09 = "select new org.vitu.jpa.query.Statistics(d.nom, sum(c.population), avg(c.population)) from Departement d, in(d.communes) c group by d.nom";
		Query query09 = entityManager.createQuery(jpql09);
		query09.getResultStream().forEach(System.out::println);
		
//		int count = (int)query00.getSingleResult();
//		Class class1 = singleResult00.getClass();
//		System.out.println("Class = " + class1);
	}
}
