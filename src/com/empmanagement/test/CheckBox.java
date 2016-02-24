/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empmanagement.test;

import com.employeemanagement.models.Client;
import com.empmanagement.persistence.IPersistence;
import com.empmanagement.persistence.Persistence;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;

public class CheckBox extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTable table;
    
    IPersistence ip = Persistence.getInstance() ;
    Object data[][];

    public CheckBox() {
        
        
        
        
         List<Client> clients = ip.getClients();
        
        data = new Object[clients.size()][4];
        
        for (int i = 0;i<clients.size();i++){
            data[i][0] = clients.get(i).getClient_id();
            data[i][1] = clients.get(i).getClient_name();
            data[i][2] = clients.get(i).getClient_mob1();
            data[i][3] =  false;        
        
        }
        
        String[] columnNames = {"Emp. ID", "Name","Address",""};
        
        
        
       
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table = new JTable(model) {

            private static final long serialVersionUID = 1L;

            /*@Override
            public Class getColumnClass(int column) {
            return getValueAt(0, column).getClass();
            }*/
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    
                    default:
                        return Boolean.class;
                }
            }
        };
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        JCheckBox chk = new JCheckBox("");
        JButton jb = new JButton("Click");
        jb.setVisible(true);
        this.add(jb);
        
        
        JScrollPane scrollPane = new JScrollPane(table);
        
        getContentPane().add(scrollPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                CheckBox frame = new CheckBox();
                frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocation(150, 150);
                frame.setVisible(true);
            }
        });
    }
}