package com.javaoutofbounds.pojo;

import java.util.concurrent.TimeUnit;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.redis.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentRoute extends RouteBuilder{

	@Autowired
	RedisClient redisClient;

	@Override
	public void configure() throws Exception {

		from("cxfrs:bean:cxfrsRestApiEndpoint")
		.process(new Processor() {

			public void process(Exchange exchange) throws Exception {

				// Read exchange body as input
				final String name = exchange.getIn().getBody(String.class);
				// Check if name exists in Redis Cache else push in cache
				if(null == redisClient.get(name)){
					redisClient.setex(name, "Hello "+name, new Long(30000), TimeUnit.MILLISECONDS);
				}
				// Set output in exchange body fetching data from Redis cache
				exchange.getOut().setBody(redisClient.get(name));
			}
		});
	}

}
