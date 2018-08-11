package com.jphotomatic.splash;

import java.awt.*;
import javax.swing.*;
import com.jphotomatic.main.MainUI;

public class Splash
{
 
private static MainUI theApp;
 public static void showSplash(int duration)
 {
	JWindow splash=new JWindow(theApp);
	JPanel content=(JPanel)splash.getContentPane();
	
	int width=500;
	int height=500;

	Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
	int x=(screen.width-width)/2;
	int y=(screen.height-height)/2;
	splash.setBounds(x,y,width,height);
	Image splashImage=Toolkit.getDefaultToolkit().getImage("./com/jphotomatic/splash/splashDuke.png");
	Image scaled=splashImage.getScaledInstance(500,500,1);
	//ImageIcon ii=new ImageIcon("AndromedaGalaxy.jpg");
	JLabel il=new JLabel(new ImageIcon(scaled),JLabel.CENTER);
	JLabel copyrt=new JLabel("Copyright 2011, NIMM.",JLabel.CENTER);
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