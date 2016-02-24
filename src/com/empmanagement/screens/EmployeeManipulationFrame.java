/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empmanagement.screens;

import com.employeemanagement.common.Common;
import com.employeemanagement.models.Employee;
import com.employeemanagement.utility.ImagePreview;
import com.employeemanagement.utility.NumericDocumentFilter;
import com.employeemanagement.utility.NumericTextField;
import com.empmanagement.persistence.IPersistence;
import com.empmanagement.persistence.Persistence;
import com.empmanagement.testswing.ResizableComponent;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.AbstractDocument;
import com.employeemanagement.utility.StringDocumentFilter;
import java.awt.FileDialog;
import java.awt.Frame;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author user
 */
public class EmployeeManipulationFrame extends javax.swing.JFrame {

    /**
     * Creates new form EmployeeManipulationFrame
     */
    MainFrame mainFrame;
    String mainImgURL = null;
    public String voterIDPath = "";
    public String voterIDBackPath = "";
    public String otherPath = "";
    public String panCardPath = "";
    public static String imagePath = "";
    public String panchayetPath = "";
    public Employee employee;
    EmployeeListFrame listFrame;

    public EmployeeManipulationFrame() {
    }

    public EmployeeManipulationFrame(String name, MainFrame mainFrame, EmployeeListFrame listFrame, Employee employee) {

        super("Employee Panel");
        setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
        this.setSize(700, 500);
        initComponents();
        this.mainFrame = mainFrame;
        this.listFrame = listFrame;
        this.employee = employee;

        mainImgURL = MainFrame.getImageURL();
        this.setResizable(true);
        setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        fileChooser = new JFileChooser();
        fileChooser.setAccessory(new ImagePreview(fileChooser));
        FileNameExtensionFilter f = new FileNameExtensionFilter("", "jpg", "png");

        fileChooser.setFileFilter(f);

        //post.setModel(new DefaultComboBoxModel(EmployeeCategories.values()));


        if (employee != null) {


            headerText.setText("Modify Employee Panel");
            delete.setVisible(true);
            save.setVisible(false);
            modify.setVisible(true);
            print_btn.setVisible(true);


            if (employee.getPhoto_ID() != null) {

                imagePath = employee.getPhoto_ID();
                System.out.println(imagePath);

                if (!imagePath.equalsIgnoreCase("") && imagePath.length() > 0) {
                    File file = new File(imagePath);
                    BufferedImage bufferedImage = null;

                    ImageIcon imageIcon = null;
                    Image imgg = null;
                    try {
                        bufferedImage = ImageIO.read(file);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // bufferedImage = resize(bufferedImage, emp_image.getWidth(), emp_image.getHeight());

                    int h = bufferedImage.getHeight();
                    int w = bufferedImage.getWidth();

                    int labelHeight = emp_image.getHeight();
                    int lebelWidth = emp_image.getWidth();

                    if (h < labelHeight && w < lebelWidth) {

                        bufferedImage = resize(bufferedImage, bufferedImage.getWidth(), bufferedImage.getHeight());
                        imageIcon = new ImageIcon(bufferedImage);

                    } else {
                        //bufferedImage = resize(bufferedImage, emp_image.getWidth(), emp_image.getHeight());
                        //Image scaledImg = bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);

                        imgg = bufferedImage.getScaledInstance(emp_image.getWidth(), emp_image.getHeight(), Image.SCALE_SMOOTH);
                        imageIcon = new ImageIcon(imgg);
                    }

                    emp_image.setText("");
                    emp_image.setIcon(imageIcon);

                } else {
                    emp_image.setText("No Photo");


                }

            }



            if (employee.getPan_ID() != null) {

                panCardPath = employee.getPan_ID();
                System.out.println(panCardPath);

                if (!panCardPath.equalsIgnoreCase("") && panCardPath.length() > 0) {
                    File file = new File(panCardPath);
                    BufferedImage bufferedImage = null;
                    try {
                        bufferedImage = ImageIO.read(file);

                        bufferedImage = resize(bufferedImage, pancard.getWidth(), pancard.getHeight());
                        //Image image = bufferedImage;
                        ImageIcon imageIcon = new ImageIcon(bufferedImage);
                        pancard.setIcon(imageIcon);
                        pancard.setText("");


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    pancard.setText("Pan Card");
                }

            }

            if (employee.getVoter_ID() != null) {

                voterIDPath = employee.getVoter_ID();
                System.out.println(voterIDPath);

                if (!voterIDPath.equalsIgnoreCase("") && voterIDPath.length() > 0) {

                    File file = new File(voterIDPath);
                    BufferedImage bufferedImage = null;
                    try {
                        bufferedImage = ImageIO.read(file);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    bufferedImage = resize(bufferedImage, voter_id.getWidth(), voter_id.getHeight());
                    //Image image = bufferedImage;
                    ImageIcon imageIcon = new ImageIcon(bufferedImage);
                    voter_id.setIcon(imageIcon);
                    voter_id.setText("");
                } else {
                    voter_id.setText("Voter ID Front");
                }
            }

            if (employee.getVoterBack_ID() != null) {

                voterIDBackPath = employee.getVoterBack_ID();
                System.out.println(voterIDPath);

                if (!voterIDBackPath.equalsIgnoreCase("") && voterIDBackPath.length() > 0) {

                    File file = new File(voterIDBackPath);
                    BufferedImage bufferedImage = null;
                    try {
                        bufferedImage = ImageIO.read(file);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    bufferedImage = resize(bufferedImage, voter_id_back.getWidth(), voter_id_back.getHeight());
                    //Image image = bufferedImage;
                    ImageIcon imageIcon = new ImageIcon(bufferedImage);
                    voter_id_back.setIcon(imageIcon);
                    voter_id_back.setText("");
                } else {
                    voter_id_back.setText("Voter ID Back");
                }
            }



            if (employee.getPanchayat() != null) {

                panchayetPath = employee.getPanchayat();
                System.out.println(panchayetPath);


                if (!panchayetPath.equalsIgnoreCase("") && panchayetPath.length() > 0) {

                    File file = new File(panchayetPath);
                    BufferedImage bufferedImage = null;
                    try {
                        bufferedImage = ImageIO.read(file);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    bufferedImage = resize(bufferedImage, panchayet.getWidth(), panchayet.getHeight());
                    //Image image = bufferedImage;
                    ImageIcon imageIcon = new ImageIcon(bufferedImage);
                    panchayet.setIcon(imageIcon);
                    panchayet.setText("");
                } else {
                    panchayet.setText("Panchayet");
                }
            }

            if (employee.getOtherDoc() != null) {

                otherPath = employee.getOtherDoc();
                System.out.println(otherPath);
                if (!otherPath.equalsIgnoreCase("") && otherPath.length() > 0) {

                    File file = new File(otherPath);
                    BufferedImage bufferedImage = null;
                    try {
                        bufferedImage = ImageIO.read(file);

                        bufferedImage = resize(bufferedImage, others.getWidth(), others.getHeight());
                        //Image image = bufferedImage;
                        ImageIcon imageIcon = new ImageIcon(bufferedImage);
                        others.setIcon(imageIcon);
                        others.setText("");
                    } catch (Exception e) {
                        e.printStackTrace();
                        others.setText("No other");
                    }
                } else {
                    others.setText("No other");
                }
            }




            if (employee.getEmp_id() != null) {
                code.setText(employee.getEmp_id());
                jPanelCode.setVisible(true);
            }



            if (employee.getF_name() != null) {

                f_name.setText(employee.getF_name());
            }

            if (employee.getM_name() != null) {
                m_name.setText(employee.getM_name());
            }

            if (employee.getL_name() != null) {
                l_name.setText(employee.getL_name());
            }


            if (employee.getPost() != null) {
                post.setSelectedItem(employee.getPost());
            }

            if (employee.getGuardian_name() != null) {
                g_name.setText(employee.getGuardian_name());
            }

            if ((employee.getMob1() != null)) {
                mob1.setText(employee.getMob1());
            }

            if ((employee.getMob2() != null)) {
                mob2.setText(employee.getMob2());
            }

            if (employee.getAge() != 0) {
                age.setText(employee.getAge().toString());
            }


            if ((employee.getAddress() != null)) {
                address.setText(employee.getAddress());
            }

            if (employee.getLandmark() != null) {
                landmark.setText(employee.getLandmark());
            }

            if (employee.getWayToGo() != null) {
                howtogo.setText(employee.getWayToGo());
            }

            if (employee.getAdm_dt() != null) {

                dt_date.setDate(employee.getAdm_dt());

            }
            if (employee.getPermanentAddress() != null) {

                permanent_add.setText(employee.getPermanentAddress());

            }

            if (employee.getAddress().equalsIgnoreCase(employee.getPermanentAddress())) {
                add_chk.setSelected(true);

            }


        } else {
            initOtherComponents();
            headerText.setText("Add New Employee");
            delete.setVisible(false);
            save.setVisible(true);
            modify.setVisible(false);
            jPanelCode.setVisible(false);
            print_btn.setVisible(false);
        }




        pack();
    }

    public void initOtherComponents() {
        SimpleDateFormat formatter = Common.getDateFormatter();
        dt_date.setDate(new Date());
    }

    public void createInsideElements(String mainImgURL) {

        if (null != mainImgURL) {
            imagePath = mainImgURL;

            File file = new File(mainImgURL);
            BufferedImage bufferedImage = null;
            ImageIcon imageIcon = null;
            Image imgg = null;
            try {
                bufferedImage = ImageIO.read(file);
            } catch (Exception e) {
                e.printStackTrace();
            }


            int h = bufferedImage.getHeight();
            int w = bufferedImage.getWidth();

            int labelHeight = emp_image.getHeight();
            int lebelWidth = emp_image.getWidth();

            if (h < labelHeight && w < lebelWidth) {

                bufferedImage = resize(bufferedImage, bufferedImage.getWidth(), bufferedImage.getHeight());
                imageIcon = new ImageIcon(bufferedImage);

            } else {
                //bufferedImage = resize(bufferedImage, emp_image.getWidth(), emp_image.getHeight());
                //Image scaledImg = bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);

                imgg = bufferedImage.getScaledInstance(emp_image.getWidth(), emp_image.getHeight(), Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(imgg);
            }


            emp_image.setText("");
            emp_image.setIcon(imageIcon);

        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser = new javax.swing.JFileChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        cancel = new javax.swing.JButton();
        save = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        mob1 = new javax.swing.JTextField(10);
        jLabel13 = new javax.swing.JLabel();
        age = new JTextField(10);
        btn_browse = new javax.swing.JButton();
        landmark = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        post = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        howtogo = new javax.swing.JTextField();
        f_name = new javax.swing.JTextField(5);
        emp_image = new javax.swing.JLabel();
        l_name = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btn_capture = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        g_name = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        address = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        m_name = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        mob2 = new javax.swing.JTextField();
        dt_date = new com.toedter.calendar.JDateChooser();
        jPanelCode = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        code = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        add_chk = new javax.swing.JCheckBox();
        jLabel17 = new javax.swing.JLabel();
        permanent_add = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        btn_pan = new javax.swing.JButton();
        pancard = new javax.swing.JLabel();
        voter_id = new javax.swing.JLabel();
        btn_panchayet = new javax.swing.JButton();
        btn_votarid = new javax.swing.JButton();
        panchayet = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        btn_votaridback = new javax.swing.JButton();
        voter_id_back = new javax.swing.JLabel();
        btn_other = new javax.swing.JButton();
        others = new javax.swing.JLabel();
        delete = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        modify = new javax.swing.JButton();
        print_btn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        headerText = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(571, 531));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(857, 800));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        cancel.setText("Cancel");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        save.setText("Save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        ((AbstractDocument)mob1.getDocument()).setDocumentFilter(
            new com.employeemanagement.utility.NumericDocumentFilter());

        jLabel13.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel13.setText("Date of Addmission");

        btn_browse.setText("Browse");
        btn_browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_browseActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel10.setText("Land Mark");

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel5.setText("Gaurdian Name");

        post.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nurse", "12 hours", "24 hours" }));

        jLabel12.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel12.setText("Mobile No 1");

        ((AbstractDocument) f_name.getDocument()).setDocumentFilter(new StringDocumentFilter());
        f_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                f_nameActionPerformed(evt);
            }
        });
        f_name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                f_nameKeyPressed(evt);
            }
        });

