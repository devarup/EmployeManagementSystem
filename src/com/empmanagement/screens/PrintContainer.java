/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empmanagement.screens;

import static com.employeemanagement.utility.PrintEmployee.A4_SIZE;
import static com.employeemanagement.utility.PrintEmployee.DEFAULT_SIZE;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author arup
 */
public class PrintContainer extends JPanel implements Printable{
    
     /**
     *
     */
    private static final long serialVersionUID = 1L;
    public static final Dimension A4_SIZE = new Dimension(595, 842);
    public static final Dimension A3_SIZE = new Dimension(842, 595 * 2);
    public static final Dimension A2_SIZE = new Dimension(595 * 2, 842 * 2);
    public static Dimension DEFAULT_SIZE = A4_SIZE;
    private GridLayout grid = new GridLayout(1, 4);
    public int id = 0;

    public PrintContainer() {
        initComponents();
    }

    private void initComponents() {
        grid.setHgap(10);
        grid.setVgap(10);
        this.setMaximumSize(DEFAULT_SIZE);
        this.setMinimumSize(DEFAULT_SIZE);
        this.setPreferredSize(DEFAULT_SIZE);
        this.setBackground(Color.white);
        this.setLayout(grid);
        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        this.setSize(new Dimension(650, 842));

    }

    @Override
    public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException {

        Paper card = pageFormat.getPaper();
        card.setImageableArea(0, 0, 153, 243);
        card.setSize(595, 842);

        pageFormat.setPaper(card);
        pageFormat.setOrientation(PageFormat.PORTRAIT);

        if (pageIndex > 0) {
            return (NO_SUCH_PAGE);
        } // end if
        else {
            Graphics2D g2d = (Graphics2D) g;
            g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
//System.out.println("Coords: " + pageFormat.getImageableX() + ", " + pageFormat.getImageableY());
            g2d.translate(0f, 0f);
//g2d.translate( 0f, -pageIndex * pageHeight );
//g2d.scale( scale, scale );
            this.paint(g2d);
            return (PAGE_EXISTS);
        } // end else
    } // end print()
    
}
