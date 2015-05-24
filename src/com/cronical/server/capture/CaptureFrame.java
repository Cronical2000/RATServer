package com.cronical.server.capture;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;

import com.cronical.server.network.packet.RectangleObject;
import com.cronical.server.network.packet.Response;
import com.esotericsoftware.kryonet.Connection;

	public class CaptureFrame implements WindowListener {


    //Main server frame
    private JFrame frame = new JFrame();
    //JDesktopPane represents the main container that will contain all
    //connected clients' screens
    private JDesktopPane desktop = new JDesktopPane();

    Connection con = null;
    RectangleObject rect = null;
    ImageIcon imgIcon;
    com.cronical.server.Client client = null;
    
    public CaptureFrame(com.cronical.server.Client client,Connection con){
    	this.con = con;
    	this.client = client;
    	initialize();
    	
    }
    
    public void initialize(){
   
            drawGUI();
            @SuppressWarnings("unused")
			CaptureClientHandler cptrClient = new CaptureClientHandler(desktop,con,client);
    }

    /*
     * Draws the main server GUI
     */
    public void drawGUI(){
            frame.add(desktop,BorderLayout.CENTER);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            //Show the frame in a maximized state
            frame.setExtendedState(frame.getExtendedState()|JFrame.MAXIMIZED_BOTH);
            frame.setVisible(true);
           
            frame.addWindowListener(this);
           
    }

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		Response r = new Response();
		r.response = "stopImgSend";
		con.sendTCP(r);
		System.out.println("[Server]: Stop Images sended by Client");
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}