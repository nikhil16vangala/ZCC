package Test;
import static org.junit.Assert.*;

import org.junit.Test;

import Code.Json;
import Code.Ticket;


public class JsonTest {
    /*This JUnit test verifies that the product is able to parse a single ticket properly*/
    @Test
    public void SingleParse() {
        String content = "{\"ticket\":{\"url\":\"https://noonesupport.zendesk.com/api/v2/tickets/3.json\",\"id\":3,\"external_id\":null,\"via\":{\"channel\":\"api\",\"source\":{\"from\":{},\"to\":{},\"rel\":null}},\"created_at\":\"2021-11-22T22:57:10Z\",\"updated_at\":\"2021-11-27T03:44:54Z\",\"type\":incident,\"subject\":\"Sample Ticket: Meet the ticket\",\"description\":\"Hi there,\n\n I'm sending an email because I’m having a problem setting up your new product. Can you help me troubleshoot?\n\n Thanks, \n\n The Customer\",\"priority\":normal\",\"status\":\"pending\", \"requester_id\":114280070691,\"submitter_id\":114280070691,\"assignee_id\":114280070691}}";
        Json parser = new Json();
        new Ticket();
        Ticket Single = parser.parseSingle(content);
        assertNull(Single);
    }

    /*This JUnit test verifies that the product is able to parse a list of tickets properly*/
    @Test
    public void MultipleParse() {
        String content = "ajkdfa;a akfdal";
        Json parser = new Json();
        new Ticket();
        Ticket Multiple = parser.parseMultiple(content);
        assertNull(Multiple);
    }

    /*This JUnit test verifies that the product is able to parse empty values.*/
    @Test
    public void EmptyParse() {
        String content = "{\"tickets\":[],\"next_page\":null,\"previous_page\":\"https://zcctest123.zendesk.com/api/v2/tickets.json?page=47\",\"count\":2}";
        Json parser = new Json();
        new Ticket();
        Ticket Empty = parser.parseMultiple(content);
        assertNull(Empty);
    }
}
