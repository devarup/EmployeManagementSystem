/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empmanagement.screens;

import com.employeemanagement.common.Common;
import com.employeemanagement.models.Client;
import com.employeemanagement.models.EmpJob;
import com.employeemanagement.models.Employee;
import com.empmanagement.persistence.IPersistence;
import com.empmanagement.persistence.Persistence;
import com.empmanagement.providers.EmployeeCategories;
import com.empmanagement.providers.EmployeeCategoriesForSearch;
import com.empmanagement.providers.JobStatus;
import com.empmanagement.view.bean.JobDetail;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.*;
import java.*;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JComboBox;
import javax.swing.text.JTextComponent;

/**
 *
 * @author arup
 */
public class AssignEmployee extends javax.swing.JFrame {

    /**
     * Creates new form Assigned
     */
    MainFrame mainFrame;
    JobDetail oldData;
    IPersistence ip;

    public AssignEmployee(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        initComponents();
        initOtherComponent();

        modify_upper_panel.setVisible(false);
        save_upper_panel.setVisible(true);
        setTitle("Assign Employee");
        setResizable(false);
    }

    public AssignEmployee(JobDetail jobdetail, MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        initComponents();

        initComponentForModify(jobdetail);
        setTitle("Assign Employee");
        setResizable(false);

    }

    public void initOtherComponent() {

        btn_modify.setVisible(false);
        cmb_status.setModel(new DefaultComboBoxModel(JobStatus.values()));

        ip = Persistence.getInstance();
        List<Employee> employees = null;
        List<Client> clients = null;

        employees = ip.getEmployees();
        clients = ip.getClients();

        post.setModel(new DefaultComboBoxModel(EmployeeCategoriesForSearch.values()));
        cmb_emp.setModel(new DefaultComboBoxModel(employees.toArray()));
        cmb_client.setModel(new DefaultComboBoxModel(clients.toArray()));



        
       cmb_emp.getEditor().addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent event) {
               
               String name = ((JTextComponent) ((JComboBox) ((Component) event
                        .getSource()).getParent()).getEditor()
                        .getEditorComponent()).getText();         
               
               String emppost = post.getSelectedItem().toString();
               
               System.out.println("name : "+name + " post : "+emppost);
               ip = Persistence.getInstance();
               List<Employee> employees = ip.getEmployies(name,emppost);
               cmb_emp.setModel(new DefaultComboBoxModel(employees.toArray()));                
            }                          
        });  
       
        cmb_client.getEditor().addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent event) {
               
               String name = ((JTextComponent) ((JComboBox) ((Component) event
                        .getSource()).getParent()).getEditor()
                        .getEditorComponent()).getText();    
                ip = Persistence.getInstance();
                List<Client> clients = ip.getClients(name);
               cmb_client.setModel(new DefaultComboBoxModel(clients.toArray()));
               
              System.out.println("client name : "+name);
            }                          
        }); 
        
        

