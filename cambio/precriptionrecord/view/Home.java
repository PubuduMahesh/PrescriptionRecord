package cambio.precriptionrecord.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Home extends JFrame{
	private JPanel panelMain = new JPanel(new GridBagLayout());
	public Home() {
		initUI();
		configureLayout();
		
	}
	private void initUI() {	
		setTitle("Dispensary Managment.");
		setSize(750, 750);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Point middle = new Point(screenSize.width/2,screenSize.height/2);
		Point location = new Point(middle.x-this.getWidth()/2,middle.y-this.getHeight()/2);
		setLocation(location);//Frame will be centered on the screen.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		setResizable(false);
		setVisible(true);
	}
	
	private void configureLayout() {
		GridBagConstraints mainC = new GridBagConstraints();		
		mainC.weightx = 0.5;//distributed space for the component horizontally.
		mainC.weighty = 0.5;//distributed space for the component vertically .		
		mainC.gridx = 0;
		mainC.gridy = 0;
		mainC.ipadx = 15;

		addTitle(mainC);
		addMenuBar(mainC);
		addEmptyPanel(mainC);
		
		getContentPane().add(panelMain);
		
	}
	
	private void addTitle(GridBagConstraints titleConstraints){
		titleConstraints.anchor = GridBagConstraints.NORTH;
		titleConstraints.gridy = 0;
		panelMain.add(new PageTitle(""),titleConstraints);
	}
	
	private void addMenuBar(GridBagConstraints menuBarConstraints){
		menuBarConstraints.anchor = GridBagConstraints.NORTHWEST;
		menuBarConstraints.gridy = 1;
		panelMain.add(new MenuBar(),menuBarConstraints);
	}
	
	private void addEmptyPanel(GridBagConstraints emptyPanelConstraints){
		emptyPanelConstraints.anchor = GridBagConstraints.NORTHWEST;
		emptyPanelConstraints.gridy = 2;
		panelMain.add(new EmptyPanel(),emptyPanelConstraints);
	}
	
	
	
}
