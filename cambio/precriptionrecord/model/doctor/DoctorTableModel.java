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
			return doctor.getId();
		case 1:
			return doctor.getName();
		case 2:
			return doctor.getNic();
		case 3:
			return doctor.getRegNumber();
		case 4:
			return doctor.getSpeiality();
		case 5:
			return doctor.getGender();
		case 6:
			return doctor.getBirthday();
		case 7:
			return doctor.getTp();
		case 8:
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
    {System.out.println("sdfjs");
		Doctor d = tableData.get(rowIndex);
		d.setName(doctor.getName());
		d.setNic(doctor.getNic());
		d.setRegNumber(doctor.getRegNumber());
		d.setSpeiality(doctor.getSpeiality());
		d.setGender(doctor.getGender());
		d.setBirthday(doctor.getBirthday());
		d.setTp(doctor.getTp());
		d.setJobHistory(doctor.getJobHistory());	
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
			if(getValueAt(rowCount, 0).equals(value)){
				return rowCount;
			}
		}
		return -1;
	}
	
	public void removeRow(int rowIndex){
			tableData.remove(rowIndex);
			fireTableDataChanged();
		
	}
}
