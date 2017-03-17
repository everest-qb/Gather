
public class Simulate {

	public static void main(String[] args) {
		Device dev=new Device();
		for (int i = 1; i < 6; i++) {
			String id = dev.genDeviceids().get(i);
			OnePerson p = new OnePerson(id, 11);
			new Thread(p).start();
		}
	}

}
