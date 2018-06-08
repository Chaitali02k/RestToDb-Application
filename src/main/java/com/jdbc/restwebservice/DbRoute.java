package com.jdbc.restwebservice;

import org.apache.camel.builder.RouteBuilder;

public class DbRoute extends RouteBuilder {

	public void configure() throws Exception {

		// onException(PSQLException.class).handled(true).log("Exception While inserting
		// messages.");

		from("direct:dbInput").to("log:?level=INFO&showBody=true").process(new InsertProcessor())
				.to("jdbc:myDataSource").to("sql:select * from country_capital?dataSource=myDataSource")
				.to("log:?level=INFO&showBody=true");

	}

}
