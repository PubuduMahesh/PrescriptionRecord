package cambio.precriptionrecord.view;

import cambio.precriptionrecord.controller.CommonController;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import cambio.precriptionrecord.controller.DoctorController;
import cambio.precriptionrecord.controller.PatientController;
import cambio.precriptionrecord.controller.PrescriptionController;
import cambio.precriptionrecord.controller.DrugController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame {

    private JPanel panelMain = new JPanel(new GridBagLayout());
    private PatientController patientController = new PatientController();
    private DoctorController doctorController = new DoctorController();
    private DrugController drugController = new DrugController();
    private PrescriptionController prescriptionController = new PrescriptionController();
    private CommonController commonController = new CommonController();
    public static String userNIC;

    public Home() {
        initUI();
        configureLayout();
        setVisible(true);

    }

    private void initUI() {
        setTitle("Dispensary Managment.");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
        setSize(770, 780);
        Point location = new Point(middle.x - this.getWidth() / 2, middle.y - this.getHeight()/2);
        setLocation(location);//Frame will be centered on the screen.
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(panelMain);
    }

    private void configureLayout() {
        addTitle();
        addLoginPanel();
        

    }

    private void addTitle() {
        GridBagConstraints titleConstraints = new GridBagConstraints();
        titleConstraints.weightx = 0.5;
        titleConstraints.weighty = 0.5;
        titleConstraints.gridx = 0;
        titleConstraints.gridy = 0;
        titleConstraints.anchor = GridBagConstraints.NORTHWEST;
        panelMain.add(new PageTitle(""), titleConstraints);
    }

    private void addMenuBar() {
        GridBagConstraints menuBarConstraints = new GridBagConstraints();
        menuBarConstraints.weightx = 0.5;
        menuBarConstraints.weighty = 0.5;
        menuBarConstraints.gridx = 0;
        menuBarConstraints.gridy = 1;
        menuBarConstraints.anchor = GridBagConstraints.NORTHWEST;
        panelMain.add(new MenuBar(patientController, doctorController, drugController, prescriptionController), menuBarConstraints);
    }

    private void addLoginPanel() {
        GridBagConstraints loginPanelConstraints = new GridBagConstraints();
        loginPanelConstraints.weightx = 0.5;
        loginPanelConstraints.weighty = 0.5;
        loginPanelConstraints.gridx = 0;
        loginPanelConstraints.gridy = 2;
        loginPanelConstraints.anchor = GridBagConstraints.NORTHWEST;
        panelMain.add(new Login(commonController), loginPanelConstraints);
        commonController.registerLoginCredentialsSuccessActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userNIC = e.getSource().toString();
                addMenuBar();
                addEmptyPanel();
            }
        });
    }

    private void addEmptyPanel() {
        GridBagConstraints emptyPanelConstraints = new GridBagConstraints();
        emptyPanelConstraints.weightx = 0.5;
        emptyPanelConstraints.weighty = 0.5;
        emptyPanelConstraints.gridx = 0;
        emptyPanelConstraints.gridy = 2;
        emptyPanelConstraints.anchor = GridBagConstraints.NORTHWEST;
        panelMain.add(new EmptyPanel(patientController, doctorController, drugController, prescriptionController), emptyPanelConstraints);
    }
}
