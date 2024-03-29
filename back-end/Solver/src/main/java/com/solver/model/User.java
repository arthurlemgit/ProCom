package com.solver.model;

import java.util.ArrayList;

import com.solver.util.Localisation;
import com.solver.util.Role;

public class User {

	private String mail;
	private Role role;
	private ArrayList<Unavailability> unavailabilities;
	private Localisation localisation;
	private ArrayList<Integer> unavailabilitiesTraduction;
	private int spreadWeeks;

	public String getMail() {
		return mail;
	}

	public Role getRole() {
		return role;
	}

	public ArrayList<Unavailability> getUnavailabilities() {
		return unavailabilities;
	}

	public Localisation getLocalisation() {
		return localisation;
	}

	public ArrayList<Integer> getUnavailabilitiesTraduction() {
		return unavailabilitiesTraduction;
	}

	public int getSpreadWeeks() {
		return spreadWeeks;
	}

	public void setUnavailabilitiesTraduction(ArrayList<Integer> unavailabilitiesTraduction) {
		this.unavailabilitiesTraduction = unavailabilitiesTraduction;
	}

}
