package com.sunteam.dc;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;




@Stateless
@LocalBean
@Path("station")
public class StationService {

	@PersistenceContext(unitName="DcJpa")
	private EntityManager em;
    
    public StationService() {
      
    }
    
    @GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
    public List<Station> findAll(){    	
    	return em.createNamedQuery("Station.findAll", Station.class).getResultList();
    }
   
    @NotNull
    @GET
	@Path("one")
	@Produces(MediaType.APPLICATION_JSON)
    public Station find(@QueryParam("id") int stationId){    	
    	return em.find(Station.class, stationId);
    }
    
}
