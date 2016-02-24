/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.employeemanagement.utility;

/**
 *
 * @author arup
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;

public class ImagePreview extends JComponent implements PropertyChangeListener
{
   // Dimensions of image preview's preferred size.

   final static int WIDTH = 150;
   final static int HEIGHT = 100;

   // Reference to ImageIcon whose image is displayed in accessory area. If
   // null reference, nothing is displayed in accessory area.

   private ImageIcon icon;

   // Create ImagePreview component to serve as a file chooser accessory.

   public ImagePreview(JFileChooser fc)
   {
      // Register a property change listener with the file chooser so that
      // the ImagePreview component is made aware of file chooser events (such
      // as a user selecting a file).

      fc.addPropertyChangeListener(this);

      // Set the ImagePreview's dimensions to accommodate image thumbnails.
      // The specified values determine the overall size of the file chooser.

      setPreferredSize(new Dimension(WIDTH, HEIGHT));
   }

   // Paint the ImagePreview component in response to a repaint() method call.

   @Override
   protected void paintComponent(Graphics g)
   {
      // When this method is called, the background has already been painted.
      // If icon is null, do nothing. This action causes the current image
      // thumbnail to disappear when the user selects a directory, for example.

      if (icon != null)
      {
         // Paint a white background behind the pixels so that a GIF image's
         // transparent pixel causes white (instead of gray or whatever color
         // is appropriate for this look and feel) to show through.

         Graphics2D g2d = (Graphics2D) g;
         Rectangle bounds = new Rectangle(0, 0, icon.getIconWidth(),
                                          icon.getIconHeight());
         g.setColor(Color.white);
         g2d.fill(bounds);

         // Paint the image -- (0, 0) is the image's upper-left corner, and
         // the upper-left corner of the accessory area.

         icon.paintIcon(this, g, 0, 0);
      }
   }

   // Respond to property change events sent to this ImagePreview component by
   // the file chooser.

   @Override
   public void propertyChange(PropertyChangeEvent e)
   {                
      // Extract property name from event object.

      String propName = e.getPropertyName();

      // Erase any displayed image if user moves up the directory hierarchy.
                      
      if (JFileChooser.DIRECTORY_CHANGED_PROPERTY.equals(propName))
      {
         icon = null;
         repaint();
         return;
      }                  

      // Display selected file. If a directory is selected, erase any
      // displayed image.

      if (JFileChooser.SELECTED_FILE_CHANGED_PROPERTY.equals(propName))
      {
         // Extract selected file's File object.

         File file = (File) e.getNewValue();

         // If file is null, a directory was selected -- the user is moving
         // between directories. In response, any displayed image in the
         // accessory area must be erased.

         if (file == null)
         {
            icon = null;
            repaint();
            return;
         }

         // Obtain the selected file's icon.

         icon = new ImageIcon(file.getPath());

        

         if (icon.getIconWidth() == -1)
         {
            icon.getImage().flush();
            icon = new ImageIcon(file.getPath());
         }

         // Scale icon to fit accessory area if icon too big.

         if (icon.getIconWidth() > WIDTH)
            icon = new ImageIcon(icon.getImage().getScaledInstance (WIDTH, -1,
                                                          Image.SCALE_DEFAULT));

         // Display image.

         repaint();
      }
   }
}
