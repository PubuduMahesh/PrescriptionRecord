package cambio.precriptionrecord.model.doctor;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class DoctorTableModel extends AbstractTableModel {

	private String [] columnNames;
	private java.util.List<Doctor> tableData = new ArrayList<Doctor>();
	
	public DoctorTableModel(ArrayList<Doctor>data,String[] columnNames){
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
		Doctor doctor = tableData.get(rowIndex);
		switch (columnIndex) {
		
		case 0:
			return doctor.getName();
		case 1:
			return doctor.getNic();
		case 2:
			return doctor.getRegNumber();
		case 3:
			return doctor.getSpeiality();
		case 4:
			return doctor.getGender();
		case 5:
			return doctor.getBirthday();
		case 6:
			return doctor.getTp();
		case 7:
			return doctor.getJobHistory();
		default:
			return null;
		}
			
	}
	
	public Doctor getValue(int rowIndex){
		Doctor doctor = tableData.get(rowIndex);
		return doctor;
		
	}
	
	public void setValueAtRow(Doctor doctor, int rowIndex)
    {
		/*Doctor doctor = tableData.get(rowIndex);
		doctor.setName(doctor.getName());
		doctor.setNIC(doctor.getNIC());
		doctor.setAddress(doctor.getAddress());
		doctor.setGender(doctor.getGender());
		doctor.setStatus(doctor.getStatus());
		doctor.setBirthday(doctor.getBirthday());
		doctor.setTp(doctor.getTp());
		doctor.setMedicalHistory(doctor.getMedicalHistory());*/
		
		fireTableDataChanged();
        
		
    }
	public void updateTable(Doctor doctor){
		tableData.add(doctor);
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