//        cmb_client.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent e) {
//                Client client = (Client) cmb_client.getSelectedItem();
//                System.out.println(client.getClient_id());
//            }
//        });
//        
        
        


    }

    public void initComponentForModify(JobDetail jobdetail) {


        modify_upper_panel.setVisible(true);
        save_upper_panel.setVisible(false);
        btn_save.setVisible(false);
        btn_modify.setVisible(true);
        oldData = jobdetail;

        if (jobdetail.getEmpID() != null && jobdetail.getEmpName() != null) {
            lbl_emp_name.setText(jobdetail.getEmpID() + " - " + jobdetail.getEmpName());
        }

        if (jobdetail.getClientID() != null && jobdetail.getClientName() != null) {
            lbl_client_name.setText(jobdetail.getClientID() + " - " + jobdetail.getClientName());
        }

        if (jobdetail.getStartDate() != null) {
            dt_start.setDate(jobdetail.getStartDate());
        }

        if (jobdetail.getEndDate() != null) {
            dt_end.setDate(jobdetail.getEndDate());
        }

        if (jobdetail.getJobDesc() != null) {
            txt_desc.setText(jobdetail.getJobDesc());
        }

        if (jobdetail.getRemarks() != null) {
            txt_remarks.setText(jobdetail.getRemarks());
        }

        cmb_status.setModel(new DefaultComboBoxModel(JobStatus.values()));
        cmb_status.setSelectedItem(jobdetail.getStatus().toString());

        JobStatus jb = null;
        for (JobStatus j : JobStatus
                .values()) {
            if (j.toString().equalsIgnoreCase(jobdetail.getStatus())) {
                jb = j;
                break;
            }
        }
        cmb_status.setSelectedItem(jb);


    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txt_empassign = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        dt_start = new com.toedter.calendar.JDateChooser();
        dt_end = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_desc = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        cmb_status = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_remarks = new javax.swing.JTextArea();
        save_upper_panel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cmb_emp = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        cmb_client = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        post = new javax.swing.JComboBox();
        modify_upper_panel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbl_client_name = new javax.swing.JLabel();
        lbl_emp_name = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btn_save = new javax.swing.JButton();
        btn_cancel = new javax.swing.JButton();
        btn_modify = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(254, 254, 254));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel4.setBackground(new java.awt.Color(254, 254, 254));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        txt_empassign.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_empassign.setForeground(new java.awt.Color(255, 255, 255));
        txt_empassign.setText("Employee Assignment");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_empassign, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(txt_empassign, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(254, 254, 254));

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel1.setText("Status");

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel4.setText("Start Date");

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel5.setText("End Date");

        dt_start.setDateFormatString("dd MMM, yyyy");

        dt_end.setDateFormatString("dd MMM, yyyy");

        txt_desc.setColumns(20);
        txt_desc.setRows(5);
        jScrollPane1.setViewportView(txt_desc);

        jLabel6.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel6.setText("Job Description");

        jLabel7.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel7.setText("Remarks");

        txt_remarks.setColumns(20);
        txt_remarks.setRows(5);
        jScrollPane2.setViewportView(txt_remarks);

        save_upper_panel.setBackground(new java.awt.Color(254, 254, 254));

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel2.setText("Employee");

        cmb_emp.setEditable(true);
        cmb_emp.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Client");

        cmb_client.setEditable(true);
        cmb_client.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setText("Post");

        post.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        post.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                postActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout save_upper_panelLayout = new javax.swing.GroupLayout(save_upper_panel);
        save_upper_panel.setLayout(save_upper_panelLayout);
        save_upper_panelLayout.setHorizontalGroup(
            save_upper_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(save_upper_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(save_upper_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(save_upper_panelLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmb_emp, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(save_upper_panelLayout.createSequentialGroup()
                        .addGroup(save_upper_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(save_upper_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmb_client, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(save_upper_panelLayout.createSequentialGroup()
                                .addComponent(post, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        save_upper_panelLayout.setVerticalGroup(
            save_upper_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(save_upper_panelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(save_upper_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmb_client, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(save_upper_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(post, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(save_upper_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_emp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        modify_upper_panel.setBackground(new java.awt.Color(254, 254, 254));

        jLabel8.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel8.setText("Employee");

        jLabel9.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel9.setText("Client");

        javax.swing.GroupLayout modify_upper_panelLayout = new javax.swing.GroupLayout(modify_upper_panel);
        modify_upper_panel.setLayout(modify_upper_panelLayout);
        modify_upper_panelLayout.setHorizontalGroup(
            modify_upper_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modify_upper_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(modify_upper_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(modify_upper_panelLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_client_name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(modify_upper_panelLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_emp_name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        modify_upper_panelLayout.setVerticalGroup(
            modify_upper_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modify_upper_panelLayout.createSequentialGroup()
                .addGroup(modify_upper_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_client_name, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(modify_upper_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(lbl_emp_name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(save_upper_panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(modify_upper_panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(dt_start, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(dt_end, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 233, Short.MAX_VALUE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmb_status, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(save_upper_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modify_upper_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(dt_start, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(dt_end, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(254, 254, 254));

        btn_save.setText("Save");
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });

        btn_cancel.setText("Cancel");
        btn_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelActionPerformed(evt);
            }
        });

        btn_modify.setText("Modify");
        btn_modify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modifyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_modify, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btn_save)
                .addComponent(btn_cancel)
                .addComponent(btn_modify))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btn_cancelActionPerformed

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        // TODO add your handling code here:

        Client client = (Client) cmb_client.getSelectedItem();
        Employee employee = (Employee) cmb_emp.getSelectedItem();
        String empName = null;
        String emp_id = null;

        if (employee != null) {

            empName = employee.getF_name() + " " + employee.getM_name() + " " + employee.getL_name();
            emp_id = employee.getEmp_id();

        }


        String client_id = client.getClient_id();
        SimpleDateFormat df = Common.getDateFormatter();
        EmpJob empjob = new EmpJob();

        if (empName != null && dt_start.getDate() != null && !cmb_status.getSelectedItem().toString().equalsIgnoreCase("Select")) {

            empjob.setEmp_id(emp_id);
            empjob.setClient_id(client_id);
            empjob.setStart_date(dt_start.getDate());
            empjob.setEnd_date(dt_end.getDate());
            empjob.setJobDesc(txt_desc.getText());
            empjob.setRemarks(txt_remarks.getText());
            empjob.setStatus(cmb_status.getSelectedItem().toString());

            IPersistence ip = Persistence.getInstance();
            Boolean flag = ip.jobAssign(empjob);
            if (flag) {
                mainFrame.reloadTableDataCurrentAssignment();
                // JOptionPane.showMessageDialog(null, "Job assigned to "+empName+ " to "+client.getClient_name());
                String mesg = empName + " is assigned to " + client.getClient_name() + "\n"
                        + "Do you want assign another employee ?";
                int more = JOptionPane.showConfirmDialog(null, mesg, "Assign employee to client",
                        JOptionPane.YES_NO_OPTION);

                if (more == 0) {
                } else {
                    this.dispose();

                }


            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Please assign again !!!!!", "Dialog",
                        JOptionPane.ERROR_MESSAGE);
                this.dispose();

            }

        } else {
            String message = "";

            System.err.println("--" + cmb_emp.getSelectedItem());



            if (cmb_status.getSelectedItem().toString().equalsIgnoreCase("Select")) {
                message = "Invalid Work Status";
            }


            if (dt_start.getDate() == null) {
                message = "Invalid Start Date";
            }

            if (cmb_emp.getSelectedItem() == null) {
                message = "Please Select Employee";
            }


            JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                    JOptionPane.ERROR_MESSAGE);

        }



    }//GEN-LAST:event_btn_saveActionPerformed

    private void btn_modifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modifyActionPerformed
        // TODO add your handling code here:


        if (dt_start.getDate() != null && !cmb_status.getSelectedItem().toString().equalsIgnoreCase("Select")) {
            IPersistence ip = Persistence.getInstance();

            EmpJob modifyedEmpJobData = new EmpJob();
            EmpJob oldEmpJobData = new EmpJob();

            modifyedEmpJobData.setEmp_id(oldData.getEmpID());
            modifyedEmpJobData.setClient_id(oldData.getClientID());
            modifyedEmpJobData.setStart_date(dt_start.getDate());
            modifyedEmpJobData.setEnd_date(dt_end.getDate());
            modifyedEmpJobData.setJobDesc(txt_desc.getText());
            modifyedEmpJobData.setStatus(cmb_status.getSelectedItem().toString());
            modifyedEmpJobData.setRemarks(txt_remarks.getText());

            oldEmpJobData.setEmp_id(oldData.getEmpID());
            oldEmpJobData.setClient_id(oldData.getClientID());
            oldEmpJobData.setStart_date(oldData.getStartDate());
            oldEmpJobData.setEnd_date(oldData.getEndDate());
            oldEmpJobData.setJobDesc(oldData.getJobDesc());
            oldEmpJobData.setRemarks(oldData.getRemarks());
            oldEmpJobData.setStatus(oldData.getStatus());



            if (cmb_status.getSelectedItem().toString().equalsIgnoreCase("Completed")
                    && dt_end.getDate() == null) {
                JOptionPane.showMessageDialog(new JFrame(), "Please Give End Date", "Dialog",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                Boolean flag = ip.updateJobDetail(modifyedEmpJobData, oldEmpJobData);

                if (flag) {
                    JOptionPane.showMessageDialog(null, "Job modified");
                    mainFrame.reloadTableDataCurrentAssignment();
                    mainFrame.reloadTableDataCompletedAssignment();
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Error in modification !!!!!", "Dialog",
                            JOptionPane.ERROR_MESSAGE);
                    this.dispose();
                }
            }






        } else {
            String message = "";

            if (dt_start.getDate() == null) {
                message = "Please set start date";
            }

            if (cmb_status.getSelectedItem().toString().equalsIgnoreCase("Select")) {
                message = "Please set start working status";
            }

            JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                    JOptionPane.ERROR_MESSAGE);


        }

    }//GEN-LAST:event_btn_modifyActionPerformed

    private void postActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_postActionPerformed
        // TODO add your handling code here:
        System.out.print("-------******>>>");
        String emppost = post.getSelectedItem().toString();

        ip = Persistence.getInstance();
        List<Employee> employees = ip.getEmployies(emppost);
        cmb_emp.setModel(new DefaultComboBoxModel(employees.toArray()));
    }//GEN-LAST:event_postActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancel;
    private javax.swing.JButton btn_modify;
    private javax.swing.JButton btn_save;
    private javax.swing.JComboBox cmb_client;
    private javax.swing.JComboBox cmb_emp;
    private javax.swing.JComboBox cmb_status;
    private com.toedter.calendar.JDateChooser dt_end;
    private com.toedter.calendar.JDateChooser dt_start;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_client_name;
    private javax.swing.JLabel lbl_emp_name;
    private javax.swing.JPanel modify_upper_panel;
    private javax.swing.JComboBox post;
    private javax.swing.JPanel save_upper_panel;
    private javax.swing.JTextArea txt_desc;
    private javax.swing.JLabel txt_empassign;
    private javax.swing.JTextArea txt_remarks;
    // End of variables declaration//GEN-END:variables
}
