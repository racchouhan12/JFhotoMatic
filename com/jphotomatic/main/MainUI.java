package com.jphotomatic.main;//951 gP

import com.jphotomatic.dao.SaveInDB;
import com.jphotomatic.effects.BandEffects;
import com.jphotomatic.filters.ConvolveFilters;
import com.jphotomatic.filters.ImageFilter;
import com.jphotomatic.layouts.TabPanelLayout;
import com.jphotomatic.model.*;
import com.jphotomatic.shape.Crop;
import com.jphotomatic.shape.Scale;
import com.jphotomatic.shape.ShearImage;
import com.jphotomatic.splash.Splash;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;

public class MainUI extends JFrame implements ActionListener// ,KeyListener
{
	Container contentPane;
	MainUI mui;
	JScrollPane scrollPane;
	File file;// The obj of file which can be used to make a BufferedImage of
				// the selected Image anywhere.
	private BufferedImage cimg;
	private BufferedImage simg;
	private BufferedImage bi;
	private JTabbedPane jtp;
	private JFileChooser jfcI;
	private JFileChooser jfcS;
	private JMenu lafMenu;
	private JMenuItem importImgItem;
	private JMenuItem exitItem;
	private JMenuItem morphImageItem;
	private JMenuItem saveItem;
	private JMenuItem saveInDBItem;
	private JMenuItem mi;
	private JMenuItem helpItem;
	private JMenuItem aboutItem;
	private JMenuItem undoItem;
	private JMenuItem redBandItem;
	private JMenuItem blueBandItem;
	private JMenuItem greenBandItem;
	private JMenuItem negativeBandItem;
	private JMenuItem yellowBandItem;
	private JMenuItem grayBandItem;
	private JMenuItem PYCCBandItem;
	private JMenuItem hflipBandItem;
	private JMenuItem vflipBandItem;
	imageLoad canvas;// The object which is an instance of class that extends
						// Canvas class.
	private Image selectedImage;
	private Image undoImage;
	private Image redoImage;
	private JPanel canvasPanel;
	private JPanel tabPanel;
	private JPanel tabInnerPanel1;
	private JPanel tabInnerPanel2;
	private JPanel tabInnerPanel3;
	private JPanel tabInnerPanel4;
	private JPanel tabInnerPanel5;
	private JPanel tabInnerPanel6;
	private JPanel tabInnerPanel7;
	private JPanel tabInnerPanel8;
	private JPanel tabInnerPanel9;
	private JPanel tabInnerPanel10;
	private JButton btnCrop;
	private JButton btnScale;
	private JButton btnapply;
	private JButton btnOK;
	private JButton btnHFlip;
	private JButton btnVFlip;
	private JButton btnRotate;
	private JButton btnRedBand;
	private JButton btnBlueBand;
	private JButton btnGreenBand;
	private JButton btnInverseBand;
	private JButton btnAverageBand;
	private JButton btnGray;
	private JButton btnShear;
	private JButton btnSharpen;
	private JButton btnBlur;
	private JButton btnEdge;
	private JButton btnPYCC;
	private JTextField tfX;
	private JTextField tfY;
	private JTextField tfWidth;
	private JTextField tfHeight;
	private JTextField tfscaleWidth;
	private JTextField tfscaleHeight;
	private JTextField tfDegree;
	private JComboBox cbT;
	private JComboBox cbSX;
	private JComboBox cbSY;
	JLabel imgIcon;
	JLabel imageInfo;

	JLabel imageSize;
	int c = 0;
	private static final String mac = "com.sun.java.swing.plaf.mac.MacLookAndFeel";
	private static final String metal = "javax.swing.plaf.metal.MetalLookAndFeel";
	private static final String motif = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
	private static final String windows = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	private static final String gtk = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
	private static final String nimbus = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
	private static final String windowsclassic = "com.sun.java.swing.plaf.windowsclassic.WindowsClassicLookAndFeel";
	String msg = "Please enter numbers only." + "\n"
			+ "Enter valid values which is according to the size of the image.";
	String msgDegree = "Please enter numbers only." + "\n" + "Enter valid value which is between 0 and 360.";
	// The current Look & Feel
	private static String currentLookAndFeel = metal;
	private ButtonGroup lafMenuGroup = new ButtonGroup();
	private static Vector<MainUI> swingSets = new Vector<MainUI>();
	private ResourceBundle bundle = null;
	int j;
	SaveInDB sidb;
	UserInterface theApp;

