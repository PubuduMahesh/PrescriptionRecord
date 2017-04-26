package cambio.precriptionrecord.model;

import java.util.Date;

public class Patient {
	String name;
	String surName;
	String address;
	String gender;
	String status;
	Date birthday;
	String tp;
	String medicalHistory;
	
	public Patient(String name,String surName,String address,String gender,String status,Date birthday,String tp,String medicalHistory){
		this.name = name;
		this.surName = surName;
		this.address = address;
		this.gender = gender;
		this.status = status;
		this.birthday = birthday;
		this.tp = tp;
		this.medicalHistory = medicalHistory;
	}
	
	public Patient getPatient(){
		return this;
	}
}
