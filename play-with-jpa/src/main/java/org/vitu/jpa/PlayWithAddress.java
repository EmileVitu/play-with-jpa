package org.vitu.jpa;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.vitu.jpa.model.Address;
import org.vitu.jpa.model.Commune;
import org.vitu.jpa.model.Maire;
import org.vitu.jpa.model.util.Civilite;

public class PlayWithAddress {

	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("play-with-jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		Date date = new Date(1952, 8, 4);
		Maire maire = new Maire("Chirac", "Jacques", Civilite.M, date);
		
		Commune ambronay = entityManager.find(Commune.class, "01007");
		
		Address address =  new Address("7 boulevard des capucines", ambronay);
		maire.setAddress(address);
		
		
	}

}
