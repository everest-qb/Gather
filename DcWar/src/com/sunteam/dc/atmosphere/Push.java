package com.sunteam.dc.atmosphere;

import java.util.concurrent.CopyOnWriteArrayList;

import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;
import org.primefaces.push.RemoteEndpoint;
import org.primefaces.push.annotation.OnClose;
import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.OnOpen;
import org.primefaces.push.annotation.PushEndpoint;
import org.primefaces.push.impl.JSONEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@PushEndpoint("/test")
public class Push {
	private Logger log = LoggerFactory.getLogger(Push.class);
	private static CopyOnWriteArrayList<EventBus> list=new CopyOnWriteArrayList<EventBus>();
	
	@OnOpen
	public void onOpen(RemoteEndpoint r){	
		EventBus e=EventBusFactory.getDefault().eventBus();
		list.add(e);		
		log.trace("open:{}",e);
	};
	
	@OnMessage(encoders={JSONEncoder.class})
	public String onMessage(String msg) {
		log.trace("message:{}",msg);
		return msg;
	}
	
	@OnClose
	public void onClose(RemoteEndpoint r){
		EventBus e=EventBusFactory.getDefault().eventBus();
		list.remove(e);
		log.trace("close");
	};
	
	
	public static void send(String msg){	
		list.forEach((EventBus e)->{		
			e.publish("/test",msg);
		});		
	}
}
