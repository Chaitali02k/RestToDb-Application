package restwebservice;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class RestRouteTest extends CamelTestSupport {

//	@Override
//	protected RouteBuilder createRouteBuilder() throws Exception {
//		return (RouteBuilder) new RestRoute();
//	}
//
//	@Test
//	public void restCall() {
//
//		String response = template.requestBody("direct:restCall", "US", String.class);
//		System.out.println("response : " + response);
//		assertNotNull(response);
//	}
	@Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RestRoute();
    }
	
	@Test
    public void restCall(){

      String response =   template.requestBody("direct:restCall","US",String.class);
        System.out.println("response : " + response);
        assertNotNull(response);
    }
	
	
	

}
