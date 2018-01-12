package com.javaoutofbounds.pojo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

public class StudentResource {

	@GET
	@Path("/{name}")
	public String getStudent(@PathParam("name")String name){
		return null;
	}
}
