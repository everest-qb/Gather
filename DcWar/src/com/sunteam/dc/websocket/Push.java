package com.sunteam.dc.websocket;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.websocket.CloseReason;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@ServerEndpoint("/hello") 
public class Push {
	private Logger log = LoggerFactory.getLogger(Push.class);
	private static CopyOnWriteArrayList<Session> list=new CopyOnWriteArrayList<Session>();
	
	@OnOpen
	public void onOpen(Session session){		
		log.trace("open");
		list.add(session);
	}
	
	@OnMessage
	public String onMessage(String msg,Session session){		
		log.trace("message");
		return msg;
	}
	
	@OnClose
	public void onClose(Session session,CloseReason reason){
		log.trace("close");
		list.remove(session);
	}
	
	@OnError
	public void onError(Throwable error){
		log.trace("error");
	}
	
	public static void send(String msg){
		list.forEach((Session s)->{
			try {
				s.getBasicRemote().sendObject(msg);
			} catch (IOException e) {			
				e.printStackTrace();
			} catch (EncodeException e) {				
				e.printStackTrace();
			}
		});
	}
}
