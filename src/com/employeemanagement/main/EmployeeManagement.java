/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.employeemanagement.main;

import javax.swing.UIManager;

import com.empmanagement.screens.MainFrame;

/**
 *
 * @author arup
 */
public class EmployeeManagement {

    /**
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
	/* Set the Nimbus look and feel */
	// <editor-fold defaultstate="collapsed"
	// desc=" Look and feel setting code (optional) ">
	/*
	 * If Nimbus (introduced in Java SE 6) is not available, stay with the
	 * default look and feel. For details see
	 * http://download.oracle.com/javase
	 * /tutorial/uiswing/lookandfeel/plaf.html
	 */
	try {
	    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
		    .getInstalledLookAndFeels()) {
		if ("Windows".equals(info.getName())) {
		    javax.swing.UIManager.setLookAndFeel(info.getClassName());
		    break;
		}
	    }
	} catch (ClassNotFoundException ex) {
	    java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(
		    java.util.logging.Level.SEVERE, null, ex);
	} catch (InstantiationException ex) {
	    java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(
		    java.util.logging.Level.SEVERE, null, ex);
	} catch (IllegalAccessException ex) {
	    java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(
		    java.util.logging.Level.SEVERE, null, ex);
	} catch (javax.swing.UnsupportedLookAndFeelException ex) {
	    java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(
		    java.util.logging.Level.SEVERE, null, ex);
	}
	// </editor-fold>

	try {
	    // for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
	    // {
	    // System.out.print(" look : "+info.getName() +"\n");
	    // String lookNfeel;
	    // // lookNfeel = "Metal";
	    // lookNfeel = "Nimbus";
	    // // lookNfeel = "CDE/Motif";
	    // // lookNfeel = "GTK+";
	    // if (lookNfeel.equals(info.getName())) {
	    // UIManager.setLookAndFeel(info.getClassName());
	    // break;
	    // }
	    //
	    // }

	    UIManager.setLookAndFeel("com.nilo.plaf.nimrod.NimRODLookAndFeel");
	    // UIManager.setLookAndFeel("org.netbeans.lib.awtextra.AbsoluteConstraints");
	    // UIManager.setLookAndFeel("napkin.NapkinLookAndFeel");
	    // UIManager.setLookAndFeel(" com.jgoodies.looks.windows.WindowsLookAndFeel");
	    // UIManager.setLookAndFeel("com.jtattoo.plaf.AbstractLookAndFeel");
	    // UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");

	    // /
	    // UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
	    // UIManager.setLookAndFeel("net.sourceforge.napkinlaf.NapkinLookAndFeel");

	    /* Create and display the form */
	    java.awt.EventQueue.invokeLater(new Runnable() {
		public void run() {
		    // WebLookAndFeel.install ();
		    new MainFrame().setVisible(true);
		}
	    });
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
