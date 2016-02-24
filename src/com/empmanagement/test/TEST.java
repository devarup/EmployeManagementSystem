/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empmanagement.test;

import java.io.File;
import java.net.URISyntaxException;

/**
 *
 * @author arup
 */
public class TEST {
    
    public static void main(String args[]) throws URISyntaxException{
   
        
        java.io.File file = new java.io.File("");   //Dummy file
        String  abspath=file.getAbsolutePath()+"/src/resource/image.jpg";
        File ff = new File(abspath);
        if(ff.exists())
        System.out.print(ff.getAbsolutePath());
    
    
    
    
    }
    
}
