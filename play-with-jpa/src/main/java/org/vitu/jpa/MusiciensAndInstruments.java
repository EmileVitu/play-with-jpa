package org.vitu.jpa;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.vitu.jpa.model.Instrument;
import org.vitu.jpa.model.Musicien;
import org.vitu.jpa.model.util.TypeInstrument;

public class MusiciensAndInstruments {
	
	private static Map<String, Instrument> registryInstrument = new HashMap<>();
	
	public static void main(String... args) {
		
		List<Instrument> instruments = readInstrument();
		List<Musicien> musiciens = readMusiciens();
		
		musiciens.forEach(System.out::println);
		
	}

	private static List<Instrument> readInstrument() {
		Function<String, Instrument> lineToInstrument = 
				line -> {
					String[] split = line.split("[ ]+");
					String nom = split[0];
					String typeInstrument = split[1];
					TypeInstrument type = TypeInstrument.of(typeInstrument);
					Instrument instrument = new Instrument(nom, type);
					registryInstrument.put(nom, instrument);
					return instrument;
				};
				
		List<Instrument> instruments = List.of();
		
		Path fichierInstruments = Path.of("data/instruments.txt");
		try (Stream<String> instrumentLines = Files.newBufferedReader(fichierInstruments).lines();) {

			instruments =
				instrumentLines
					.filter(line -> !line.startsWith("#"))
					.map(line -> lineToInstrument.apply(line))
					.collect(Collectors.toList());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return instruments;
	}
	
	private static List<Musicien> readMusiciens() {
		Function<String, Musicien> lineToMusicien = 
				line -> {
					String[] split = line.split("[ ]+");
					String nom = split[0];
					Musicien musicien = new Musicien(nom);
					String[] nomInstruments = Arrays.copyOfRange(split, 1, split.length);
					Arrays.stream(nomInstruments)
						.map(nomInstrument -> registryInstrument.get(nomInstrument))
						.forEach(instrument -> musicien.addInstrument(instrument));
					return musicien;
				};
				
		List<Musicien> musiciens = List.of();
		
		Path fichierMusiciens = Path.of("data/musiciens.txt");
		try (Stream<String> musiciensLines = Files.newBufferedReader(fichierMusiciens).lines();) {

			musiciens =
				musiciensLines
					.filter(line -> !line.startsWith("#"))
					.map(line -> lineToMusicien.apply(line))
					.collect(Collectors.toList());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return musiciens;
	}

}
