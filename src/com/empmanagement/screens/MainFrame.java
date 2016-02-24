/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empmanagement.screens;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;

import com.employeemanagement.common.Common;
import com.employeemanagement.main.EmployeeManagement;
import com.employeemanagement.models.CustomTableModel;
import com.employeemanagement.models.Employee;
import com.empmanagement.persistence.IPersistence;
import com.empmanagement.persistence.Persistence;
import com.empmanagement.view.bean.JobDetail;

/**
 *
 * @author user
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    private Vector<Vector<String>> data; // used for data from database
    private Vector<Vector<String>> data1; // used for data from database
    private Vector<Vector<String>> data2; // used for data from database
    private Vector<String> header; // used to store data header
    private Vector<String> header1; // used to store data header
    private Vector<String> header2; // used to store data header
    private Vector<String> vector = null;
    private Vector<String> vector1 = null;
    private Vector<String> vector2 = null;
    IPersistence ip = null;
    CustomTableModel cusTableModel;
    CustomTableModel cusTableModel1;

    public MainFrame() {
	super("Dashboard Panel");
	ip = Persistence.getInstance();

	List<JobDetail> completejob = ip.getJobStatusDetail("Completed");

	data1 = new Vector<Vector<String>>();

	List<JobDetail> jb = ip.getJobStatusDetail("Work in progress");
	data = new Vector<Vector<String>>();
	// set working job detail
	for (int i = 0; i < jb.size(); i++) {
	    vector = new Vector<String>();
	    vector.add(jb.get(i).getEmpID());
	    vector.add(jb.get(i).getEmpName());
	    vector.add(jb.get(i).getEmpContact());
	    vector.add(jb.get(i).getClientID());
	    vector.add(jb.get(i).getClientName());
	    vector.add(jb.get(i).getClientContact());

	    String startDate = "";
	    String endDate = "";
	    if (jb.get(i).getStartDate() != null) {
		startDate = Common.getDateFormatter().format(
			jb.get(i).getStartDate());
	    }

	    if (jb.get(i).getEndDate() != null) {
		endDate = Common.getDateFormatter().format(
			jb.get(i).getEndDate());
	    }

	    vector.add(startDate);
	    vector.add(endDate);
	    vector.add(jb.get(i).getJobDesc());
	    vector.add(jb.get(i).getPost());

	    data.add(vector);
	}

	// set completed job detail

	for (int j = 0; j < completejob.size(); j++) {
	    vector1 = new Vector<String>();
	    vector1.add(completejob.get(j).getEmpID());
	    vector1.add(completejob.get(j).getEmpName());
	    vector1.add(completejob.get(j).getEmpContact());
	    vector1.add(completejob.get(j).getClientID());
	    vector1.add(completejob.get(j).getClientName());
	    vector1.add(completejob.get(j).getClientContact());

	    String startDate = "";
	    String endDate = "";
	    if (completejob.get(j).getStartDate() != null) {
		startDate = Common.getDateFormatter().format(
			completejob.get(j).getStartDate());
	    }

	    if (completejob.get(j).getEndDate() != null) {
		endDate = Common.getDateFormatter().format(
			completejob.get(j).getEndDate());
	    }

	    vector1.add(startDate);
	    vector1.add(endDate);
	    vector1.add(completejob.get(j).getJobDesc());
	    vector1.add(completejob.get(j).getPost());

	    data1.add(vector1);
	}

	List<Employee> emplist = ip.getEmployees();

	data2 = new Vector<Vector<String>>();

	for (int i = 0; i < emplist.size(); i++) {
	    vector2 = new Vector<String>();

	    System.out.println(" Emp Id : " + emplist.get(i).getEmp_id());

	    vector2.add(emplist.get(i).getEmp_id());
	    String name = emplist.get(i).getF_name() + " "
		    + emplist.get(i).getM_name() + " "
		    + emplist.get(i).getL_name();
	    vector2.add(name.replaceAll("( +)", " "));
	    vector2.add(emplist.get(i).getMob1());

	    data2.add(vector2);
	}

	displayJobDetail(data, data1, data2);

	// initComponents();
	// Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	// setBounds(0,0,screenSize.width, screenSize.height);
	// setVisible(true);
	// setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public void displayJobDetail(Vector<Vector<String>> data,
	    Vector<Vector<String>> completedJobdata,
	    Vector<Vector<String>> Empdata) {
	this.data = data;
	this.data1 = completedJobdata;
	header = new Vector<String>();
	header.add("Emp. ID");
	header.add("Emp. Name ");
	header.add("Emp. Contact");
	header.add("Client ID");
	header.add("Client Name");
	header.add("Client Contact");
	header.add("Start Date");
	header.add("End Date");
	header.add("Job Desc.");
	header.add("Post");

	header1 = new Vector<String>();
	header1.add("Emp. ID");
	header1.add("Emp. Name ");
	header1.add("Emp. Contact");
	header1.add("Client ID");
	header1.add("Client Name");
	header1.add("Client Contact");
	header1.add("Start Date");
	header1.add("End Date");
	header1.add("Job Desc.");
	header1.add("Post");

	this.data2 = Empdata;
	header2 = new Vector<String>();
	header2.add("Emp Code");
	header2.add("Name");
	header2.add("Phone No");

	initComponents();

	workInProgressTable.setAutoCreateRowSorter(true);
	completedJobTable.setAutoCreateRowSorter(true);
	workInProgressTable.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {

		    int row = workInProgressTable.getSelectedRow();
		    String empID = (String) workInProgressTable.getValueAt(row,
			    0);
		    String clientID = (String) workInProgressTable.getValueAt(
			    row, 3);
		    String startDate = (String) workInProgressTable.getValueAt(
			    row, 6);
		    String status = "Work in progress";
		    try {
			Date start_dt = Common.getDateFormatter().parse(
				startDate);

			IPersistence ip = Persistence.getInstance();
			JobDetail jobdetail = ip.getJobStatusDetail(empID,
				clientID, start_dt, status);

			AssignEmployee ass;
			ass = new AssignEmployee(jobdetail, MainFrame.this);
			ass.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
			ass.setVisible(true);
			ass.pack();

		    } catch (ParseException ex) {
			System.err.println("--------- Error ------------");
			ex.printStackTrace();

		    }

		}

	    }
	});

	completedJobTable.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {

		if (e.getClickCount() == 2) {

		    int row = completedJobTable.getSelectedRow();
		    String empID = (String) completedJobTable
			    .getValueAt(row, 0);
		    String clientID = (String) completedJobTable.getValueAt(
			    row, 3);
		    String startDate = (String) completedJobTable.getValueAt(
			    row, 6);
		    String status = "Completed";

		    try {
			Date start_dt = Common.getDateFormatter().parse(
				startDate);

			IPersistence ip = Persistence.getInstance();
			JobDetail jobdetail = ip.getJobStatusDetail(empID,
				clientID, start_dt, status);

			AssignEmployee ass;
			ass = new AssignEmployee(jobdetail, MainFrame.this);
			ass.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
			ass.setVisible(true);
			ass.pack();

		    } catch (ParseException ex) {
			System.err.println("--------- Error ------------");
			ex.printStackTrace();

		    }

		}

	    }
	});

	workInProgressTable
		.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	completedJobTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	jTable1.setAutoCreateRowSorter(true);

	jTable1.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {

		if (e.getClickCount() == 2) {
		    int row = jTable1.getSelectedRow();
		    String empCode = (String) jTable1.getValueAt(row, 0);
		    System.out.println("Selected Row : " + row);
		    System.out.println("Selected Row : " + empCode);

		    Employee employee = ip.getEmployee(empCode);
		    System.err.println("Emp  : " + employee.getF_name());

		    EmployeeDisplayPanel displayPanel;
		    displayPanel = new EmployeeDisplayPanel(MainFrame.this,
			    null, employee);
		    displayPanel.setTitle("Employee I-card");
		    displayPanel.setBounds(170, 30, 105, 70);
		    displayPanel
			    .setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		    displayPanel.setResizable(false);
		    displayPanel.setVisible(true);
		    displayPanel.pack();

		    // EmployeeListFrame.this.dispose();

		}
	    }
	});

	setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
	pack();

    }

    public void reloadTableData() {

	ip = Persistence.getInstance();
	List<Employee> employees = ip.getEmployees();

	data2 = new Vector<Vector<String>>();

	for (int i = 0; i < employees.size(); i++) {
	    vector2 = new Vector<String>();
	    vector2.add(employees.get(i).getEmp_id());
	    String name = employees.get(i).getF_name() + " "
		    + employees.get(i).getM_name() + " "
		    + employees.get(i).getL_name();
	    vector2.add(name.replaceAll("( +)", " "));
	    vector2.add(employees.get(i).getMob1());
	    data2.add(vector2);
	}
	cusTableModel = new CustomTableModel(data2, header2);
	jTable1.setModel(cusTableModel);
	jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	cusTableModel.fireTableDataChanged();

    }

    public void reloadTableDataCurrentAssignment() {

	ip = Persistence.getInstance();
	List<JobDetail> jb = ip.getJobStatusDetail("Work in progress");
	data = new Vector<Vector<String>>();
	// set working job detail
	for (int i = 0; i < jb.size(); i++) {
	    vector = new Vector<String>();
	    vector.add(jb.get(i).getEmpID());
	    vector.add(jb.get(i).getEmpName());
	    vector.add(jb.get(i).getEmpContact());
	    vector.add(jb.get(i).getClientID());
	    vector.add(jb.get(i).getClientName());
	    vector.add(jb.get(i).getClientContact());

	    String startDate = "";
	    String endDate = "";
	    if (jb.get(i).getStartDate() != null) {
		startDate = Common.getDateFormatter().format(
			jb.get(i).getStartDate());
	    }

	    if (jb.get(i).getEndDate() != null) {
		endDate = Common.getDateFormatter().format(
			jb.get(i).getEndDate());
	    }

	    vector.add(startDate);
	    vector.add(endDate);
	    vector.add(jb.get(i).getJobDesc());
	    vector.add(jb.get(i).getPost());

	    data.add(vector);
	}

	cusTableModel = new CustomTableModel(data, header);
	workInProgressTable.setModel(cusTableModel);
	workInProgressTable
		.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	cusTableModel.fireTableDataChanged();

    }

    public void reloadTableDataCompletedAssignment() {
	ip = Persistence.getInstance();
	List<JobDetail> completejob = ip.getJobStatusDetail("Completed");
	data1 = new Vector<Vector<String>>();
	for (int j = 0; j < completejob.size(); j++) {
	    vector1 = new Vector<String>();
	    vector1.add(completejob.get(j).getEmpID());
	    vector1.add(completejob.get(j).getEmpName());
	    vector1.add(completejob.get(j).getEmpContact());
	    vector1.add(completejob.get(j).getClientID());
	    vector1.add(completejob.get(j).getClientName());
	    vector1.add(completejob.get(j).getClientContact());

	    String startDate = "";
	    String endDate = "";
	    if (completejob.get(j).getStartDate() != null) {
		startDate = Common.getDateFormatter().format(
			completejob.get(j).getStartDate());
	    }

	    if (completejob.get(j).getEndDate() != null) {
		endDate = Common.getDateFormatter().format(
			completejob.get(j).getEndDate());
	    }

	    vector1.add(startDate);
	    vector1.add(endDate);
	    vector1.add(completejob.get(j).getJobDesc());
	    vector1.add(completejob.get(j).getPost());

	    data1.add(vector1);
	}

	cusTableModel = new CustomTableModel(data1, header1);

	completedJobTable.setModel(cusTableModel);
	completedJobTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	cusTableModel.fireTableDataChanged();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed"
    // desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

	jPanel5 = new javax.swing.JPanel();
	jScrollPane = new javax.swing.JScrollPane();
	jTable1 = new javax.swing.JTable() {

	    public Component prepareRenderer(TableCellRenderer renderer,
		    int row, int column) {
		Component c = super.prepareRenderer(renderer, row, column);
		if (c instanceof JComponent) {
		    if (column <= 10) {
			// X is your particlur column number
			JComponent jc = (JComponent) c;
			String text = getValueAt(row, column).toString();

			if (text.equalsIgnoreCase(""))
			    text = "NA";

			jc.setToolTipText(text);
		    }
		}
		return c;
	    }

	};
	jPanel1 = new javax.swing.JPanel();
	jLabel1 = new javax.swing.JLabel();
	jLabel7 = new javax.swing.JLabel();
	jPanel6 = new javax.swing.JPanel();
	jPanel4 = new javax.swing.JPanel();
	jLabel5 = new javax.swing.JLabel();
	work_in_progress = new javax.swing.JScrollPane();
	workInProgressTable = new javax.swing.JTable() {

	    public Component prepareRenderer(TableCellRenderer renderer,
		    int row, int column) {
		Component c = super.prepareRenderer(renderer, row, column);
		if (c instanceof JComponent) {
		    if (column <= 10) {
			// X is your particlur column number

			JComponent jc = (JComponent) c;
			String text = getValueAt(row, column).toString();
			if (text.equalsIgnoreCase(""))
			    text = "NA";
			jc.setToolTipText(text);
		    }
		}

		return c;
	    }
	};
	jPanel2 = new javax.swing.JPanel();
	jPanel7 = new javax.swing.JPanel();
	jLabel8 = new javax.swing.JLabel();
	completed_job = new javax.swing.JScrollPane();
	completedJobTable = new javax.swing.JTable() {

	    public Component prepareRenderer(TableCellRenderer renderer,
		    int row, int column) {
		Component c = super.prepareRenderer(renderer, row, column);
		if (c instanceof JComponent) {
		    if (column <= 10) {
			// X is your particlur column number
			JComponent jc = (JComponent) c;
			String text = getValueAt(row, column).toString();
			if (text.equalsIgnoreCase(""))
			    text = "NA";
			jc.setToolTipText(text);
		    }
		}
		return c;
	    }

	};
	Menu = new javax.swing.JMenuBar();
	File = new javax.swing.JMenu();
	New = new javax.swing.JMenu();
	Employee = new javax.swing.JMenuItem();
	Client = new javax.swing.JMenuItem();
	emp_assign = new javax.swing.JMenuItem();
	Close = new javax.swing.JMenuItem();
	edit = new javax.swing.JMenu();
	delete = new javax.swing.JMenu();
	emp = new javax.swing.JMenuItem();
	client = new javax.swing.JMenuItem();
	View = new javax.swing.JMenu();
	empSearch = new javax.swing.JMenuItem();
	clientSearch = new javax.swing.JMenuItem();
	jMenuItemAssignEmployee = new javax.swing.JMenuItem();
	jMenuItem1 = new javax.swing.JMenuItem();
	system = new javax.swing.JMenu();
	db = new javax.swing.JMenuItem();
	clean_db = new javax.swing.JMenuItem();

	setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	setBackground(new java.awt.Color(255, 255, 255));

	jPanel5.setBackground(new java.awt.Color(255, 255, 255));
	jPanel5.setBorder(new javax.swing.border.MatteBorder(null));

	jScrollPane.setBackground(new java.awt.Color(255, 255, 204));
	jScrollPane.setPreferredSize(new java.awt.Dimension(452, 500));

	cusTableModel = new CustomTableModel(data2, header2);
	jTable1.setModel(cusTableModel);
	jScrollPane.setViewportView(jTable1);

	jPanel1.setBackground(new java.awt.Color(255, 255, 255));
	jPanel1.setBorder(javax.swing.BorderFactory
		.createLineBorder(new java.awt.Color(0, 0, 0)));
	jPanel1.setForeground(new java.awt.Color(255, 255, 255));

	jLabel1.setBackground(new java.awt.Color(255, 255, 204));
	jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource(
		"/com/empmanagement/screens/ss-centre.jpg"))); // NOI18N

	jLabel7.setFont(new java.awt.Font("Shruti", 1, 14)); // NOI18N
	jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	jLabel7.setText("<html> <head></head> <body> <span style=\"text-align:right;margin:auto auto;line-height:2px;\"> <p>Tollygunge</p> <p>Kolkata,West Bengal, India</p> <p>Ph-1234567890 /0987654321</p> </span> <body> </html>");
	jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

	javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
		jPanel1);
	jPanel1.setLayout(jPanel1Layout);
	jPanel1Layout
		.setHorizontalGroup(jPanel1Layout
			.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(
				jPanel1Layout
					.createSequentialGroup()
					.addContainerGap()
					.addComponent(
						jLabel1,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
					.addPreferredGap(
						javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
					.addComponent(
						jLabel7,
						javax.swing.GroupLayout.PREFERRED_SIZE,
						421,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addContainerGap()));
	jPanel1Layout
		.setVerticalGroup(jPanel1Layout
			.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(
				jPanel1Layout
					.createSequentialGroup()
					.addGroup(
						jPanel1Layout
							.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
							.addGroup(
								jPanel1Layout
									.createSequentialGroup()
									.addContainerGap()
									.addComponent(
										jLabel1))
							.addGroup(
								jPanel1Layout
									.createSequentialGroup()
									.addGap(28,
										28,
										28)
									.addComponent(
										jLabel7,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										92,
										javax.swing.GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(
						javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)));

	jPanel4.setBackground(new java.awt.Color(0, 0, 0));
	jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(
		0, 0, 0), 1, true));

	jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
	jLabel5.setForeground(new java.awt.Color(255, 255, 255));
	jLabel5.setText("Work in progress");

	javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(
		jPanel4);
	jPanel4.setLayout(jPanel4Layout);
	jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(
		javax.swing.GroupLayout.Alignment.LEADING).addGroup(
		javax.swing.GroupLayout.Alignment.TRAILING,
		jPanel4Layout
			.createSequentialGroup()
			.addContainerGap()
			.addComponent(jLabel5,
				javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE,
				Short.MAX_VALUE).addContainerGap()));
	jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(
		javax.swing.GroupLayout.Alignment.LEADING).addComponent(
		jLabel5, javax.swing.GroupLayout.Alignment.TRAILING,
		javax.swing.GroupLayout.PREFERRED_SIZE, 30,
		javax.swing.GroupLayout.PREFERRED_SIZE));

	cusTableModel = new CustomTableModel(data, header);
	workInProgressTable.setModel(cusTableModel);
	work_in_progress.setViewportView(workInProgressTable);

	jPanel7.setBackground(new java.awt.Color(0, 0, 0));
	jPanel7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(
		0, 0, 0), 1, true));

	jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
	jLabel8.setForeground(new java.awt.Color(255, 255, 255));
	jLabel8.setText("Job completed in last 30 days");

	javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(
		jPanel7);
	jPanel7.setLayout(jPanel7Layout);
	jPanel7Layout.setHorizontalGroup(jPanel7Layout.createParallelGroup(
		javax.swing.GroupLayout.Alignment.LEADING).addGroup(
		jPanel7Layout
			.createSequentialGroup()
			.addContainerGap()
			.addComponent(jLabel8,
				javax.swing.GroupLayout.DEFAULT_SIZE, 893,
				Short.MAX_VALUE)));
	jPanel7Layout.setVerticalGroup(jPanel7Layout.createParallelGroup(
		javax.swing.GroupLayout.Alignment.LEADING).addComponent(
		jLabel8, javax.swing.GroupLayout.Alignment.TRAILING,
		javax.swing.GroupLayout.PREFERRED_SIZE, 30,
		javax.swing.GroupLayout.PREFERRED_SIZE));

	cusTableModel1 = new CustomTableModel(data1, header1);
	completedJobTable.setModel(cusTableModel1);
	completed_job.setViewportView(completedJobTable);

	javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(
		jPanel2);
	jPanel2.setLayout(jPanel2Layout);
	jPanel2Layout
		.setHorizontalGroup(jPanel2Layout
			.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(
				jPanel2Layout
					.createSequentialGroup()
					.addContainerGap()
					.addGroup(
						jPanel2Layout
							.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
							.addComponent(
								completed_job,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								905,
								Short.MAX_VALUE)
							.addComponent(
								jPanel7,
								javax.swing.GroupLayout.Alignment.TRAILING,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE))
					.addContainerGap()));
	jPanel2Layout
		.setVerticalGroup(jPanel2Layout
			.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(
				jPanel2Layout
					.createSequentialGroup()
					.addContainerGap(
						javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
					.addComponent(
						jPanel7,
						javax.swing.GroupLayout.PREFERRED_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(
						javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
					.addComponent(
						completed_job,
						javax.swing.GroupLayout.PREFERRED_SIZE,
						204,
						javax.swing.GroupLayout.PREFERRED_SIZE)));

	javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(
		jPanel6);
	jPanel6.setLayout(jPanel6Layout);
	jPanel6Layout
		.setHorizontalGroup(jPanel6Layout
			.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING)
			.addComponent(jPanel2,
				javax.swing.GroupLayout.Alignment.TRAILING,
				javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE,
				Short.MAX_VALUE)
			.addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				jPanel6Layout
					.createSequentialGroup()
					.addContainerGap()
					.addGroup(
						jPanel6Layout
							.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
							.addComponent(
								work_in_progress,
								javax.swing.GroupLayout.Alignment.TRAILING,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								905,
								Short.MAX_VALUE)
							.addComponent(
								jPanel4,
								javax.swing.GroupLayout.Alignment.TRAILING,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE))
					.addContainerGap()));
	jPanel6Layout
		.setVerticalGroup(jPanel6Layout
			.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(
				jPanel6Layout
					.createSequentialGroup()
					.addContainerGap()
					.addComponent(
						jPanel4,
						javax.swing.GroupLayout.PREFERRED_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(
						javax.swing.LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(
						work_in_progress,
						javax.swing.GroupLayout.PREFERRED_SIZE,
						204,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(
						javax.swing.LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(
						jPanel2,
						javax.swing.GroupLayout.PREFERRED_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addContainerGap()));

	javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(
		jPanel5);
	jPanel5.setLayout(jPanel5Layout);
	jPanel5Layout
		.setHorizontalGroup(jPanel5Layout
			.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(
				jPanel5Layout
					.createSequentialGroup()
					.addContainerGap()
					.addGroup(
						jPanel5Layout
							.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
							.addComponent(
								jPanel1,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
							.addGroup(
								jPanel5Layout
									.createSequentialGroup()
									.addComponent(
										jScrollPane,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										269,
										javax.swing.GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(
										jPanel6,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)))
					.addContainerGap()));
	jPanel5Layout
		.setVerticalGroup(jPanel5Layout
			.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(
				jPanel5Layout
					.createSequentialGroup()
					.addContainerGap()
					.addComponent(
						jPanel1,
						javax.swing.GroupLayout.PREFERRED_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(
						javax.swing.LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(
						jPanel5Layout
							.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
							.addComponent(
								jPanel6,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
							.addComponent(
								jScrollPane,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE))
					.addContainerGap()));

	Menu.setPreferredSize(new java.awt.Dimension(56, 24));

	File.setText("File");

	New.setText("New");

	Employee.setText("Employee");
	Employee.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		EmployeeActionPerformed(evt);
	    }
	});
	New.add(Employee);

	Client.setText("Client");
	Client.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		ClientActionPerformed(evt);
	    }
	});
	New.add(Client);

	emp_assign.setText("Employee Assign");
	emp_assign.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		emp_assignActionPerformed(evt);
	    }
	});
	New.add(emp_assign);

	File.add(New);

	Close.setText("Close");
	Close.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		CloseActionPerformed(evt);
	    }
	});
	File.add(Close);

	Menu.add(File);

	edit.setText("Edit");

	delete.setText("Delete");

	emp.setText("Employee");
	emp.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		empActionPerformed(evt);
	    }
	});
	delete.add(emp);

	client.setText("Client");
	client.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		clientActionPerformed(evt);
	    }
	});
	delete.add(client);

	edit.add(delete);

	Menu.add(edit);

	View.setText("Search");

	empSearch.setText("Employee");
	empSearch.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		empSearchActionPerformed(evt);
	    }
	});
	View.add(empSearch);

	clientSearch.setText("Client");
	clientSearch.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		clientSearchActionPerformed(evt);
	    }
	});
	View.add(clientSearch);

	jMenuItemAssignEmployee.setText("Working Employee");
	jMenuItemAssignEmployee
		.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
			jMenuItemAssignEmployeeActionPerformed(evt);
		    }
		});
	View.add(jMenuItemAssignEmployee);

	jMenuItem1.setText("Working Clients");
	jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		jMenuItem1ActionPerformed(evt);
	    }
	});
	View.add(jMenuItem1);

	Menu.add(View);

	system.setText("System");

	db.setText("Database Backup/Restore");
	db.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		dbActionPerformed(evt);
	    }
	});
	system.add(db);

	clean_db.setText("Clean Database");
	clean_db.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		clean_dbActionPerformed(evt);
	    }
	});
	system.add(clean_db);

	Menu.add(system);

	setJMenuBar(Menu);

	javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
		getContentPane());
	getContentPane().setLayout(layout);
	layout.setHorizontalGroup(layout.createParallelGroup(
		javax.swing.GroupLayout.Alignment.LEADING).addComponent(
		jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE,
		javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
	layout.setVerticalGroup(layout.createParallelGroup(
		javax.swing.GroupLayout.Alignment.LEADING).addComponent(
		jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE,
		javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

	pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EmployeeActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_EmployeeActionPerformed
	internalFrame = new EmployeeManipulationFrame(
		"Employee Manipulation Panel", this, null, null);
	internalFrame.setBounds(170, 30, 105, 70);
	internalFrame.setPreferredSize(new java.awt.Dimension(1000, 750));

	internalFrame.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
	internalFrame.setVisible(true);
	internalFrame.pack();
    }// GEN-LAST:event_EmployeeActionPerformed

    private void ClientActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_ClientActionPerformed
	clientFrame = new ClientFrame("Client Panel", this, null, null);
	clientFrame.setBounds(150, 30, 105, 70);
	clientFrame.setPreferredSize(new Dimension(600, 400));
	clientFrame.setResizable(false);
	// jDesktopPane1.add(clientFrame, JLayeredPane.DEFAULT_LAYER);
	clientFrame.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
	clientFrame.pack();
    }// GEN-LAST:event_ClientActionPerformed

    private void empSearchActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_empSearchActionPerformed
	EmployeeListFrame allEmployee = new EmployeeListFrame("All Employee",
		this);
	allEmployee.setBounds(170, 30, 105, 70);
	allEmployee.setPreferredSize(new java.awt.Dimension(950, 700));
	// jDesktopPane1.add(clientFrame, JLayeredPane.DEFAULT_LAYER);
	allEmployee.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
	allEmployee.setVisible(true);
	allEmployee.pack();
    }// GEN-LAST:event_empSearchActionPerformed

    private void emp_assignActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_emp_assignActionPerformed
	// TODO add your handling code here:
	AssignEmployee assignEmployee = new AssignEmployee(this);

	// allEmployee.setBounds(150, 30, 105, 70);
	// allEmployee.setPreferredSize(new java.awt.Dimension(460, 334));
	// jDesktopPane1.add(clientFrame, JLayeredPane.DEFAULT_LAYER);
	assignEmployee
		.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
	assignEmployee.setVisible(true);
	assignEmployee.pack();

    }// GEN-LAST:event_emp_assignActionPerformed

    private void clientSearchActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_clientSearchActionPerformed
	// TODO add your handling code here:
	ClientListFrame allClients = new ClientListFrame("All Clients", this);
	allClients.setBounds(170, 30, 105, 70);
	allClients.setPreferredSize(new java.awt.Dimension(900, 700));
	// jDesktopPane1.add(clientFrame, JLayeredPane.DEFAULT_LAYER);
	allClients.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
	allClients.setVisible(true);
	allClients.pack();
    }// GEN-LAST:event_clientSearchActionPerformed

    private void jMenuItemAssignEmployeeActionPerformed(
	    java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItemAssignEmployeeActionPerformed

	AssignedEmployeeToClient assignEmp = new AssignedEmployeeToClient();
	assignEmp.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
	assignEmp.setVisible(true);
	assignEmp.pack();

    }// GEN-LAST:event_jMenuItemAssignEmployeeActionPerformed

    private void CloseActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_CloseActionPerformed
	System.exit(0);
    }// GEN-LAST:event_CloseActionPerformed

    private void dbActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_dbActionPerformed
	// TODO add your handling code here:
	DBManupulation db = new DBManupulation(this);
	db.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
	db.setVisible(true);
	db.setTitle("Database Backup/Restore");
	db.pack();

    }// GEN-LAST:event_dbActionPerformed

    private void clientActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_clientActionPerformed
	// TODO add your handling code here:
	DeleteClient delClient = new DeleteClient(this);
	delClient.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
	delClient.setVisible(true);
	delClient.setTitle("Delete client");
	delClient.pack();
    }// GEN-LAST:event_clientActionPerformed

    private void empActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_empActionPerformed
	// TODO add your handling code here:
	DeleteEmployee delEmps = new DeleteEmployee(this);
	delEmps.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
	delEmps.setVisible(true);
	delEmps.setTitle("Delete client");
	delEmps.pack();

    }// GEN-LAST:event_empActionPerformed

    private void clean_dbActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_clean_dbActionPerformed
	// TODO add your handling code here:
	String mesg = "Do you really want to clean the database";
	int more = JOptionPane.showConfirmDialog(null, mesg,
		"Assign employee to client", JOptionPane.YES_NO_OPTION);

	if (more == 0) {

	    IPersistence ip = Persistence.getInstance();

	    Boolean flag = ip.cleanDatabase();

	    if (flag) {

		JFrame j = new JFrame();
		j.setAlwaysOnTop(true);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setVisible(false);
		JOptionPane.showMessageDialog(null,
			"Database clean successfully", "Database Backup",
			JOptionPane.INFORMATION_MESSAGE);
		this.dispose();
		EmployeeManagement.main(null);

	    } else {

		JFrame j = new JFrame();
		j.setAlwaysOnTop(true);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setVisible(false);
		JOptionPane.showMessageDialog(j, "Database not cleaned", "",
			JOptionPane.INFORMATION_MESSAGE);

	    }

	} else {
	}

    }// GEN-LAST:event_clean_dbActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItem1ActionPerformed
	// TODO add your handling code here:
	AssignedClientToEmployee assignClient = new AssignedClientToEmployee();
	assignClient.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
	assignClient.setVisible(true);
	assignClient.pack();
    }// GEN-LAST:event_jMenuItem1ActionPerformed

    /**
     * @return the imageURL
     */
    public static String getImageURL() {
	return imageURL;
    }

    /**
     * @param aImageURL
     *            the imageURL to set
     */
    public static void setImageURL(String aImageURL) {
	imageURL = aImageURL;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Client;
    private javax.swing.JMenuItem Close;
    private javax.swing.JMenuItem Employee;
    private javax.swing.JMenu File;
    private javax.swing.JMenuBar Menu;
    private javax.swing.JMenu New;
    private javax.swing.JMenu View;
    private javax.swing.JMenuItem clean_db;
    private javax.swing.JMenuItem client;
    private javax.swing.JMenuItem clientSearch;
    private javax.swing.JTable completedJobTable;
    private javax.swing.JScrollPane completed_job;
    private javax.swing.JMenuItem db;
    private javax.swing.JMenu delete;
    private javax.swing.JMenu edit;
    private javax.swing.JMenuItem emp;
    private javax.swing.JMenuItem empSearch;
    private javax.swing.JMenuItem emp_assign;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItemAssignEmployee;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JTable jTable1;
    private javax.swing.JMenu system;
    private javax.swing.JTable workInProgressTable;
    private javax.swing.JScrollPane work_in_progress;
    // End of variables declaration//GEN-END:variables
    Dimension window = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension preferredSize = new Dimension(window.width - 50,
	    window.height - 159);
    Dimension tablePreferredSize = new Dimension(window.width,
	    window.height - 159);
    Dimension searchSize = new Dimension(window.width - 50, 40);
    private static String imageURL;
    private EmployeeManipulationFrame internalFrame;
    private ClientFrame clientFrame;
}
