/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.employeemanagement.models;

import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class CustomTableModel extends DefaultTableModel{
 
  private Vector<Vector<String>> data;  
  private Vector<String> cols;
  
   public CustomTableModel(Vector<Vector<String>> data, Vector<String> cols) 
   {
      super(data, cols);
      this.cols = cols;
      this.data = data;
   }

   public boolean isCellEditable(int row, int col) 
   {
      return false;
   }
    
    
     
    public String getValueAt(int row,int col) {
        return (data.get(row)).get(col);
    } 
     
    
 
}
