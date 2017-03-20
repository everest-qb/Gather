import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class DeviceTest {

	private Device d;
	
	@Before
	public void setUp() throws Exception {
		d=new Device();
	}

	@Test
	public void testGenDeviceids() {
		
		assertEquals(d.genDeviceids().size(),d.getUsers());
	}
	
	@Test
	public void testGenCsv(){
		for(Map<String,String> m:d.genExport()){
			System.out.println("\"1\","+m.get("CODE")+",\""+m.get("ACTIVITY_ID")+"\",\""+m.get("ITEM_ID")+"\",\"0\"");
		}
	}

	@Test
	public void testGenExcelFile(){
		try {
			d.genExcelFile();
			assertEquals(1,1);
		} catch (Exception e) {
			assertEquals(1,2);
		}
	}
	
}
