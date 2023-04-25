package org.vitu.jpa;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import org.vitu.jpa.model.Commune;

public class PlayWithMaire {

	public static void main(String... args) {
		
		Path path = Path.of("base-commune/maires-25-04-2014.csv");
		try(BufferedReader reader = Files.newBufferedReader(path);) {
			
			String line = reader.readLine();
			line = reader.readLine();
			
			while (line != null) {
				
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
				String nom = split[3];
				
				Commune commune = new Commune(codePostal, nom);
				
				Commune previousCommune = communes.put(codePostal, commune);
				if (previousCommune != null) {
					System.out.println("Doublon = " + previousCommune);
				} else {
					preparedStatement.setString(1, codePostal);
					preparedStatement.setString(2, nom);
					preparedStatement.addBatch();
				}
								
				line = reader.readLine();
			
			}
			System.out.println("Executing barch");
			int[] counts = preparedStatement.executeBatch();
			System.out.println("Done batch");
			
			int count = Arrays.stream(counts).sum();
			System.out.println("Nombre de communes créées = " + count);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
