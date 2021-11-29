package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Code.Ticket;

public class TicketTest {
    /*This JUnit test verifies that the product prompts an error when a negative ticket ID is entered.*/
    @Test
    public void IdNegative() {
        Ticket single = new Ticket();
        String result = single.showTicket(-34);
        assertEquals("ERROR: ID must be > 0",result);
    }

    /*This JUnit test verifies that the product prompts an error when a ticket with the ID = 0 is entered.*/
    @Test
    public void IdZero() {
        Ticket single = new Ticket();
        String result = single.showTicket(0);
        assertEquals("ERROR: ID must be > 0",result);
    }

    /*This JUnit test verifies that the product returns a ticket with a valid and existing ticket ID.*/
    @Test
    public void IdExists() {
        Ticket single = new Ticket();
        String result = single.showTicket(7);
        assertEquals("SUCCESS",result);
    }

    /*This JUnit test verifies that the product prompts an error when a ticket ID that does not exist is entered.*/
    @Test
    public void IdDoesNotExist() {
        Ticket single = new Ticket();
        String result = single.showTicket(123456);
        assertEquals("Not Found: ticket does not exist or unable to access",result);
    }

    /*This JUnit test verifies that the product outputs properly with valid page number and valid per page number.*/
    @Test
    public void MultipleValid() {
        Ticket Multiple = new Ticket();
        String result = Multiple.mult(2, 25);
        assertEquals("SUCCESS", result);
        assertEquals(Multiple.ticketArray.length, 25);
    }


    /*This JUnit test verifies that the product divides the tickets into pages of 25 each and the last page includes 1
    ticket.*/
    @Test
    public void MultipleLimit() {
        Ticket Multiple = new Ticket();
        Multiple.mult(1, 25);
        Multiple.mult(2, 25);
        Multiple.mult(3, 25);
        String result = Multiple.mult(5, 25);
        assertEquals("SUCCESS", result);
        assertEquals(Multiple.ticketArray.length, 1);
    }

    /*This JUnit test verifies that the product prompts an error when page 0 is accessed.*/
    @Test
    public void MultiplePage0() {
        Ticket Multiple = new Ticket();
        String result = Multiple.mult(0, 25);
        assertEquals("ERROR: page number must be > 0", result);
    }

    /*This JUnit test verifies that the product prompts an error when page -5 is accessed.*/
    @Test
    public void MultiplePageMinus() {
        Ticket Multiple = new Ticket();
        String result = Multiple.mult(-5, 25);
        assertEquals("ERROR: page number must be > 0", result);
    }

    /*This JUnit test verifies that the product prompts an error when a per page of 0 is accessed.*/
    @Test
    public void Multiple0() {
        Ticket Multiple = new Ticket();
        String result = Multiple.mult(1, 0);
        assertEquals("ERROR: number of records on a per-request must be > 0", result);
    }

    /*This JUnit test verifies that the product prompts an error when a per page of -5 is accessed.*/
    @Test
    public void MultipleMinus() {
        Ticket Multiple = new Ticket();
        String result = Multiple.mult(1, -5);
        assertEquals("ERROR: number of records on a per-request must be > 0", result);
    }

    /*This JUnit test verifies that the product prompts an error when a page that does not exist is accessed.*/
    @Test
    public void pageDNE() {
        Ticket Multiple = new Ticket();
        String result = Multiple.mult(1234, 2);
        assertEquals("Error: no records found", result);
    }

    /*This JUnit test verifies that the product prompts an error when a page beyond the last page is called*/
    @Test
    public void lastPage() {
        Ticket Multiple = new Ticket();
        Multiple.mult(1, 25);
        Multiple.mult(2, 25);
        Multiple.mult(3, 25);
        Multiple.mult(4, 25);
        String result = Multiple.mult(6, 25);
        assertEquals("Error: no records found", result);
    }
}
