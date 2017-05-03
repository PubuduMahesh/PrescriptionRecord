package cambio.precriptionrecord.view.drug;

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
import javax.swing.JLabel;
import javax.swing.JTextField;

import cambio.precriptionrecord.controller.DoctorController;
import cambio.precriptionrecord.controller.DrugController;
import cambio.precriptionrecord.model.doctor.Doctor;
import cambio.precriptionrecord.model.doctor.DoctorTableModel;
import cambio.precriptionrecord.model.drug.Drug;
import cambio.precriptionrecord.model.drug.DrugTableModel;
import cambio.precriptionrecord.util.DBConnection;

import javax.swing.JTable;
import javax.swing.BorderFactory;
import javax.swing.JButton;

public class DrugSearchPanel extends JPanel{
	private GridBagLayout gridbag;
	private JLabel lSearchName;
	private JLabel lSearchDrugId;
	private JTextField tSearchName;
	private JTextField tSearchDrugId;
	private JButton bSearchButton;
	private JTable searchTable;
	
	private DrugController drugController;
	public DrugSearchPanel(DrugController drugController,int tbWidth, int tbHeight){
		this.drugController = drugController;		
		this.gridbag = new GridBagLayout();
		setLayout(gridbag);
		setPreferredSize(new Dimension(tbWidth+25, tbHeight+70));
		addSearchBar();
		setBorder(BorderFactory.createLineBorder(Color.black));
		addTable(tbWidth,tbHeight);	
	}
	
	private void addSearchBar(){
		GridBagConstraints searchConstraints = new GridBagConstraints();
		
		lSearchName = new JLabel("Name");
		lSearchDrugId = new JLabel("Drug ID");
		tSearchName = new JTextField(10);
		tSearchDrugId = new JTextField(6);
		bSearchButton = new JButton("Search");
		
		/*Temporary*/
		tSearchDrugId.setText("DrugID");
		tSearchName.setText("name");
		
		searchConstraints.anchor = GridBagConstraints.WEST;
		
		searchConstraints.gridx = 0;
		searchConstraints.gridy = 0;
		searchConstraints.insets = new Insets(0, 0, 0, 0);
		gridbag.setConstraints(lSearchName, searchConstraints);
		add(lSearchName);	
		
		searchConstraints.insets = new Insets(0, 50, 0, 0);
		gridbag.setConstraints(tSearchName, searchConstraints);
		add(tSearchName);
		
		searchConstraints.insets = new Insets(0, 175, 10, 0);
		gridbag.setConstraints(lSearchDrugId, searchConstraints);
		add(lSearchDrugId);
		
		searchConstraints.insets = new Insets(0, 225, 0, 0);
		gridbag.setConstraints(tSearchDrugId, searchConstraints);
		add(tSearchDrugId);
		
		searchConstraints.insets = new Insets(0, 325, 0, 0);
		gridbag.setConstraints(bSearchButton, searchConstraints);
		add(bSearchButton);
		
		/*button Actions*/
		searchButtonAction();		
	}
	
	private void addTable(int tbWidth, int tbHeight){

		GridBagConstraints tablConstraints = new GridBagConstraints();
		
		final String[] header = {"id","name","description","type","dosage"};
		ArrayList<Drug> data = new ArrayList<Drug>();
		searchTable = new JTable(new DrugTableModel(data,header));
		
		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		searchTable.setPreferredScrollableViewportSize(new Dimension(tbWidth,tbHeight));
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
					sql = "select * from drug";			
					rs = stmt.executeQuery(sql);
					setDrugObject(rs);
					con.close();					
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
		});
	}
	
	private void setDrugObject(ResultSet rs){
		try {
			String id;
			String name;
			String description;
			String type;
			String dosage;
			
			while(rs.next()){
				id = rs.getObject("drugID").toString();
				name = rs.getObject("drugName").toString();
				description = rs.getObject("description").toString();
				type = rs.getObject("type").toString();
				dosage = rs.getObject("dosage").toString();
				Drug drug = new Drug();
				drug.setDrugId(id);
				drug.setDrugName(name);
				drug.setDescription(description);
				drug.setType(type);
				drug.setDosage(dosage);
				DrugTableModel tbModel = (DrugTableModel)searchTable.getModel();
				tbModel.updateTable(drug);
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
					Drug drug = ((DrugTableModel)target.getModel()).getValue(row);
					ActionEvent eClick = new ActionEvent(drug, -1, "");
					drugController.fireRowClickActionPerformed(eClick);
				}
			}
		});
	}
	
	private void removeRow(){
		drugController.registerRemoveRowDrugSearchTableListeners(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int rowIndex = ((DrugTableModel) searchTable.getModel()).getRowIndex(e.getSource().toString(),searchTable);
				System.out.println(rowIndex);
				((DrugTableModel) searchTable.getModel()).removeRow(rowIndex);
			}
		});	
	}
	
	private void updateRow(){
		drugController.registerEditRowDrugSearchTableListeners(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				Drug drug = (Drug) e.getSource();
				int updatedRow = ((DrugTableModel) searchTable.getModel()).getRowIndex(drug.getDrugId(),searchTable);				
				((DrugTableModel) searchTable.getModel()).setValueAtRow(drug,updatedRow);
			}
		});	
	}
}
