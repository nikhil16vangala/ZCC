package Code;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Json {
    public static final int INT = 25;
    public static String failure;
    public Ticket parseSingle(String jsonContent)
    {
        JSONParser jsonParser = new JSONParser();
        try
        {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonContent);
            JSONObject Format = (JSONObject) jsonObject.get("ticket");
            if(Format.isEmpty())
            {
                failure = "Error: no record found";
                return null;
            }
            return parseFormat(Format);
        }
        catch (Exception e)
        {
            failure = "Internal Error: internal error occurs while processing your request";
            return null;
        }
    }

    public Ticket parseMultiple(String jsonContent)
    {
        Ticket ticketList = new Ticket();
        JSONParser json = new JSONParser();

        try
        {
            JSONObject jsonObject = (JSONObject) json.parse(jsonContent);
            JSONArray ticketArray = (JSONArray)  jsonObject.get("tickets");
            if(ticketArray.isEmpty())
            {
                failure = "Error: no records found";
                return null;
            }
            if(ticketArray.size()< INT)
                ticketList.ticketArray = new Ticket[ticketArray.size()];
            else
                ticketList.ticketArray = new Ticket[INT];
            for(int i = 0; i<ticketArray.size() && i< INT; i++)
            {
                JSONObject Format = (JSONObject)ticketArray.get(i);
                Ticket t = parseFormat(Format);
                if (t == null)
                {
                    failure = "Error: no records found";
                    return null;
                }
                ticketList.ticketArray[i]  = t;
            }
            parseNavigationFlags(jsonObject,ticketList);

            return ticketList;
        }
        catch (Exception e)
        {
            failure = "Internal Error: internal error";
            return null;
        }
    }

    private Ticket parseFormat(JSONObject format)
    {
        Ticket single = new Ticket();
        if(format == null)
            return null;
        single.url = (String)format.get("url");
        single.id = (long)format.get("id");
        single.externalId = (String)format.get("external_id");
        single.dateCreated = (String)format.get("created_at");
        single.dateUpdated = (String)format.get("updated_at");
        single.description = (String) format.get("description");
        single.type = (String)format.get("type");
        single.subject = (String)format.get("subject");
        single.priority = (String) format.get("priority");
        single.status = (String) format.get("status");
        single.requesterId = (long) format.get("requester_id");
        if(format.get("submitter_id") != null)
            single.submitterId = (long) format.get("submitter_id");
        if (format.get("assignee_id")!= null)
            single.assigneeId = (long) format.get("assignee_id");
        return single;
    }

    private void parseNavigationFlags(JSONObject jsonObject, Ticket multiple)
    {
        String next_page = (String) jsonObject.get("next_page");
        String previous_page = (String) jsonObject.get("previous_page");
        multiple.next = next_page != null;
        multiple.prev = previous_page != null;
    }
}
