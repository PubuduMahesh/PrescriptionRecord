package cambio.precriptionrecord.model.prescription;

public class Prescription {
	private String id;
	private String patientID;
	private String doctorID;
	private String diagnosisDescription;
	private String drugList;
	private String date;
	private String patientName;
	private String doctorName;
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
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
		return drugList;
	}
	public void setDrugList(String drugList) {
		this.drugList = drugList;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setDoctorName(String doctorName){
		this.doctorName = doctorName;
	}
	public String getDoctorName(){
		return this.doctorName;
	}
	
	
}
