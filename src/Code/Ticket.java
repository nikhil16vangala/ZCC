package Code;

import java.util.ArrayList;


public class Ticket {
    public static final String method = "/api/v2/tickets/";
    long id, requesterId, submitterId, assigneeId;
    String url, externalId, type, subject, description, priority, status, dateCreated, dateUpdated;
    public Ticket[] ticketArray;
    public boolean prev = false;
    public boolean next = false;

    public String mult(int page, int perPage)
    {
        if(page<1)
            return "ERROR: page does not exist";
        if(perPage<1)
            return "ERROR: the page does not have valid number of records";

        System.out.println("\nTickets being fetched...");
        Authentication authenticationRequest = new Authentication();
        String parameters = "?page=" + page + "&per_page=" + perPage;
        String response = authenticationRequest.sendGet(method, parameters);

        if(Authentication.responseCode> 199 && Authentication.responseCode < 400)
        {
            Json jsonParser = new Json();
            Ticket ticketList = jsonParser.parseMultiple(response);
            if(ticketList == null)
                return Json.failure;
            this.ticketArray = ticketList.ticketArray;
            this.next = ticketList.next;
            this.prev = ticketList.prev;
            displayList(page);

            return "SUCCESS";
        }
        return authenticationRequest.printErrorMessage(Authentication.responseCode) ;
    }

    private void displayList(int page)
    {
        System.out.format("\n %66s \n", " Page " + page );
        displayHeadline();
        for (Ticket ticket : ticketArray) displayInformation(ticket);
    }

    private void displayInformation(Ticket ticket)
    {
        String str = ticket.subject;
        if (str.length() >50)
            str = str.substring(0,46) + "...";
        System.out.format(" %-2s  %-8s  %-45s  %-8s  %-7s  %-20s  %-20s \n", ticket.id+"", ticket.type, str, ticket.priority, ticket.status, ticket.dateCreated, ticket.dateUpdated);
    }

    private void displayHeadline()
    {
        System.out.format(" %-2s  %-8s  %-45s  %-8s  %-7s  %-20s  %-20s \n", "ID", "Type", "Subject", "Priority", "Status", "Created", "Updated");
        System.out.println(" ----------------------------------------------------------------------------------------------------------------------------------");
    }

    public String showTicket(int id)
    {
        if(id<=0)
            return "ERROR: ID must be > 0";

        System.out.println("\nTicket being fetched...");
        Authentication authenticationRequest = new Authentication();
        String url = method + id + ".json";
        String response = authenticationRequest.sendGet(url, null);
        if(Authentication.responseCode> 199 && Authentication.responseCode < 400)
        {
            Json jsonParser = new Json();
            Ticket ticket = jsonParser.parseSingle(response);
            if(ticket == null)
                return Json.failure;

            ticket.displayInformation();
            return "SUCCESS";
        }

        return authenticationRequest.printErrorMessage(Authentication.responseCode);
    }

    private void displayInformation()
    {
        System.out.format("\n %66s \n", "Ticket Id:" + this.id);
        System.out.format(" Subject: %-82s  Date Created: %-25s \n", this.subject, this.dateCreated);
        System.out.format("%-132s\n Description: %-116s  \n", " ","");

        int length=this.description.length();

        if(length <= 130)
            System.out.format(" %-130s \n", this.description);
        ArrayList<String> descriptionFormat = textFormat (this.description);
        for (String s : descriptionFormat) {
            System.out.format(" %-130s \n", s);
        }

        System.out.format("%-132s\n Requester id: %-28s  Submitter id: %-33s  Assignee id: %-24s \n", "", this.requesterId, this.submitterId, this.assigneeId);
    }


    private ArrayList<String> textFormat(String s)
    {
        ArrayList<String> result = new ArrayList<>();
        s = s.replace("\n", "");
        String[] wordsArray = s.split(" ");
        String line = "";
        for(int i=0; i<wordsArray.length; i++)
        {
            if(line.length() + wordsArray[i].length() <= 130 -1)
                line = line.concat(wordsArray[i] + " ");
            else
            {
                i--;
                result.add(line);
                line = "";
            }
        }
        result.add(line);
        return result;
    }
}
