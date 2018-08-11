package com.jphotomatic.model;

import com.jphotomatic.layouts.detailsLayout;
import com.jphotomatic.layouts.outputLayout;
import com.jphotomatic.layouts.singleLayout;
import com.jphotomatic.filters.ImageFilter;

import java.awt.*;                            //Contains all of the classes for creating user interfaces and for painting graphics and images.
import java.awt.event.*;
import java.awt.image.PixelGrabber;
import java.io.File;
import javax.swing.*;

public class UserInterface extends JFrame
        implements ActionListener {
    JPanel output;
    JLabel imgmorph;
    JLabel srcimg;
    JLabel interimg;
    JLabel destimg;
    JLabel interm;
    JButton bs;
    JButton bd;
    JButton morph;
    Display d;
    Display ds;
    JPanel details;
    JLabel detail;
    JLabel algolabel;
    JLabel animlabel;
    JLabel framelabel;
    JLabel ratelabel;
    JLabel savelabel;
    JRadioButton crossd;
    JRadioButton ptwarp;
    JRadioButton linewarp;
    JRadioButton continuous;
    JRadioButton fbyf;
    JTextField frameField;
    JTextField fps;
    JButton stImages;
    JCheckBox saveImg;
    JLabel imgPath;
    String defaultPath;
    JPanel single;
    JLabel slabel;
    JLabel interlabel;
    JButton bs2;
    JButton morph2;
    JFileChooser fc;
    JFileChooser fc2;
    String fimage;
    Display obj;
    JInternalFrame sourceFrame;
    JInternalFrame destFrame;
    int algorithm;
    int animation;
    int flag;
    int flag2;
    int frames;
    int rate;
    int siw;
    int sih;
    int diw;
    int dih;
    int iw;
    int ih;
    int larger;
    int newImage[];
    PixelGrabber pg;
    Image imgS;
    Image imgD;
    Image singleImg;
    JLabel intermLabel;
    ImageIcon intermIcon;
    File fimg;
    Image scaledImgS;
    Image scaledImgD;
    private SourceModel Ssketch;
    private SourceView Sview;
    private static SourceFrame Swindow;
    private static SourceFrame Swindow2;
    private DestinationModel Dsketch;
    private DestinationView Dview;
    private static DestinationFrame Dwindow;
    private static UserInterface theApp;

    public UserInterface() {
        setTitle("Morph Images");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        pack();
        setVisible(true);
        defaultPath = "d:";
        fimage = null;
        algorithm = 1;/*predefined*/
        animation = 1;
        flag = 1;
        flag2 = 0;
        frames = 20;/*predefined*/
        rate = 5;/*predefined*/
        larger = 0;
        scaledImgS = null;
        scaledImgD = null;

        details = new JPanel();
        detailsLayout detailslayout = new detailsLayout();
        details.setLayout(detailslayout);
        detail = new JLabel("Enter following details :");
        Font font2 = new Font("Comic Sans", Font.BOLD, 22);
        detail.setFont(font2);
        details.add(detail);
        algolabel = new JLabel("1 : Select the algorithm you would like to implement :");
        Font font1 = new Font("Comic Sans", Font.BOLD, 16);
        algolabel.setFont(font1);
        details.add(algolabel);
        crossd = new JRadioButton("Cross dissolve");
        crossd.addActionListener(this);
        crossd.setSelected(true);
        ptwarp = new JRadioButton("CustomPoint warping");
        ptwarp.addActionListener(this);
        ButtonGroup buttongroup = new ButtonGroup();
        buttongroup.add(crossd);
        buttongroup.add(ptwarp);
        details.add(crossd);
        details.add(ptwarp);
        animlabel = new JLabel("2 : Select how you would like to see the animation :");
        animlabel.setFont(font1);
        details.add(animlabel);
        fbyf = new JRadioButton("Frame by Frame");
        fbyf.addActionListener(this);
        continuous = new JRadioButton("Continuous");
        continuous.addActionListener(this);
        continuous.setSelected(true);
        ButtonGroup buttongroup1 = new ButtonGroup();
        buttongroup1.add(continuous);
        buttongroup1.add(fbyf);
        details.add(continuous);
        details.add(fbyf);
        framelabel = new JLabel("3 : Enter number of intermediate frames in the animated sequence : ");
        framelabel.setFont(font1);
        details.add(framelabel);
        frameField = new JTextField(3);
        frameField.addActionListener(this);
        frameField.setText(String.valueOf(frames));
        details.add(frameField);
        ratelabel = new JLabel("4 : Enter number of frames per second in the animation : ");
        ratelabel.setFont(font1);
        details.add(ratelabel);
        fps = new JTextField(3);
        fps.addActionListener(this);
        fps.setText(String.valueOf(rate));
        details.add(fps);
        savelabel = new JLabel("5 : Enter location to save intermediate images : ");
        savelabel.setFont(font1);
        details.add(savelabel);
        saveImg = new JCheckBox("Save intermediate images to location:");
        saveImg.addActionListener(this);
        details.add(saveImg);
        imgPath = new JLabel(defaultPath);
        details.add(imgPath);
        stImages = new JButton("Change path");
        stImages.addActionListener(this);
        details.add(stImages);


        outputLayout outputlayout = new outputLayout();
        output = new JPanel();
        output.setLayout(outputlayout);
        imgmorph = new JLabel("Image Morphing");
        Font font = new Font("Comic Sans", 1, 30);
        imgmorph.setFont(font);
        output.add(imgmorph);
        srcimg = new JLabel("Source Image");
        srcimg.setFont(font1);
        output.add(srcimg);
        interimg = new JLabel("Intermediate Images");
        interimg.setFont(font1);
        output.add(interimg);
        destimg = new JLabel("Destination Image");
        destimg.setFont(font1);
        output.add(destimg);
        bs = new JButton("BROWSE SOURCE");
        bs.addActionListener(this);
        output.add(bs);
        bd = new JButton("BROWSE DESTINATION");
        bd.addActionListener(this);
        output.add(bd);
        morph = new JButton("MORPH");
        morph.addActionListener(this);
        output.add(morph);
        interm = new JLabel("");
        output.add(interm);


        single = new JPanel();
        singleLayout singlelayout = new singleLayout();
        single.setLayout(singlelayout);
        slabel = new JLabel("Source Image");
        slabel.setFont(font1);
        single.add(slabel);
        interlabel = new JLabel("Intermediate Images");
        interlabel.setFont(font1);
        single.add(interlabel);
        bs2 = new JButton("BROWSE SOURCE");
        bs2.addActionListener(this);
        single.add(bs2);
        morph2 = new JButton("MORPH");
        morph2.addActionListener(this);
        single.add(morph2);
        JTabbedPane jtabbedpane = new JTabbedPane(JTabbedPane.LEFT);
        int width = 850;
        int height = 549;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;
        setBounds(x, y, width, height);

        jtabbedpane.addTab("Enter Details", null, details, "Enter Morphing Details");
        jtabbedpane.addTab("View Output", null, output, "View result of Morphing Source to Destination Image");
        jtabbedpane.addTab("Morph Single Image", null, single, "View result of Morphing Single Image");
        jtabbedpane.setSelectedIndex(0);
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(jtabbedpane);

        obj = new Display();
    }

    public void actionPerformed(ActionEvent ae) {
        label0:
        {
            label1:
            {
                label2:
                {
                    label3:
                    {
//----------------------------------------------------------------------------------------------------------------------
                        if (ae.getSource() == bs) {
                            if (d != null && d.isVisible()) {
                                d.pack();/*pack makes its Window displayable but not 
                                necessarily visible. Displayable means it has been hooked into the native GUI,
                                any peer objects have been created, and addNotify has been called. */
                                d.setVisible(false);
                            }                    /*this chunk of code checks whether 							the intermediate display internal frame 							is not null & visible, if so then makes it 							in-Visible*/
                            if (ds != null && ds.isVisible()) {
                                ds.pack();
                                ds.setVisible(false);
                            }
//----------------------------------------------------------------------------------------------------------------------                            
                            if (sourceFrame != null && sourceFrame.isVisible()) {
                                sourceFrame.pack();
                                sourceFrame.setVisible(false);
                            } else if (Swindow != null && Swindow.isVisible()) {
                                Swindow.pack();
                                Swindow.setVisible(false);
                            }                    /*this chunk of code checks whether 							the source display internal frame 							is not null & visible, if tis then makes it 							in-Visible*/
                            if (Swindow2 != null && Swindow2.isVisible()) {
                                Swindow2.pack();
                                Swindow2.setVisible(false);
                            }
//-----------------------------------------------------------------------------------------------------------------               
                            bs.setVisible(false);
                            if (fc == null) {
                                fc = new JFileChooser("D:");/*Constructs a JFileChooser pointing to the user's default directory.*/
                                fc.addChoosableFileFilter(new ImageFilter());
                                fc.setAcceptAllFileFilterUsed(false);
                                //fc.setFileView(new ImageFileView());
                                fc.setAccessory(new ImagePreview(fc)); /*for viewing image i.e to view the selected image in the filechoosers dialog box.  For this setAccessory(..) (as  the name suggests used to add accessory to fc) method is used. */
                            }
                            int i = fc.showDialog(this, "Open");
                            if (i == 0)/*OR i==JFileChooser.APPROVE_OPTION */ {
                                File file = fc.getSelectedFile();
                                imgS = Toolkit.getDefaultToolkit().getImage(file.getPath());
                                siw = imgS.getWidth(null);
                                sih = imgS.getHeight(null);
                                if (destFrame != null && destFrame.isVisible() || Dwindow != null && Dwindow.isVisible()) {
                                    if (scaledImgD != null && Dwindow != null && Dwindow.isVisible()) {
                                        imgD = scaledImgD;
                                        scaledImgD = null;
                                        resetDwindow();
                                    } else if (scaledImgD != null && destFrame != null && destFrame.isVisible()) {
                                        imgD = scaledImgD;
                                        scaledImgD = null;
                                        resetdestFrame();
                                    }
                                    if (siw != diw || sih != dih) {
                                        if (siw * sih < diw * dih) {
                                            larger = 0;
                                            iw = diw;
                                            ih = dih;
                                            scaledImgD = null;
                                            scaledImgS = imgS;
                                            imgS = imgS.getScaledInstance(iw, ih, 1);
                                        } else {
                                            larger = 1;
                                            iw = siw;
                                            ih = sih;
                                            scaledImgS = null;
                                            scaledImgD = imgD;
                                            imgD = imgD.getScaledInstance(iw, ih, 1);
                                            if (algorithm == 1) {
                                                flag2 = 1;
                                                destFrame.pack();
                                                destFrame.setVisible(false);
                                                destFrame = new JInternalFrame("Destination Image", true, true, true, true);
                                                destFrame.setBounds(555, 160, 230, 230);
                                                ImageIcon imageicon2 = new ImageIcon(imgD);
                                                JLabel jlabel2 = new JLabel(imageicon2);
                                                destFrame.getContentPane().add(new JScrollPane(jlabel2));
                                                output.add(destFrame);
                                                destFrame.show();
                                            }
                                            if (algorithm == 2) {
                                                Dwindow.pack();
                                                Dwindow.setVisible(false);
                                                obj.resetD();
                                                Dsketch = new DestinationModel();
                                                Dwindow = new DestinationFrame("Destination Image", this);
                                                Dwindow.setBounds(555, 160, 230, 230);
                                                Dview = new DestinationView(this, imgD, algorithm);
                                                Dsketch.addObserver(Dview);
                                                Dwindow.getContentPane().add(Dview);
                                                output.add(Dwindow);
                                                Dwindow.setVisible(true);
                                                Dwindow.show();
                                            }
                                        }
                                    } else {
                                        iw = siw;
                                        ih = sih;
                                    }
                                }
                                if (algorithm == 1) {
                                    flag2 = 1;
                                    sourceFrame = new JInternalFrame("Source Image", true, true, true, true);
                                    sourceFrame.setBounds(15, 160, 230, 230);
                                    ImageIcon imageicon3 = new ImageIcon(imgS);
                                    JLabel jlabel3 = new JLabel(imageicon3);
                                    sourceFrame.getContentPane().add(new JScrollPane(jlabel3));
                                    output.add(sourceFrame);
                                    sourceFrame.show();
                                } else if (algorithm == 2) {
                                    obj.resetS();//makes noOfSMarkers=0
                                    obj.resetD();//makes noOfDMarkers=0
                                    Ssketch = new SourceModel();
                                    Swindow = new SourceFrame("Source Image", this);
                                    Swindow.setBounds(15, 160, 230, 230);
                                    Sview = new SourceView(this, imgS, algorithm, 0);
                                    Ssketch.addObserver(Sview);
                                    Swindow.getContentPane().add(Sview);
                                    output.add(Swindow);
                                    Swindow.setVisible(true);
                                    Swindow.show();
                                }
                            }
                            fc.setSelectedFile(null);
                            bs.setVisible(true);
                            if (Dwindow != null && Dwindow.isVisible())
                                resetDwindow();
                            break label0;
                        }
                        if (ae.getSource() == bd) {

                            if (d != null && d.isVisible()) {
                                d.pack();
                                d.setVisible(false);
                            }
                            if (ds != null && ds.isVisible()) {
                                ds.pack();
                                ds.setVisible(false);
                            }

                            if (destFrame != null && destFrame.isVisible()) {
                                destFrame.pack();
                                destFrame.setVisible(false);
                            } else if (Dwindow != null && Dwindow.isVisible()) {
                                Dwindow.pack();
                                Dwindow.setVisible(false);
                            } else if (Swindow2 != null && Swindow2.isVisible()) {
                                Swindow2.pack();
                                Swindow2.setVisible(false);
                            }

                            bd.setVisible(false);
                            if (fc == null) {
                                fc = new JFileChooser("D:");
                                fc.addChoosableFileFilter(new ImageFilter());
                                fc.setAcceptAllFileFilterUsed(false);
                                fc.setAccessory(new ImagePreview(fc));
                            }
                            int j = fc.showDialog(this, "Open");
                            if (j == 0) {
                                File file1 = fc.getSelectedFile();
                                imgD = Toolkit.getDefaultToolkit().getImage(file1.getPath());
                                diw = imgD.getWidth(null);
                                dih = imgD.getHeight(null);
                                if (sourceFrame != null && sourceFrame.isVisible() || Swindow != null && Swindow.isVisible()) {
                                    if (scaledImgS != null && Swindow != null && Swindow.isVisible()) {
                                        imgS = scaledImgS;
                                        scaledImgS = null;
                                        resetSwindow();
                                    } else if (scaledImgS != null && sourceFrame != null && sourceFrame.isVisible()) {
                                        imgS = scaledImgS;
                                        scaledImgS = null;
                                        resetsourceFrame();
                                    }
                                    if (siw != diw || sih != dih) {
                                        if (siw * sih > diw * dih) {
                                            larger = 0;
                                            iw = siw;
                                            ih = sih;
                                            scaledImgS = null;
                                            scaledImgD = imgD;
                                            imgD = imgD.getScaledInstance(iw, ih, 1);
                                        } else {
                                            larger = 1;
                                            iw = diw;
                                            ih = dih;
                                            scaledImgD = null;
                                            scaledImgS = imgS;
                                            imgS = imgS.getScaledInstance(iw, ih, 1);
                                            if (algorithm == 1) {
                                                flag2 = 1;
                                                sourceFrame.pack();
                                                sourceFrame.setVisible(false);
                                                sourceFrame = new JInternalFrame("Source Image", true, true, true, true);
                                                sourceFrame.setBounds(15, 160, 230, 230);
                                                ImageIcon imageicon4 = new ImageIcon(imgS);
                                                JLabel jlabel4 = new JLabel(imageicon4);
                                                sourceFrame.getContentPane().add(new JScrollPane(jlabel4));
                                                output.add(sourceFrame);
                                                sourceFrame.show();
                                            }
                                            if (algorithm == 2) {
                                                Swindow.pack();
                                                Swindow.setVisible(false);
                                                obj.resetS();
                                                Ssketch = new SourceModel();
                                                Swindow = new SourceFrame("Source Image", this);
                                                Swindow.setBounds(15, 160, 230, 230);
                                                Sview = new SourceView(this, imgS, algorithm, 0);
                                                Ssketch.addObserver(Sview);
                                                Swindow.getContentPane().add(Sview);
                                                output.add(Swindow);
                                                Swindow.setVisible(true);
                                                Swindow.show();
                                            }
                                        }
                                    } else {
                                        iw = siw;
                                        ih = sih;
                                    }
                                }
                                if (algorithm == 1) {
                                    destFrame = new JInternalFrame("Destination Image", true, true, true, true);
                                    destFrame.setBounds(555, 160, 230, 230);
                                    ImageIcon imageicon5 = new ImageIcon(imgD);
                                    JLabel jlabel5 = new JLabel(imageicon5);
                                    destFrame.getContentPane().add(new JScrollPane(jlabel5));
                                    output.add(destFrame);
                                    destFrame.show();
                                } else if (algorithm == 2) {
                                    obj.resetS();
                                    obj.resetD();
                                    Dsketch = new DestinationModel();
                                    Dwindow = new DestinationFrame("Destination Image", this);
                                    Dwindow.setBounds(555, 160, 230, 230);
                                    Dview = new DestinationView(this, imgD, algorithm);
                                    Dsketch.addObserver(Dview);
                                    Dwindow.getContentPane().add(Dview);
                                    output.add(Dwindow);
                                    Dwindow.setVisible(true);
                                    Dwindow.show();
                                }
                            }
                            fc.setSelectedFile(null);
                            bd.setVisible(true);
                            if (Swindow != null && Swindow.isVisible())
                                resetSwindow();
                            break label0;
                        }
                        if (ae.getSource() == bs2) {
                            if (algorithm == 1) {
                                String s = "Please select CustomPoint Warping Algorithm\n to warp single image";
                                JOptionPane.showMessageDialog(this, s, "Wrong Algorithm Selection", 0/*ERROR_MESSAGE*/);
                            } else {
                                if (Swindow2 != null && Swindow2.isVisible()) {
                                    Swindow2.pack();
                                    Swindow2.setVisible(false);
                                }
                                if (ds != null && ds.isVisible()) {
                                    ds.pack();
                                    ds.setVisible(false);
                                }
                                if (Swindow != null && Swindow.isVisible()) {
                                    Swindow.pack();
                                    Swindow.setVisible(false);
                                }
                                if (Dwindow != null && Dwindow.isVisible()) {
                                    Dwindow.pack();
                                    Dwindow.setVisible(false);
                                }
                                if (d != null && d.isVisible()) {
                                    d.pack();
                                    d.setVisible(false);
                                }
                                bs2.setVisible(false);
                                if (fc == null) {
                                    fc = new JFileChooser("D://");
                                    fc.addChoosableFileFilter(new ImageFilter());
                                    fc.setAcceptAllFileFilterUsed(false);
                                    // fc.setFileView(new ImageFileView());
                                    fc.setAccessory(new ImagePreview(fc));
                                }
                                int k = fc.showDialog(this, "Open");
                                if (k == 0) {
                                    File file2 = fc.getSelectedFile();
                                    singleImg = Toolkit.getDefaultToolkit().getImage(file2.getPath());
                                    iw = singleImg.getWidth(null);
                                    ih = singleImg.getHeight(null);
                                    obj.resetS();
                                    obj.resetD();
                                    Ssketch = new SourceModel();
                                    Swindow2 = new SourceFrame("Source Image", this);
                                    Swindow2.setBounds(15, 60, 320, 320);
                                    Sview = new SourceView(this, singleImg, algorithm, 1);
                                    Ssketch.addObserver(Sview);
                                    Swindow2.getContentPane().add(Sview);
                                    single.add(Swindow2);
                                    Swindow2.setVisible(true);
                                    Swindow2.show();
                                }
                                fc.setSelectedFile(null);
                                bs2.setVisible(true);
                            }
                            break label0;
                        }
                        if (ae.getSource() == saveImg) {

                            {
                                fimage = null;
                            }
                            break label0;
                        }
                        if (ae.getSource() == stImages) {
                            if (saveImg.isSelected()) {
                                fc2 = new JFileChooser("D://");
                                fc2.setDialogTitle("Save Intermediate Images");
                                fc2.setApproveButtonText("Ok");
                                fc2.setApproveButtonToolTipText("Save Intermediate Images in a directory");
                                JFileChooser _tmp = fc2;
                                fc2.setFileSelectionMode(1);
                                int l = fc2.showDialog(this, "Ok");
                                if (l == 0) {
                                    File file3 = fc2.getSelectedFile();
                                    fimage = file3.getPath() + "\\";
                                    defaultPath = fimage;
                                    imgPath.setText(defaultPath);

                                }
                                fc2.setSelectedFile(null);
                                fc2 = null;
                            }
                            break label0;
                        }

                        if (ae.getSource() != morph)
                            break label1;


                        if (algorithm == 1 && (sourceFrame == null || !sourceFrame.isVisible()) && (destFrame == null || !destFrame.isVisible())) {
                            String s1 = "Please provide source & destination images to Morph";
                            JOptionPane.showMessageDialog(this, s1, "No source & destination images", 0);
                            break label0;
                        }
                        if (algorithm == 1 && (sourceFrame == null || !sourceFrame.isVisible())) {
                            String s2 = "Please provide a source image";
                            JOptionPane.showMessageDialog(this, s2, "No source image", 0);
                            break label0;
                        }
                        if (algorithm == 1 && (destFrame == null || !destFrame.isVisible())) {
                            String s3 = "Please provide a destination image";
                            JOptionPane.showMessageDialog(this, s3, "No destination image", 0);
                            break label0;
                        }
                        if ((algorithm == 2) && (Swindow == null || !Swindow.isVisible()) && (Dwindow == null || !Dwindow.isVisible())) {
                            String s4 = "Please provide source & destination images";
                            JOptionPane.showMessageDialog(this, s4, "No source & destination images", 0);
                            break label0;
                        }
                        if ((algorithm == 2) && (Swindow == null || !Swindow.isVisible())) {
                            String s5 = "Please provide a source image";
                            JOptionPane.showMessageDialog(this, s5, "No source image", 0);
                            Dwindow.pack();
                            Dwindow.setVisible(false);
                            obj.resetD();
                            Dsketch = new DestinationModel();
                            Dwindow = new DestinationFrame("Destination Image", this);
                            Dwindow.setBounds(555, 160, 230, 230);
                            Dview = new DestinationView(this, imgD, algorithm);
                            Dsketch.addObserver(Dview);
                            Dwindow.getContentPane().add(Dview);
                            output.add(Dwindow);
                            Dwindow.setVisible(true);
                            Dwindow.show();
                            break label0;
                        }
                        if ((algorithm == 2) && (Dwindow == null || !Dwindow.isVisible())) {
                            String s6 = "Please provide a destination image";
                            JOptionPane.showMessageDialog(this, s6, "No destination image", 0);
                            Swindow.pack();
                            Swindow.setVisible(false);
                            obj.resetS();
                            Ssketch = new SourceModel();
                            Swindow = new SourceFrame("Source Image", this);
                            Swindow.setBounds(15, 160, 230, 230);
                            Sview = new SourceView(this, imgS, algorithm, 0);
                            Ssketch.addObserver(Sview);
                            Swindow.getContentPane().add(Sview);
                            output.add(Swindow);
                            Swindow.setVisible(true);
                            Swindow.show();
                            break label0;
                        }

                        if (algorithm == 2) {

                            if (Display.noOfSMarkers != Display.noOfDMarkers)//|| (Display.noOfSMarkers==0 && Display.noOfDMarkers==0))
                                break label3;
                        }

                        if (algorithm == 2) {
                            if (Display.noOfSMarkers == 0 && Display.noOfDMarkers == 0) {

                                String s2 = "Please provide points";
                                JOptionPane.showMessageDialog(this, s2, "No points to Morph", 0);
                                break label3;
                            }

                        }


                        break label2;
                    }
                    String s7;
                    label4:
                    {
                        s7 = "";
                        if (algorithm == 2) {

                            if (Display.noOfSMarkers > Display.noOfDMarkers) {
                                s7 = "You have not marked some destination points,\nPlease provide all corresponding pairs of points.";
                                JOptionPane.showMessageDialog(this, s7, "Missing few points", 0);
                                break label4;
                            }
                        }
                        if (algorithm == 2) {

                            if (Display.noOfSMarkers < Display.noOfDMarkers) {
                                s7 = "You have not marked some source points,\nPlease provide all corresponding pairs of points.";
                                JOptionPane.showMessageDialog(this, s7, "Missing few points", 0);
                                break label4;
                            }
                        }


                    }//end of label 4
                    //JOptionPane.showMessageDialog(this, s7, "Missing few points", 0);
                    Swindow.pack();
                    Swindow.setVisible(false);
                    obj.resetS();
                    Ssketch = new SourceModel();
                    Swindow = new SourceFrame("Source Image", this);
                    Swindow.setBounds(15, 160, 230, 230);
                    Sview = new SourceView(this, imgS, algorithm, 0);
                    Ssketch.addObserver(Sview);
                    Swindow.getContentPane().add(Sview);
                    output.add(Swindow);
                    Swindow.setVisible(true);
                    Swindow.show();
                    Dwindow.pack();
                    Dwindow.setVisible(false);
                    obj.resetD();
                    Dsketch = new DestinationModel();
                    Dwindow = new DestinationFrame("Destination Image", this);
                    Dwindow.setBounds(555, 160, 230, 230);
                    Dview = new DestinationView(this, imgD, algorithm);
                    Dsketch.addObserver(Dview);
                    Dwindow.getContentPane().add(Dview);
                    output.add(Dwindow);
                    Dwindow.setVisible(true);
                    Dwindow.show();
                    break label0;
                }//end of label 2
                frames = Integer.parseInt(frameField.getText());
                rate = Integer.parseInt(fps.getText());
                d = new Display(algorithm, animation, imgS, imgD, frames, rate, fimage);
                if (animation == 1)
                    d.setBounds(285, 160, 230, 230);
                else
                    d.setBounds(285, 140, 230, 300);
                output.add(d);
                d.show();
                d.startAnimation();
                break label0;
            }//end of label 1
            if (ae.getSource() == morph2) {
                frames = Integer.parseInt(frameField.getText());
                rate = Integer.parseInt(fps.getText());
                if (ds != null && ds.isVisible())
                    break label0;

                if (Swindow2 == null || !Swindow2.isVisible()) {
                    String s8;
                    if (algorithm == 1)
                        s8 = "Choose either \" CustomPoint Warping \" algorithm in \" Enter details \" tab \nand then provide a source image";
                    else
                        s8 = "Please provide a source image";
                    JOptionPane.showMessageDialog(this, s8, "No source image", 0);
                    break label0;
                }
                if (algorithm == 2) {
                    if (Display.noOfSMarkers > Display.noOfDMarkers) {
                        String s9 = "You have not marked some destination points,\nPlease provide all corresponding points.\nFor any doubts go through user manual";
                        JOptionPane.showMessageDialog(this, s9, "Missing few points", 0);
                        Swindow2.pack();
                        Swindow2.setVisible(false);
                        obj.resetS();
                        obj.resetD();
                        Ssketch = new SourceModel();
                        Swindow2 = new SourceFrame("Source Image", this);
                        Swindow2.setBounds(15, 60, 320, 320);
                        Sview = new SourceView(this, singleImg, algorithm, 1);
                        Ssketch.addObserver(Sview);
                        Swindow2.getContentPane().add(Sview);
                        single.add(Swindow2);
                        Swindow2.setVisible(true);
                        Swindow2.show();
                        break label0;
                    }
                }
                if (algorithm == 2) {
                    if (Display.noOfSMarkers < 3) {
                        String s10 = "           To warp single image,\nyou must mark atleast three pair of points";
                        JOptionPane.showMessageDialog(this, s10, "Inadequate points", 0);
                        break label0;
                    }
                }

                ds = new Display(algorithm, animation, singleImg, null, frames, rate, fimage);
                if (animation == 1)
                    ds.setBounds(400, 60, 320, 320);
                else
                    ds.setBounds(400, 60, 320, 360);
                single.add(ds);
                ds.show();
                ds.startAnimation();

            }
            if (ae.getSource() == crossd) {
                flag = algorithm;
                algorithm = 1;
                flag2 = 1;
                if (Swindow2 != null && Swindow2.isVisible()) {
                    Swindow2.pack();
                    Swindow2.setVisible(false);
                    Swindow2 = null;
                }
                if (Swindow != null && Swindow.isVisible()) {
                    Swindow.pack();
                    Swindow.setVisible(false);
                    sourceFrame = new JInternalFrame("Source Image", true, true, true, true);
                    sourceFrame.setBounds(15, 160, 230, 230);
                    ImageIcon imageicon = new ImageIcon(imgS);
                    JLabel jlabel = new JLabel(imageicon);
                    sourceFrame.getContentPane().add(new JScrollPane(jlabel));
                    output.add(sourceFrame);
                    sourceFrame.show();
                }
                if (Dwindow != null && Dwindow.isVisible()) {
                    Dwindow.pack();
                    Dwindow.setVisible(false);
                    destFrame = new JInternalFrame("Destination Image", true, true, true, true);
                    destFrame.setBounds(555, 160, 230, 230);
                    ImageIcon imageicon1 = new ImageIcon(imgD);
                    JLabel jlabel1 = new JLabel(imageicon1);
                    destFrame.getContentPane().add(new JScrollPane(jlabel1));
                    output.add(destFrame);
                    destFrame.show();
                }
                if (d != null && d.isVisible()) {
                    d.pack();
                    d.setVisible(false);
                    d = null;
                }
                if (ds != null && ds.isVisible()) {
                    ds.pack();
                    ds.setVisible(false);
                    ds = null;
                }

            } else if (ae.getSource() == ptwarp) {
                flag = algorithm;
                algorithm = 2;
                if (Swindow2 != null && Swindow2.isVisible()) {
                    Swindow2.pack();
                    Swindow2.setVisible(false);
                    Swindow2 = null;
                }
                if (Swindow != null && Swindow.isVisible()) {
                    Swindow.pack();
                    Swindow.setVisible(false);
                    obj.resetS();
                    Ssketch = new SourceModel();
                    Swindow = new SourceFrame("Source Image", this);
                    Swindow.setBounds(15, 160, 230, 230);
                    Sview = new SourceView(this, imgS, algorithm, 0);
                    Ssketch.addObserver(Sview);
                    Swindow.getContentPane().add(Sview);
                    output.add(Swindow);
                    Swindow.setVisible(true);
                    Swindow.show();
                    obj.resetD();
                }
                if (Dwindow != null && Dwindow.isVisible()) {
                    Dwindow.pack();
                    Dwindow.setVisible(false);
                    Dsketch = new DestinationModel();
                    Dwindow = new DestinationFrame("Destination Image", this);
                    Dwindow.setBounds(555, 160, 230, 230);
                    Dview = new DestinationView(this, imgD, algorithm);
                    Dsketch.addObserver(Dview);
                    Dwindow.getContentPane().add(Dview);
                    output.add(Dwindow);
                    Dwindow.setVisible(true);
                    Dwindow.show();
                }
                if (sourceFrame != null && sourceFrame.isVisible()) {
                    sourceFrame.pack();
                    sourceFrame.setVisible(false);
                    obj.resetS();
                    Ssketch = new SourceModel();
                    Swindow = new SourceFrame("Source Image", this);
                    Swindow.setBounds(15, 160, 230, 230);
                    Sview = new SourceView(this, imgS, algorithm, 0);
                    Ssketch.addObserver(Sview);
                    Swindow.getContentPane().add(Sview);
                    output.add(Swindow);
                    Swindow.setVisible(true);
                    Swindow.show();
                }
                if (destFrame != null && destFrame.isVisible()) {
                    destFrame.pack();
                    destFrame.setVisible(false);
                    obj.resetD();
                    Dsketch = new DestinationModel();
                    Dwindow = new DestinationFrame("Destination Image", this);
                    Dwindow.setBounds(555, 160, 230, 230);
                    Dview = new DestinationView(this, imgD, algorithm);
                    Dsketch.addObserver(Dview);
                    Dwindow.getContentPane().add(Dview);
                    output.add(Dwindow);
                    Dwindow.setVisible(true);
                    Dwindow.show();
                }
                if (d != null && d.isVisible()) {
                    d.pack();
                    d.setVisible(false);
                    d = null;
                }
                if (ds != null && ds.isVisible()) {
                    ds.pack();
                    ds.setVisible(false);
                    ds = null;
                }

            } else if (ae.getSource() == continuous) {
                animation = 1;
                if (d != null && d.isVisible()) {
                    d.setBounds(285, 160, 230, 230);
                    d.changeAnimation(1);
                }
                if (ds != null && ds.isVisible()) {
                    ds.setBounds(400, 60, 320, 360);
                    ds.changeAnimation(1);
                }
            } else if (ae.getSource() == fbyf) {
                animation = 2;
                if (d != null && d.isVisible()) {
                    d.setBounds(285, 160, 230, 300);
                    d.changeAnimation(2);
                }
                if (ds != null && ds.isVisible()) {
                    ds.setBounds(400, 60, 320, 360);
                    ds.changeAnimation(2);
                }
            } else
                /* TO set number of frames*/
                if (ae.getSource() == frameField)
                    frames = Integer.parseInt(frameField.getText());
                else
                    /* To set frame rate*/
                    if (ae.getSource() == fps)
                        rate = Integer.parseInt(fps.getText());
        }
    }

    public SourceFrame getSourceWindow() {
        return Swindow;
    }

    public SourceFrame getSourceWindow2() {
        return Swindow2;
    }

    public SourceModel getSourceModel() {
        return Ssketch;
    }

    public SourceView getSourceView() {
        return Sview;
    }

    public DestinationFrame getDestinationWindow() {
        return Dwindow;
    }

    public DestinationModel getDestinationModel() {
        return Dsketch;
    }

    public DestinationView getDestinationView() {
        return Dview;
    }

    public void resetdestFrame() {
        destFrame.pack();
        destFrame.setVisible(false);
        destFrame = new JInternalFrame("Destination Image", true, true, true, true);
        destFrame.setBounds(555, 160, 230, 230);
        ImageIcon imageicon = new ImageIcon(imgD);
        JLabel jlabel = new JLabel(imageicon);
        destFrame.getContentPane().add(new JScrollPane(jlabel));
        output.add(destFrame);
        destFrame.show();
    }

    public void resetsourceFrame() {
        sourceFrame.pack();
        sourceFrame.setVisible(false);
        sourceFrame = new JInternalFrame("Source Image", true, true, true, true);
        sourceFrame.setBounds(15, 160, 230, 230);
        ImageIcon imageicon = new ImageIcon(imgS);
        JLabel jlabel = new JLabel(imageicon);
        sourceFrame.getContentPane().add(new JScrollPane(jlabel));
        output.add(sourceFrame);
        sourceFrame.show();
    }

    public void resetDwindow() {
        Dwindow.pack();
        Dwindow.setVisible(false);
        obj.resetD();
        Dsketch = new DestinationModel();
        Dwindow = new DestinationFrame("Destination Image", this);
        Dwindow.setBounds(555, 160, 230, 230);
        Dview = new DestinationView(this, imgD, algorithm);
        Dsketch.addObserver(Dview);
        Dwindow.getContentPane().add(Dview);
        output.add(Dwindow);
        Dwindow.setVisible(true);
        Dwindow.show();
    }

    public void resetSwindow() {
        Swindow.pack();
        Swindow.setVisible(false);
        obj.resetS();
        Ssketch = new SourceModel();
        Swindow = new SourceFrame("Source Image", this);
        Swindow.setBounds(15, 160, 230, 230);
        Sview = new SourceView(this, imgS, algorithm, 0);
        Ssketch.addObserver(Sview);
        Swindow.getContentPane().add(Sview);
        output.add(Swindow);
        Swindow.setVisible(true);
        Swindow.show();
    }

    /*------------------------------------------------------Main-------------------------------------------------------*/
    public static void main(String args[]) {
        theApp = new UserInterface();
    }
    /*--------------------------------------------------------------------------------------------------------------------*/
}