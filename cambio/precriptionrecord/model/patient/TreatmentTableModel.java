package cambio.precriptionrecord.model.patient;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import cambio.precriptionrecord.model.drug.Drug;
import cambio.precriptionrecord.model.prescription.Prescription;

public class TreatmentTableModel extends AbstractTableModel{
	private String [] columnNames;
	private java.util.List<Prescription> tableData = new ArrayList<Prescription>();
	
	public TreatmentTableModel(ArrayList<Prescription>data,String[] columnNames){
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
		Prescription prescription = tableData.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return prescription.getDate();
		case 1:
			return prescription.getDiagnosisDescription();
		case 2:
			return prescription.getDrugList();
		case 3:
			return prescription.getDoctorName();
		default:
			return null;
		}
			
	}
	
	public Prescription getValue(int rowIndex){
		Prescription prescription = tableData.get(rowIndex);
		return prescription;
		
	}
	
	public void setValueAtRow(Prescription prescription, int rowIndex)
    {
		tableData.set(rowIndex, prescription);
		fireTableDataChanged();
        
		
    }
	public void updateTable(Prescription prescription){
		tableData.add(prescription);
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
		return 0;
	}
	
	public void removeRow(int rowIndex){
		tableData.remove(rowIndex);
		fireTableDataChanged();
	}
	
	public java.util.List<Prescription> getTableData(){
		return tableData;
	}
}
