package cambio.precriptionrecord.view.doctor;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import cambio.precriptionrecord.controller.DoctorController;
import cambio.precriptionrecord.model.doctor.Doctor;
import cambio.precriptionrecord.model.doctor.DoctorTableModel;
import cambio.precriptionrecord.util.DBConnection;

import javax.swing.JTable;
import javax.swing.JButton;

public class DoctorSearchPanel extends JPanel{
	private GridBagLayout gridbag;
	private JLabel lSearchName;
	private JLabel lSearchRegNumber;
	private JTextField tSearchName;
	private JTextField tSearchRegNumber;
	private JButton bSearchButton;
	private JTable searchTable;
	
	private DoctorController doctorController;
	public DoctorSearchPanel(DoctorController doctroController){
		this.doctorController = doctroController;
		
		this.gridbag = new GridBagLayout();
		setLayout(gridbag);
		setPreferredSize(new Dimension(720, 200));
		
		addSearchBar();
		addTable();	
	}
	
	private void addSearchBar(){
		GridBagConstraints searchConstraints = new GridBagConstraints();
		
		lSearchName = new JLabel("Name");
		lSearchRegNumber = new JLabel("Reg:Number");
		tSearchName = new JTextField(20);
		tSearchRegNumber = new JTextField(12);
		bSearchButton = new JButton("Search");
		
		/*Temporary*/
		tSearchRegNumber.setText("RegNumber");
		tSearchName.setText("name");
		
		searchConstraints.anchor = GridBagConstraints.NORTHWEST;
		
		searchConstraints.gridx = 0;
		searchConstraints.gridy = 0;
		searchConstraints.insets = new Insets(0, 0, 0, 0);
		gridbag.setConstraints(lSearchName, searchConstraints);
		add(lSearchName);	
		
		searchConstraints.insets = new Insets(0, 50, 0, 0);
		gridbag.setConstraints(tSearchName, searchConstraints);
		add(tSearchName);
		
		searchConstraints.insets = new Insets(0, 300, 10, 0);
		gridbag.setConstraints(lSearchRegNumber, searchConstraints);
		add(lSearchRegNumber);
		
		searchConstraints.insets = new Insets(0, 380, 0, 0);
		gridbag.setConstraints(tSearchRegNumber, searchConstraints);
		add(tSearchRegNumber);
		
		searchConstraints.insets = new Insets(0, 550, 0, 0);
		gridbag.setConstraints(bSearchButton, searchConstraints);
		add(bSearchButton);
		
		/*button Actions*/
		searchButtonAction();		
	}
	
	private void addTable(){

		GridBagConstraints tablConstraints = new GridBagConstraints();
		
		final String[] header = {"Name","NIC","Reg:No","Speciality","Gender","Birthday","Telehphone","Job History"};
		ArrayList<Doctor> data = new ArrayList<Doctor>();
		searchTable = new JTable(new DoctorTableModel(data,header));
		
		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		searchTable.setPreferredScrollableViewportSize(new Dimension(690, 130));
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
				DBConnection dbCon = new DBConnection();
				Connection con = dbCon.getConnection();
				Statement stmt = null;
				String sql = null;
				ResultSet rs= null;
				try {
					stmt = con.createStatement();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					sql = "select * from doctor";			
					rs = stmt.executeQuery(sql);
					setDoctorObject(rs);
					con.close();					
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
		});
	}
	
	private void setDoctorObject(ResultSet rs){
		try {
			String id;
			String name;
			String nic;
			String regNumber;
			String speciality;
			String gender;
			String birthday;
			String telephone;
			String jobHistory;
			
			while(rs.next()){
				id = rs.getObject("id").toString();
				name = rs.getObject("name").toString();
				nic = rs.getObject("nic").toString();
				regNumber = rs.getObject("regNumber").toString();
				speciality = rs.getObject("speciality").toString();
				gender = rs.getObject("gender").toString();
				birthday = rs.getObject("birthday").toString();
				telephone = rs.getObject("telephone").toString();
				jobHistory = rs.getObject("jobHistory").toString();
				
				Doctor doctor = new Doctor();
				doctor.setId(id);
				doctor.setName(name);
				doctor.setNic(nic);
				doctor.setRegNumber(regNumber);
				doctor.setSpeiality(speciality);
				doctor.setGender(gender);
				doctor.setBirthday(birthday);
				doctor.setTp(telephone);
				doctor.setJobHistory(jobHistory);
				
				
				DoctorTableModel tbModel = (DoctorTableModel)searchTable.getModel();
				tbModel.updateTable(doctor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void mouseClickAction(){
		searchTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent e){
				if(e.getClickCount() == 1){
					final JTable target = (JTable)e.getSource();
					final int row = target.getSelectedRow();
					Doctor doctor = ((DoctorTableModel)target.getModel()).getValue(row);
					ActionEvent eClick = new ActionEvent(doctor, -1, "");
					doctorController.fireRowClickActionPerformed(eClick);
				}
			}
		});
	}
	
	private void removeRow(){
		/*doctorController.registerRemoveRowPatientSearchTableListeners(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int rowIndex = ((MyTableModel) searchTable.getModel()).getRowIndex(e.getSource().toString(),searchTable);
				System.out.println(rowIndex);
				((MyTableModel) searchTable.getModel()).removeRow(rowIndex);
			}
		});*/	
	}
	
	private void updateRow(){
		/*doctorController.registerUpdateRowPatientSearchTableListeners(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {		
				Patient patient = (Patient) e.getSource();
				int updatedRow = ((MyTableModel) searchTable.getModel()).getRowIndex(patient.getID(),searchTable);
				String[] patientValues;
				patientValues = new String[]{
						patient.getID(),
						patient.getName(),
						patient.getNIC(),
						patient.getAddress(),
						patient.getGender(),
						patient.getStatus(),
						patient.getBirthday(),
						patient.getTp(),
						patient.getMedicalHistory()
				};
				((MyTableModel) searchTable.getModel()).setValueAtRow(patient,updatedRow);
			}
		});*/	
	}
}
