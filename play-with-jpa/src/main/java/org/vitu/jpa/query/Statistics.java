package org.vitu.jpa.query;

public class Statistics {

	private String nomDepartement;
	private long population;
	private double populationMoyenne;
	
	public Statistics(String nomDepartement, long population, double populationMoyenne) {
		this.nomDepartement = nomDepartement;
		this.population = population;
		this.populationMoyenne = populationMoyenne;
	}
	public String getNomDepartement() {
		return nomDepartement;
	}
	public void setNomDepartement(String nomDepartement) {
		this.nomDepartement = nomDepartement;
	}
	public long getPopulation() {
		return population;
	}
	public void setPopulation(long population) {
		this.population = population;
	}
	public double getPopulationMoyenne() {
		return populationMoyenne;
	}
	public void setPopulationMoyenne(double populationMoyenne) {
		this.populationMoyenne = populationMoyenne;
	}
	
	@Override
	public String toString() {
		return "Statistics [nomDepartement=" + nomDepartement + ", population=" + population + ", populationMoyenne="
				+ populationMoyenne + "]";
	}
}
