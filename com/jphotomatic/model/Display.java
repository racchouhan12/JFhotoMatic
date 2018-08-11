package com.jphotomatic.model;/*It creates an internal frame, which displays intermediate images in chosen animation.*/

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Display extends JInternalFrame
        implements ActionListener, ChangeListener {
    int frameNumber;
    int NUM_FRAMES;
    int delay;
    int algo;
    int anim;
    ImageIcon images[];
    Image img[];
    Image imgS;
    Image imgD;
    Timer timer;
    boolean frozen;
    String fimage;
    Vector mS;
    Vector mD;
    static CustomPoint ps[] = new CustomPoint[40];
    static CustomPoint pd[] = new CustomPoint[40];
    static int noOfSMarkers = 0;
    static int noOfDMarkers = 0;
    JLabel picture;
    JPanel p;
    JSlider temp;

    public Display() {
        frameNumber = 0;
        frozen = false;
    }

    public Display(int i, int j, Image image, Image image1, int k, int l, String s) {
        super("Intermediate Images", true, true, true, true);
        frameNumber = 0;
        frozen = false;
        algo = i;            /* which algo*/
        anim = j;            /*which animation continuos or frama by frame*/
        imgS = image;
        imgD = image1;
        NUM_FRAMES = k;
        delay = 1000 / l;
        fimage = s;
        img = new Image[NUM_FRAMES + 2];    /*Array of intermediate images*/
        images = new ImageIcon[NUM_FRAMES + 2];
        picture = new JLabel();
        if (anim == 1) {
            getContentPane().add(new JScrollPane(picture));
            timer = new Timer(delay, this);
            timer.setInitialDelay(1400);
            timer.setCoalesce(true);
        } else {
         //   layout layout1 = new layout();
            p = new JPanel();
          //  p.setLayout(layout1);
            picture.setIcon(new ImageIcon(imgS));
            JSlider jslider = new JSlider(0, 0, NUM_FRAMES, 0);
            jslider.addChangeListener(this);
            jslider.setMajorTickSpacing(5); /* com.jphotomatic.shape.Scale Spacing*/
            jslider.setMinorTickSpacing(1);
            jslider.setPaintTicks(true);
            jslider.setPaintLabels(true);
            p.add(picture);
            p.add(jslider);
            getContentPane().add(p);
        }
    }

    public void changeAnimation(int i) {
        if (i == 1) {
            getContentPane().remove(p);
            getContentPane().add(new JScrollPane(picture));
            timer = new Timer(delay, this);
            timer.setInitialDelay(1400);
            timer.setCoalesce(true);
            timer.start();
        } else if (i == 2) {
            timer.stop();
            getContentPane().remove(0);
           // layout layout1 = new layout();
            p = new JPanel();
         //   p.setLayout(layout1);
            p.add(picture);
            picture.setIcon(new ImageIcon(imgS));
            JSlider jslider = new JSlider(0, 0, NUM_FRAMES, 0);
            jslider.addChangeListener(this);
            jslider.setMajorTickSpacing(5);
            jslider.setMinorTickSpacing(1);
            jslider.setPaintTicks(true);
            jslider.setPaintLabels(true);
            p.add(jslider);
            getContentPane().add(p);
        }
    }

    public void startAnimation() {
        if (algo == 1) {
            CrossDissolver crossdissolver = new CrossDissolver();
            img = crossdissolver.CrossDissolve(imgS, imgD, NUM_FRAMES, fimage);
        } else if (algo == 2) {
            Morph morph1 = new Morph();
            img = morph1.mainMorph(imgS, imgD, ps, pd, noOfSMarkers + 4, NUM_FRAMES, fimage);
            mS = morph1.mshS;
            /*Here mS and mD are vectors definrd in this class and mshS vector defined in class Morph*/

            mD = morph1.mshD;
            DisplayMesh displaymesh = new DisplayMesh();
            displaymesh.setS(mS);
            displaymesh.setD(mD);
        }

        if (anim == 1) {
            timer.start();
            frozen = false;
        }
    }


    public void addSourcePoint(int i, int j) {
        ps[noOfSMarkers] = new CustomPoint(i, j, noOfSMarkers);/*ps[]: array of source points*/
        noOfSMarkers++;
    }

    public void addDestinationPoint(int i, int j) {
        pd[noOfDMarkers] = new CustomPoint(i, j, noOfDMarkers);
        noOfDMarkers++;
    }

    public void resetS() {
        noOfSMarkers = 0;
    }

    public void resetD() {
        noOfDMarkers = 0;
    }

    public void actionPerformed(ActionEvent actionevent) {
        if (imgD != null && frameNumber == NUM_FRAMES + 1)
            frameNumber = 0;/*continuity of intermediate images*/
        else if (imgD == null && frameNumber == NUM_FRAMES)
            frameNumber = 0;
        else
            frameNumber++;
        updatePictureC(frameNumber);
        if (imgD != null && frameNumber == NUM_FRAMES + 1)
            timer.restart();
        else if (imgD == null && frameNumber == NUM_FRAMES)
            timer.restart();
    }

    public void stateChanged(ChangeEvent changeevent) {
        JSlider jslider = (JSlider) changeevent.getSource();
        temp = jslider;
        if (!jslider.getValueIsAdjusting()) {
            frameNumber = jslider.getValue();
            if (imgD == null && frameNumber == NUM_FRAMES)
                frameNumber = NUM_FRAMES - 1;
            updatePictureF(frameNumber);
        }
    }

    protected void updatePictureC(int i) {
        if (images[frameNumber] == null)
            images[frameNumber] = new ImageIcon(img[frameNumber]);
        if (images[frameNumber] != null)
            picture.setIcon(images[frameNumber]);
    }

    protected void updatePictureF(int i) {
        if (images[frameNumber] == null)
            images[frameNumber] = new ImageIcon(img[frameNumber]);
        if (images[frameNumber] != null)
            if (temp.getValue() == NUM_FRAMES)
                picture.setIcon(images[NUM_FRAMES - 1]);
            else
                picture.setIcon(images[frameNumber]);
    }
}