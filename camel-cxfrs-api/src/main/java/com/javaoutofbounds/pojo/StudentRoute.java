package com.javaoutofbounds.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class StudentRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {

		from("cxfrs:bean:cxfrsRestApiEndpoint")
		.process(new Processor() {

			@Override
			public void process(Exchange exchange) throws Exception {

				final Map<String, StudentDetails> studentMap = new HashMap<>();

				final StudentDetails sd1 = new StudentDetails();
				sd1.setId("101");
				sd1.setName("Sam Wood");
				sd1.setGrade("B");
				sd1.setMarks(65.78);
				studentMap.put("101", sd1);

				final StudentDetails sd2 = new StudentDetails();
				sd2.setId("102");
				sd2.setName("Peter Russel");
				sd2.setGrade("D");
				sd2.setMarks(0.0);
				studentMap.put("102", sd2);

				final String id = exchange.getIn().getBody(String.class);
				if(null != id && !"".equals(id) && null != studentMap.get(id)){

					exchange.getOut().setBody(studentMap.get(id));

				}else if(exchange.getIn().getHeader(Exchange.HTTP_URI).toString().contains("getAll")){

					exchange.getOut().setBody(new ArrayList<StudentDetails>(studentMap.values()));
				}else{
					exchange.getOut().setBody("Fault : Request Validation Failed");
				}
			}
		});
	}
}
