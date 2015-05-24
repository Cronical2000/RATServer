package com.cronical.server;

import java.util.ArrayList;

import com.esotericsoftware.kryonet.Connection;

public class ClientManager {

	/**
	 * Client ArrayList
	 */
	//Saves all created Client's in an ArrayList
	public ArrayList<Client> clients = new ArrayList<Client>(); //Liste aller verbundenen Clients
	
	//Default Constructor ClientManager
	public ClientManager() {
		
	}
		
	/**
	 * Adding the client to the ArrayList in ClientManager
	 * @param client
	 */
	public void addClient(Client client) { //Fuegt Client zur Liste hinzu
		clients.add(client);
	}
		
	/**
	 * Get the client Name
	 * @param id
	 * @return
	 */
	public Client getClient(String id) { //Fordert die ID des Clients
		for (Client c : clients)
			if (c.getId().equalsIgnoreCase(id))
				return c;
		return null;
	}
		
	/**
	 * Get the clients connection
	 * @param con
	 * @return
	 */
	public Client getClient(Connection con) { 
		for (Client c : clients)
			if (c.getConnection() == con)
				return c;
		return null;
	}
	
	/**
	 * Boolean if client is connected
	 * @param client
	 * @return
	 */
	public boolean clientConnected(Client client) { //Prueft ob ein Client noch verbunden ist
		for (Client c : clients)
			if (c.getId().equalsIgnoreCase(client.getId()))
				return true;
		return false;
	}

}