package com.javaoutofbounds.pojo;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;

public class AMQRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {

		from("cxfrs:bean:cxfrsRestApiEndpoint")
		.convertBodyTo(String.class, "UTF-8")
		// Below line sends the message to AMQ queue JavaOutOfBoundsQueue
		.to(ExchangePattern.InOnly, "activemq:queue:JavaOutOfBoundsQueue")
		.log("Messsage sent to AMQ Queue :: ${body}")
		// Below line reads the message from the AMQ queue JavaOutOfBoundsQueue
		.from("activemq:queue:JavaOutOfBoundsQueue")
		.log("Messsage read from AMQ Queue : ${body}")
		.transform(simple("Hello ${body}, Welcome to JavaOutOfBounds.com"))
		.convertBodyTo(String.class, "UTF-8");
	}
}
