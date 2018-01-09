package com.javaoutofbounds.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class StudentResource {

	public Map<String, StudentDetails> studentMap = new HashMap<>();

	StudentResource(){

		final StudentDetails sd1 = new StudentDetails();
		sd1.setId("101");
		sd1.setName("Sam Wood");
		sd1.setGrade("B");
		sd1.setMarks(65.78);
		studentMap.put("101", sd1);

		final StudentDetails sd2 = new StudentDetails();
		sd2.setId("102");
		sd2.setName("Anna Ellis");
		sd2.setGrade("A");
		sd2.setMarks(85.78);
		studentMap.put("102", sd2);

		final StudentDetails sd3 = new StudentDetails();
		sd3.setId("103");
		sd3.setName("Peter Russel");
		sd3.setGrade("D");
		sd3.setMarks(0.0);
		studentMap.put("103", sd3);
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public StudentDetails getStudent(@PathParam("id")String id){
		return studentMap.get(id);
	}

	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public List<StudentDetails> getAllStudents() {
		return new ArrayList<StudentDetails>(studentMap.values());
	}
}
