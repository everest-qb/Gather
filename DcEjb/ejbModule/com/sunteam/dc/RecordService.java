package com.sunteam.dc;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


@Stateless
@LocalBean
@Path("data")
public class RecordService {

	@PersistenceContext(unitName="DcJpa")
	private EntityManager em;
	
    public RecordService() {
       
    }

    @GET
	@Path("sta")
	@Produces(MediaType.APPLICATION_JSON)    
    public List<DeviceRecord> findByStation(@QueryParam("id") int stationId){
    	
    	return em.createQuery("SELECT d FROM DeviceRecord d INNER JOIN d.station s WHERE s.id=:ID", DeviceRecord.class)
    	.setParameter("ID", stationId)
    	.getResultList();    	
    }
    
    @GET
	@Path("dev")
	@Produces(MediaType.APPLICATION_JSON)
    public List<DeviceRecord> findByDevide(@QueryParam("id") String devId){
    	
    	List<DeviceRecord> returnValue=em.createQuery("SELECT d FROM DeviceRecord d JOIN FETCH d.station WHERE d.deviceId=:DEVID", DeviceRecord.class)
    	    	.setParameter("DEVID", devId)
    	    	.getResultList(); 
    	for(DeviceRecord r:returnValue)
    		r.getStationId();
    	return returnValue;
    }
}
