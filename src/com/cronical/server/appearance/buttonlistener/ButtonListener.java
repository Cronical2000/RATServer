package com.cronical.server.appearance.buttonlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.cronical.server.Client;
import com.cronical.server.Main;
import com.cronical.server.Monitor;
import com.cronical.server.capture.CaptureFrame;
import com.esotericsoftware.kryonet.Connection;

public class ButtonListener{
	
	/**
	 * Default Constructor
	 */
	public ButtonListener(){
			
	}
	
	public ActionListener sort(String whichListener){
		if(whichListener.equals("task")){
			return new ButtonTaskListener();
		}
		else if(whichListener.equals("screen")){
			return new ButtonScreenCapture();					
		}
		else if(whichListener.equals("tskkill")){
			return new ButtonKillListener();			
		}
		else if(whichListener.equals("tskrefresh")){
			return new ButtonRefresh();
		}
		else{
			System.out.println("[Server]: The ButtonListener was not found");
			return null;
		}
	}
}

class ButtonScreenCapture implements ActionListener{
	@Override	
	public void actionPerformed(ActionEvent event) {
		String selected = Main.monitor.window.getSelectedClient(); //Gets the selected client from the list of clients
		Client c = Monitor.clientManager.getClient(selected); //Gets the client from the id
		Connection con = Monitor.clientManager.getClient(selected).getConnection();
		new CaptureFrame(c,con);
				
		if (c != null) {
			c.getScreen(); 
		}
	}
}

class ButtonTaskListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		Main.monitor.window.openTaskFrame();		
	}
}

class ButtonKillListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		String selected = Main.monitor.window.getSelectedClient(); //Gets the selected client from the list of clients
		Client c = Monitor.clientManager.getClient(selected); //Gets the client from the id
		if (c != null) { 
			String process = (String) Main.monitor.window.taskList.getSelectedValue(); //Get the selected task name
			c.killTask(process.trim()); //Request to kill the tasks (trim takes out all spaces)
			c.getTasks();
		}
	}
}

class ButtonRefresh implements ActionListener{
	@Override	
	public void actionPerformed(ActionEvent event) {
		String selected = Main.monitor.window.getSelectedClient(); //Gets the selected client from the list of clients
		Client c = Monitor.clientManager.getClient(selected); //Gets the client from the id
		if (c != null) {
			c.getTasks(); //Requests all of the tasks
		}
	}
}

