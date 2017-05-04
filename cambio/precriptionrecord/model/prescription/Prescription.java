package cambio.precriptionrecord.model.prescription;

public class Prescription {
	private String id;
	private String patientID;
	private String doctorID;
	private String diagnosisDescription;
	private String DrugList;
	private String Date;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPatientID() {
		return patientID;
	}
	public void setPatientID(String patientID) {
		this.patientID = patientID;
	}
	public String getDoctorID() {
		return doctorID;
	}
	public void setDoctorID(String doctorID) {
		this.doctorID = doctorID;
	}
	public String getDiagnosisDescription() {
		return diagnosisDescription;
	}
	public void setDiagnosisDescription(String diagnosisDescription) {
		this.diagnosisDescription = diagnosisDescription;
	}
	public String getDrugList() {
		return DrugList;
	}
	public void setDrugList(String drugList) {
		DrugList = drugList;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	
	
}
