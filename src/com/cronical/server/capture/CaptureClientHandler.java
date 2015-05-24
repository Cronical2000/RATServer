package com.cronical.server.capture;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.beans.PropertyVetoException;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import com.cronical.server.Client;
import com.esotericsoftware.kryonet.Connection;

public class CaptureClientHandler extends Thread {
  
	@SuppressWarnings("unused")
	private Client client = null;
	@SuppressWarnings("unused")
	private Connection con = null;
	private JDesktopPane desktop = null;
	private JPanel cPanel = new JPanel();
    private JInternalFrame interFrame = new JInternalFrame("Client Screen",
                                                            true, true, true);
       
    public static Rectangle rect;
    public static ImageIcon imgI;
    
    public CaptureClientHandler(JDesktopPane desktop, Connection con,Client client) {
        this.con = con;
        this.desktop = desktop;
        this.client = client;
        
        start();
    }

    /**
    * Draw GUI per each connected client
    */
    public void drawGUI(){
    	
        interFrame.setLayout(new BorderLayout());
        interFrame.getContentPane().add(cPanel,BorderLayout.CENTER);
        interFrame.setSize(100,100);
        desktop.add(interFrame);
        try {
            //Initially show the internal frame maximized
            interFrame.setMaximum(true);
        } 
        catch (PropertyVetoException ex) {
            ex.printStackTrace();
        }
        //this allows to handle KeyListener events
        cPanel.setFocusable(true);
        interFrame.setVisible(true);
    }

   public void run(){
        drawGUI();        
        while(true){
        	if(imgI != null){
        		Image image = imgI.getImage();
                image = image.getScaledInstance(cPanel.getWidth(),cPanel.getHeight()
                                                        ,Image.SCALE_FAST);
                //Draw the recieved screenshot
                Graphics graphics = cPanel.getGraphics();
                graphics.drawImage(image, 0, 0, cPanel.getWidth(),cPanel.getHeight(),cPanel);	
        	}
        	try {
				Thread.sleep(100);
			} 
        	catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }        
   }
}








/**
        try{
            //Read client screen dimension
            ois = new ObjectInputStream(cSocket.getInputStream());
            clientScreenDim =(Rectangle) ois.readObject();
        }catch(IOException ex){
            ex.printStackTrace();
        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
        //Start recieveing screenshots
    //new ClientScreenReciever(ois,cPanel);
        //Start sending events to the client
   // new ClientCommandsSender(cSocket,cPanel,clientScreenDim);
    */
    


