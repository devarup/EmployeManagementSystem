/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empmanagement.testswing;

/**
 *
 * @author arup
 */
import com.employeemanagement.common.Common;
import com.employeemanagement.utility.ImagePanel;
import com.employeemanagement.utility.ImageScroller;
import com.empmanagement.screens.ClientFrame;
import com.empmanagement.screens.EmployeeManipulationFrame;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/* ResizableComponent.java */



public class ResizableComponent extends JFrame {

  private JPanel panel = new JPanel(null);
  private Resizable resizer;
  
  JPanel area = null;
  EmployeeManipulationFrame emf =null;
  String imagePath="";
  JCheckBox checkBox ;
  JButton crop ;
  JButton ok ;
  JLabel alertmesg;
  JPanel mesgPanel ;
  com.employeemanagement.utility.ImageScroller ic  = null;
  
  int xPosition = 0;
  int yPosition = 0; 



  public ResizableComponent(String img,EmployeeManipulationFrame em) {
      emf = em;
      imagePath = img;
    //  JScrollPane jScrollPane = new JScrollPane();
    //  jScrollPane.setPreferredSize(new Dimension(200, 200));
    //s  jScrollPane.setVisible(true);
      
      ImageIcon icon = new ImageIcon(img);
      ImagePanel im=new ImagePanel(img);  
      ic = new ImageScroller(icon);
      
      int width = im.getWidth();
      int height = im.getHeight();
     
      ic.getViewport().add(im);
      this.setAlwaysOnTop(true);

       
      //ic.add(im);
     // jScrollPane.add(im);
    
     // setContentPane(ic);
      add(ic,BorderLayout.CENTER);
      checkBox = new JCheckBox("Show Frame");
      checkBox.setSelected(true);
      alertmesg = new JLabel();
      String alertText = "<html><font color='red'><B>Please uncheck \"Show Frame\"</B></font></html>";
      
      alertmesg.setText(alertText);
      crop = new JButton("Crop");
      ok = new JButton("Ok");
      crop.setPreferredSize(new Dimension(100, 50));
      ok.setPreferredSize(new Dimension(100, 50));
      
      JPanel cropBtnPanel = new JPanel();
      cropBtnPanel.add(checkBox,BorderLayout.CENTER);
      cropBtnPanel.add(crop,BorderLayout.CENTER);
      cropBtnPanel.add(ok,BorderLayout.CENTER);
     // cropBtnPanel.add(alertmesg);
      mesgPanel = new JPanel();  
      
      mesgPanel.add(alertmesg);
      mesgPanel.setVisible(false);    
     
      cropBtnPanel.setPreferredSize(new Dimension(150, 70));
      
      add(cropBtnPanel,BorderLayout.SOUTH);
      add(mesgPanel,BorderLayout.NORTH);
     
      area = new JPanel(); 
      //area.setBackground(Color.black);
      area.setOpaque(false);
      resizer = new Resizable(area);
      resizer.setBounds(50, 50, 100, 100);
      im.add(resizer); 
      
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      //this.setSize(im.getHeight(),im.getWidth());
      this.setSize(500, 500);
      setTitle("Crop Image");
      getContentPane().setBackground(new Color(Color.OPAQUE));
      
      setLocationRelativeTo(null);
      

      addMouseListener(new MouseAdapter() {
        public void mousePressed(MouseEvent me) {

          requestFocus();
          resizer.repaint();
        }
      });
      
       crop.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {        
             
              if(!checkBox.isSelected())  
                 cropImage();
              
              else{
                              
                  JDialog dialog = new JOptionPane("Please uncheck \"Show Frame\"").createDialog(ResizableComponent.this,"Alert");
                  Point dialogLoc = dialog.getLocation();           
                  dialog.setVisible(true);
                 }
              
              
              
            }
        });
       
        ok.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
                emf.createInsideElements(imagePath);
                emf.repaint();
                emf.pack();
                ResizableComponent.this.dispose();
            }
        });
        
        
        
        
        checkBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
        
                if(checkBox.isSelected()){
                
                  resizer.setVisible(true);
                }
                
                else{
                    yPosition = (int)resizer.getLocationOnScreen().getY();
                    xPosition = (int)resizer.getLocationOnScreen().getX();
                    resizer.setVisible(false);
                
                }
                
            }
        });
        
        
        
        
       
        
               
   
  }
  
  public void cropImage(){
       
        try {
        //Execute when button is pressed
            
            //Point pt = resizer.getLocation();  
           //  Point pt = ic.getLocationOnScreen();
            
                
               //this.setVisible(false);
                Robot robot= new Robot();
                
              // Point pt = this.resizer.getLocationOnScreen();
               // int x = ((int)this.getLocation().getX()+1)+(int)pt.getX()+1;
               // int y = ((int)this.getLocation().getY()+1)+(int)pt.getY()+1;
                
              //  int x = (int)pt.getX()+1+(int)resizer.getLocation().getX();
              //  int y = (int)pt.getY()+1+(int)resizer.getLocation().getY();
                
               // y = (int)resizer.getLocationOnScreen().getY();
               // x= (int)resizer.getLocationOnScreen().getX();
                            
                BufferedImage img = robot.createScreenCapture(new Rectangle(xPosition ,yPosition,area.getWidth(),area.getHeight()));

                
                String saveFilePath = Common.getTempPath()+Common.getSeparator()+"temp.jpg";
                File save_path=new File(saveFilePath); 
                ImageIO.write(img, "JPG", save_path);

                emf.createInsideElements(saveFilePath);
                emf.repaint();
                emf.pack();
                this.dispose();

        } catch (AWTException ex) {
             Logger.getLogger(ResizableComponent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
             Logger.getLogger(ResizableComponent.class.getName()).log(Level.SEVERE, null, ex);
        } 
      
  
  }
  
   public void demoCropImage(int x1,int y1,int width1,int height1){
       
        try {
        //Execute when button is pressed
            
                //Point pt = resizer.getLocation();    
                
                
                Robot robot= new Robot();
                
                
                resizer = null;
                BufferedImage img = robot.createScreenCapture(new Rectangle(x1,y1,width1,height1));
                String saveFilePath = Common.getTempPath()+Common.getSeparator()+"temp.jpg";
                File save_path=new File(saveFilePath); 
                ImageIO.write(img, "JPG", save_path);

                emf.createInsideElements(saveFilePath);
              //  emf.repaint();
               // emf.pack();
                this.dispose();

        } catch (AWTException ex) {
             Logger.getLogger(ResizableComponent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
             Logger.getLogger(ResizableComponent.class.getName()).log(Level.SEVERE, null, ex);
        } 
      
  
  }
   
   public void cropImageeee(){
       
        try {
        //Execute when button is pressed
            
            //Point pt = resizer.getLocation();  
           //  Point pt = ic.getLocationOnScreen();
            
                
               //this.setVisible(false);
                if(yPosition==0 && xPosition==0)
                {
                    yPosition = (int)resizer.getLocationOnScreen().getY();
                    xPosition = (int)resizer.getLocationOnScreen().getX();
                    System.out.println("xPosition: "+xPosition+ " AND yPosition: "+yPosition);
                }
                
                Robot robot= new Robot();
                            
                BufferedImage img = robot.createScreenCapture(new Rectangle(xPosition ,yPosition,area.getWidth(),area.getHeight()));

                
                String saveFilePath = Common.getTempPath()+Common.getSeparator()+"temp.jpg";
                File save_path=new File(saveFilePath); 
                ImageIO.write(img, "JPG", save_path);

                emf.createInsideElements(saveFilePath);
                emf.repaint();
                emf.pack();
                this.dispose();

        } catch (AWTException ex) {
             Logger.getLogger(ResizableComponent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
             Logger.getLogger(ResizableComponent.class.getName()).log(Level.SEVERE, null, ex);
        } 
      
  
  }

   
   
  
  

}
