/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empmanagement.providers;

/**
 *
 * @author arup
 */
public enum JobStatus {
    
    SELECT("Select"),WORKINPROGRESS("Work in progress"),COMPLETED("Completed");  
     
    
    private String status;
    
    private JobStatus(String s) {
        this.status = s;
    }
    
    @Override
    public String toString() {
        return status;
    } 
    
}
