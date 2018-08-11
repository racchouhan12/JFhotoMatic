package com.jphotomatic.dao;

import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.FileInputStream;
import java.io.InputStream;

import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.jphotomatic.main.MainUI;
import com.jphotomatic.model.ImagePreview;

public class SaveInDB extends JFrame {

	JButton b, insert; // s
	JTextField t;
	JLabel lb;
	Container container;
	Image canvasImage;
	final JFileChooser fc = new JFileChooser("d:");// user's default directory

	File dialog;

	public SaveInDB(String titleText, Image canvasImage) {
		super(titleText);
		setLayout(null);
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		
		int width = 800;
		int height = 665;
		
		int x = (screen.width) / 2;
		int y = (screen.height) / 2;

		lb = new JLabel(); 
		lb.setBounds(50, 170, x - 20, y);
		JLabel lbltxt = new JLabel("Image ID:");

		t = new JTextField();
		b = new JButton("Get Image");
		insert = new JButton("Insert In DB");

		this.canvasImage = canvasImage;

		fc.setAccessory(new ImagePreview(fc));

		container = getContentPane();

		lbltxt.setBounds(220, 70, 80, 30);
		t.setBounds(310, 70, 120, 30);
		b.setBounds(200, 130, 140, 30);
		insert.setBounds(360, 130, 140, 30);
		// s.setBounds(330, 100, 150, 30);

		// s = new JButton("Browse An Image");

		container.add(lb);
		// c.add(s);
		container.add(lbltxt);
		container.add(t);
		container.add(b);
		container.add(insert);

		// lb.setBorder(BorderFactory.createTitledBorder(""));
		b.addActionListener(new getset());
		insert.addActionListener(new insert());

		// s.addActionListener(new ActionListener() {

		// public void actionPerformed(ActionEvent e) {

		// openFile();
		// // lb.removeAll();
		// }
		// });
		setVisible(true);
		
		setBounds(x, y, 800, 600);
		displayImageOnSaveWindow();
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(false);
		

	}

	class getset implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Connection con = null;
			ResultSet rs = null;
			PreparedStatement psmnt = null;
			FileInputStream fis;
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/ImageMorpher", "root", "admin");

				PreparedStatement ps = (PreparedStatement) con.prepareStatement("select * from images where id=?");
				ps.setString(1, t.getText());
				ResultSet rs1 = ps.executeQuery();

				String imgLen = "";
				if (rs1.next()) {
					String h = rs1.getString(1);
					imgLen = rs1.getString(2);
				}
				ps = (PreparedStatement) con.prepareStatement("select img from images where id=?");
				ps.setString(1, t.getText());
				rs1 = ps.executeQuery();

				if (rs1.next()) {
					int len = imgLen.length();
					byte[] rb = new byte[len];
					InputStream readImg = rs1.getBinaryStream(1);
					int index = readImg.read(rb, 0, len);
					ImageIcon i = new ImageIcon(rb, imgLen);
					// lb.setIcon(i);
					ps.close();
					// lb.setIcon(i);
				}

			} catch (Exception ex) {
				System.out.println("Found some error : " + ex);
			}

		}
	}

	// ----------------------------------BROWSE
	// IMAGE-----------------------------------------
	private void displayImageOnSaveWindow() {

		lb.setIcon(new ImageIcon(canvasImage));
	}

	/*--------------------------------------------------------------------------------------------*/
	class insert implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Connection connection = null;
			ResultSet rs = null;
			PreparedStatement psmnt = null;
			FileInputStream fis;
			try {
				dialog = new File("/_1.png");
				ImageIO.write(getCanvasBImage(), "png", dialog );
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/ImageMorpher", "root", "admin");
				psmnt = (PreparedStatement) connection.prepareStatement("insert into images values(?,?)");
				psmnt.setString(1, t.getText());
				fis = new FileInputStream(new File("/_1.png"));
				psmnt.setBinaryStream(2, (InputStream) fis, (int) (dialog.length()));				
				int s = psmnt.executeUpdate();
				if (s > 0) {
					JOptionPane.showMessageDialog(null, "Image Uploaded successfully !", "Image Inserted", 1);
					System.out.println("Uploaded successfully !");
				} else {
					JOptionPane.showMessageDialog(null, "Image Upload Failed", "Error", JOptionPane.ERROR_MESSAGE);
					System.out.println("Failed to upload image.");
				}
			} catch (Exception ex) {
				System.out.println("Found some error : " + ex);
			} finally {
				dialog.delete();
			}

		}
	}
	
	private BufferedImage getCanvasBImage() {
		Image im = canvasImage;
		
		int w = im.getWidth(null);
		int h = im.getHeight(null);
		BufferedImage bi = new BufferedImage(w, h, 1);
		Graphics2D g2 = bi.createGraphics();
		g2.drawImage(im, 0, 0, null);
		g2.dispose();

		return bi;
	}

	
	

}