        emp_image.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        emp_image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        emp_image.setText("Photo");
        emp_image.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        ((AbstractDocument) l_name.getDocument()).setDocumentFilter(new StringDocumentFilter());
        l_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                l_nameActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel2.setText("Employee Image");

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel3.setText("First Name");

        btn_capture.setText("Capture");
        btn_capture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_captureActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel4.setText("Middle Name");

        g_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                g_nameActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel7.setText("Age");

        jLabel11.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel11.setText("How To Go");

        jLabel14.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel14.setText("Post");

        jLabel9.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel9.setText("Address");

        jLabel6.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel6.setText("Last Name");

        ((AbstractDocument) m_name.getDocument()).setDocumentFilter(new StringDocumentFilter());

        jLabel21.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel21.setText("Mobile No 2");

        dt_date.setDateFormatString("dd MMM, yyyy");

        jPanelCode.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setText("Emp Code");

        code.setEditable(false);
        code.setBackground(new java.awt.Color(102, 255, 255));
        code.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        javax.swing.GroupLayout jPanelCodeLayout = new javax.swing.GroupLayout(jPanelCode);
        jPanelCode.setLayout(jPanelCodeLayout);
        jPanelCodeLayout.setHorizontalGroup(
            jPanelCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCodeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(code, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
        );
        jPanelCodeLayout.setVerticalGroup(
            jPanelCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(code))
        );

