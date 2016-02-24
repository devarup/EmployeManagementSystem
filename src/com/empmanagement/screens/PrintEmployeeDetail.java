/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empmanagement.screens;

import com.employeemanagement.common.Common;
import com.employeemanagement.models.Employee;
import com.employeemanagement.utility.PrintEmployee;
import com.empmanagement.test.MyPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.PrinterJob;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

/**
 *
 * @author arup
 */
public class PrintEmployeeDetail extends JFrame {

    private static final long serialVersionUID = 1L;
    private BorderLayout borderLayout1 = new BorderLayout();
    private FlowLayout flow = new FlowLayout();
    private Font font = new Font("Tahoma", 0, 11);
    private int panelsNo = 6;
    private Dimension buttonDimension = new Dimension(120, 25);
    private JPanel centerPanel = new JPanel();
    private JScrollPane scroll = new JScrollPane(centerPanel);
    private PrintEmployee[] previewPanel = null;
    private JPanel operationsPanel = new JPanel();
    private JCheckBox chk_biodata = new JCheckBox("Biodata");
    private JCheckBox chk_voter_front = new JCheckBox("Voter(front)");
    private JCheckBox chk_voter_back = new JCheckBox("Voter(back)");
    private JCheckBox chk_pan = new JCheckBox("Pan");
    private JCheckBox chk_panchayet = new JCheckBox("Panchayet");
    private JCheckBox chk_other = new JCheckBox("Other");
    public Employee employee = null;
    public static final Dimension A4_SIZE_DIMENTION = new Dimension(595, 842);
    // private JButton nextButton = new JButton("Next");
    // private JButton previousButton = new JButton("Previous");
    private JButton printButton = new JButton("Print");

    public PrintEmployeeDetail(Employee emp) {

        try {

            // this.panelsNo = panelsNo;
            // employee = emp;
            previewPanel = new PrintEmployee[this.panelsNo];

            jbInit(emp);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    private void jbInit(Employee emp) throws Exception {

        flow.setHgap(20);
        flow.setVgap(20);
        centerPanel.setLayout(flow);
        centerPanel.setBackground(new Color(51, 51, 51));
        centerPanel.setPreferredSize(new Dimension(400, panelsNo * (842 + 40)));
        String filePath = "";


        for (int i = 0; i < panelsNo; i++) {
            try {
                previewPanel[i] = new PrintEmployee();
                if (i == 0) {
                    MyPanel mp = new MyPanel(emp);
                    mp.setVisible(true);
                    previewPanel[i].add(mp);
                } else {
                    File f1 = null;;
                    if (i == 1) {
                        f1 = new File(emp.getVoter_ID());
                    } else if (i == 2) {
                        f1 = new File(emp.getVoterBack_ID());
                    } else if (i == 3) {
                        f1 = new File(emp.getPanchayat());
                    } else if (i == 4) {

                        f1 = new File(emp.getPan_ID());

                    } else if (i == 5) {
                        f1 = new File(emp.getOtherDoc());

                    } else {
                    }

                    if (f1.exists()) {

                        previewPanel[i].add(getImage(f1));

                    } else {
                    }
                }
                this.centerPanel.add(previewPanel[i], null);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

        printButton.setForeground(Color.BLACK.darker());
        printButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                printActionPerformed(evt);

            }
        });

//        testChk.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//
//                if (testChk.isSelected()) {
//                    System.out.println("true ...........");
//                    previewPanel[1].setVisible(false);
//                } else {
//                    System.out.println("false........");
//
//                    previewPanel[1].setVisible(true);
//
//                }
//
//            }
//        });


        operationsPanel.setPreferredSize(new Dimension(10, 40));

        //  operationsPanel.add(previousButton, null);

        // operationsPanel.add(nextButton, null);

//        operationsPanel.add(printButton, null);
//        Component comps[] = operationsPanel.getComponents();
//
//        for (Component comp : comps) {
//
//            ((JButton) comp).setFont(font);
//
//            ((JButton) comp).setPreferredSize(buttonDimension);
//
//        }
        operationsPanel.add(chk_biodata, null);
        operationsPanel.add(chk_voter_front, null);
        operationsPanel.add(chk_voter_back, null);
        operationsPanel.add(chk_panchayet, null);
        operationsPanel.add(chk_pan, null);
        operationsPanel.add(chk_other, null);
        operationsPanel.add(printButton, null);


        this.setTitle("Print");
        this.getContentPane().setLayout(borderLayout1);
        this.setBounds(new Rectangle(10, 10, 650, 930));
        this.getContentPane().add(scroll, BorderLayout.CENTER);
        this.getContentPane().add(operationsPanel, BorderLayout.NORTH);
    }

    public JLabel getImage(File file) {

        JLabel jl = new JLabel();
        jl.setVisible(true);
        jl.setPreferredSize(A4_SIZE_DIMENTION);
        jl.setHorizontalAlignment(SwingConstants.CENTER);

        BufferedImage bufferedImage = null;
        ImageIcon imageIcon = null;
        Image imgg = null;
        try {

            bufferedImage = ImageIO.read(file);
            int h = bufferedImage.getHeight();
            int w = bufferedImage.getWidth();


            if (h < A4_SIZE_DIMENTION.getHeight() && w < A4_SIZE_DIMENTION.getWidth()) {

                bufferedImage = Common.resize(bufferedImage, w, h);
                imageIcon = new ImageIcon(bufferedImage);

            } else {

                imgg = bufferedImage.getScaledInstance((int) A4_SIZE_DIMENTION.getWidth() - 20, (int) A4_SIZE_DIMENTION.getHeight(), Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(imgg);
            }

            jl.setText("");
            jl.setIcon(imageIcon);
            jl.setVisible(true);


        } catch (Exception e) {
        }


        return jl;
    }

    private void printActionPerformed(java.awt.event.ActionEvent event) {

        PrinterJob printJob = PrinterJob.getPrinterJob();
        PageFormat pf = printJob.defaultPage();

        pf.setOrientation(0);
        pf.setOrientation(0);

        if ((this.chk_biodata.isSelected()) || (this.chk_voter_front.isSelected()) || (this.chk_voter_back.isSelected()) || (this.chk_pan.isSelected()) || (this.chk_panchayet.isSelected()) || (this.chk_other.isSelected())) {
            printJob.printDialog();
            try {
                if (this.chk_biodata.isSelected()) {
                    printJob.setPrintable(this.previewPanel[0]);
                    printJob.print();
                }

                if (this.chk_voter_front.isSelected()) {
                    printJob.setPrintable(this.previewPanel[1]);
                    printJob.print();
                }

                if (this.chk_voter_back.isSelected()) {
                    printJob.setPrintable(this.previewPanel[2]);
                    printJob.print();
                }
              

                if (this.chk_panchayet.isSelected()) {
                    printJob.setPrintable(this.previewPanel[3]);
                    printJob.print();
                }

                if (this.chk_pan.isSelected()) {
                    printJob.setPrintable(this.previewPanel[4]);
                    printJob.print();
                }
                
                if (this.chk_other.isSelected()) {
                    printJob.setPrintable(this.previewPanel[5]);
                    printJob.print();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            JOptionPane.showMessageDialog(new JFrame(), "Nothing to print", "Dialog", 1);
        }
    }
}
