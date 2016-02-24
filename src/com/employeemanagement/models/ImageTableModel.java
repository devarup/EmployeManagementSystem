/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.employeemanagement.models;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author arup
 */
public class ImageTableModel implements TableModel{

    Object data[][];
    String column[];
    public ImageTableModel(Object [][] data,String []columnName) {
        this.data = data;
        this.column = columnName;
        
    }
    
    
    

    @Override
    public int getRowCount() {
       return data.length;
    }

    @Override
    public int getColumnCount() {
       return column.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return column[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
     return (getValueAt(0, columnIndex).getClass());    
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
       return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        return; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        return;//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        return; //To change body of generated methods, choose Tools | Templates.
    }
    
}
