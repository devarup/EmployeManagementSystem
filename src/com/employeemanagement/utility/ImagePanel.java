/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.employeemanagement.utility;


import java.awt.Dimension; 
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon; 
import javax.swing.JPanel;



/**
 *
 * @author admin
 */
public class ImagePanel extends  JPanel  {
   public Image img; 
   public ImagePanel(String img) 
   {
    //ImageIcon im = new ImageIcon(img);
     // new ImagePanel(im.getImage());
       this(new ImageIcon(img).getImage());
   }
   public ImagePanel(Image img)
   { 
       
       ImageObserver a = new ImageObserver() {

          @Override
           public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
              throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
           }
       };
      System.out.print(" heign "+ img.getHeight(a));
      System.out.print(" image "+ img.getWidth(a));
//       this.img = img;
       
       this.img = img;
       Dimension size = new Dimension(img.getWidth(a), img.getHeight(a));
       setPreferredSize(size);
       setMinimumSize(size);
       setMaximumSize(size);
       setSize(size);
       setLayout(null);
   } 
   
   public void paintComponent(Graphics g)
   { 
       g.drawImage(img, 0, 0, null); 
   }


   
  // Dimension size = new Dimension(img.getWidth(null), img.getHeight(null)); 
   // Dimension size = new Dimension(10,10); setPreferredSize(size); setMinimumSize(size); setMaximumSize(size); setSize(size); setLayout(null); } public void paintComponent(Graphics g) { g.drawImage(img, 0, 0, null); } 


}