        jLabel15.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel15.setText("Present Address :");

        jLabel16.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel16.setText("Permanent Address :");

        add_chk.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        add_chk.setText("Same As Above");
        add_chk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_chkActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel17.setText("Address");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(56, 56, 56)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(howtogo, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(landmark, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel13))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(permanent_add, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(mob1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(dt_date, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(post, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(mob2))))))
                        .addGap(0, 5, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(btn_capture, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_browse, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(emp_image, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(g_name, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(age, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(l_name, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                                    .addComponent(m_name)
                                    .addComponent(f_name))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanelCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(add_chk)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(f_name))
                    .addComponent(jPanelCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(m_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(l_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(g_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(age, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(landmark, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(howtogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(add_chk)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(permanent_add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(mob1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mob2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(post, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dt_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(emp_image, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_capture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_browse))
                .addContainerGap())
        );

        ((AbstractDocument)age.getDocument()).setDocumentFilter(
            new com.employeemanagement.utility.NumericDocumentFilter());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        btn_pan.setText("Browse");
        btn_pan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_panActionPerformed(evt);
            }
        });

        pancard.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        pancard.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pancard.setText("Pan Card");
        pancard.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pancard.setMaximumSize(new java.awt.Dimension(63, 19));
        pancard.setMinimumSize(new java.awt.Dimension(63, 19));
        pancard.setPreferredSize(new java.awt.Dimension(63, 19));

        voter_id.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        voter_id.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        voter_id.setText("Voter ID Front");
        voter_id.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btn_panchayet.setText("Browse");
        btn_panchayet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_panchayetActionPerformed(evt);
            }
        });

        btn_votarid.setText("Browse");
        btn_votarid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_votaridActionPerformed(evt);
            }
        });

        panchayet.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        panchayet.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panchayet.setText("Panchayet");
        panchayet.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel19.setBackground(new java.awt.Color(255, 255, 204));
        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("Upload Scaned Photo :");

        btn_votaridback.setText("Browse");
        btn_votaridback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_votaridbackActionPerformed(evt);
            }
        });

        voter_id_back.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        voter_id_back.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        voter_id_back.setText("Voter ID Back");
        voter_id_back.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btn_other.setText("Browse");
        btn_other.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_otherActionPerformed(evt);
            }
        });

        others.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        others.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        others.setText("Others");
        others.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(voter_id, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_votarid, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(voter_id_back, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_votaridback, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addComponent(btn_panchayet, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_pan, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                            .addComponent(panchayet, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(26, 26, 26)
                            .addComponent(pancard, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btn_other, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(others, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(voter_id, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(voter_id_back, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_votarid)
                    .addComponent(btn_votaridback))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(panchayet, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pancard, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_panchayet)
                    .addComponent(btn_pan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(others, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_other)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        delete.setText("Delete");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        modify.setText("Modify");
        modify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyActionPerformed(evt);
            }
        });

        print_btn.setText("Print");
        print_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                print_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(modify, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(print_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator1))
                        .addGap(113, 113, 113))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancel)
                    .addComponent(save, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(modify)
                    .addComponent(print_btn))
                .addGap(23, 23, 23))
        );

        jScrollPane1.setViewportView(jPanel1);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        headerText.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        headerText.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(headerText, javax.swing.GroupLayout.PREFERRED_SIZE, 869, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headerText, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 702, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static BufferedImage resize(BufferedImage image, int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();
        return bi;
    }

    private void btn_captureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_captureActionPerformed
        SwingUtilities.invokeLater(new WebCamFunctionalPanel(mainFrame, this));

        //imagePath = mainFrame.getImageURL();
        //System.out.println("Image Path : "+ imagePath);
    }//GEN-LAST:event_btn_captureActionPerformed

    private void btn_browseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_browseActionPerformed

        fd = new FileDialog(new Frame(), "Choose a file", FileDialog.LOAD);
        fd.setVisible(true);
        File filename[] = fd.getFiles();
        if (filename == null) {
            System.out.println("You cancelled the choice");
        } else {
            File file = filename[0];

            ResizableComponent r = new ResizableComponent(file.getAbsolutePath(), this);
            r.setAlwaysOnTop(true);
            r.setVisible(true);
            // r.setAlwaysOnTop(true);


        }



