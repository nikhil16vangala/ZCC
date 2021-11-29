package Test;

import static org.junit.Assert.*;
import org.junit.Test;

import Code.Authentication;

public class AuthenticationTest {
    /*This JUnit test verifies that the product outputs a valid response when a Send Request is inputted without any
    constraints*/
    @Test
    public void sendGetWithExistWithoutParameters() {
        Authentication authenticationReq = new Authentication();
        authenticationReq.sendGet("/api/v2/tickets/74.json", null);
        assertEquals(200, Authentication.responseCode);
    }

    /*This JUnit test verifies that the product outputs a valid response when a Send Request is inputted with
    constraints*/
   @Test
   public void sendGetWithWithParameters() {
       Authentication authenticationReq = new Authentication();
       authenticationReq.sendGet("/api/v2/tickets.json", "?page=1&per_page=2");
       assertEquals(200, Authentication.responseCode);
   }

   /*This JUnit test verifies that the product outputs an error if a method does not exist. In this situation ticket
    with the ID: 15632 does not exist. For that reason, it should prompt a Not Found error.*/
    @Test
    public void sendGetWithNonExistEndpoint() {
        Authentication authenticationReq = new Authentication();
        String result = authenticationReq.sendGet("/api/v2/tickets/145632.json", null);
        assertEquals("Not Found: ticket does not exist or unable to access",result);
    }
}
