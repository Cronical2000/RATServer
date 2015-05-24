package com.cronical.server.handle;

import java.awt.Rectangle;

import javax.swing.ImageIcon;

import com.cronical.server.Client;
import com.cronical.server.Main;
import com.cronical.server.capture.CaptureClientHandler;
import com.cronical.server.network.packet.SystemName;
import com.cronical.server.network.packet.Tasks;
import com.esotericsoftware.kryonet.Connection;

public class PacketHandler {
			
	//TODO static access shouldnt be
	@SuppressWarnings("static-access")
	public void handleSysName(SystemName SystemNameCast, Connection con){
		
		String id = SystemNameCast.systemName;
		Main.monitor.clientManager.addClient(new Client(con,id));//static access clientManager
		Main.monitor.window.addClientToList(id);

	}
	
	public void handleTasks(Tasks TasksCast, Connection con){
		Main.monitor.window.updateTasks(TasksCast.tasks);		
	}
	
	//TODO capture command
	public void handleRectangle(Rectangle rectCast, Connection con){
		CaptureClientHandler.rect = rectCast;	
	}
	
	public void handleImgPkt(ImageIcon imgpktCast, Connection con){
		CaptureClientHandler.imgI = imgpktCast;

	}	
}