//            int returnVal = 23;
//
//            if (returnVal == JFileChooser.APPROVE_OPTION) {                
//                File file = fileChooser.getSelectedFile();               
//               ResizableComponent r = new ResizableComponent(file.getAbsolutePath(),this);
//               r.setVisible(true);
//            } else {
//               
//            }

    }//GEN-LAST:event_btn_browseActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelActionPerformed

    private void btn_votaridActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_votaridActionPerformed

        fd = new FileDialog(new JFrame(), "Choose a file", FileDialog.LOAD);
        fd.setVisible(true);
        File filename[] = fd.getFiles();
        if (filename == null) {
            System.out.println("You cancelled the choice");
        } else {
            File file = filename[0];
            BufferedImage bufferedImage = null;
            try {
                bufferedImage = ImageIO.read(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
            bufferedImage = resize(bufferedImage, voter_id.getWidth(), voter_id.getHeight());


            ImageIcon icon = new ImageIcon(file.getPath());
            System.out.println("Icon - " + icon.toString());
            //Image image = bufferedImage;
            ImageIcon imageIcon = new ImageIcon(bufferedImage);
            voter_id.setIcon(imageIcon);

            voterIDPath = file.getPath();

            System.out.println("Voter Path : " + voterIDPath);

        }

        //int returnVal = fileChooser.showOpenDialog(EmployeeManipulationFrame.this);

        int returnVal = 23;
        if (returnVal == JFileChooser.APPROVE_OPTION && false) {

            File file = fileChooser.getSelectedFile();
            BufferedImage bufferedImage = null;
            try {
                bufferedImage = ImageIO.read(file);
            } catch (Exception e) {
                e.printStackTrace();
            }

            bufferedImage = resize(bufferedImage, voter_id.getWidth(), voter_id.getHeight());

            System.err.println("Path : " + file.getPath());
            ImageIcon icon = new ImageIcon(file.getPath());
            System.out.println("Icon - " + icon.toString());
            //Image image = bufferedImage;
            ImageIcon imageIcon = new ImageIcon(bufferedImage);
            voter_id.setIcon(imageIcon);

            voterIDPath = file.getPath();

            System.out.println("Voter Path : " + voterIDPath);


            //  log.append("Opening: " + file.getName() + "." + newline);
        } else {
            // log.append("Open command cancelled by user." + newline);
        }
    }//GEN-LAST:event_btn_votaridActionPerformed

    private void btn_panActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_panActionPerformed
        fd = new FileDialog(new JFrame(), "Choose a file", FileDialog.LOAD);
        fd.setVisible(true);
        File filename[] = fd.getFiles();
        if (filename == null) {
            System.out.println("You cancelled the choice");
        } else {
            File file = filename[0];
            BufferedImage bufferedImage = null;

            // FIXME Exception handling is not correct
            try {
                bufferedImage = ImageIO.read(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
            bufferedImage = resize(bufferedImage, pancard.getWidth(), pancard.getHeight());

            System.err.println("Path : " + file.getPath());
            ImageIcon icon = new ImageIcon(file.getPath());
            System.out.println("Icon - " + icon.toString());
            //Image image = bufferedImage;
            ImageIcon imageIcon = new ImageIcon(bufferedImage);
            pancard.setIcon(imageIcon);
            panCardPath = file.getPath();

        }






        int returnVal = 23;
        if (returnVal == JFileChooser.APPROVE_OPTION) {

            File file = fileChooser.getSelectedFile();
            BufferedImage bufferedImage = null;
            try {
                bufferedImage = ImageIO.read(file);
            } catch (Exception e) {
                e.printStackTrace();
            }

            bufferedImage = resize(bufferedImage, pancard.getWidth(), pancard.getHeight());

            System.err.println("Path : " + file.getPath());
            ImageIcon icon = new ImageIcon(file.getPath());
            System.out.println("Icon - " + icon.toString());
            //Image image = bufferedImage;
            ImageIcon imageIcon = new ImageIcon(bufferedImage);
            pancard.setIcon(imageIcon);
            panCardPath = file.getPath();

            System.out.println("Pan Path : " + panCardPath);

            //  log.append("Opening: " + file.getName() + "." + newline);
        } else {
            // log.append("Open command cancelled by user." + newline);
        }
    }//GEN-LAST:event_btn_panActionPerformed

    private void btn_panchayetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_panchayetActionPerformed
        fd = new FileDialog(new JFrame(), "Choose a file", FileDialog.LOAD);
        fd.setVisible(true);
        File filename[] = fd.getFiles();
        if (filename == null) {
            System.out.println("You cancelled the choice");
        } else {
            File file = filename[0];
            BufferedImage bufferedImage = null;

            // FIXME Exception handling is not correct
            try {
                bufferedImage = ImageIO.read(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
            bufferedImage = resize(bufferedImage, panchayet.getWidth(), panchayet.getHeight());

            System.err.println("Path : " + file.getPath());
            ImageIcon icon = new ImageIcon(file.getPath());
            System.out.println("Icon - " + icon.toString());
            //Image image = bufferedImage;
            ImageIcon imageIcon = new ImageIcon(bufferedImage);
            panchayet.setIcon(imageIcon);
            panchayetPath = file.getPath();

        }




        int returnVal = 23;

        if (returnVal == JFileChooser.APPROVE_OPTION) {

            File file = fileChooser.getSelectedFile();
            BufferedImage bufferedImage = null;
            try {
                bufferedImage = ImageIO.read(file);
            } catch (Exception e) {
                e.printStackTrace();
            }


            bufferedImage = resize(bufferedImage, panchayet.getWidth(), panchayet.getHeight());

            System.err.println("Path : " + file.getPath());
            ImageIcon icon = new ImageIcon(file.getPath());
            System.out.println("Icon - " + icon.toString());
            //Image image = bufferedImage;
            ImageIcon imageIcon = new ImageIcon(bufferedImage);
            panchayet.setIcon(imageIcon);
            panchayetPath = file.getPath();

            System.out.println("Panchayet Path : " + panchayetPath);

            //  log.append("Opening: " + file.getName() + "." + newline);
        } else {
            // log.append("Open command cancelled by user." + newline);
        }
    }//GEN-LAST:event_btn_panchayetActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        // TODO add your handling code here:
        int more = JOptionPane.showConfirmDialog(null, "Do you really want to delete ?", "Confirm delete",
                JOptionPane.YES_NO_OPTION);


        if (more == 0) {
            String empCode = code.getText();
            IPersistence ip = Persistence.getInstance();
            boolean flag = ip.deleteEmployee(empCode);

            if (flag) {
                String mesg = "Permanently deleted " + employee.getF_name() + " " + employee.getM_name() + " " + employee.getL_name();
                JOptionPane.showMessageDialog(new JFrame(), mesg, "Dialog", JOptionPane.INFORMATION_MESSAGE);
                if (listFrame != null) {
                    listFrame.reloadTableData();
                }
                mainFrame.reloadTableData();
                mainFrame.reloadTableDataCurrentAssignment();
                mainFrame.reloadTableDataCompletedAssignment();
                this.dispose();
            } else {
                System.out.print("Data not persisted");
                JOptionPane.showMessageDialog(new JFrame(), "Error occured. ", "Dialog", JOptionPane.ERROR_MESSAGE);
            }
        } else {
        }

    }//GEN-LAST:event_deleteActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        // TODO add your handling code here:

        String imageUploadPath = "";
        String votarUploadPath = "";
        String panUploadPath = "";
        String panchayetUploadPath = "";
        String ext = "";
        String source = "";
        String target = "";

        try {

            IPersistence ip = Persistence.getInstance();
           String empID = ip.getEmployeeID(post.getSelectedItem().toString());
         //   String empID = ip.getEmployeeID();
            System.out.print("ID : " + empID);


            if (imagePath.length() > 0 && imagePath != null) {
                ext = imagePath.substring(imagePath.lastIndexOf("."), imagePath.length());
                source = imagePath;
                target = Common.getPhotoDir() + empID + ext;

                System.out.print(" Photo : " + source + " - " + target);;
                Common.copyFile(source, target);
                imageUploadPath = target;
                //File f = new File(source);
                //f.delete();
            }
            if (voterIDPath.length() > 0 && voterIDPath != null) {

                ext = voterIDPath.substring(voterIDPath.lastIndexOf("."), voterIDPath.length());
                source = voterIDPath;
                target = Common.getVoterDir() + empID + ext;
                votarUploadPath = target;
                System.out.print(" voter : " + source + " - " + target);;
                Common.copyFile(source, target);
            }

            if (panCardPath.length() > 0 && panCardPath != null) {

                ext = panCardPath.substring(panCardPath.lastIndexOf("."), panCardPath.length());
                source = panCardPath;
                target = Common.getPanDir() + empID + ext;

                System.out.print(" pan : " + source + " - " + target);;
                panUploadPath = target;
                Common.copyFile(source, target);

            }

            if (panchayetPath.length() > 0 && panchayetPath != null) {

                ext = panchayetPath.substring(panchayetPath.lastIndexOf("."), panchayetPath.length());
                source = panchayetPath;
                target = Common.getPanchayetDir() + empID + ext;
                panchayetUploadPath = target;
                System.out.print(" panchayet : " + source + " - " + target);;
                Common.copyFile(source, target);
            }

            if (voterIDBackPath.length() > 0 && voterIDBackPath != null) {

                ext = voterIDBackPath.substring(voterIDBackPath.lastIndexOf("."), voterIDBackPath.length());
                source = voterIDBackPath;
                target = Common.getVoterBackDir() + empID + ext;
                voterIDBackPath = target;
                System.out.print(" panchayet : " + source + " - " + target);;
                Common.copyFile(source, target);
            }

            if (otherPath.length() > 0 && otherPath != null) {

                ext = otherPath.substring(otherPath.lastIndexOf("."), otherPath.length());
                source = otherPath;
                target = Common.getOtherDir() + empID + ext;
                otherPath = target;
                System.out.print(" other : " + source + " - " + target);;
                Common.copyFile(source, target);
            }



            Employee emp = new Employee();
            if (f_name.getText().trim().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(new JFrame(), "Put first name !!!!", "Dialog",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            emp.setF_name(f_name.getText());
            emp.setM_name(m_name.getText());
            emp.setL_name(l_name.getText());
            emp.setEmp_id(empID);

            int ages = 0;
            if (age.getText().equalsIgnoreCase("")) {
            } else {
                ages = Integer.parseInt(age.getText());
            }

            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date dt = dt_date.getDate();

            df.format(dt);

            System.out.println("Admissin date -->: " + dt);
            emp.setAdm_dt(dt);


            emp.setAge(ages);
            emp.setAddress(address.getText());
            emp.setLandmark(landmark.getText());
            emp.setWayToGo(howtogo.getText());
            emp.setGuardian_name(g_name.getText());
            emp.setPost(post.getSelectedItem().toString());

            emp.setIsActive(true);
            emp.setMob1(mob1.getText());
            emp.setMob2(mob2.getText());
            emp.setRemark("remarks ");
            emp.setPanchayat(panchayetUploadPath);
            emp.setPhoto_ID(imageUploadPath);
            emp.setVoter_ID(votarUploadPath);
            emp.setPan_ID(panUploadPath);
            emp.setVoterBack_ID(voterIDBackPath);
            emp.setOtherDoc(otherPath);
            emp.setPermanentAddress(permanent_add.getText());

            Boolean flag = ip.saveEmployee(emp);

            if (flag) {
                System.out.print("Data persisted succesfully");
                JOptionPane.showMessageDialog(null, "Employee added");
                mainFrame.reloadTableData();
                imagePath = "";
            } else {
                System.out.print("Data not persisted");
                JOptionPane.showMessageDialog(new JFrame(), "Data not saved!!!!!", "Dialog", JOptionPane.ERROR_MESSAGE);
            }
            this.dispose();
        } catch (Exception e) {

            e.printStackTrace();
            System.out.println("Exception ");

        }
    }//GEN-LAST:event_saveActionPerformed

    private void modifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyActionPerformed

        String imageUploadPath = "";
        String votarUploadPath = "";
        String panUploadPath = "";
        String panchayetUploadPath = "";
        String otherUploadPath = "";
        String ext = "";
        String source = "";
        String target = "";


        try {

            IPersistence ip = Persistence.getInstance();
            Employee emp = new Employee();

            String empID = code.getText();

            if (imagePath.length() > 0 && imagePath != null) {
                ext = imagePath.substring(imagePath.lastIndexOf("."), imagePath.length());
                source = imagePath;
                if (!source.trim().equalsIgnoreCase(employee.getPhoto_ID().trim())) {
                    target = Common.getPhotoDir() + empID + ext;
                    imageUploadPath = target;
                    System.out.print(" image : " + source + " - " + target);;
                    Common.copyFile(source, target);
                } else {
                    imageUploadPath = employee.getPhoto_ID();
                }


            }

            if (voterIDPath.length() > 0 && voterIDPath != null) {
                ext = voterIDPath.substring(voterIDPath.lastIndexOf("."), voterIDPath.length());
                source = voterIDPath;
                if (!source.trim().equalsIgnoreCase(employee.getVoter_ID().trim())) {
                    target = Common.getVoterDir() + empID + ext;
                    votarUploadPath = target;
                    System.out.print(" voter : " + source + " - " + target);;
                    Common.copyFile(source, target);
                } else {
                    votarUploadPath = employee.getVoter_ID();
                }
            }

            if (voterIDBackPath.length() > 0 && voterIDBackPath != null) {
                ext = voterIDBackPath.substring(voterIDBackPath.lastIndexOf("."), voterIDBackPath.length());
                source = voterIDBackPath;
                if (!source.trim().equalsIgnoreCase(employee.getVoterBack_ID().trim())) {
                    target = Common.getVoterBackDir() + empID + ext;
                    voterIDBackPath = target;
                    System.out.print(" voter : " + source + " - " + target);;
                    Common.copyFile(source, target);
                } else {
                    voterIDBackPath = employee.getVoterBack_ID();
                }
            }

            if (panCardPath.length() > 0 && panCardPath != null) {

                ext = panCardPath.substring(panCardPath.lastIndexOf("."), panCardPath.length());
                source = panCardPath;
                if (!source.trim().equalsIgnoreCase(employee.getPan_ID().trim())) {
                    target = Common.getPanDir() + empID + ext;
                    panUploadPath = target;
                    System.out.print(" pan : " + source + " - " + target);
                    Common.copyFile(source, target);
                } else {
                    panUploadPath = employee.getPan_ID();
                }



            }

            if (panchayetPath.length() > 0 && panchayetPath != null) {
                ext = panchayetPath.substring(panchayetPath.lastIndexOf("."), panchayetPath.length());
                source = panchayetPath;
                if (!source.trim().equalsIgnoreCase(employee.getPanchayat().trim())) {
                    target = Common.getPanchayetDir() + empID + ext;
                    panchayetUploadPath = target;
                    System.out.print(" panchayet : " + source + " - " + target);;
                    Common.copyFile(source, target);
                } else {
                    panchayetUploadPath = employee.getPanchayat();
                }

            }

            if (otherPath.length() > 0 && otherPath != null) {
                ext = otherPath.substring(otherPath.lastIndexOf("."), otherPath.length());
                source = otherPath;
                if (!source.trim().equalsIgnoreCase(employee.getOtherDoc().trim())) {
                    target = Common.getOtherDir() + empID + ext;
                    otherUploadPath = target;
                    System.out.print(" other : " + source + " - " + target);;
                    Common.copyFile(source, target);
                } else {
                    otherUploadPath = employee.getOtherDoc();
                }

            }


            if (f_name.getText().trim().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(new JFrame(), "Put first name !!!!", "Dialog",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            emp.setF_name(f_name.getText());
            emp.setM_name(m_name.getText());
            emp.setL_name(l_name.getText());
            emp.setEmp_id(empID);

            int ages = 0;
            if (age.getText().equalsIgnoreCase("")) {
            } else {
                ages = Integer.parseInt(age.getText());
            }


            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date dt = dt_date.getDate();
            df.format(dt);
            emp.setAdm_dt(dt);


            emp.setAge(ages);
            emp.setAddress(address.getText());
            emp.setLandmark(landmark.getText());
            emp.setWayToGo(howtogo.getText());
            emp.setGuardian_name(g_name.getText());
            emp.setPost(post.getSelectedItem().toString());

            emp.setIsActive(employee.getIsActive());
            emp.setMob1(mob1.getText());
            emp.setMob2(mob2.getText());
            emp.setRemark(employee.getRemark());
            emp.setPanchayat(panchayetUploadPath);
            emp.setPhoto_ID(imageUploadPath);
            emp.setVoter_ID(votarUploadPath);
            emp.setPan_ID(panUploadPath);
            emp.setVoterBack_ID(voterIDBackPath);
            emp.setOtherDoc(otherUploadPath);
            emp.setPermanentAddress(permanent_add.getText());


            Boolean flag = ip.updateEmployee(emp);

            if (flag) {
                System.out.print("Data persisted succesfully");
                JOptionPane.showMessageDialog(new JFrame(), "Employee data saved susscessfully", "Dialog", JOptionPane.INFORMATION_MESSAGE);
                if (listFrame != null) {
                    listFrame.reloadTableData();
                }
                mainFrame.reloadTableData();
            } else {
                System.out.print("Data not persisted");
                JOptionPane.showMessageDialog(new JFrame(), "Data not persisted", "Dialog", JOptionPane.ERROR_MESSAGE);
            }

            this.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_modifyActionPerformed

    private void print_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_print_btnActionPerformed
        // TODO add your handling code here:
        String empCode = code.getText();

        if (!empCode.trim().equalsIgnoreCase("")) {

            IPersistence ip = Persistence.getInstance();
            Employee emp = ip.getEmployee(empCode);


            // EmployeeDetailView empDetailView = new EmployeeDetailView(emp,this);
            // empDetailView.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
            // empDetailView.setVisible(true);
            // empDetailView.pack();   

            PrintEmployeeDetail ped = new PrintEmployeeDetail(emp);
            ped.setVisible(true);

        }

    }//GEN-LAST:event_print_btnActionPerformed

    private void btn_votaridbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_votaridbackActionPerformed
        // TODO add your handling code here:
        fd = new FileDialog(new JFrame(), "Choose a file", FileDialog.LOAD);
        fd.setVisible(true);
        File filename[] = fd.getFiles();
        if (filename == null) {
            System.out.println("You cancelled the choice");
        } else {
            File file = filename[0];
            BufferedImage bufferedImage = null;

            // FIXME Exception handling is not correct
            try {
                bufferedImage = ImageIO.read(file);
            } catch (Exception e) {
                e.printStackTrace();
            }

            bufferedImage = resize(bufferedImage, voter_id_back.getWidth(), voter_id_back.getHeight());

            System.err.println("Path : " + file.getPath());
            ImageIcon icon = new ImageIcon(file.getPath());
            System.out.println("Icon - " + icon.toString());
            //Image image = bufferedImage;
            ImageIcon imageIcon = new ImageIcon(bufferedImage);
            voter_id_back.setIcon(imageIcon);

            voterIDBackPath = file.getPath();

            System.out.println("Voter Back Path : " + voterIDBackPath);

        }

        int returnVal = 23;
        if (returnVal == JFileChooser.APPROVE_OPTION) {

            File file = fileChooser.getSelectedFile();
            BufferedImage bufferedImage = null;
            try {
                bufferedImage = ImageIO.read(file);
            } catch (Exception e) {
                e.printStackTrace();
            }

            bufferedImage = resize(bufferedImage, voter_id_back.getWidth(), voter_id_back.getHeight());

            System.err.println("Path : " + file.getPath());
            ImageIcon icon = new ImageIcon(file.getPath());
            System.out.println("Icon - " + icon.toString());
            //Image image = bufferedImage;
            ImageIcon imageIcon = new ImageIcon(bufferedImage);
            voter_id_back.setIcon(imageIcon);

            voterIDBackPath = file.getPath();

            System.out.println("Voter Back Path : " + voterIDBackPath);


            //  log.append("Opening: " + file.getName() + "." + newline);
        } else {
            // log.append("Open command cancelled by user." + newline);
        }
    }//GEN-LAST:event_btn_votaridbackActionPerformed

    private void btn_otherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_otherActionPerformed
        // TODO add your handling code here:

        fd.setVisible(true);
        File filename[] = fd.getFiles();
        if (filename == null) {
            System.out.println("You cancelled the choice");
        } else {
            File file = filename[0];
            BufferedImage bufferedImage = null;

            // FIXME Exception handling is not correct
            try {
                bufferedImage = ImageIO.read(file);
            } catch (Exception e) {
                e.printStackTrace();
            }

            bufferedImage = resize(bufferedImage, others.getWidth(), others.getHeight());

            System.err.println("Path : " + file.getPath());
            ImageIcon icon = new ImageIcon(file.getPath());
            System.out.println("Icon - " + icon.toString());
            //Image image = bufferedImage;
            ImageIcon imageIcon = new ImageIcon(bufferedImage);
            others.setIcon(imageIcon);

            otherPath = file.getPath();

            System.out.println("Voter Back Path : " + otherPath);

        }
        int returnVal = 23;
        if (returnVal == JFileChooser.APPROVE_OPTION) {

            File file = fileChooser.getSelectedFile();
            BufferedImage bufferedImage = null;
            try {
                bufferedImage = ImageIO.read(file);
            } catch (Exception e) {
                e.printStackTrace();
            }

            bufferedImage = resize(bufferedImage, others.getWidth(), others.getHeight());

            System.err.println("Path : " + file.getPath());
            ImageIcon icon = new ImageIcon(file.getPath());
            System.out.println("Icon - " + icon.toString());
            //Image image = bufferedImage;
            ImageIcon imageIcon = new ImageIcon(bufferedImage);
            others.setIcon(imageIcon);

            otherPath = file.getPath();

            System.out.println("Voter Back Path : " + otherPath);


            //  log.append("Opening: " + file.getName() + "." + newline);
        } else {
            // log.append("Open command cancelled by user." + newline);
        }


    }//GEN-LAST:event_btn_otherActionPerformed

    private void add_chkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_chkActionPerformed
        // TODO add your handling code here:

        //address
        boolean flag = add_chk.isSelected();
        if (flag) {
            permanent_add.setText(address.getText());
            permanent_add.setEditable(false);
        } else {
            permanent_add.setText("");
            permanent_add.setEditable(true);

        }
    }//GEN-LAST:event_add_chkActionPerformed

    private void f_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_f_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_f_nameActionPerformed

    private void l_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_l_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_l_nameActionPerformed

    private void g_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_g_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_g_nameActionPerformed

    private void f_nameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_f_nameKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_f_nameKeyPressed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox add_chk;
    private javax.swing.JTextField address;
    private javax.swing.JTextField age;
    private javax.swing.JButton btn_browse;
    private javax.swing.JButton btn_capture;
    private javax.swing.JButton btn_other;
    private javax.swing.JButton btn_pan;
    private javax.swing.JButton btn_panchayet;
    private javax.swing.JButton btn_votarid;
    private javax.swing.JButton btn_votaridback;
    private javax.swing.JButton cancel;
    private javax.swing.JTextField code;
    private javax.swing.JButton delete;
    private com.toedter.calendar.JDateChooser dt_date;
    private javax.swing.JLabel emp_image;
    private javax.swing.JTextField f_name;
    private javax.swing.JTextField g_name;
    private javax.swing.JLabel headerText;
    private javax.swing.JTextField howtogo;
    private javax.swing.JFileChooser jFileChooser;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanelCode;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField l_name;
    private javax.swing.JTextField landmark;
    private javax.swing.JTextField m_name;
    private javax.swing.JTextField mob1;
    private javax.swing.JTextField mob2;
    private javax.swing.JButton modify;
    private javax.swing.JLabel others;
    private javax.swing.JLabel pancard;
    private javax.swing.JLabel panchayet;
    private javax.swing.JTextField permanent_add;
    private javax.swing.JComboBox post;
    private javax.swing.JButton print_btn;
    private javax.swing.JButton save;
    private javax.swing.JLabel voter_id;
    private javax.swing.JLabel voter_id_back;
    // End of variables declaration//GEN-END:variables
    JLabel jLabel;
    private JFileChooser fileChooser;
    FileDialog fd;
}
