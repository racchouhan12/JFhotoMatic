package com.jphotomatic.model;

import com.jphotomatic.main.MainUI;

import java.awt.*;
import javax.swing.*;

public class Splash
{
 
private static MainUI theApp;
 public static void showSplash(int duration)
 {
	JWindow splash=new JWindow(theApp);
	JPanel content=(JPanel)splash.getContentPane();
	
	int width=550;
	int height=350;

	Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
	int x=(screen.width-width)/2;
	int y=(screen.height-height)/2;
	splash.setBounds(x,y,width,height);
	Image splashImage=Toolkit.getDefaultToolkit().getImage("com/jphotomatic/resources/images/splashDuke.png");
	Image scaled=splashImage.getScaledInstance(550,350,1);
	//ImageIcon ii=new ImageIcon("splashDuke.png");
	JLabel il=new JLabel(new ImageIcon(scaled),JLabel.CENTER);
	JLabel copyrt=new JLabel("",JLabel.CENTER);
	copyrt.setFont(new Font("Comic Sans MS",Font.BOLD,14));
	content.add(il,BorderLayout.CENTER);
	content.add(copyrt,BorderLayout.SOUTH);
	content.setBorder(BorderFactory.createLineBorder(Color.BLACK,10));

	splash.setVisible(true);

	try
	{
		Thread.sleep(duration);
	}
	catch(Exception e){}
	splash.setVisible(false);

 }

}