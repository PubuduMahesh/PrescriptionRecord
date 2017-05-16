package cambio.precriptionrecord.view.doctor;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;

import cambio.precriptionrecord.SQLToolKit.DoctorSQLTollkit;
import cambio.precriptionrecord.controller.DoctorController;
import cambio.precriptionrecord.model.doctor.Doctor;
import cambio.precriptionrecord.model.doctor.DoctorTableModel;
import cambio.precriptionrecord.util.DBConnection;

import javax.swing.JTable;
import javax.print.Doc;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class DoctorSearchPanel extends JPanel{
	private final GridBagLayout gridbag;
	private JLabel lSearchName;
	private JLabel lSearchRegNumber;
	private JTextField tSearchName;
	private JTextField tSearchRegNumber;
	private JButton bSearchButton;
	private JButton bClear;
	private JTable searchTable;
	private final DoctorTableModel tbModel;

	private final DoctorController doctorController;
	public DoctorSearchPanel(DoctorController doctroController){
		this.doctorController = doctroController;
		this.gridbag = new GridBagLayout();
		setLayout(gridbag);
		setPreferredSize(new Dimension(720, 250));

		addSearchBar();
		addTable();
		tbModel = (DoctorTableModel)searchTable.getModel();
	}

	private void addSearchBar(){
		GridBagConstraints searchConstraints = new GridBagConstraints();

		lSearchName = new JLabel("Name");
		lSearchRegNumber = new JLabel("Reg:Number");
		tSearchName = new JTextField(20);
		tSearchRegNumber = new JTextField(12);
		bSearchButton = new JButton("Search");
		bClear = new JButton("Clear");

		searchConstraints.anchor = GridBagConstraints.NORTHWEST;

		searchConstraints.gridx = 0;
		searchConstraints.gridy = 0;
		searchConstraints.insets = new Insets(0, 0, 0, 0);
		gridbag.setConstraints(lSearchName, searchConstraints);
		add(lSearchName);	

		searchConstraints.insets = new Insets(0, 45, 0, 0);
		gridbag.setConstraints(tSearchName, searchConstraints);
		add(tSearchName);

		searchConstraints.insets = new Insets(0, 290, 10, 0);
		gridbag.setConstraints(lSearchRegNumber, searchConstraints);
		add(lSearchRegNumber);

		searchConstraints.insets = new Insets(0, 380, 0, 0);
		gridbag.setConstraints(tSearchRegNumber, searchConstraints);
		add(tSearchRegNumber);

		searchConstraints.insets = new Insets(0, 540, 0, 0);
		gridbag.setConstraints(bSearchButton, searchConstraints);
		add(bSearchButton);

		searchConstraints.insets = new Insets(0, 630, 0, 0);
		gridbag.setConstraints(bClear, searchConstraints);
		add(bClear);

		/*button Actions*/
		searchButtonAction();	
		clearButtonAction();
	}

	private void addTable(){

		GridBagConstraints tablConstraints = new GridBagConstraints();

		final String[] header = {"ID","Name","NIC","Reg:No","Speciality","Gender","Birthday","Telehphone","Job History"};
		List<Doctor> data = new ArrayList<Doctor>();
		searchTable = new JTable(new DoctorTableModel(data,header));

		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		searchTable.setPreferredScrollableViewportSize(new Dimension(700, 130));
		searchTable.setFillsViewportHeight(true);
		scrollPane.setViewportView(searchTable);


		tablConstraints.anchor = GridBagConstraints.NORTHWEST;//align content of main panel in to left top corner.
		tablConstraints.gridx = 0;
		tablConstraints.gridy = 1;
		gridbag.setConstraints(scrollPane, tablConstraints);
		add(scrollPane);


		/*Mouse Clicked Event*/
		mouseClickAction();

		/*Remove Button Clicked Actions*/
		removeRow();

		/*Edit Button Clicked Action*/
		updateRow();
	}

	private void searchButtonAction(){
		bSearchButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				clearTable();
				DoctorSQLTollkit doctorSqlToolkit = new DoctorSQLTollkit();
				List<Doctor> doctorList = doctorSqlToolkit.searchDoctor(tSearchName.getText(), tSearchRegNumber.getText());
				if(doctorList.size()>0){
					for(Doctor doctor:doctorList)
						tbModel.updateTable(doctor);
				}
				
			}

		});
	}

	

	private void mouseClickAction(){
		searchTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent e){
				if(e.getClickCount() == 1){
					final JTable target = (JTable)e.getSource();
					final int row = target.getSelectedRow();
					if(row >= 0){
						Doctor doctor = tbModel.getValue(row);
						ActionEvent eClick = new ActionEvent(doctor, -1, "");
						doctorController.fireRowClickActionPerformed(eClick);
					}

				}
			}
		});
	}

	private void removeRow(){
		doctorController.registerRemoveRowDoctorSearchTableListeners(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int rowIndex = ((DoctorTableModel) searchTable.getModel()).getRowIndex(e.getSource().toString());
				if(rowIndex>=0)
					tbModel.removeRow(rowIndex);
			}
		});	
	}

	private void updateRow(){
		doctorController.registerUpdateRowDoctorSearchTableListeners(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				Doctor doctor = (Doctor) e.getSource();
				int updatedRow = tbModel.getRowIndex(doctor.getRegNumber());		
				if(updatedRow >= 0)
					tbModel.setValueAtRow(doctor,updatedRow);
				else
					tbModel.updateTable(doctor);
			}
		});	
	}

	private void clearButtonAction(){
		bClear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tSearchName.setText("");
				tSearchRegNumber.setText("");
				clearTable();

			}

		});

	}
	private void clearTable(){
		int rowCount = tbModel.getRowCount();
		for(int i = 0; i<rowCount; i++){
			tbModel.removeRow(0);

		}
	}

}
