import java.text.DecimalFormat;
import java.util.Calendar;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import com.sunteam.dc.RfidRecord;

public class RfidInsert {

	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("USER01", "123456789");
		client.register(feature);
		WebTarget webTarget = client.target("http://dev02.localdomain:8080/DcWar/rest");
		WebTarget dataInsertWebTarget = webTarget.path("data").path("rfid");
		DecimalFormat format=new DecimalFormat("000000000000000000000000");
		for(int i=1;i<3;i++){
			
		final int count=i;
		new Thread(()->{
			RfidRecord record=new RfidRecord();			
			record.setDeviceId(format.format(count));		
			record.setCreateTime(Calendar.getInstance().getTime());		
			record.setStationId(99);			
			Invocation.Builder invocation=dataInsertWebTarget.request();		
			Response response=invocation.post(Entity.entity(record, MediaType.APPLICATION_JSON_TYPE));
			
			int status =response.getStatus();
			System.out.println(status);//200 ok
			
		}).start();	
			
		
		}
	}

}
