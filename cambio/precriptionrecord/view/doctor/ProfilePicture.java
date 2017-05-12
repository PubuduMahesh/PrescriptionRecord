
package cambio.precriptionrecord.view.doctor;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialException;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ProfilePicture extends JPanel{
    private GridBagLayout gridbag;
    private JLabel lProfilePicture;
    private JButton bProfilePicAdd;
    private JButton bProfilePicDelete;
    private String selectedProfilePicPath = "";
    
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
//        
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
                    selectedProfilePicPath = selectedFile.getAbsolutePath();
                    lProfilePicture.setIcon(ResizeImage(selectedProfilePicPath));
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
    private ImageIcon ResizeImage(String imagePath) {
        ImageIcon MyImage = new ImageIcon(imagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(lProfilePicture.getWidth(), lProfilePicture.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
        
    }
    public String getProfilePicturePath(){
    	return this.selectedProfilePicPath;
    }
    public void clearProfilePicture(){
    	lProfilePicture.setIcon(null);
    }
    public void setProfilePic(String imagePath){
    	lProfilePicture.setIcon(ResizeImage(imagePath));
    }
    public Blob getProfilePicture() throws IOException, SerialException, SQLException{    	
    	Icon icon = lProfilePicture.getIcon();
    	BufferedImage bImage = new BufferedImage(icon.getIconWidth(),icon.getIconHeight(),BufferedImage.TYPE_INT_RGB);
    	ByteArrayOutputStream b =new ByteArrayOutputStream();
    	ImageIO.write(bImage, "jpg", b );
    	byte[] imageInByte = b.toByteArray();
    	Blob blobProfilePicture = new javax.sql.rowset.serial.SerialBlob(imageInByte);
    	return blobProfilePicture;
    }
}
