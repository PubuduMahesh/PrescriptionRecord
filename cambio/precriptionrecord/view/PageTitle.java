package cambio.precriptionrecord.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class PageTitle extends JPanel{
	public PageTitle(String title){
		JLabel titleLabel = new JLabel("PrescriptionRecord"+title);
		titleLabel.setFont(new Font("seif",Font.BOLD,18));
		add(titleLabel);
		setPreferredSize(new Dimension(710,30));
//		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}
}
