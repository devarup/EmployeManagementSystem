package com.empmanagement.screens;
 
import com.employeemanagement.common.Common;
import com.employeemanagement.utility.CropImage;
import com.empmanagement.testswing.ResizableComponent;
import java.awt.BorderLayout; 
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.lang.Thread.UncaughtExceptionHandler;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamEvent;
import com.github.sarxos.webcam.WebcamListener;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamPicker;
import com.github.sarxos.webcam.WebcamResolution;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 * Proof of concept of how to handle webcam video stream from Java
 * 
 * @author Bartosz Firyn (SarXos)
 */
public class WebCamFunctionalPanel extends JFrame implements  Runnable, ActionListener, WebcamListener, WindowListener, UncaughtExceptionHandler{

	private static final long serialVersionUID = 1L;

	private Webcam webcam = null;
	private WebcamPanel panel = null;
	private WebcamPicker picker = null;
        private JButton capture = null;
        private String imagePath = "";
        Thread t;
        static MainFrame mainFrame;
        static EmployeeManipulationFrame emf;
        public WebCamFunctionalPanel(MainFrame mainFrame,EmployeeManipulationFrame emf){
           this.mainFrame = mainFrame;
           this.emf = emf;
        }
        
         @Override
            public void run() {

                    setTitle("Capture Your Picture");
                    setDefaultCloseOperation(WebCamFunctionalPanel.DISPOSE_ON_CLOSE);
                    setLayout(new BorderLayout());

                    addWindowListener(this);
                    setLocation(200,50);
                    this.setResizable(false);


                    picker = new WebcamPicker();
                    picker.disable();

                    webcam = picker.getSelectedWebcam();

                    if (webcam == null) {
                           // System.out.println("No webcams found...");
                           // System.exit(1);
                          // this.dispose();
                        
                         JFrame j = new JFrame();
                         j.setAlwaysOnTop(true);
                         j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                         j.setVisible(false);
                         JOptionPane.showMessageDialog(j, "No Camera found!!!", "",
                                JOptionPane.INFORMATION_MESSAGE); 
                        
                        
                        
                        
                    }

                    else {
                    webcam.setViewSize(WebcamResolution.VGA.getSize());
                    webcam.addWebcamListener(WebCamFunctionalPanel.this);

                    panel = new WebcamPanel(webcam, false);
                    panel.setFPSDisplayed(true);

                    ImageIcon icon = new ImageIcon(getClass().getResource("/com/empmanagement/screens/capture.png"));
                    capture = new JButton("Capture"); 
                    capture.setIcon(icon);
                    //capture.setSize(getContentPane().getWidth(),500);
                    capture.addActionListener(this);



                    
                    add(picker, BorderLayout.NORTH);
                    JPanel jp = new JPanel();
                    jp.add(capture);
                    //add(capture,BorderLayout.SOUTH);
                    add(jp,BorderLayout.SOUTH);
                    add(panel, BorderLayout.CENTER);

                    pack();
                    setVisible(true);
                    Thread t = new Thread() {

                           @Override
                           public void run() {
                                   panel.start();
                           }
                   };
                   t.setName("example-starter");
                   t.setDaemon(true);
                   t.setUncaughtExceptionHandler(this);
                   t.start();
                    }
                    
            }
        
	 
	@Override
	public void webcamOpen(WebcamEvent we) {
		System.out.println("webcam open");
	}

	@Override
	public void webcamClosed(WebcamEvent we) {
		System.out.println("webcam closed");
	}

	@Override
	public void webcamDisposed(WebcamEvent we) {
		System.out.println("webcam disposed");
	}

	@Override
	public void webcamImageObtained(WebcamEvent we) {
		// do nothing
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
		webcam.close();
	}

	@Override
	public void windowClosing(WindowEvent e) {
            
            MainFrame.setImageURL(imagePath);
            
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		System.out.println("webcam viewer resumed");
		panel.resume();
	}

	@Override
	public void windowIconified(WindowEvent e) {
		System.out.println("webcam viewer paused");
		panel.pause();
	}

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.err.println(String.format("Exception in thread %s", t.getName()));
		e.printStackTrace();
	}

    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println(e.getActionCommand());
        if(e.getActionCommand().trim().equalsIgnoreCase("Capture"))
        {
            try {
                
                String path = new File("").getAbsolutePath();
                System.out.println(path);
                
                File file = new File(Common.getTempPath()+Common.getSeparator()+String.format("image-%d.jpg", System.currentTimeMillis()));
                ImageIO.write(webcam.getImage(), "JPG", file);
                imagePath = file.getAbsolutePath();
                System.out.println("Image saved in " + imagePath);
                MainFrame.setImageURL(imagePath);
                EmployeeManipulationFrame.imagePath = imagePath;
                
               //CropImage cropFrame = new CropImage();
              // cropFrame.start(imagePath,emf);
                
                ResizableComponent rc = new ResizableComponent(imagePath,emf);
                rc.setAlwaysOnTop(true);
                rc.setVisible(true);
                
               //JFrameCamPicture imagePanel = new JFrameCamPicture(imagePath,mainFrame,emf);
              // imagePanel.setVisible(true);
               // imagePanel.pack();
                
                this.dispose();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        
    }
 
}
