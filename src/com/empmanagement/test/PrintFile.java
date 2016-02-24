/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empmanagement.test;

/**
 *
 * @author arup
 */



 import java.io.*;    
      public class PrintFile {    
          public static void main (String[] argv) {    
              try {    
                  System.err.println("--");
                  FileOutputStream os = new FileOutputStream("LPT1");    
                  //wrap stream in "friendly" PrintStream    
                  PrintStream ps = new PrintStream(os);    
      
                  //print text here    
                  ps.println("Hello world!");    
      
                  //form feed -- this is important    
                  //Without the form feed, the text will simply sit    
                  // in print buffer until something else gets printed.    
                  ps.print("\f");    
                  //flush buffer and close    
                  ps.close();    
              } catch (Exception e) {    
                  System.out.println("Exception occurred: " + e);    
              }    
          }    
}