	public MainUI() {
		setTitle(getString("UITitle.title"));
		setVisible(true);
		setSize(1366, 750);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = getContentPane();
		setJMenuBar(buildMenuBar());
		// -----------------------------------------------TOOLBAR-----------------------------------
		// JToolBar jtb=new JToolBar(JToolBar.VERTICAL);
		// contentPane.add(jtb,BorderLayout.EAST);
		// btnTred=new JButton(new ImageIcon(""));
		// jtb .add(btnTred);
		// -----------------------------------------------------------------------------------------------
		// ------------------------------------------------CANVAS------------------------------------
		canvasPanel = new JPanel();
		canvasPanel.setBorder(new SoftBevelBorder(1));
		contentPane.add(canvasPanel, BorderLayout.CENTER);
		canvas = new imageLoad(null);
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.getViewport().add(canvas);
		canvasPanel.add(scrollPane);
		// -----------------------------------------------------------------------------------------------
		// ------------------------Add Tabbed Pane Panel in
		// container-------------------------------
		tabPanel = new JPanel(new TabPanelLayout());
		tabPanel.setBorder(new BevelBorder(0));
		contentPane.add(tabPanel, BorderLayout.WEST);
		jtp = new JTabbedPane(JTabbedPane.BOTTOM);
		jtp.setBorder(new BevelBorder(0));
		tabPanel.add(jtp);
		btnapply = new JButton("APPLY CHANGES");
		btnapply.setToolTipText("Enables saving option.");
		tabPanel.add(btnapply);
		btnapply.addActionListener(this);

		imgIcon = new JLabel();
		tabPanel.add(imgIcon);
		imageInfo = new JLabel();
		tabPanel.add(imageInfo);
		imageSize = new JLabel();
		tabPanel.add(imageSize);
		// tabInnerPanel1=createBriConControls();
		// jtp.addTab("Information",null,tabInnerPanel1,"INFORMATION");
		tabInnerPanel2 = createCropControls();
		jtp.addTab("Crop", null, tabInnerPanel2, "CROP IMAGE");
		tabInnerPanel3 = createScaleControls();
		jtp.addTab("Scale/Resize", null, tabInnerPanel3, "SCALE OR RESIZE IMAGE");
		tabInnerPanel4 = createTransparencyControls();
		jtp.addTab("Transparent", null, tabInnerPanel4, "CHANGE TRANSPARENCY");
		tabInnerPanel5 = createFlipControls();
		jtp.addTab("Flip", null, tabInnerPanel5, "FLIP IMAGE");
		tabInnerPanel6 = createRotateControls();
		jtp.addTab("Rotate", null, tabInnerPanel6, "ROTATE IMAGE");
		tabInnerPanel7 = createColEffectsControls();
		jtp.addTab("Color Effects", null, tabInnerPanel7, "COLOR EFFECTS");
		tabInnerPanel8 = createDiffColEffectControls();
		jtp.addTab("Photo Effects", null, tabInnerPanel8, "PHOTO EFFECTS");
		tabInnerPanel9 = createShearEffectControls();
		jtp.addTab("Shear", null, tabInnerPanel9, "SHEAR IMAGE");
		tabInnerPanel10 = createConvolveEffectControls();
		jtp.addTab("Convolve", null, tabInnerPanel10, "CONVOLVE EFFECTS");
		// ----------------------------------------------------------------------------------------------------------------------

	}// class constructor end.

	// ---------------------------------------Canvas
	// class----------------------------------------
	public class imageLoad extends Canvas {
		Image img;

		public imageLoad(Image img) {
			this.img = img;
		}

		public void paint(Graphics g) {
			if (img != null) {
				if ((img.getWidth(this) > 1024) && (img.getHeight(this) > 680))
					g.drawImage(img, 0, 0, 1024, 680, this);
				else
					g.drawImage(img, 0, 0, img.getWidth(this), img.getHeight(this), this);
			}
		}

		public void setImage(Image img) {
			this.img = img;
		}

		public Image getCanvasImage() {
			return this.img;
		}

		public BufferedImage getCanvasBImage() {
			Image im = this.img;
			undoImage = this.img;
			int w = im.getWidth(null);
			int h = im.getHeight(null);
			BufferedImage bi = new BufferedImage(w, h, 1);
			Graphics2D g2 = bi.createGraphics();
			g2.drawImage(this.img, 0, 0, null);
			g2.dispose();

			return bi;
		}
	}

