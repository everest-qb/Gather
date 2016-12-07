package com.sunteam.dc.rest;

import java.util.List;

import javax.inject.Inject;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.sunteam.dc.DeviceRecord;
import com.sunteam.dc.Rank;
import com.sunteam.dc.RecordService;

@Path("data")
public class RsRecord {

	@Inject
	private RecordService service;
	
    @GET
	@Path("sta")
	@Produces(MediaType.APPLICATION_JSON)    
    public List<DeviceRecord> findByStation(@QueryParam("id") int stationId){
    	  return service.findByStation(stationId);
    }
    
    @GET
	@Path("dev")
	@Produces(MediaType.APPLICATION_JSON)
    public List<DeviceRecord> findByDevice(@QueryParam("id") String devId){
    	
    	return service.findByDevide(devId);
    }
    
    @GET
	@Path("top")
	@Produces(MediaType.APPLICATION_JSON)
    public List<Rank> findRank(@QueryParam("limit") @Max(30) int limit){
    	
    	return service.findRank(limit);    	
    }
    
    @GET
	@Path("one")
	@Produces(MediaType.APPLICATION_JSON)
    @NotNull
    public DeviceRecord find(@QueryParam("id") int stationId){
    	
    	return service.find(stationId);    	
    }
    
    @POST
   	@Path("pick")
    @Consumes(MediaType.APPLICATION_JSON)
    public void insert(@NotNull DeviceRecord record){
    	service.insert(record);    
    }
}
