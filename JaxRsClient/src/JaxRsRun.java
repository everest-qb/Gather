import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.grizzly.connector.GrizzlyConnectorProvider;

import com.sunteam.dc.DeviceRecord;
import com.sunteam.dc.Station;

public class JaxRsRun {

	public static void main(String[] args) {
		
		
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.connectorProvider(new GrizzlyConnectorProvider());
		Client client = ClientBuilder.newClient(clientConfig);
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("USER01", "123456789");
		client.register(feature);
		
		WebTarget webTarget = client.target("http://localhost:8080/DcWar/rest");
		WebTarget stationAllWebTarget = webTarget.path("station").path("all");
		WebTarget resordAllWebTarget = webTarget.path("data").path("sta").queryParam("id", 1);
		
		Invocation.Builder invocationBuilder=stationAllWebTarget.request();			
		Response response = invocationBuilder.accept(MediaType.APPLICATION_JSON).get();			
		List<Station> list=response.readEntity(new GenericType<List<Station>>(){});
		
		for(Station s:list){
			System.out.println(s.getName()+" "+s.getId()+" "+s.getIp());
		}
		
		response=resordAllWebTarget.request().accept(MediaType.APPLICATION_JSON).get();				
		List<DeviceRecord> dList=response.readEntity(new GenericType<List<DeviceRecord>>(){});
		for(DeviceRecord r:dList){
			System.out.println(r.getDeviceId()+" "+r.getStationId()+" "+r.getDHeartRate());
		}
	}

}
