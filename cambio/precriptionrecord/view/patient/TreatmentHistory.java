package cambio.precriptionrecord.view.patient;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import cambio.precriptionrecord.controller.PatientController;
import cambio.precriptionrecord.util.DatePicker;

public class TreatmentHistory extends JInternalFrame{
	PatientController patientController;	
	GridBagLayout gridbag;
	JButton bTo;
	JButton bFrom;
	JTextField tFrom;
	JTextField tTo;
	
	public TreatmentHistory(PatientController patientController){
		this.patientController = patientController;
		gridbag = new GridBagLayout();
		JDesktopPane desktopPane = new JDesktopPane();
		setTitle("Add New Patient");
		setPreferredSize(new Dimension(740,665));
		setClosable(true);
		setVisible(true);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
		gridbag = new GridBagLayout();
		setLayout(gridbag);	
		
		createLayout();
			
		desktopPane.add(this);
	}
	
	private void createLayout() {
		addPatientSearchPanel();
		addDateFieldPanel();
		
	}
	
	private void addPatientSearchPanel(){
		GridBagConstraints constraints = new GridBagConstraints();
		PatientSearchPanel patientSearchPanel = new PatientSearchPanel(patientController, 700, 130);
		constraints.anchor = GridBagConstraints.NORTHWEST;
		constraints.gridx = 0;
		constraints.gridy = 0;
		gridbag.setConstraints(patientSearchPanel, constraints);
		add(patientSearchPanel);
		
	}
	
	private void addDateFieldPanel(){
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.NORTHWEST;
		
		JLabel lTimePeriod = new JLabel("Time Period");
		JLabel lFrom = new JLabel("From");
		JLabel lTo = new JLabel("To");
		tFrom = new JTextField(15);
		bFrom = new JButton(":)");
		tTo = new JTextField(15);
		bTo = new JButton(":)");
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets  = new Insets(225, 0, 0, 0);
		gridbag.setConstraints(lTimePeriod, constraints);
		add(lTimePeriod);
		
		constraints.insets = new Insets(225, 120, 0, 0);
		gridbag.setConstraints(lFrom, constraints);
		add(lFrom);
		
		constraints.insets = new Insets(225, 160, 0, 0);
		gridbag.setConstraints(tFrom, constraints);
		add(tFrom);
		
		constraints.insets = new Insets(225, 330, 0, 0);
		gridbag.setConstraints(bFrom, constraints);
		add(bFrom);
		
		constraints.insets = new Insets(225, 400, 0, 0);
		gridbag.setConstraints(lTo, constraints);
		add(lTo);
		
		constraints.insets = new Insets(225, 440, 0, 0);
		gridbag.setConstraints(tTo, constraints);
		add(tTo);
		
		constraints.insets = new Insets(225, 610, 0, 0);
		gridbag.setConstraints(bTo, constraints);
		add(bTo);
		
		/*to button action*/
		periodButtonAction(bFrom, tFrom);
		periodButtonAction(bTo,tTo);
				
	}
	
	private void periodButtonAction(final JButton button, final JTextField textfield){
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String selDate = new DatePicker((TreatmentHistory)(button.getParent().getParent().getParent().getParent())).setPickedDate();
					Date birthday = new SimpleDateFormat("yyyy/MM/dd").parse(selDate);//convert the selected Date in to the "Date" type
					if (birthday.before(new Date())) {//check whether the selected date is grater than with respect to the current date. 
						textfield.setText(selDate);//set the date to the birthday text field.
					} else {//if validation is failed, warning message. 
						JOptionPane.showMessageDialog(null, "Birthday should be previous date", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (ParseException ex) {
					//add logger.
				}
				
			}
		});
		
	}
}
