/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empmanagement.test;

import java.awt.FileDialog;
import javax.swing.JFrame;

/**
 *
 * @author arup
 */
public class ThumbnailView {
    
    public static void main(String args[]){
    
FileDialog fd = new FileDialog(new JFrame(), "Choose a file", FileDialog.LOAD);
fd.setDirectory("/home/arup/Desktop");
fd.setFile("*.xml");
fd.setVisible(true);
String filename = fd.getFile();
if (filename == null)
  System.out.println("You cancelled the choice");
else
  System.out.println("You chose " + filename);}
    
}
