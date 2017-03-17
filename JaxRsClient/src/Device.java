import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Device {

	private int users=50;
	private int groups=2;
	private int activeId=20;
	private int startItemId=23;
	private String prefix="0020170315";
	
	public List<String> genDeviceids(){
		List<String> returnValue=new ArrayList<String>();
		DecimalFormat f4=new DecimalFormat("0000");
		DecimalFormat f6=new DecimalFormat("000000");
		for(int i=startItemId;i<(startItemId+groups);i++){
			for(int j=1;j<=(users/2);j++){
				returnValue.add(prefix+f4.format(activeId)+f4.format(i)+f6.format(j));
			}
		}
		return returnValue;
	}

	public List<Map<String,String>> genExport(){
		List<Map<String,String>> returnValue=new ArrayList<Map<String,String>>();
				
		DecimalFormat f2=new DecimalFormat("00");
		DecimalFormat f4=new DecimalFormat("0000");
		DecimalFormat f6=new DecimalFormat("000000");
		for(int i=startItemId;i<(startItemId+groups);i++){
			for(int j=1;j<=(users/2);j++){
				String code=prefix+f4.format(activeId)+f4.format(i)+f6.format(j);
				String activity_id=f2.format(activeId);
				String item_id=f2.format(i);				
				Map<String,String> map=new HashMap<String,String>();
				map.put("CODE", code);
				map.put("ACTIVITY_ID", activity_id);
				map.put("ITEM_ID", item_id);
				returnValue.add(map);
			}
		}		
		return returnValue;
	}
	
	public void genExcelFile() throws Exception{
		FileOutputStream out = new FileOutputStream("devices.xlsx");
		Workbook wb = new XSSFWorkbook();	
		Sheet s = wb.createSheet();
		List<Map<String,String>> data=genExport();
		Row header=s.createRow(0);
		Cell hc0=header.createCell(0);
		Cell hc1=header.createCell(1);
		Cell hc2=header.createCell(2);
		Cell hc3=header.createCell(3);
		Cell hc4=header.createCell(4);
		hc0.setCellValue("id");
		hc1.setCellValue("code");
		hc2.setCellValue("activity_id");
		hc3.setCellValue("item_id");
		hc4.setCellValue("used");
		
		for(int rownum =0;rownum<data.size();rownum++){
			Map<String,String> map=data.get(rownum);
			Row r=s.createRow(rownum+1);
			Cell  c0=r.createCell(0);
			Cell  c1=r.createCell(1);
			Cell  c2=r.createCell(2);
			Cell  c3=r.createCell(3);
			Cell  c4=r.createCell(4);
			c0.setCellValue(1);
			c1.setCellValue(map.get("CODE"));			
			c2.setCellValue(Integer.parseInt(map.get("ACTIVITY_ID")));
			c3.setCellValue(Integer.parseInt(map.get("ITEM_ID")));
			c4.setCellValue(0);			
		}
		wb.write(out);
		out.close();		
	}
	
	public int getUsers() {
		return users;
	}

	public void setUsers(int users) {
		this.users = users;
	}

	public int getGroups() {
		return groups;
	}

	public void setGroups(int groups) {
		this.groups = groups;
	}

	public int getActiveId() {
		return activeId;
	}

	public void setActiveId(int activeId) {
		this.activeId = activeId;
	}

	public int getStartItemId() {
		return startItemId;
	}

	public void setStartItemId(int startItemId) {
		this.startItemId = startItemId;
	}



	public String getPrefix() {
		return prefix;
	}



	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
}
