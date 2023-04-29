package org.vitu.jpa;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.vitu.jpa.model.Commune;
import org.vitu.jpa.model.Maire;
import org.vitu.jpa.model.util.Civilite;

public class PlayWithMaire {

	public static void main(String... args) {
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("play-with-jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		Map<String, Commune> communes = readCommunes("base-commune/maires-25-04-2014.csv");
		Map<String, Maire> maires = readMaires("base-commune/maires-25-04-2014.csv");
		
//		entityManager.getTransaction().begin();
//		communes.values().forEach(entityManager::persist);
//		entityManager.getTransaction().commit();
		System.out.println("Persisted " + communes.size() + " communes.");
		System.out.println("Persisted " + maires.size() + " maires.");
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

	private static Map<String, Maire> readMaires(String fileName) {
		Map<String, Maire> maires = new HashMap<>();
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
				
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");				
				Date dateDeNaissance = dateFormat.parse(split[8]);
				
				Maire maire = new Maire(nom, prenom, civilite, dateDeNaissance);
				
				Maire previousMaire = maires.put(codePostal, maire);
				if (previousMaire != null) {
					System.out.println("Doublon = " + previousMaire);
				}
				line = reader.readLine();
			}
		} catch(IOException | ParseException e) {
			e.printStackTrace();
		}
		return maires;
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