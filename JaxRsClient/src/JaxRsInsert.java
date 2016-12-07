import java.util.Calendar;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sunteam.dc.DeviceRecord;
import com.sunteam.dc.Station;

public class JaxRsInsert {

	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://dev02.localdomain:8080/DcWar/rest");
		WebTarget dataInsertWebTarget = webTarget.path("data").path("pick");
		WebTarget stationWebTarget = webTarget.path("station");		
		Station station=stationWebTarget.path("one").queryParam("id", 1).request(MediaType.APPLICATION_JSON).get(Station.class);
		DeviceRecord record=new DeviceRecord();	
		record.setStation(station);
		record.setDeviceId("D99999");		
		record.setCreateTime(Calendar.getInstance().getTime());
		record.setDHeartRate(80);
		record.setDTemperature(29);
		record.setStationId(station.getId());
		/*Invocation.Builder invocation=dataInsertWebTarget.request();		
		Response response=invocation.post(Entity.entity(record, MediaType.APPLICATION_JSON_TYPE));
		int status =response.getStatus();
		System.out.println(status);*/
		
		Invocation.Builder invocation=stationWebTarget.path("modify").request();
		station.setIp("192.168.7.101");
		station.setChangeTime(Calendar.getInstance().getTime());
		Response response=invocation.post(Entity.entity(station, MediaType.APPLICATION_JSON_TYPE));
		System.out.println(response.getStatus());
	}

}
