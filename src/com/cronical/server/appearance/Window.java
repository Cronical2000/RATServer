package com.cronical.server.appearance;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import com.cronical.server.Client;
import com.cronical.server.Monitor;
import com.cronical.server.appearance.buttonlistener.ButtonListener;


public class Window extends JFrame {
	
	private static final long serialVersionUID = -6877766245520009022L;

	@SuppressWarnings("rawtypes")
	private final DefaultListModel clientListModel = new DefaultListModel();
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private final JList clientList = new JList(clientListModel);
	
	private final JButton btnTasks = new JButton("Task Manager");
	private final JButton btnKill = new JButton("Kill Task");
	private final JButton btnRefresh = new JButton("Refresh Tasks");
	private final JButton btnScreenCapture = new JButton("Screen Capture");
	
	@SuppressWarnings("deprecation")
	public Window() {
		//JFrame Settings
		super("Server Monitor");
		setSize(800, 400);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		
		//ScrollPane
		JScrollPane scrollPane = new JScrollPane(); //Scroll pane (scroll bars) for the connected clients list
		scrollPane.setSize(500, 350);
		scrollPane.setLocation(10, 10);
		clientList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.getViewport().add(clientList);
		
		//Sets all locations and sizes
		btnScreenCapture.setLocation(600,60);
		btnScreenCapture.setSize(150, 25);
		btnTasks.setLocation(600, 90);
		btnTasks.setSize(150, 25);
		
		addButtonListeners(); //Adds all button listeners
		
		//Adds all of the components to the frame
		add(scrollPane);
		add(btnScreenCapture);
		add(btnTasks);		
		show(); //Shows the frame
	}
	
	private void addButtonListeners() {
		ButtonListener btnListener = new ButtonListener();
		
		btnScreenCapture.addActionListener(btnListener.sort("screen"));
		btnTasks.addActionListener(btnListener.sort("task"));
		btnKill.addActionListener(btnListener.sort("tskkill"));
		btnRefresh.addActionListener(btnListener.sort("tskrefresh"));	
	}
		
	@SuppressWarnings("rawtypes")
	private final DefaultListModel taskListModel = new DefaultListModel();
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public final JList taskList = new JList(taskListModel);
	
	/**
	 * Opens the Task Frame and set the Settings for it
	 */
	public void openTaskFrame()	{ 
		//New JFrame for TaskManager and Settings		
		JFrame frame = new JFrame("Task Manager");
		frame.setSize(400, 350);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		
		//New ScrollPane and Settings
		JScrollPane scrollPane = new JScrollPane(); //Creates the scroll pane for the tasks
		scrollPane.setSize(200, 300);
		scrollPane.setLocation(10, 10);
		taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.getViewport().add(taskList);
		
		//Sets the location and sizes for the buttons
		btnKill.setLocation(228, 10);
		btnKill.setSize(150, 25);
		btnRefresh.setLocation(228, 50);
		btnRefresh.setSize(150, 25);

		//Clear all entries
		taskListModel.clear();
		//Get the selected Client from the list
		String selected = getSelectedClient();
		//Gets the client from the id
		Client c = Monitor.clientManager.getClient(selected); 
		
		if (c != null) {
			c.getTasks(); //Requests to get all tasks
		}
		
		//Adds all to the frame
		frame.add(scrollPane);
		frame.add(btnKill);
		frame.add(btnRefresh);	
		frame.setVisible(true); //shows the tasks manager frame
	}

	/**
	 * Adds a client id to the list of clients connected
	 */
	@SuppressWarnings("unchecked")
	public void addClientToList(String id) {
		clientListModel.addElement(id);
	}

	/**
	 * Removes a client id from the list of clients connected
	 */
	public void removeClient(String id) { 
		clientListModel.removeElement(id);
	}

	/**
	 * Gets the current client that is selected in the list
	 */
	public String getSelectedClient() {
		return (String) clientList.getSelectedValue();
	}

	/**
	 * Updates the tasks in the task manager window
	 */
	@SuppressWarnings("unchecked")
	public void updateTasks(String[] tasks) {
		taskListModel.clear(); //Clears previous entries
		for (int i = 0; i < tasks.length; i++) { //For each tasks
			String s = tasks[i]; //Get the task
			if (!s.equalsIgnoreCase("null")) { //If it doesnt equal null
				taskListModel.addElement(s); //Add the element
				try {
					Thread.sleep(20); //Reason for sleeping is because if you dont the tasks wont show up or load into the list correctly
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**	
	private final JTextArea textArea = new JTextArea();
	
	public void openInfoFrame() { //Shows the information frame
		JFrame frame = new JFrame("Information");
		frame.setSize(400, 350);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		
		textArea.setFont(new Font("Serif", Font.PLAIN, 12)); //Sets smaller text of the text area
		textArea.setWrapStyleWord(true);
		
		JScrollPane scrollPane = new JScrollPane(); //Adds a scroll pane to the text area
		scrollPane.getViewport().add(textArea);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		//Sets the size and location of the text area
		scrollPane.setSize(375, 325);
		scrollPane.setLocation(10, 10);

		textArea.setText(""); //Clears out anything in it
		
		frame.add(scrollPane); //Adds it to the frame
		
		frame.show(); //Shows the frame
	}

	public void setInfoFrame(String[] info) {
		textArea.setText(""); //Clears out the current info
		for (String s : info) //Goes through each entry
			textArea.append(s + "\n"); //Adds it then adds the new line operator
	}
	**/
}