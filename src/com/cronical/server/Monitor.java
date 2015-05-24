package com.cronical.server;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import javax.swing.ImageIcon;
import com.cronical.server.appearance.Window;
import com.cronical.server.network.packet.Command;
import com.cronical.server.network.packet.ImagePacket;
import com.cronical.server.network.packet.Packet;
import com.cronical.server.network.packet.RectangleObject;
import com.cronical.server.network.packet.Response;
import com.cronical.server.network.packet.SystemName;
import com.cronical.server.network.packet.TaskKillName;
import com.cronical.server.network.packet.Tasks;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;


public class Monitor {
	
	private int TCP_PORT, UDP_PORT;
	private Server server;
	private Kryo kryo;

	public static ClientManager clientManager;
	public Window window;

	/**
	 * Creates a new Monitor, creates a new Server, creates a new ClientManager, registering Kryo Classes, creates a new Window
	 * @param tcp,udp
	 */
	public Monitor(int tcp, int udp) {
		this.TCP_PORT = tcp;
		this.UDP_PORT = udp;

		//server = new Server(16384, 5096);
		server = new Server(930000,930000);
		clientManager = new ClientManager();
		System.out.println("[Server]: New ClientManager added");
		startServer();
		kryo = server.getKryo();
		registerKryoClasses();
		window = new Window();
		System.out.println("[Server]: New Window (Server Monitor) created");
	}
		
	/**
	 * Launch the Server and binding Ports also adding ServerListener to the Server
	 */
	public void startServer() {		
		server.start();
		System.out.println("[Server]: Server started");
		try {
			//Log.TRACE();
			server.bind(TCP_PORT, UDP_PORT);
			System.out.println("[Server]: Ports binded");
			server.addListener(new ServerListener());
			System.out.println("[Server]: ServerListener added (Listen to the Packets from the Client now!)");			
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	/**
	 * Closing the Server
	 */
	public void stopServer() {
		server.stop();
		System.out.println("[Server]: Server closed");
	}
	
	/**
	 * Registers Packets from com.cronical.server.network.packet
	 * The Packets have to be in the same order as the client side
	 */
	private void registerKryoClasses() {
		//Super Classes
		kryo.register(String[].class);
		System.out.println("[Server]: String[].class registered");
		kryo.register(String.class);
		System.out.println("[Server]: String.class registered");
		kryo.register(Rectangle.class);
		System.out.println("[Server]: Rectangle.class registered");
		kryo.register(ImageIcon.class);
		System.out.println("[Server]: ImageIcon.class registered");
		kryo.register(Packet.class);
		System.out.println("[Server]: Packet.class registered");
		kryo.register(Image.class);
		System.out.println("[Server]: Image.class registered");
		kryo.register(byte[].class);
		System.out.println("[Server]: byte[].class registered");
						
		//Packets
		kryo.register(Response.class);
		System.out.println("[Server]: Response.class registered");
		kryo.register(SystemName.class);	
		System.out.println("[Server]: SystemName.class registered");
		kryo.register(Tasks.class);
		System.out.println("[Server]: Tasks.class registered");
		kryo.register(RectangleObject.class);	
		System.out.println("[Server]: RectangleObject.class registered");
		kryo.register(ImagePacket.class);
		System.out.println("[Server]: ImagePacket.class registered");
		kryo.register(Command.class);
		System.out.println("[Server]: Command.class registered");
		kryo.register(TaskKillName.class);
		System.out.println("[Server]: TaskKillName.class registered");
	
		System.out.println("[Server]: Server Kryo Classes registered");
	}
}
