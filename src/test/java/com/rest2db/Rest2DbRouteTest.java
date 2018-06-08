package com.rest2db;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;

public class Rest2DbRouteTest extends CamelTestSupport{

	@Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new Rest2DbRoute();
    }
	
	 @Override
	    public CamelContext createCamelContext() {

	        String url = "jdbc:postgresql://localhost:5432/localDB";
	        DataSource dataSource = setupDataSource(url);

	        SimpleRegistry registry = new SimpleRegistry();
	        registry.put("myDataSource",dataSource);

	        CamelContext context = new DefaultCamelContext(registry);
	        // plug in a seda component, as we don't really need an embedded broker
	        return context;
	    }

	 private static DataSource setupDataSource(String connectURI) {
	        BasicDataSource ds = new BasicDataSource();
	        ds.setUsername("postgres");
	        ds.setDriverClassName("org.postgresql.Driver");
	        ds.setPassword("restdb");
	        ds.setUrl(connectURI);
	        return ds;
	    }
	 
	/* @Test
	    public void rest2dbroute(){

	        ArrayList responseList =  consumer.receiveBody("direct:dbOutput", ArrayList.class);
	        System.out.println("responseList : " + responseList.size());

	        assertNotEquals(0, responseList.size());

	    }
*/
	    @Test
	    public void rest2dbroute_exception(){

	        ArrayList responseList =  consumer.receiveBody("timer:learnTimer", ArrayList.class);

	        assertNull(responseList);

	    }
}
