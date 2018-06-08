 package com.rest2db;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.postgresql.util.PSQLException;

import com.exception.ExceptionProcessor;
import com.jdbc.restwebservice.InsertProcessor;

public class Rest2DbRoute extends RouteBuilder{

	
	@Override
	public void configure() throws Exception {
		
		onException(PSQLException.class, Exception.class).handled(true).log("Exception While inserting messages.").process(new ExceptionProcessor());
		// TODO Auto-generated method stub
		from("timer:learnTimer?period=10s")
        .to("log:?level=INFO&showBody=true")
        .setHeader(Exchange.HTTP_METHOD, constant("GET"))
        .setHeader(Exchange.HTTP_URI, simple("https://restcountries.eu/rest/v2/alpha/us"))
        .to("https://restcountries.eu/rest/v2/alpha/us").convertBodyTo(String.class)
        .to("log:?level=INFO&showBody=true")
        .process(new InsertProcessor())
        .to("jdbc:myDataSource")
        .to("sql:select * from country_capital?dataSource=myDataSource")
        .to("direct:dbOutput");

	}

}
