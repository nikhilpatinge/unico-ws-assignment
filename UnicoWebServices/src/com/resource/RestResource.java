package com.resource;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.bean.BIBean;

@Stateless
@LocalBean
@Path("rest")
public class RestResource {
    @SuppressWarnings("unused")
    @Context
    private UriInfo context;

    @EJB
    BIBean bean;

    public RestResource() {
        // TODO Auto-generated constructor stub
    }

    //1. push method
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("push/{i1}/{i2}")
    public Response push(@PathParam("i1") int i1,@PathParam("i2") int i2) {
    	//1. Push into the Queue
    	System.out.println("rest:Push called");
    	int status=bean.pushInQueue(i1, i2);
    	System.out.println("rest:Push serviced");
    	//return status;
    	if(status==200)
    	return Response.status(200).entity("OK").build();
    	else
    	return Response.status(500).entity("Internal Error").build();	
    }

    //2. Method for getting the list of all the integers entered so far in order.
    @GET 
    @Produces(MediaType.APPLICATION_JSON)
    @Path("list")
    public String list(){
    	System.out.println("rest:list called");
    	String list=bean.getList();
    	System.out.println("rest:list serviced");
    	return list;
    }
    
    //Method for Testing - QueueBrowser
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("browseQueue")
    public String browseQueue(){
    	String queue=bean.browseQueue();
    	return queue;
    }
  
}