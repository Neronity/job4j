package ru.job4j.profession;

public class Doctor extends Profession {

	public Diagnose healPatient(Patient patient) {
		return new Diagnose("Диагноз");
	}
}