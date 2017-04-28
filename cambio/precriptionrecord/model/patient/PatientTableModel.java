package cambio.precriptionrecord.model.patient;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class PatientTableModel extends AbstractTableModel {

	private String [] columnNames;
	private java.util.List<Patient> tableData = new ArrayList<Patient>();
	
	public PatientTableModel(ArrayList<Patient>data,String[] columnNames){
		this.columnNames = columnNames;
		this.tableData = data;
	}
	
	@Override
	public int getRowCount() {
		return tableData.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
	public String getColumnName(int col) {
        return columnNames[col];
    }

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Patient pat = tableData.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return pat.getID();
		case 1:
			return pat.getName();
		case 2:
			return pat.getNIC();
		case 3:
			return pat.getAddress();
		case 4:
			return pat.getGender();
		case 5:
			return pat.getStatus();
		case 6:
			return pat.getBirthday();
		case 7:
			return pat.getTp();
		case 8:
			return pat.getMedicalHistory();
		default:
			return null;
		}
			
	}
	
	public Patient getValue(int rowIndex){
		Patient patient = tableData.get(rowIndex);
		return patient;
		
	}
	
	public void setValueAtRow(Patient pat, int rowIndex)
    {
		Patient patient = tableData.get(rowIndex);
		patient.setName(pat.getName());
		patient.setNIC(pat.getNIC());
		patient.setAddress(pat.getAddress());
		patient.setGender(pat.getGender());
		patient.setStatus(pat.getStatus());
		patient.setBirthday(pat.getBirthday());
		patient.setTp(pat.getTp());
		patient.setMedicalHistory(pat.getMedicalHistory());
		
		fireTableDataChanged();
        
		
    }
	public void updateTable(Patient patient){
		tableData.add(patient);
		int row = tableData.size()-1;// New data added to the first row each time. 
		fireTableRowsInserted(row, row);//fire row inserted. 
		
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex){
		return false;
	}
	
	public int getRowIndex(String value,JTable table){
		for(int rowCount = 0; rowCount < table.getRowCount(); rowCount++){
			System.out.println(getValueAt(rowCount, 0));
			if(getValueAt(rowCount, 0).equals(value)){
				return rowCount;
			}
		}
		return 0;
	}
	
	public void removeRow(int rowIndex){
		tableData.remove(rowIndex);
		fireTableDataChanged();
	}
}
