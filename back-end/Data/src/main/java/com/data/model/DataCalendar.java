package com.data.model;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.data.annotation.Id;

import com.data.util.Localisation;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DataCalendar {

	@Id
	private String id;

	@Min(value = 13, message = "weeksNumber: minimum value is 13")
	@Max(value = 16, message = "weeksNumber: maximum value is 16")
	private int weeksNumber;

	@NotBlank(message = "startDate is mandatory")
	private String startDate;

	@NotNull(message = "modulesUeA is mandatory")
	private ArrayList<@Valid Module> modulesUeA;

	@NotNull(message = "modulesUeB is mandatory")
	private ArrayList<@Valid Module> modulesUeB;

	@NotNull(message = "modulesUeC is mandatory")
	private ArrayList<@Valid Module> modulesUeC;

	@NotNull(message = "unavailabilities is mandatory")
	private Map<Localisation, ArrayList<@Valid Unavailability>> unavailabilities;

	private long creationDate;
	private ArrayList<String> teacherWaitingList;
	private boolean existCalendarFile;

	public String getId() {
		return id;
	}

	public int getWeeksNumber() {
		return weeksNumber;
	}

	public String getStartDate() {
		return startDate;
	}

	public ArrayList<Module> getModulesUeA() {
		return modulesUeA;
	}

	public ArrayList<Module> getModulesUeB() {
		return modulesUeB;
	}

	public ArrayList<Module> getModulesUeC() {
		return modulesUeC;
	}

	public Map<Localisation, ArrayList<Unavailability>> getUnavailabilities() {
		return unavailabilities;
	}

	public long getCreationDate() {
		return creationDate;
	}

	public ArrayList<String> getTeacherWaitingList() {
		return teacherWaitingList;
	}

	public boolean existCalendarFile() {
		return existCalendarFile;
	}

	public void setCreationDate(long creationDate) {
		this.creationDate = creationDate;
	}

	public void setTeacherWaitingList(ArrayList<String> teacherWaitingList) {
		this.teacherWaitingList = teacherWaitingList;
	}

	public void setExistCalendarFile(boolean existCalendarFile) {
		this.existCalendarFile = existCalendarFile;
	}

	public void generateTeacherWaitingList() {
		teacherWaitingList = new ArrayList<>();
		modulesUeA.forEach((module) -> {
			module.getMails().values().forEach((mail) -> {
				addMailToList(mail);
			});
		});
		modulesUeB.forEach((module) -> {
			module.getMails().values().forEach((mail) -> {
				addMailToList(mail);
			});
		});
		modulesUeC.forEach((module) -> {
			module.getMails().values().forEach((mail) -> {
				addMailToList(mail);
			});
		});
	}

	public void deleteMailToList(String mail) {
		teacherWaitingList.remove(mail);
	}

	private void addMailToList(String mail) {
		if (!teacherWaitingList.contains(mail)) {
			teacherWaitingList.add(mail);
		}
	}

}
