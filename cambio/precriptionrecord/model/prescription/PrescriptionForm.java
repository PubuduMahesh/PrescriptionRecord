package cambio.precriptionrecord.model.prescription;

public class PrescriptionForm {
	private String patientName;
	private String patientAge;
	private String patientTelePhone;
	private String date;
	private String doctorName;
	private String doctorTelephone;
	private String drugList;

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientAge() {
		return patientAge;
	}

	public void setPatientAge(String patientAge) {
		this.patientAge = patientAge;
	}

	public String getPatientTelePhone() {
		return patientTelePhone;
	}

	public void setPatientTelePhone(String patientTelePhone) {
		this.patientTelePhone = patientTelePhone;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDoctorTelephone() {
		return doctorTelephone;
	}

	public void setDoctorTelephone(String doctorTelephone) {
		this.doctorTelephone = doctorTelephone;
	}

	public String getDrugList() {
		return drugList;
	}

	public void setDrugList(String drugList) {
		this.drugList = drugList;
	}
	
}