	// ------------------------------------------------------------------------------------------------
	// -------------------------------create com.jphotomatic.shape.Crop Inner
	// Panel--------------------------------
	public JPanel createCropControls() {
		JPanel ciPanel = new JPanel();
		// ciPanel.setToolTipText(cropHelp);
		// ciPanel.setBorder(new BevelBorder(1));
		GridLayout clayout = new GridLayout(7, 2);
		ciPanel.setLayout(clayout);
		JLabel lblX = new JLabel("X:");
		JLabel lblY = new JLabel("Y:");
		JLabel lblWidth = new JLabel("Width:");
		JLabel lblHeight = new JLabel("Height:");
		JLabel empty1 = new JLabel(" ");
		JLabel empty2 = new JLabel(" ");
		JLabel empty3 = new JLabel(" ");
		JLabel empty4 = new JLabel(" ");
		btnCrop = new JButton("Crop");
		btnCrop.setEnabled(false);

		tfX = new JTextField(4);
		tfY = new JTextField(4);
		tfWidth = new JTextField(4);
		tfHeight = new JTextField(4);

		ciPanel.add(lblX);
		ciPanel.add(tfX);
		ciPanel.add(lblY);
		ciPanel.add(tfY);
		ciPanel.add(lblWidth);
		ciPanel.add(tfWidth);
		ciPanel.add(lblHeight);
		ciPanel.add(tfHeight);
		ciPanel.add(empty1);
		ciPanel.add(empty2);
		ciPanel.add(btnCrop);
		btnCrop.addActionListener(this);
		ciPanel.add(empty3);
		ciPanel.add(empty4);

		tfX.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				char c = ke.getKeyChar();
				if (c >= '0' && c <= '9' || c == '\b') {
				} else {
					JOptionPane.showMessageDialog(null, msg, "Error", 0);
					tfX.setText("");
				}
			}
		});
		tfY.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				char c = ke.getKeyChar();
				if (c >= '0' && c <= '9' || c == '\b') {
				} else {
					JOptionPane.showMessageDialog(null, msg, "Error", 0);
					tfY.setText("");
				}
			}
		});
		tfWidth.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				char c = ke.getKeyChar();
				if (c >= '0' && c <= '9' || c == '\b') {
				} else {
					JOptionPane.showMessageDialog(null, msg, "Error", 0);
					tfWidth.setText("");
				}
			}
		});
		tfHeight.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				char c = ke.getKeyChar();
				if (c >= '0' && c <= '9' || c == '\b') {
				} else {
					JOptionPane.showMessageDialog(null, msg, "Error", 0);
					tfHeight.setText("");
				}
			}
		});

		return ciPanel;
	}

	/*---------------------------------------------------------------------------------------------*/
	// -----------------------------------create com.jphotomatic.shape.Scale
	// Inner Panel---------------------------------
	public JPanel createScaleControls() {
		JPanel siPanel = new JPanel();
		// siPanel.setToolTipText(scaleHelp);
		GridLayout slayout = new GridLayout(7, 2);
		siPanel.setLayout(slayout);
		JLabel lblscaleWidth = new JLabel("Width:");
		JLabel lblscaleHeight = new JLabel("Height:");
		JLabel empty1 = new JLabel(" ");
		JLabel empty2 = new JLabel(" ");
		JLabel empty3 = new JLabel(" ");
		JLabel empty4 = new JLabel(" ");
		JLabel empty5 = new JLabel("  ");
		JLabel empty6 = new JLabel("  ");
		JLabel empty7 = new JLabel("  ");
		JLabel empty8 = new JLabel("  ");
		btnScale = new JButton("Scale");
		btnScale.setEnabled(false);
		tfscaleWidth = new JTextField(4);
		tfscaleHeight = new JTextField(4);

		siPanel.add(lblscaleWidth);
		siPanel.add(tfscaleWidth);
		siPanel.add(lblscaleHeight);
		siPanel.add(tfscaleHeight);
		siPanel.add(empty1);
		siPanel.add(empty2);
		siPanel.add(btnScale);
		btnScale.addActionListener(this);
		siPanel.add(empty3);
		siPanel.add(empty4);
		siPanel.add(empty5);
		siPanel.add(empty6);
		siPanel.add(empty7);
		siPanel.add(empty8);

		tfscaleWidth.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				char c = ke.getKeyChar();
				if (c >= '0' && c <= '9' || c == '\b') {
				} else {
					JOptionPane.showMessageDialog(null, msg, "Error", 0);
					tfscaleWidth.setText("");
				}
			}
		});
		tfscaleHeight.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				char c = ke.getKeyChar();
				if (c >= '0' && c <= '9' || c == '\b') {
				} else {
					JOptionPane.showMessageDialog(null, msg, "Error", 0);
					tfscaleHeight.setText("");
				}
			}
		});
		return siPanel;
	}

	/*-----------------------------------------------------------------------------------------------------------------*/
	// ------------------------------create transparency
	// panel-------------------------------------------------
	public JPanel createTransparencyControls() {
		JPanel ti = new JPanel();
		ti.setLayout(null);
		JLabel lblTval = new JLabel("Transparency Value:");
		cbT = new JComboBox(
				new String[] { "0%", "10%", "20%", "30%", "40%", "50%", "60%", "70%", "80%", "90%", "100%" });
		btnOK = new JButton("OK");
		btnOK.setEnabled(false);
		lblTval.setBounds(0, 0, 150, 100);
		cbT.setBounds(0, 60, 60, 30);
		btnOK.setBounds(0, 120, 100, 30);
		ti.add(lblTval);
		ti.add(cbT);
		ti.add(btnOK);
		btnOK.addActionListener(this);

		return ti;

	}

	/*-----------------------------------------------------------------------------------------------------------*/
	// ------------------------------create FLIP'per
	// panel-------------------------------------------------
	public JPanel createFlipControls() {
		JPanel fi = new JPanel();
		fi.setLayout(null);

		btnVFlip = new JButton("Vertical Flip");
		btnHFlip = new JButton("Horizontal Flip");
		btnVFlip.setEnabled(false);
		btnHFlip.setEnabled(false);
		btnVFlip.setBounds(20, 40, 150, 30);
		btnHFlip.setBounds(20, 80, 150, 30);

		fi.add(btnVFlip);
		fi.add(btnHFlip);
		btnVFlip.addActionListener(this);
		btnHFlip.addActionListener(this);

		return fi;

	}

	/*-----------------------------------------------------------------------------------------------------------*/
	// ------------------------------create Image Rotator
	// panel-------------------------------------------------
	public JPanel createRotateControls() {
		JPanel ri = new JPanel();
		ri.setLayout(null);

		JLabel lblR = new JLabel("Degree:");
		btnRotate = new JButton("Rotate");
		btnRotate.setEnabled(false);
		tfDegree = new JTextField(4);
		lblR.setBounds(0, 0, 150, 100);
		tfDegree.setBounds(0, 60, 40, 30);
		btnRotate.setBounds(0, 120, 100, 30);

		ri.add(lblR);
		ri.add(tfDegree);
		ri.add(btnRotate);

		btnRotate.addActionListener(this);

		tfDegree.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				char c = ke.getKeyChar();
				if (c >= '0' && c <= '9' || c == '\b') {
				} else {
					JOptionPane.showMessageDialog(null, msgDegree, "Error", 0);
					tfDegree.setText("");
				}
			}

			public void keyReleased(KeyEvent ke) {
				int i = Integer.parseInt(tfDegree.getText().trim());
				int count = 1;
				if (!(i >= 0 && i <= 360)) {
					if (count == 1) {
						JOptionPane.showMessageDialog(null, msgDegree, "Error", 0);
					}
					tfDegree.setText("");
				}
			}
		});
		return ri;

	}

	/*-----------------------------------------------------------------------------------------------------------*/
	// ------------------------------create Image Effects
	// panel-------------------------------------------------
	public JPanel createColEffectsControls() {
		JPanel ce = new JPanel();
		ce.setLayout(null);

		btnRedBand = new JButton("Red Band Effect");
		btnBlueBand = new JButton("Blue Band Effect");
		btnGreenBand = new JButton("Green Band Effect");
		btnAverageBand = new JButton("Yellow Band Effect");
		btnRedBand.setBounds(20, 40, 150, 30);
		btnGreenBand.setBounds(20, 80, 150, 30);
		btnBlueBand.setBounds(20, 120, 150, 30);
		btnAverageBand.setBounds(20, 160, 150, 30);

		btnRedBand.setEnabled(false);
		btnBlueBand.setEnabled(false);
		btnGreenBand.setEnabled(false);
		btnAverageBand.setEnabled(false);

		ce.add(btnRedBand);
		ce.add(btnBlueBand);
		ce.add(btnGreenBand);
		ce.add(btnAverageBand);

		btnRedBand.addActionListener(this);
		btnBlueBand.addActionListener(this);
		btnGreenBand.addActionListener(this);
		btnAverageBand.addActionListener(this);

		return ce;

	}

	/*-----------------------------------------------------------------------------------------------------------*/
	// ----------------------------------------create Diff color Effect
	// Panel--------------------------------------
	public JPanel createDiffColEffectControls() {
		JPanel colPanel = new JPanel();
		colPanel.setLayout(null);
		btnGray = new JButton("Black & White Effect");
		btnGray.setBounds(20, 40, 150, 30);
		colPanel.add(btnGray);
		btnGray.addActionListener(this);
		btnPYCC = new JButton("Photo YCC Effect");
		btnPYCC.setBounds(20, 80, 150, 30);
		colPanel.add(btnPYCC);
		btnInverseBand = new JButton("Negative Effect");
		btnInverseBand.setBounds(20, 120, 150, 30);
		colPanel.add(btnInverseBand);
		btnInverseBand.setEnabled(false);
		btnGray.setEnabled(false);
		btnPYCC.setEnabled(false);
		btnPYCC.addActionListener(this);
		btnInverseBand.addActionListener(this);
		return colPanel;
	}

	/*-----------------------------------------------------------------------------------------------------------*/
	// --------------------------------------create Shear Inner
	// Panel-----------------------------------------------
	public JPanel createShearEffectControls() {
		JPanel shearPanel = new JPanel();
		shearPanel.setLayout(null);
		btnShear = new JButton("Shear");
		cbSX = new JComboBox(new String[] { "0.00", "0.25", "0.50", "0.75", "1.00" });
		cbSY = new JComboBox(new String[] { "0.00", "0.25", "0.50", "0.75", "1.00" });
		JLabel lblsx = new JLabel("Shear X:");
		JLabel lblsy = new JLabel("Shear Y:");
		lblsx.setBounds(20, 40, 150, 30);
		cbSX.setBounds(90, 40, 80, 30);
		lblsy.setBounds(20, 80, 150, 30);
		cbSY.setBounds(90, 80, 80, 30);
		btnShear.setBounds(20, 120, 70, 30);
		shearPanel.add(lblsx);
		shearPanel.add(cbSX);
		shearPanel.add(lblsy);
		shearPanel.add(cbSY);
		shearPanel.add(btnShear);
		btnShear.setEnabled(false);
		btnShear.addActionListener(this);
		return shearPanel;
	}

	/*-----------------------------------------------------------------------------------------*/
	// --------------------------------------create Convolve Inner
	// Panel-----------------------------------
	public JPanel createConvolveEffectControls() {
		JPanel convolvePanel = new JPanel();
		convolvePanel.setLayout(null);
		btnSharpen = new JButton("Sharpen Effect");
		btnSharpen.setBounds(20, 40, 150, 30);
		convolvePanel.add(btnSharpen);
		btnSharpen.addActionListener(this);
		btnBlur = new JButton("Blur Effect");
		btnBlur.setBounds(20, 80, 150, 30);
		convolvePanel.add(btnBlur);
		btnEdge = new JButton("Edge Effect");
		btnEdge.setBounds(20, 120, 150, 30);
		convolvePanel.add(btnEdge);
		btnEdge.setEnabled(false);
		btnSharpen.setEnabled(false);
		btnBlur.setEnabled(false);
		btnEdge.addActionListener(this);
		btnBlur.addActionListener(this);
		return convolvePanel;
	}

	/*-----------------------------------------------------------------------------------------*/
	// --------------------------------------Enable Button &
	// menuitems---------------------------
	public void enableAllButtonsAndItems() {
		btnScale.setEnabled(true);
		btnCrop.setEnabled(true);
		btnOK.setEnabled(true);
		btnHFlip.setEnabled(true);
		btnVFlip.setEnabled(true);
		btnRotate.setEnabled(true);
		btnRedBand.setEnabled(true);
		btnBlueBand.setEnabled(true);
		btnGreenBand.setEnabled(true);
		btnInverseBand.setEnabled(true);
		btnAverageBand.setEnabled(true);
		btnGray.setEnabled(true);
		btnPYCC.setEnabled(true);
		redBandItem.setEnabled(true);
		greenBandItem.setEnabled(true);
		blueBandItem.setEnabled(true);
		yellowBandItem.setEnabled(true);
		grayBandItem.setEnabled(true);
		PYCCBandItem.setEnabled(true);
		negativeBandItem.setEnabled(true);
		hflipBandItem.setEnabled(true);
		vflipBandItem.setEnabled(true);
		btnShear.setEnabled(true);
		btnSharpen.setEnabled(true);
		btnBlur.setEnabled(true);
		btnEdge.setEnabled(true);
	}

	/*------------------------------------------------------------------------------------------------------*/
	// ------------------------------------------FLOAT
	// GENERATORS----------------------------------
	public float generateFloatVal(String p) {
		if (p.equals("0%"))
			return (float) 1.0;
		else if (p.equals("10%"))
			return (float) 0.9;
		else if (p.equals("20%"))
			return (float) 0.8;
		else if (p.equals("30%"))
			return (float) 0.7;
		else if (p.equals("40%"))
			return (float) 0.6;
		else if (p.equals("50%"))
			return (float) 0.5;
		else if (p.equals("60%"))
			return (float) 0.4;
		else if (p.equals("70%"))
			return (float) 0.3;
		else if (p.equals("80%"))
			return (float) 0.2;
		else if (p.equals("90%"))
			return (float) 0.1;
		else if (p.equals("100%"))
			return (float) 0.0;
		else
			return (float) 1.0;
	}

	// -------------------------------------------Menubar----------------------------------------------------------------
	public JMenuBar buildMenuBar() {
		JMenuBar mb = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		fileMenu.setToolTipText("FILE");
		JMenu editMenu = new JMenu("Edit");
		editMenu.setToolTipText("EDIT");
		exitItem = new JMenuItem("Exit");
		importImgItem = new JMenuItem("Import Image");
		saveItem = new JMenuItem("Save Image in Folder");
		saveInDBItem=new JMenuItem("Save Image in DB");
		morphImageItem = new JMenuItem("Morph Image");
		redBandItem = new JMenuItem("Red Effect..");
		greenBandItem = new JMenuItem("Green Effect..");
		blueBandItem = new JMenuItem("Blue Effect..");
		yellowBandItem = new JMenuItem("Yellow Effect..");
		negativeBandItem = new JMenuItem("Negative Effect..");
		grayBandItem = new JMenuItem("Black & White Effect..");
		PYCCBandItem = new JMenuItem("Photo YCC Effect..");
		hflipBandItem = new JMenuItem("Flip Horizontally");
		vflipBandItem = new JMenuItem("Flip Vertically");
		saveItem.setEnabled(false);
		saveInDBItem.setEnabled(false);
		undoItem = new JMenuItem("Undo");
		KeyStroke ks0 = KeyStroke.getKeyStroke(KeyEvent.VK_Z, Event.CTRL_MASK);
		undoItem.setAccelerator(ks0);

		fileMenu.setMnemonic(KeyEvent.VK_F);
		editMenu.setMnemonic(KeyEvent.VK_E);
		importImgItem.setMnemonic(KeyEvent.VK_I);
		saveItem.setMnemonic('S');
		KeyStroke ks1 = KeyStroke.getKeyStroke(KeyEvent.VK_M, Event.CTRL_MASK);
		morphImageItem.setAccelerator(ks1);
		KeyStroke ks3 = KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK);
		saveItem.setAccelerator(ks3);
		KeyStroke ksDB = KeyStroke.getKeyStroke(KeyEvent.VK_D, Event.SHIFT_MASK);
		saveInDBItem.setAccelerator(ksDB);
		exitItem.setMnemonic(KeyEvent.VK_X);

		mb.add(fileMenu);
		mb.add(editMenu);

		fileMenu.add(importImgItem);
		fileMenu.add(saveItem);
		fileMenu.add(saveInDBItem);
		fileMenu.addSeparator();
		fileMenu.add(morphImageItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		editMenu.add(undoItem);
		editMenu.addSeparator();
		editMenu.add(redBandItem);
		editMenu.add(greenBandItem);
		editMenu.add(blueBandItem);
		editMenu.add(yellowBandItem);
		editMenu.add(negativeBandItem);
		editMenu.add(grayBandItem);
		editMenu.add(PYCCBandItem);
		editMenu.addSeparator();
		editMenu.add(hflipBandItem);
		editMenu.add(vflipBandItem);

		importImgItem.addActionListener(this);
		saveItem.addActionListener(this);
		saveInDBItem.addActionListener(this);
		morphImageItem.addActionListener(this);
		exitItem.addActionListener(this);
		undoItem.addActionListener(this);
		redBandItem.addActionListener(this);
		greenBandItem.addActionListener(this);
		blueBandItem.addActionListener(this);
		yellowBandItem.addActionListener(this);
		negativeBandItem.addActionListener(this);
		grayBandItem.addActionListener(this);
		PYCCBandItem.addActionListener(this);
		hflipBandItem.addActionListener(this);
		vflipBandItem.addActionListener(this);

		lafMenu = (JMenu) mb.add(new JMenu(getString("LafMenu.laf_label")));
		lafMenu.setMnemonic(getMnemonic("LafMenu.laf_mnemonic"));
		lafMenu.getAccessibleContext().setAccessibleDescription(getString("LafMenu.laf_accessible_description"));
		lafMenu.setToolTipText("LOOK AND FEEL");

		JMenu helpMenu = new JMenu(getString("HelpMenu.h_label"));
		helpMenu.setMnemonic(getMnemonic("HelpMenu.h_mnemonic"));
		helpItem = new JMenuItem(getString("HelpMenu.Help_label"));
		aboutItem = new JMenuItem(getString("HelpMenu.About_label"));
		helpItem.setMnemonic(getMnemonic("HelpMenu.Help_mnemonic"));
		aboutItem.setMnemonic(getMnemonic("HelpMenu.About_mnemonic"));
		KeyStroke ks2 = KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0);
		helpItem.setAccelerator(ks2);
		mb.add(helpMenu);
		helpMenu.add(helpItem);
		helpMenu.add(aboutItem);
		helpItem.addActionListener(this);
		aboutItem.addActionListener(this);

		redBandItem.setEnabled(false);
		greenBandItem.setEnabled(false);
		blueBandItem.setEnabled(false);
		yellowBandItem.setEnabled(false);
		grayBandItem.setEnabled(false);
		PYCCBandItem.setEnabled(false);
		negativeBandItem.setEnabled(false);
		hflipBandItem.setEnabled(false);
		vflipBandItem.setEnabled(false);

		mi = createLafMenuItem(lafMenu, "LafMenu.java_label", "LafMenu.java_mnemonic",
				"LafMenu.java_accessible_description", metal);
		mi.setSelected(true); // this is the default l&f

		UIManager.LookAndFeelInfo[] lafInfo = UIManager.getInstalledLookAndFeels();

		for (int counter = 0; counter < lafInfo.length; counter++) {
			String className = lafInfo[counter].getClassName();
			if (className == motif) {
				createLafMenuItem(lafMenu, "LafMenu.motif_label", "LafMenu.motif_mnemonic",
						"LafMenu.motif_accessible_description", motif);
			} else if (className == windows) {
				createLafMenuItem(lafMenu, "LafMenu.windows_label", "LafMenu.windows_mnemonic",
						"LafMenu.windows_accessible_description", windows);
			} else if (className == gtk) {
				createLafMenuItem(lafMenu, "LafMenu.gtk_label", "LafMenu.gtk_mnemonic",
						"LafMenu.gtk_accessible_description", gtk);
			} else if (className == nimbus) {
				createLafMenuItem(lafMenu, "LafMenu.nimbus_label", "LafMenu.nimbus_mnemonic",
						"LafMenu.nimbus_accessible_description", nimbus);
			} else if (className == windowsclassic) {
				createLafMenuItem(lafMenu, "LafMenu.windowsclassic_label", "LafMenu.windowsclassic_mnemonic",
						"LafMenu.windowsclassic_accessible_description", windowsclassic);
			}
		}

		return mb;

	}

	// ---------------------------------------------------------------------------------------------------------------------
	// -----------------------------RESOURCE BUNDLE FOR OUR
	// LOCALE---------------------------------------
	public ResourceBundle getResourceBundle() {
		if (bundle == null) {
			bundle = ResourceBundle.getBundle("com.jphotomatic.resources.swingLAF");
		}
		return bundle;
	}

	public char getMnemonic(String key) {
		return (getString(key)).charAt(0);
	}

	public String getString(String key) {
		String value = null;
		try {
			value = getResourceBundle().getString(key);
		} catch (MissingResourceException e) {
			System.out.println("java.util.MissingResourceException: Couldn't find value for: " + key);
		}
		if (value == null) {
			value = "Could not find resource: " + key + "  ";
		}
		return value;
	}

	/*-------------------------------------------------------------------------------------------------------------*/
	// ---------------------------------------LAF menuitem
	// creator----------------------------------------------------
	public JMenuItem createLafMenuItem(JMenu menu, String label, String mnemonic, String accessibleDescription,
			String laf) {
		JMenuItem mi = (JRadioButtonMenuItem) menu.add(new JRadioButtonMenuItem(getString(label)));
		lafMenuGroup.add(mi);
		mi.setMnemonic(getMnemonic(mnemonic));
		mi.getAccessibleContext().setAccessibleDescription(getString(accessibleDescription));
		mi.addActionListener(new ChangeLookAndFeelAction(this, laf));

		mi.setEnabled(isAvailableLookAndFeel(laf));

		return mi;
	}

	/*-----------------------------------------------------------------------------------------------------------------*/
	// -------------------------------------------LAF checker
	// set'ter---------------------------------------------------
	protected boolean isAvailableLookAndFeel(String laf) {
		try {
			Class lnfClass = Class.forName(laf);
			LookAndFeel newLAF = (LookAndFeel) (lnfClass.newInstance());
			return newLAF.isSupportedLookAndFeel();
		} catch (Exception e) { // If ANYTHING weird happens, return false
			return false;
		}
	}

	public void setLookAndFeel(String laf) {
		if (currentLookAndFeel != laf) {
			currentLookAndFeel = laf;
			String lafName = null;
			if (laf == mac)
				lafName = getString("LafMenu.mac_label");
			if (laf == metal)
				lafName = getString("LafMenu.java_label");
			if (laf == motif)
				lafName = getString("LafMenu.motif_label");
			if (laf == windows)
				lafName = getString("LafMenu.windows_label");
			if (laf == gtk)
				lafName = getString("LafMenu.gtk_label");
			if (laf == nimbus)
				lafName = getString("LafMenu.nimbus_label");
			if (laf == windowsclassic)
				lafName = getString("LafMenu.windowsclassic_label");
			// themesMenu.setEnabled(laf == metal);
			updateLookAndFeel();
			for (int i = 0; i < lafMenu.getItemCount(); i++) {
				JMenuItem item = lafMenu.getItem(i);
				if (item.getText() == lafName) {
					item.setSelected(true);
				} else {
					item.setSelected(false);
				}
			}
		}
	}

	private void updateThisSwingSet() {
		SwingUtilities.updateComponentTreeUI(this);
	}

	public void updateLookAndFeel() {
		try {
			UIManager.setLookAndFeel(currentLookAndFeel);
			updateThisSwingSet();
			for (MainUI ss : swingSets) {
				ss.updateThisSwingSet();
			}
		} catch (Exception ex) {
			System.out.println("Failed loading L&F: " + currentLookAndFeel);
			System.out.println(ex);
		}
	}

	class ChangeLookAndFeelAction extends AbstractAction {
		MainUI mainUI;
		String laf;

		protected ChangeLookAndFeelAction(MainUI mainUI, String laf) {
			super("ChangeTheme");
			this.mainUI = mainUI;
			this.laf = laf;
		}

		public void actionPerformed(ActionEvent e) {
			mainUI.setLookAndFeel(laf);
		}
	}

	/*---------------------------------------------------------------------------------------------*/
	// ----------------------------------------------ACTION-------------------------------------
	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == importImgItem) {
			if (jfcI == null) {
				jfcI = new JFileChooser("D:");
				jfcI.addChoosableFileFilter(new ImageFilter());
				jfcI.setAccessory(new ImagePreview(jfcI));
			}
			int i = jfcI.showOpenDialog(this);
			if (i == JFileChooser.APPROVE_OPTION) {
				file = jfcI.getSelectedFile();
				String imgName = file.getName();
				selectedImage = Toolkit.getDefaultToolkit().getImage(file.getPath());
				int w = selectedImage.getWidth(this);
				int h = selectedImage.getHeight(this);
				canvas.setImage(selectedImage);
				imgIcon.setIcon(new ImageIcon(selectedImage.getScaledInstance(90, -1, 1)));
				imageInfo.setText(imgName);
				imageSize.setText(w + "x" + h + " px");
				enableAllButtonsAndItems();
				canvas.setSize(new Dimension(w, h));
				scrollPane.setPreferredSize(new Dimension(w, h));
				canvas.repaint();

			}
		}

		if (ae.getSource() == exitItem) {
			System.exit(0);
		}
		if (ae.getSource() == btnapply && canvas.getCanvasImage() != null) {
			saveItem.setEnabled(true);
			saveInDBItem.setEnabled(true);
		} else {
			saveItem.setEnabled(false);
			// JOptionPane.showMessageDialog(this,"No Image.\nImport image from
			// FILE menu or press Alt+F and I","Image is not
			// specified",JOptionPane.ERROR_MESSAGE);
		}

		if (ae.getSource() == btnCrop) {
			int valX = Integer.parseInt(tfX.getText().trim());
			int valY = Integer.parseInt(tfY.getText().trim());
			int valWidth = Integer.parseInt(tfWidth.getText().trim());
			int valHeight = Integer.parseInt(tfHeight.getText().trim());

			Crop crp = new Crop();
			cimg = crp.cropImage(canvas.getCanvasImage(), valX, valY, valWidth, valHeight);
			canvas.setImage((Image) cimg);
			canvas.repaint();
			tfY.setText("");
			tfX.setText("");
			tfWidth.setText("");
			tfHeight.setText("");
		}

		if (ae.getSource() == btnScale) {
			int valsWidth = Integer.parseInt(tfscaleWidth.getText().trim());
			int valsHeight = Integer.parseInt(tfscaleHeight.getText().trim());
			BufferedImage Sclimg = canvas
					.getCanvasBImage();/* CreateImage.CreateBImage(file); */
			cimg = Scale.Resize(Sclimg, valsWidth, valsHeight);
			canvas.setImage((Image) cimg);
			canvas.repaint();
			tfscaleWidth.setText("");
			tfscaleHeight.setText("");
		}

		if (ae.getSource() == btnHFlip || ae.getSource() == hflipBandItem) {
			BufferedImage fimg = canvas
					.getCanvasBImage();/* CreateImage.CreateBImage(file); */
			cimg = HVFlip.HorizontalFlip(fimg);
			canvas.setImage((Image) cimg);
			canvas.repaint();
		}

		if (ae.getSource() == btnVFlip || ae.getSource() == vflipBandItem) {
			BufferedImage fimg = canvas
					.getCanvasBImage();/* CreateImage.CreateBImage(file); */
			cimg = HVFlip.VerticalFlip(fimg);
			canvas.setImage((Image) cimg);
			canvas.repaint();
		}

		if (ae.getSource() == btnOK) {
			String per = cbT.getSelectedItem().toString();
			float Tval = generateFloatVal(per);
			BufferedImage Tbi = canvas
					.getCanvasBImage();/* CreateImage.CreateBImage(file); */
			cimg = TranslucentImage.createTranslucentImage(Tbi, Tval);
			canvas.setImage((Image) cimg);
			canvas.repaint();
			// tfT.setText("");
		}

		if (ae.getSource() == btnRotate) {
			int Rval = Integer.parseInt(tfDegree.getText().trim());
			BufferedImage Rbi = canvas
					.getCanvasBImage();/* CreateImage.CreateBImage(file); */
			cimg = ImageRotator.Rotate(Rbi, Rval);
			canvas.setImage((Image) cimg);
			canvas.repaint();
			tfDegree.setText("");
		}

		if (ae.getSource() == btnRedBand || ae.getSource() == redBandItem) {
			BufferedImage Bimg = canvas
					.getCanvasBImage();/* CreateImage.CreateBImage(file); */
			cimg = BandEffects.RedBandEff(Bimg);
			canvas.setImage((Image) cimg);
			canvas.repaint();
		}

		if (ae.getSource() == btnBlueBand || ae.getSource() == blueBandItem) {
			BufferedImage Bimg = canvas
					.getCanvasBImage();/* CreateImage.CreateBImage(file); */
			cimg = BandEffects.BlueBandEff(Bimg);
			canvas.setImage((Image) cimg);
			canvas.repaint();
		}

		if (ae.getSource() == btnGreenBand || ae.getSource() == greenBandItem) {
			BufferedImage Bimg = canvas
					.getCanvasBImage();/* CreateImage.CreateBImage(file); */
			cimg = BandEffects.GreenBandEff(Bimg);
			canvas.setImage((Image) cimg);
			canvas.repaint();
		}

		if (ae.getSource() == btnInverseBand || ae.getSource() == negativeBandItem) {
			BufferedImage Bimg = canvas
					.getCanvasBImage();/* CreateImage.CreateBImage(file); */
			cimg = BandEffects.InverseBandEff(Bimg);
			canvas.setImage((Image) cimg);
			canvas.repaint();
		}

		if (ae.getSource() == btnAverageBand || ae.getSource() == yellowBandItem) {
			BufferedImage Bimg = canvas
					.getCanvasBImage();/* CreateImage.CreateBImage(file); */
			cimg = BandEffects.AverageBandEff(Bimg);
			canvas.setImage((Image) cimg);
			canvas.repaint();
		}

		if (ae.getSource() == btnGray || ae.getSource() == grayBandItem) {
			BufferedImage Dimg = canvas
					.getCanvasBImage();/* CreateImage.CreateBImage(file); */
			cimg = MultiColorOp.GrayColorEff(Dimg);
			canvas.setImage((Image) cimg);
			canvas.repaint();
		}

		if (ae.getSource() == btnPYCC || ae.getSource() == PYCCBandItem) {
			BufferedImage Dimg = canvas
					.getCanvasBImage();/* CreateImage.CreateBImage(file); */
			cimg = MultiColorOp.PYCCEff(Dimg);
			canvas.setImage((Image) cimg);
			canvas.repaint();
		}

		if (ae.getSource() == btnShear) {
			double sx = Double.parseDouble(cbSX.getSelectedItem().toString());
			double sy = Double.parseDouble(cbSY.getSelectedItem().toString());
			BufferedImage Simg = canvas.getCanvasBImage();
			cimg = ShearImage.Shear(Simg, sx, sy);
			canvas.setImage((Image) cimg);
			canvas.repaint();
		}

		if (ae.getSource() == btnSharpen) {
			BufferedImage src = canvas.getCanvasBImage();
			cimg = ConvolveFilters.Sharpen(src);
			canvas.setImage((Image) cimg);
			canvas.repaint();
		}

		if (ae.getSource() == btnBlur) {
			BufferedImage src = canvas.getCanvasBImage();
			cimg = ConvolveFilters.Blur(src);
			canvas.setImage((Image) cimg);
			canvas.repaint();
		}

		if (ae.getSource() == btnEdge) {
			BufferedImage src = canvas.getCanvasBImage();
			cimg = ConvolveFilters.EdgeDetect(src);
			canvas.setImage((Image) cimg);
			canvas.repaint();
		}

		if (ae.getSource() == saveItem) {
			jfcS = new JFileChooser("D:");
			if (canvas.img != null) {
				j = jfcS.showSaveDialog(this);
			}

			if (j == JFileChooser.APPROVE_OPTION) {
				try {
					File sf = jfcS.getSelectedFile();
					ImageIO.write(cimg, "png", new File(sf.getPath()));
				} catch (IOException ie) {
					ie.printStackTrace();
				}
			}
		}

		if (ae.getSource() == morphImageItem) {
		  new UserInterface();
		}

		if (ae.getSource() == helpItem) {
			new HelpFrame();
		}

		if (ae.getSource() == aboutItem) {
			JDialog jd = new JDialog(this, "About");
			JPanel ap = new JPanel();
			jd.setVisible(true);
			jd.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
			jd.setTitle("About");
			int width = 310;
			int height = 220;
			Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (screen.width - width) / 2;
			int y = (screen.height - height) / 2;
			jd.setBounds(x, y, width, height);
			JLabel lb = new JLabel(new ImageIcon("src/com/jphotomatic/resources/images/about.png"), JLabel.CENTER);
			ap.add(lb);
			jd.getContentPane().add(ap);
		}

		if (ae.getSource() == undoItem) {
			canvas.setImage((Image) undoImage);
			canvas.repaint();

		}
		 if (ae.getSource() == saveInDBItem) {
		  sidb = new SaveInDB("Save Image In Database", canvas.getCanvasImage());
		 }

	}

	// -------------------------------------------------------------------------------------------
	// ----------------------------------------CHANGE----------------------------------------
	/*
	 * public void stateChanged(ChangeEvent ce) { if(ce.getSource()==bslider) {
	 * setBrightnessFactor((float)((bslider.getValue())*0.5));//.8 } }
	 */
	/*------------------------------------------------------------------------------------------*/
	// --------------------------------------------------------------------------------------------
	public static void main(String[] args) {
		Splash.showSplash(2000);
		new MainUI();
	}
	/*------------------------------------------------------------------------------------------*/

}