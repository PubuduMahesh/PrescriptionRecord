
package cambio.precriptionrecord.view.patient;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Blob;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ProfilePicture extends JPanel{
    private final GridBagLayout gridbag;
    private final JLabel lProfilePicture;
    private final JButton bProfilePicAdd;
    private final JButton bProfilePicDelete;
    private String emptyProfilePicPath = "";
    
    public ProfilePicture(){
        gridbag = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        setLayout(gridbag);
        
        
        /*Label-profile picture*/
        lProfilePicture = new JLabel();
        constraints.anchor = GridBagConstraints.NORTHWEST;
        lProfilePicture.setBorder(BorderFactory.createEtchedBorder());
        lProfilePicture.setPreferredSize(new Dimension(135, 135));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(0, 0, 0, 0);
        gridbag.setConstraints(lProfilePicture, constraints);
        add(lProfilePicture);
        
        /*add button*/
        bProfilePicAdd = new JButton("Edit");
        constraints.insets = new Insets(140, 5, 0, 0);
        bProfilePicAdd.setPreferredSize(new Dimension(60, 15));
        bProfilePicAdd.setFont(new Font("seif", Font.ITALIC, 10));
        constraints.gridx = 0;
        constraints.gridy = 0;
        gridbag.setConstraints(bProfilePicAdd, constraints);
        add(bProfilePicAdd);
        
        /*delete button*/
        bProfilePicDelete = new JButton("Delete");
        constraints.insets = new Insets(140, 75, 0, 0);
        bProfilePicDelete.setPreferredSize(new Dimension(60, 15));
        bProfilePicDelete.setFont(new Font("seif", Font.ITALIC, 10));
        constraints.gridx = 0;
        constraints.gridy = 0;
        gridbag.setConstraints(bProfilePicDelete, constraints);
        add(bProfilePicDelete);
        
        /*Button Action*/
        profilePictureAddButtonAction();
        profilePictureDeleteButtonAction();
        
    }
    private void profilePictureAddButtonAction() {
        bProfilePicAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser file = new JFileChooser();
                file.setCurrentDirectory(new File(System.getProperty("user.home")));
                FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images", "jpg", "gif", "png");
                file.addChoosableFileFilter(filter);
                int result = file.showSaveDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = file.getSelectedFile();
                    emptyProfilePicPath = selectedFile.getAbsolutePath();
                    lProfilePicture.setIcon(ResizeImage(emptyProfilePicPath));
                }

            }
        });
    }
    private void profilePictureDeleteButtonAction() {
        bProfilePicDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogResult = JOptionPane.showConfirmDialog(null, "Would You Like to Remove Picture?", "Warning", 0);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    lProfilePicture.setIcon(null);
                }
            }
        });
        
    }
    private ImageIcon ResizeImage(String ImagePath) {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(lProfilePicture.getWidth(), lProfilePicture.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
        
    }
    public String getProfilePicturePath(){
    	return this.emptyProfilePicPath;
    }
    public void clearProfilePicture(){
    	lProfilePicture.setIcon(null);
    }
    public void setProfilePic(String path){
    	lProfilePicture.setIcon(ResizeImage(path));
    }
    public Blob getProfilePicBlob(){
    	return (Blob) lProfilePicture.getIcon();
    }
}
