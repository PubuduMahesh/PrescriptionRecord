package cambio.precriptionrecord.model.doctor;

public class Doctor {
	private String id;
	private String name;
	private String nic;
	private String regNumber;
	private String speiality;
	private String gender;
	private String birthday;
	private String tp;
	private String jobHistory;
	
	public String getId(){
		return id;
	}
	
	public void setId(String id){
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNic() {
		return nic;
	}

	public void setNic(String nic) {
		this.nic = nic;
	}

	public String getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public String getSpeiality() {
		return speiality;
	}

	public void setSpeiality(String speiality) {
		this.speiality = speiality;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getTp() {
		return tp;
	}

	public void setTp(String tp) {
		this.tp = tp;
	}

	public String getJobHistory() {
		return jobHistory;
	}

	public void setJobHistory(String jobHistory) {
		this.jobHistory = jobHistory;
	}
}
