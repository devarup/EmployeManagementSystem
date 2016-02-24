/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.employeemanagement.utility;

import com.employeemanagement.common.Common;
import com.empmanagement.screens.EmployeeManipulationFrame;
import java.awt.BorderLayout;
import java.awt.Graphics; 
import java.awt.Rectangle;
import java.awt.Robot; 
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener; 
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;




/**
 *
 * @author admin
 */
public class CropImage extends JFrame implements MouseListener, MouseMotionListener{
    
int drag_status=0,c1,c2,c3,c4;
EmployeeManipulationFrame emf;

public void start(String urls,EmployeeManipulationFrame emp) {     
    //String  imgsrc = "/home/arup/Desktop/images.jpg";     
    String  imgsrc = urls;     
    emf = emp;
    ImagePanel im=new ImagePanel(imgsrc);
    System.out.println("Height "+ im.getHeight());
    System.out.println("Width "+  im.getWidth()); 
    

this.add(im, BorderLayout.CENTER);

this.setSize(im.getHeight(),im.getWidth());
this.setVisible(true);
this.addMouseListener(this);
this.addMouseMotionListener( this );
this.pack();
this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
}

@Override public void mouseClicked(MouseEvent arg0)
{	 } 
@Override public void mouseEntered(MouseEvent arg0) {	 }
@Override public void mouseExited(MouseEvent arg0) { } 
@Override public void mousePressed(MouseEvent arg0)
{ repaint(); c1=arg0.getX(); c2=arg0.getY(); } 
@Override public void mouseReleased(MouseEvent arg0) 
{ repaint(); 
    if(drag_status==1)
    { c3=arg0.getX();
    c4=arg0.getY(); 
    try { 
        draggedScreen();
    } catch(Exception e)
    { e.printStackTrace(); } 
    } 
} 
@Override public void mouseDragged(MouseEvent arg0) 
{ repaint(); drag_status=1; c3=arg0.getX(); c4=arg0.getY(); } 

@Override public void mouseMoved(MouseEvent arg0) { } 
public void paint(Graphics g) {
    super.paint(g); 
    int w = c1 - c3; 
    int h = c2 - c4; 
    w = w * -1; h = h * -1; 
    if(w<0) w = w * -1;
    g.drawRect(c1, c2, w, h); 
}

public void draggedScreen()throws Exception {
    int w = c1 - c3;
    int h = c2 - c4;
    w = w * -1;
    h = h * -1;
    Robot robot = new Robot();
    BufferedImage img = robot.createScreenCapture(new Rectangle(c1, c2,w,h));
    String saveFilePath = Common.getTempPath()+Common.getSeparator()+"temp.jpg";
    File save_path=new File(saveFilePath); 
    
    ImageIO.write(img, "JPG", save_path);
    
     emf.createInsideElements(saveFilePath);
         emf.repaint();
         emf.pack();
         this.dispose();
    System.out.println("Cropped image saved successfully."); }
}








