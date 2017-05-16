package cambio.precriptionrecord.model.drug;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class DrugTableModel extends AbstractTableModel {

    private String[] columnNames;
    private List<Drug> tableData = new ArrayList<Drug>();

    public DrugTableModel(List<Drug> data, String[] columnNames) {
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
        Drug drug = tableData.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return drug.getDrugId();
            case 1:
                return drug.getDrugName();
            case 2:
                return drug.getDescription();
            case 3:
                return drug.getType();
            default:
                return null;
        }

    }

    public Drug getValue(int rowIndex) {
        Drug drug = tableData.get(rowIndex);
        return drug;

    }

    public void setValueAtRow(Drug drug, int rowIndex) {
        tableData.set(rowIndex, drug);
        fireTableDataChanged();

    }

    public void updateTable(Drug drug) {
        tableData.add(drug);
        int row = tableData.size() - 1;// New data added to the first row each time. 
        fireTableRowsInserted(row, row);//fire row inserted. 

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public int getRowIndex(String value) {
        for (int rowCount = 0; rowCount < getRowCount(); rowCount++) {
            if (getValueAt(rowCount, 0).equals(value)) {
                return rowCount;
            }
        }
        return -1;
    }

    public void removeRow(int rowIndex) {
        tableData.remove(rowIndex);
        fireTableDataChanged();
    }
}
