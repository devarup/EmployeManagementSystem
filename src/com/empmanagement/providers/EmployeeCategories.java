/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empmanagement.providers;

/**
 *
 * @author user
 */
public enum EmployeeCategories {
    
    Nurse("Nurse"), Twelve("12 hours"), TwentyFour("24 hours");
    
    private String display;
    
    private EmployeeCategories(String s) {
        display = s;
    }
    
    @Override
    public String toString() {
        return display;
    } 
}
