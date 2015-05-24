package com.cronical.server;


import com.cronical.server.network.packet.Response;
import com.cronical.server.network.packet.TaskKillName;
import com.esotericsoftware.kryonet.Connection;

public class Client {

	private final Connection con;
	public final String id;

	// Creates a Client with Connection and ID	
	public Client(Connection connection, String id) {
		this.con = connection;
		this.id = id;
	}
	
	//Getter Client Connection
	public Connection getConnection() {
		return con;
	}
	
	//Getter Client ID
	public String getId() {
		return id;
	}
	
	//Getter Client Tasks
	public void getTasks() {
		Response gettsk = new Response();
		gettsk.response = "gettasks";
		con.sendTCP(gettsk);
		System.out.println("[Server]: Send the order gettasks");
	}
	
	//Getter Client Screen
	public void getScreen() {
		Response scr = new Response();
		scr.response = "startCapture";		
		con.sendTCP(scr);
		System.out.println("[Server]: Send the order startCapture");
	}
	public void killTask(String task){
		TaskKillName tskKill = new TaskKillName();
		tskKill.task = task;
		con.sendTCP(tskKill);
	}
}