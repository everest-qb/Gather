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
public class StationService {
	private Logger log = LoggerFactory.getLogger(StationService.class);
	@PersistenceContext(unitName="DcJpa")
	private EntityManager em;
    
    public StationService() {
      
    }
    
    public List<Station> findAll(){ 
    	log.trace("findAll");
    	return em.createNamedQuery("Station.findAll", Station.class).getResultList();
    }
   
    public Station find(int stationId){ 
    	log.trace("find {}",stationId);
    	return em.find(Station.class, stationId);
    }
    
    public void insert(Station s){
    	em.persist(s);
    }
    
    public void update(Station s){
    	Station station=em.find(Station.class, s.getId());
    	if(station!=null){
    		station.setChangeTime(s.getChangeTime());
    		station.setDescription(s.getDescription());
    		station.setIp(s.getIp());
    		station.setLatitude(s.getLatitude());
    		station.setLongitude(s.getLongitude());
    		station.setName(s.getName());
    		em.merge(station);    		
    	}else{
    		log.warn("Modyfy Error!");
    	}
    }
    
}
