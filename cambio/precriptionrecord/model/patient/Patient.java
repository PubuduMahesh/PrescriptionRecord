package cambio.precriptionrecord.model.patient;

import java.io.InputStream;
import java.sql.Blob;
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
    private String profilePic;
    private Blob pp;
	
	public Patient(){

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
        
        public String getProfilePicPath(){
            return profilePic;
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
        
    public void setProfilePicPath(String profilePic){
        this.profilePic = profilePic;
    }
    
    public void setProfilePicBLOB(Blob pp){
    	this.pp = pp;
    }
    public Blob getProfilePicBLOB(){
    	return this.pp;
    }
}
