/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empmanagement.test;

/**
 *
 * @author arup
 */
public class MultipleSpaceIntoSingle {
    
    
    public static void main(String args[]){
        
        String a = "Hello    World";
        
        
        System.out.println("---"+a+"---");
        
        String after = a.trim().replaceAll(" +", " ");
        
        System.out.println("---"+after+"---");
    
    
    }            
    
}
