package cambio.precriptionrecord.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Home extends JFrame{

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
		
	}
	
	
}
