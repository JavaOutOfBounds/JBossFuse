package com.javaoutofbounds.pojo;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import com.javaoutofbounds.HelloStudentRequest;
import com.javaoutofbounds.HelloStudentResponse;

public class StudentRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {

		from("cxf:bean:cxfSoapServiceEndpoint")
		.process(new Processor() {

			public void process(Exchange exchange) throws Exception {

				final HelloStudentRequest helloStudentRequest = exchange.getIn().getBody(HelloStudentRequest.class);

				final HelloStudentResponse studentResponse = new HelloStudentResponse();
				studentResponse.setOutputResult(helloStudentRequest.getInputName().concat(", Welcome to JavaOutOfBounds.com"));

				exchange.getOut().setBody(studentResponse);
			}
		});
	}
}
