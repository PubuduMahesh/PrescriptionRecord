package cambio.precriptionrecord.model.patient;

import java.util.Date;

public class Patient {

	private String id;
	private String name;
	private String nic;
	private String address;
	private String gender;
	private String status;
	private String birthday;
	private String tp;
	private String medicalHistory;
	
	public Patient(String id,String name,String nic,String address,String gender,String status,String birthday,String tp,String medicalHistory){
		this.id = id;
		this.name = name;
		this.nic = nic;
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
	
	public String getID(){
		return this.id;
	}
	
	public String getName(){
		return this.name;
	}

	public String getNIC() {
		return nic;
	}

	public String getAddress() {
		return address;
	}

	public String getGender() {
		return gender;
	}

	public String getStatus() {
		return status;
	}

	public String getBirthday() {
		return birthday;
	}

	public String getTp() {
		return tp;
	}

	public String getMedicalHistory() {
		return medicalHistory;
	}
	
	public void setID(String id){
		this.id = id;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setNIC(String nic){
		this.nic = nic;
	}
	
	public void setAddress(String address){
		this.address = address;
	}
	
	public void setGender(String gender){
		this.gender = gender;
	}
	
	public void setStatus(String status){
		this.status = status;
	}
	
	public void setBirthday(String birthday){
		this.birthday = birthday;
	}
	
	public void setTp(String tp){
		this.tp = tp;
	}
	
	public void setMedicalHistory(String medicalHistory){
		this.medicalHistory = medicalHistory;
	}
}
