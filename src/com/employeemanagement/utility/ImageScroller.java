/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.employeemanagement.utility;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

/**
 *
 * @author arup
 */
public class ImageScroller extends JScrollPane   
     {  
          public ImageScroller(Icon icon)   
          {  
               super();  
    
              // Panel to hold the icon image   
              JPanel p = new JPanel(new BorderLayout());   
              p.add(new JLabel(icon), BorderLayout.CENTER);  
              setPreferredSize(new Dimension(150, 150));
              getViewport().add(p);  
         
             // JScrollBar vsb = getVerticalScrollBar();   
              //JScrollBar hsb = getHorizontalScrollBar();   
         
              //vsb.setValue(icon.getIconHeight());   
              //hsb.setValue(icon.getIconWidth()/10);   
          }//end of constructor  
          
          
          
          public static void main(String[] argv) {  
                ImageIcon ic = new ImageIcon("/home/arup/Desktop/img.png");  
                JFrame f = new JFrame("ImageScroller Test");  
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  

                f.setContentPane(new ImageScroller(ic));  
                //f.setContentPane(new JScrollPane(new JLabel(ic)));  

                f.setSize(100, 100); // or f.pack() to take size from the image  
                f.setVisible(true);  
} 
     }
