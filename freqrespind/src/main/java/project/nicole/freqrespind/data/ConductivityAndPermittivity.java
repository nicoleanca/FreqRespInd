package project.nicole.freqrespind.data;

import java.util.ArrayList;

public class ConductivityAndPermittivity {

	private ArrayList<Double> permittivity = new ArrayList<Double>();

	private ArrayList<Double> conductivity = new ArrayList<Double>();;

	public ArrayList<Double> getPermittivity() {
		return permittivity;
	}

	public ArrayList<Double> getConductivity() {
		return conductivity;
	}

	public void addConductivity(double element) { conductivity.add(element); }

	public void addPermittivity(double element) { permittivity.add(element); }

}
