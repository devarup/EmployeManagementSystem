/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empmanagement.screens;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException; 
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{

    private Image backgroundImage;

    public ImagePanel(Image backgroundImage) {
        super();
        this.backgroundImage = backgroundImage;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);        
        g.drawImage(this.backgroundImage, 0, 0, null);
    }

}
