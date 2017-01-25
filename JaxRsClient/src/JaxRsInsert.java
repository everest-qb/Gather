import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.logging.LoggingFeature;

import com.sunteam.dc.DeviceRecord;
import com.sunteam.dc.Station;

public class JaxRsInsert {

	public static Logger logger = Logger.getLogger(JaxRsInsert.class.getName());
	
	public static void main(String[] args) {
		
		Client client = ClientBuilder.newClient();
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "!qaz2wsx");
		client.register(feature).register(new LoggingFeature(JaxRsInsert.logger,Level.INFO,null,null));		
		WebTarget webTarget = client.target("http://192.168.7.114/api/collect_dc_records.php");
		//http://dev02.localdomain:8080/DcWar/rest
		WebTarget dataInsertWebTarget = webTarget.path("data").path("pick");
		WebTarget stationWebTarget = webTarget.path("station");		
		//Station station=stationWebTarget.path("one").queryParam("id", 2).request(MediaType.APPLICATION_JSON).get(Station.class);			
		//System.out.println(station.getName());
		DecimalFormat format=new DecimalFormat("D00000");
		List<DeviceRecord> list=new ArrayList<DeviceRecord>(); 
		for(int i=12;i<14;i++){
			DeviceRecord record=new DeviceRecord();	
			final int count=i;
			record.setId(i);
			record.setDeviceId(format.format(count));		
			record.setCreateTime(Calendar.getInstance().getTime());
			record.setDHeartRate(80);
			record.setDTemperature(29);
			record.setStationId(1);
			record.setdBloodOxygen(0.95f);
			record.setDc_id(1);
			list.add(record);
		/*final int count=i;
		new Thread(()->{
			DeviceRecord record=new DeviceRecord();	
			//record.setStation(station);
			record.setDeviceId(format.format(count));		
			record.setCreateTime(Calendar.getInstance().getTime());
			record.setDHeartRate(80);
			record.setDTemperature(29);
			record.setStationId(1);
			record.setdBloodOxygen(0.95f);		
			Invocation.Builder invocation=webTarget.request();		
			Response response=invocation.post(Entity.entity(record, MediaType.APPLICATION_JSON_TYPE));
			
			int status =response.getStatus();
			System.out.println(status+" "+response.readEntity(String.class));//200 ok
			
		}).start();*/
			
			
		}
		
		Invocation.Builder invocation=webTarget.request();		
		Response response=invocation.post(Entity.entity(list.toArray(new DeviceRecord[list.size()]), MediaType.APPLICATION_JSON_TYPE));
		
		int status =response.getStatus();
		System.out.println(status+" "+response.readEntity(String.class));//200 ok		
		
		/*Invocation.Builder invocation=stationWebTarget.path("modify").request();
		station.setIp("192.168.7.101");
		station.setChangeTime(Calendar.getInstance().getTime());
		Response response=invocation.post(Entity.entity(station, MediaType.APPLICATION_JSON_TYPE));
		System.out.println(response.getStatus());*/
	}

}
