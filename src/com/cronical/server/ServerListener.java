package com.cronical.server;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.cronical.server.handle.PacketHandler;
import com.cronical.server.network.packet.ImagePacket;
import com.cronical.server.network.packet.Packet;
import com.cronical.server.network.packet.RectangleObject;
import com.cronical.server.network.packet.Response;
import com.cronical.server.network.packet.SystemName;
import com.cronical.server.network.packet.Tasks;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class ServerListener extends Listener {

	PacketHandler packetHandle;

	public ServerListener() {
		packetHandle = new PacketHandler();
	}

	/**
	 * Get the package and the connection of the client and cast it and also send it to the handler
	 * @param Connection,Object
	 */
	public void received(Connection con, Object object) {		
		//If the Object is a Packet
		if (object instanceof Packet) {		
			if (object instanceof SystemName) {				
				SystemName SystemNameCast = (SystemName) object;
				packetHandle.handleSysName(SystemNameCast, con);
				System.out.println("[Server]: SystemName Object received,casted and sent to the handler");
			}
			//If the Object is a Tasks (String[])	
			else if(object instanceof Tasks){			
				Tasks TasksCast = (Tasks) object;
				packetHandle.handleTasks(TasksCast,con);
				System.out.println("[Server]: Tasks Object received,casted and sent to the handler");
			}
			//If the Object is a RectangleObject (Rectangle)
			else if(object instanceof RectangleObject){
				System.out.println("Server: Get Rectangle (Dimension of Screen)");
				RectangleObject rect = (RectangleObject)object;
				@SuppressWarnings("unused")
				Rectangle rectCast =(Rectangle) rect.rect;
				//packetHandle.handleRectangle(rectCast,con);				
			}
			//If the Object is a Response (String)
			else if (object instanceof Response){
				@SuppressWarnings("unused")
				Response response = (Response)object;
				
			}
			//If the Object is a ImagePacket (ImageIcon)
			else if(object instanceof ImagePacket){
			ImagePacket imgPkt = (ImagePacket)object;
			byte[] img = imgPkt.image;
		//	System.out.println(img);
			BufferedImage bi = null;
			try {
				bi = ImageIO.read(new ByteArrayInputStream(img));
			//	System.out.println(bi);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ImageIcon imageIcon = new ImageIcon(bi);
			if(imageIcon != null){
			//	System.out.println("img ok");
			}
		
		packetHandle.handleImgPkt(imageIcon,con);
			//System.out.println("[Server]: ImagePacket Object received,casted and sent to the handler");
			}
		}
	}
}

