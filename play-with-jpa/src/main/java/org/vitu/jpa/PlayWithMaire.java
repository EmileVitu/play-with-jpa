package org.vitu.jpa;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.vitu.jpa.model.Commune;
import org.vitu.jpa.model.util.Civilite;

public class PlayWithMaire {

	public static void main(String... args) {
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("play-with-jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		Map<String, Commune> communes = readCommunes("base-commune/maires-25-04-2014.csv");
		
		entityManager.getTransaction().begin();
		communes.values().forEach(entityManager::persist);
		entityManager.getTransaction().commit();
		System.out.println("Persisted " + communes.size() + " communes.");	
	}

	private static Map<String, Commune> readCommunes(String fileName) {
		Map<String, Commune> communes = new HashMap<>();
		Path path = Path.of(fileName);
		try(BufferedReader reader = Files.newBufferedReader(path);) {
			
			String line = reader.readLine();
			line = reader.readLine();
			
			while (line != null) {
				
				String[] split = line.split(";");
				
				String codePostal = readCodePostal(line);
				String nom = split[3];
				
				Commune commune = new Commune(codePostal, nom);
				
				Commune previousCommune = communes.put(codePostal, commune);
				if (previousCommune != null) {
					System.out.println("Doublon = " + previousCommune);
				}
				line = reader.readLine();
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		return communes;
	}

	private static Map<String, Commune> readMaires(String fileName) {
		Map<String, Maire> communes = new HashMap<>();
		Path path = Path.of(fileName);
		try(BufferedReader reader = Files.newBufferedReader(path);) {
			
			String line = reader.readLine();
			line = reader.readLine();
			
			while (line != null) {
				
				String[] split = line.split(";");
				
				String codePostal = readCodePostal(line);
				String nom = split[5];
				String prenom = split[6];
				Civilite civilite = Civilite.of(split[7]);
				Date dateDeNaissance = split[8];
				
				Maire maire = new Maire(codePostal, nom);
				
				Commune previousCommune = communes.put(codePostal, commune);
				if (previousCommune != null) {
					System.out.println("Doublon = " + previousCommune);
				}
				line = reader.readLine();
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		return communes;
	}
	
	private static String readCodePostal(String line) {
		String[] split = line.split(";");
		String codeDepartement = split[0];
		if (codeDepartement.length() == 1) {
			codeDepartement = "0" + codeDepartement;
		}
		
		String codeInsee = split[2];
		if (codeInsee.length() == 1) {
			codeInsee = "00" + codeInsee;
		} else if (codeInsee.length() == 2) {
			codeInsee = "0" + codeInsee;
		}
		
		String codePostal = codeDepartement + codeInsee;
		return codePostal;
	}	
}