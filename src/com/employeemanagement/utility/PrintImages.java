/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.employeemanagement.utility;

import java.awt.print.Printable;
import java.awt.Graphics;  
import java.awt.Graphics2D;  
import java.awt.geom.AffineTransform;  
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;  
import java.awt.print.PageFormat;  
import java.awt.print.Printable;  
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
      
import java.util.ArrayList;  
import java.util.Collection;  
import javax.imageio.ImageIO;
      
  
import javax.print.Doc;  
import javax.print.DocFlavor;  
import javax.print.DocPrintJob;  
import javax.print.PrintException;  
import javax.print.PrintService;  
import javax.print.PrintServiceLookup;  
import javax.print.SimpleDoc;  
import javax.print.attribute.HashPrintRequestAttributeSet;  
import javax.print.attribute.PrintRequestAttributeSet;  
import javax.print.attribute.standard.JobName;  
import javax.print.attribute.standard.MediaSizeName;

/**
 *
 * @author arup
 */
@SuppressWarnings("rawtypes") 
public class PrintImages implements Printable {
    
           
    protected ArrayList imageList = new ArrayList<Object>();  
  
    public PrintImages()   
    {  
  
    }  
          
    @SuppressWarnings("unchecked")  
    public void print(Collection images, String printerName, String requestNumber)   
    {  
        this.imageList.clear();  
        this.imageList.addAll(images);  
        printData(printerName, requestNumber);  
    }  
  
    protected void printData(String printerName, String requestNumber)   
    {  
        String printer = printerName;             
              
        DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PRINTABLE;  
        PrintService printService[] = PrintServiceLookup.lookupPrintServices(null, null);  
        PrintService service = null;  
  
              
        for (int i = 0; i < printService.length; i++)  
        {  
            String p_name = printService[i].getName();  
           // if (p_name.equals(printer))  
          //  {  
                service = printService[i];  
          //  }  
        }             
              int k=0;
  
        try   
        {  
            DocPrintJob pj = service.createPrintJob();  
            PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();  
            aset.add(new JobName(String.valueOf(k++), null));  
            aset.add(MediaSizeName.NA_LETTER);  
            Doc doc = new SimpleDoc(this, flavor, null);  
            pj.print(doc, aset);  
        }   
        catch (PrintException pe)   
        {  
            System.err.println(pe);  
        }  
    }  
  
    public int print(Graphics g, PageFormat f, int pageIndex)   
    {  
        if (pageIndex >= imageList.size())   
        {  
            return Printable.NO_SUCH_PAGE;  
        }  
        RenderedImage image = (RenderedImage) imageList.get(pageIndex);  
  
        if (image != null) {  
            Graphics2D g2 = (Graphics2D) g;  
            g2.translate(f.getImageableX(), f.getImageableY());  
  
            int imgWidth = (int)image.getWidth();  
            int imgHeight = (int)image.getHeight();  
            double xRatio = (double) f.getImageableWidth() / (double) imgWidth;  
            double yRatio = (double) f.getImageableHeight() / (double) imgHeight;  
  
            g2.scale(xRatio, yRatio);  
  
            AffineTransform at = AffineTransform.getTranslateInstance(f.getImageableX(), f.getImageableY());  
            g2.drawRenderedImage(image, at);  
                  
            return Printable.PAGE_EXISTS;  
            }   
        else   
        {  
            return Printable.NO_SUCH_PAGE;  
        }  
    }  
    
    
    public static void main(String args[]) throws FileNotFoundException, IOException{
    ArrayList images = new ArrayList();  
    
    String path = "/home/arup/LATEST/PAN/RISEMP10.jpg";
    FileInputStream fis = new FileInputStream((path.toString()));  
                    BufferedImage ri = ImageIO.read(fis);  
                    
                    
                    images.add(ri);  
                      
                    fis.close();  
                    ri.flush();                   
                    ri = null;  

    path = "/home/arup/LATEST/PAN/RISEMP21.png";                
     fis = new FileInputStream((path.toString()));  
        ri = ImageIO.read(fis);  
        images.add(ri);  

            fis.close();  
            ri.flush();                   
            ri = null;    
            
            new PrintImages().print(images, "", "");     
                    
    
    
    
    
    }
    
}
