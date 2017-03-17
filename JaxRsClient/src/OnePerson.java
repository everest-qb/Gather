import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import com.sunteam.dc.DeviceRecord;
import com.sunteam.dc.MultiRecord;
import com.sunteam.dc.RfidRecord;

public class OnePerson implements Runnable{

	private Random r=new Random();
	private String  deviceId;

	private Client client = ClientBuilder.newClient();
	private HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("USER01", "123456789");
	private WebTarget webTarget;
	private WebTarget dataInsertWebTarget;
	private WebTarget rfidtWebTarget;
	private WebTarget muchInsertWebTarget;
	private int amountOfStation;
	public OnePerson(String id,int stations) {		
		deviceId=id;
		amountOfStation=stations;
		client.register(feature);
		webTarget = client.target("http://dev02.localdomain:8080/DcWar/rest");
		dataInsertWebTarget = webTarget.path("data").path("pick");
		rfidtWebTarget = webTarget.path("data").path("rfid");
		muchInsertWebTarget=webTarget.path("data").path("pickmuch");
	}
	
	
	private void rfid(int stationId){
		RfidRecord record=new RfidRecord();			
		record.setDeviceId(deviceId);		
		record.setCreateTime(Calendar.getInstance().getTime());		
		record.setStationId(stationId);			
		Invocation.Builder invocation=rfidtWebTarget.request();		
		Response response=invocation.post(Entity.entity(record, MediaType.APPLICATION_JSON_TYPE));
	}
	
	private void data(int stationId){
		DeviceRecord record=new DeviceRecord();			
		record.setDeviceId(deviceId);		
		record.setCreateTime(Calendar.getInstance().getTime());
		record.setDHeartRate(80);
		record.setDTemperature(29);
		record.setStationId(stationId);
		record.setdBloodOxygen(0.95f);		
		dataInsertWebTarget.request().post(Entity.entity(record, MediaType.APPLICATION_JSON_TYPE));		

		MultiRecord record1 = new MultiRecord();
		record1.setDeviceId(deviceId);
		record1.setCreateTime(Calendar.getInstance().getTime());
		record1.setDBloodOxygen(0.95f);
		record1.setDHeartRate(80);
		record1.setDTemperature(29);
		record1.setStationId(stationId);
		for(int i=0;i<5;i++){
			muchInsertWebTarget.request().post(Entity.entity(record1, MediaType.APPLICATION_JSON_TYPE));
			try {
				TimeUnit.SECONDS.sleep(1);
				record1.setCreateTime(Calendar.getInstance().getTime());
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
	}


	@Override
	public void run() {
		rfid(0);
		try {
			TimeUnit.SECONDS.sleep(r.nextInt(2)+1);
		} catch (InterruptedException e) {				
			e.printStackTrace();
		}
		for(int i=1;i<=amountOfStation;i++){
			data(i);
		}
		try {
			TimeUnit.SECONDS.sleep(r.nextInt(2)+1);
		} catch (InterruptedException e) {				
			e.printStackTrace();
		}
		rfid(99);
	}


	
	
}
