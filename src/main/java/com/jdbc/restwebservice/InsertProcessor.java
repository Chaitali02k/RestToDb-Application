package com.jdbc.restwebservice;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class InsertProcessor implements Processor {

	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		String input = (String) exchange.getIn().getBody();
        System.out.println("Input to be persisted : " + input);

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(input);

        JSONObject jsonObject = (JSONObject) obj;

        String name = (String) jsonObject.get("name");

        String capital = (String) jsonObject.get("capital");

       String insertQuery = "INSERT INTO country_capital values ( ".concat("'").concat(name).concat("'").concat(",").concat("'").concat(capital).concat("'").concat(" )");
        System.out.println("Insert Query is : " + insertQuery);
        exchange.getIn().setBody(insertQuery);


	}

}
