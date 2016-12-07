package com.sunteam.dc.rest;

import java.util.List;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.sunteam.dc.Station;
import com.sunteam.dc.StationService;
import com.sunteam.dc.atmosphere.Push;


@Path("station")
public class RsStation {
	
	@Inject
	private StationService service;

    @GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
    public List<Station> findAll(){     	
    	return service.findAll();
    }
   
    @NotNull
    @GET
	@Path("one")
	@Produces(MediaType.APPLICATION_JSON)
    public Station find(@QueryParam("id") int stationId){ 
    	
    	return service.find(stationId);
    }
    
    @POST
   	@Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    public void insert(Station s){
    	service.insert(s);
    }
    
    @POST
   	@Path("modify")
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(Station s){
    	Push.send("Station Modify");
    	service.update(s);
    }
}
