package com.sunteam.dc;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Stateless
@LocalBean
public class RecordService {
	private Logger log = LoggerFactory.getLogger(RecordService.class);
	@PersistenceContext(unitName="DcJpa")
	private EntityManager em;
	
    public RecordService() {
       
    }
 
    public List<DeviceRecord> findByStation(int stationId){
    	log.trace("findByStation");
    	return em.createQuery("SELECT d FROM DeviceRecord d INNER JOIN d.station s WHERE s.id=:ID", DeviceRecord.class)
    	.setParameter("ID", stationId)
    	.getResultList();    	
    }
    
    public List<DeviceRecord> findByDevide(String devId){
    	log.trace("findByDevide");
    	List<DeviceRecord> returnValue=em.createQuery("SELECT d FROM DeviceRecord d JOIN FETCH d.station WHERE d.deviceId=:DEVID", DeviceRecord.class)
    	    	.setParameter("DEVID", devId)
    	    	.getResultList(); 
    	for(DeviceRecord r:returnValue)
    		r.getStationId();
    	return returnValue;
    }

    public List<Rank> findRank(int limit){
    	
    	return em.createNamedQuery("Rank.findAll", Rank.class).setMaxResults(limit).getResultList();    	
    }
    
    public DeviceRecord find(int stationId){
    	
    	return em.find(DeviceRecord.class, stationId);    	
    }

    public void insert(DeviceRecord record){
    	log.trace("Insert DeviceRecord:{} {}",record.getDeviceId(),record.getStationId());
    	Station station=em.find(Station.class,record.getStationId());     
    	record.setStation(station);
    	List<DeviceRecord> list=em.createQuery("SELECT d FROM DeviceRecord d "
    			+ "WHERE d.deviceId=:DEV_ID and d.stationId=:STATION_ID", DeviceRecord.class)
    	.setParameter("STATION_ID", record.getStationId())
    	.setParameter("DEV_ID", record.getDeviceId())
    	.getResultList();
    	if(list.size()==0){
    		em.persist(record);
    	}else{
    		log.warn("Insert DeviceRecord Fail!");
    	}			    
    }
    
}
