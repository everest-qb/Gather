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
import com.sunteam.dc.MultiRecord;


public class MultiInsert {
	public static Logger logger = Logger.getLogger(MultiInsert.class.getName());	
	
	public static void main(String[] args){
		Client client = ClientBuilder.newClient();
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "!qaz2wsx");
		client.register(feature).register(new LoggingFeature(JaxRsInsert.logger,Level.INFO,null,null));		
		WebTarget webTarget = client.target("http://192.168.7.114/api/collect_dc_all.php");		
		DecimalFormat format=new DecimalFormat("D00000");
		List<MultiRecord> list=new ArrayList<MultiRecord>(); 
		for(int i=1;i<3;i++){
			MultiRecord record=new MultiRecord();	
			final int count=i;
			record.setId(i);
			record.setDeviceId(format.format(count));		
			record.setCreateTime(Calendar.getInstance().getTime());
			record.setDHeartRate(80);
			record.setDTemperature(29);
			record.setStationId(1);
			record.setDBloodOxygen(0.95f);
			record.setDc_id(15);
			list.add(record);					
			
		}
		Invocation.Builder invocation=webTarget.request();		
		Response response=invocation.post(Entity.entity(list.toArray(new MultiRecord[list.size()]), MediaType.APPLICATION_JSON_TYPE));
		
		int status =response.getStatus();
		System.out.println(status+" "+response.readEntity(String.class));
	}
}


