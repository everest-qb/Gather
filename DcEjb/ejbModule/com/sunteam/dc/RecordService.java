package com.sunteam.dc;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class RecordService
 */
@Stateless
@LocalBean
public class RecordService {

	@PersistenceContext(unitName="DcJpa")
	private EntityManager em;
	
    public RecordService() {
       
    }

    public List<DeviceRecord> findByStation(int stationId){
    	
    	return em.createQuery("SELECT d FROM DeviceRecord d INNER d.station s WHERE s.id=:ID", DeviceRecord.class)
    	.setParameter("ID", stationId)
    	.getResultList();    	
    }
    
    
    public List<DeviceRecord> findByDevide(String devId){
    	return em.createQuery("SELECT d FROM DeviceRecord d JOIN FETCH d.station WHERE d.deviceId=:DEVID", DeviceRecord.class)
    	    	.setParameter("DEVID", devId)
    	    	.getResultList();  
    }
}
