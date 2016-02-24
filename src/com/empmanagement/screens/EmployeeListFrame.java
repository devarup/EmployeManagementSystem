package com.empmanagement.screens;

 
import com.employeemanagement.models.CustomTableModel;
import com.employeemanagement.models.Employee;
import com.employeemanagement.utility.StringDocumentFilter;
import com.empmanagement.persistence.IPersistence;
import com.empmanagement.persistence.Persistence; 
import com.empmanagement.providers.EmployeeCategoriesForSearch;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent; 
import java.awt.print.PrinterException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector; 
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JInternalFrame; 
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.AbstractDocument;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author arup
 */
public class EmployeeListFrame extends javax.swing.JFrame {
    
    private Vector<Vector<String>> data; //used for data from database
    private Vector<String> header; //used to store data header
    private Vector<String> vector = null;
    private IPersistence iPersistence =null; 
    List<Employee> emplist = null;
    
    CustomTableModel empTableModel;
    
    /**
     * Creates new form EmployeeListFrame
     */
    
   
    
    
    public EmployeeListFrame(String name,MainFrame mainFrame) {
        
        super("Search Employee");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        this.mainFrame = mainFrame; 
        
        iPersistence = Persistence.getInstance(); 
        emplist = iPersistence.getEmployees();
        
        data = new Vector<Vector<String>>();
        
        for(int i = 0; i < emplist.size(); i++ ){
            vector = new Vector<String>();
            
            System.out.println(" Emp Id : "+emplist.get(i).getEmp_id());
            
            vector.add(emplist.get(i).getEmp_id());
            vector.add(emplist.get(i).getPost());
            vector.add(emplist.get(i).getF_name() + " " + emplist.get(i).getM_name() + " " + emplist.get(i).getL_name());
            vector.add(emplist.get(i).getMob1());
           // vector.add(emplist.get(i).getMob2());
            //vector.add(emplist.get(i).getAge().toString());
           // vector.add(emplist.get(i).getGuardian_name());
            vector.add(emplist.get(i).getAddress());
            
            data.add(vector);
        } 
        
        createEmployeeListFrame(data);
         
    }
        

    
    
    
    
    
    
    public void createEmployeeListFrame( Vector<Vector<String>> data){
         
        this.data = data;
        header = new Vector<String>();
        header.add("Emp Code"); 
        header.add("Post"); 
        header.add("Name");  
        header.add("Mobile No 1"); 
        //header.add("Phone No2"); 
        //header.add("Age"); 
        //header.add("Guardian");
        header.add("Address");
        
        
        initComponents();  
        
        
        
        jTable1.setAutoCreateRowSorter(true);
        jTable1.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseClicked(MouseEvent e) {
               if (e.getClickCount() == 2) { 
                   int row =   jTable1.getSelectedRow();
                   String empCode = (String) jTable1.getValueAt(row,0);
                   System.out.println("Selected Row : "+row);
                   System.out.println("Selected Row : "+empCode);
                   
                   Employee employee = iPersistence.getEmployee(empCode);
                   System.err.println("Emp  : "+employee.getF_name());
                   
                   
                   EmployeeDisplayPanel displayPanel = new EmployeeDisplayPanel(mainFrame,EmployeeListFrame.this,employee);
                   displayPanel.setTitle("Employee I-card");
                   displayPanel.setBounds(170, 30, 105, 70);      
                   displayPanel.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
                   displayPanel.setResizable(false);
                   displayPanel.setVisible(true);
                   displayPanel.pack();
                   
                   
                   
                   
                    
                   //EmployeeListFrame.this.dispose();
                 
               }
            }
         });
        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
        
        emp_Post.setModel(new DefaultComboBoxModel(EmployeeCategoriesForSearch.values()));
        //setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize()); 
        
        pack();
   }
    
    
    
    
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable(){

            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (c instanceof JComponent) {
                    if(column <= 10 ){
                        //X is your particlur column number
                        JComponent jc = (JComponent) c;
                        String text = getValueAt(row, column).toString();
                        if(text.equalsIgnoreCase(""))
                        text = "NA";
                        jc.setToolTipText(text);
                    }
                }
                return c;
            }

        };
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        emp_Post = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        emp_Name = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        emp_Phno = new javax.swing.JTextField();
        jButtonEmpSearch = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(253, 235, 235));
        jLabel1.setText("  Search Employee");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 927, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setForeground(new java.awt.Color(0, 0, 153));
        empTableModel = new com.employeemanagement.models.CustomTableModel(data, header);
        jTable1.setModel(empTableModel);
        jTable1.setGridColor(new java.awt.Color(153, 153, 153));
        jTable1.setRowHeight(20);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(5);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(15);
        jTable1.setSelectionBackground(new java.awt.Color(153, 0, 0));
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(2);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(5);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(5);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(40);
        jScrollPane1.setViewportView(jTable1);

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Post :");

        emp_Post.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        emp_Post.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emp_PostActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Name :");

        ((AbstractDocument) emp_Name.getDocument()).setDocumentFilter(new StringDocumentFilter());

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Mobile No 1 :");

        ((AbstractDocument)emp_Phno.getDocument()).setDocumentFilter(
            new com.employeemanagement.utility.NumericDocumentFilter());

        jButtonEmpSearch.setText("Search");
        jButtonEmpSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEmpSearchActionPerformed(evt);
            }
        });

        jButton1.setText("Print");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(emp_Post, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(emp_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(emp_Phno, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonEmpSearch)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(emp_Post, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(emp_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(emp_Phno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonEmpSearch)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonEmpSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEmpSearchActionPerformed
        reloadTableData();
    }//GEN-LAST:event_jButtonEmpSearchActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // TODO add your handling code here:
        EmployeeListPrintView printView = new EmployeeListPrintView(emplist);
        printView.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        printView.setVisible(true);
        printView.pack();  
            
            //jTable1.print(JTable.PrintMode.NORMAL, null, null);
        } catch (Exception ex) {
            Logger.getLogger(EmployeeListFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void emp_PostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emp_PostActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emp_PostActionPerformed

    
    
    public void reloadTableData(){
        
        
        List<String> params = new ArrayList<String>();
        params.add(emp_Post.getSelectedItem().toString());
        params.add(emp_Name.getText());
        params.add(emp_Phno.getText());
       // List<Employee> employees = null;
        emplist = iPersistence.getSearchedEmployeeList(params);
        //employees = emplist;
        data = new Vector<Vector<String>>();
        
        for(int i = 0; i < emplist.size(); i++ ){
            vector = new Vector<String>();
            
            System.out.println(" Emp Id : "+emplist.get(i).getEmp_id() + " Name : "+emplist.get(i).getF_name() );
            
            vector.add(emplist.get(i).getEmp_id());
            vector.add(emplist.get(i).getPost());
            vector.add(emplist.get(i).getF_name() + " " + emplist.get(i).getM_name() + " " + emplist.get(i).getL_name());
            vector.add(emplist.get(i).getMob1());
           // vector.add(emplist.get(i).getMob2());
            //vector.add(emplist.get(i).getAge().toString());
            //vector.add(emplist.get(i).getGuardian_name());
            vector.add(emplist.get(i).getAddress());
            
            data.add(vector);
            
        }
         
        jTable1.setModel(new CustomTableModel(data, header)); 
        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(2);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(2);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(5);
        
        
        
        empTableModel.fireTableDataChanged();
        
    }
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField emp_Name;
    private javax.swing.JTextField emp_Phno;
    private javax.swing.JComboBox emp_Post;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonEmpSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    MainFrame mainFrame;
    
    
}
