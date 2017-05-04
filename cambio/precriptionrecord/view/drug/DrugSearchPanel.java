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
import java.awt.Font;
import java.awt.GridBagConstraints;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.StyledEditorKit.FontSizeAction;

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
	private JButton bClearButton;
	private JTable searchTable;
	DrugTableModel tbModel;
	
	private DrugController drugController;
	public DrugSearchPanel(DrugController drugController,int tbWidth, int tbHeight){
		this.drugController = drugController;		
		this.gridbag = new GridBagLayout();
		setLayout(gridbag);
		setPreferredSize(new Dimension(tbWidth+25, tbHeight+70));
		addSearchBar();
		setBorder(BorderFactory.createLineBorder(Color.black));
		addTable(tbWidth,tbHeight);
		tbModel = (DrugTableModel)searchTable.getModel();
	}
	
	private void addSearchBar(){
		GridBagConstraints searchConstraints = new GridBagConstraints();		
		searchConstraints.anchor = GridBagConstraints.WEST;
		
		lSearchName = new JLabel("Name");
		lSearchDrugId = new JLabel("ID");
		tSearchName = new JTextField(10);
		tSearchDrugId = new JTextField(6);
		bSearchButton = new JButton("Search");
		bClearButton = new JButton("Clear");		
		
		searchConstraints.gridx = 0;
		searchConstraints.gridy = 0;
		searchConstraints.insets = new Insets(0, 0, 0, 0);
		gridbag.setConstraints(lSearchName, searchConstraints);
		add(lSearchName);	
		
		searchConstraints.insets = new Insets(0, 35, 0, 0);
		gridbag.setConstraints(tSearchName, searchConstraints);
		add(tSearchName);
		
		searchConstraints.insets = new Insets(0, 160, 10, 0);
		gridbag.setConstraints(lSearchDrugId, searchConstraints);
		add(lSearchDrugId);
		
		searchConstraints.insets = new Insets(0, 175, 0, 0);
		gridbag.setConstraints(tSearchDrugId, searchConstraints);
		add(tSearchDrugId);
		
		searchConstraints.insets = new Insets(0, 250, 0, 0);
		gridbag.setConstraints(bSearchButton, searchConstraints);
		add(bSearchButton);
		
		searchConstraints.insets = new Insets(0, 330, 0, 0);
		gridbag.setConstraints(bClearButton, searchConstraints);
		add(bClearButton);
		
		/*button Actions*/
		searchButtonAction();
		clearButtonAction();
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
				clearTable();
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
	private void clearButtonAction(){
		bClearButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				clearTable();
				
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
					if(row >= 0){
						Drug drug = tbModel.getValue(row);
						ActionEvent eClick = new ActionEvent(drug, -1, "");
						drugController.fireRowClickActionPerformed(eClick);
					}

				}
			}
		});
	}
	
	private void removeRow(){
		drugController.registerRemoveRowDrugSearchTableListeners(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int rowIndex = ((DrugTableModel) searchTable.getModel()).getRowIndex(e.getSource().toString(),searchTable);
				tbModel.removeRow(rowIndex);
			}
		});	
	}
	
	private void updateRow(){
		drugController.registerEditRowDrugSearchTableListeners(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				Drug drug = (Drug) e.getSource();
				int updatedRow = ((DrugTableModel) searchTable.getModel()).getRowIndex(drug.getDrugId(),searchTable);				
				tbModel.setValueAtRow(drug,updatedRow);
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
