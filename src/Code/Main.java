package Code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static final int PAGE = 25;
    public static Ticket single = new Ticket(), multiple = new Ticket();
    public static String response;
    public static boolean next = false, prev = false, Main = true;

    private static void Title()
    {
        System.out.println();
        System.out.println("                                     Zendesk Ticket Viewer                               ");

    }

    private static void Main()
    {
        System.out.println();
        System.out.println("      Type: 'single' to view a single ticket | 'all' to view all tickets | 'exit' to exit");
    }


    private static void PageMenu()
    {
        Main = false;
        System.out.println("\nType: 'next' to move to the next page | 'prev' to move to the previous page | 'single' " +
                "to view a single ticket | 'menu' go back to the menu");
    }

    private static void TicketMenu()
    {
        Main = false;
        System.out.println("\nType: 'menu' to return to home");
    }

    private static void pageNavigation(int page)
    {
        response = multiple.mult(page, PAGE);
        if(!response.equals("SUCCESS"))
            System.out.println(response);
        next = multiple.next;
        prev = multiple.prev;
        PageMenu();
    }

    public static void main(String[] args) {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int page = 1;
        Title();
        while(true)
        {
            try {
                if(Main)
                    Main();
                String Input = input.readLine();
                switch(Input)
                {
                    case "single":
                    {
                        int id = 0;
                        do
                        {
                            System.out.print("Enter ticket ID: ");
                            try{
                                id = Integer.parseInt(input.readLine());
                                if(id>0)
                                {
                                    response = single.showTicket(id);
                                    if(!response.equals("SUCCESS"))
                                        System.out.println(response);
                                }
                                else
                                    System.out.println("The ID does not exist");
                            }
                            catch(NumberFormatException e){
                                System.out.println("The ID does not exist");
                            }
                        } while(id<=0);
                        TicketMenu();
                        break;
                    }
                    case "all":
                    {
                        pageNavigation(page);
                        break;
                    }
                    case "next":
                    {
                        if(next)
                        {
                            page++;
                            pageNavigation(page);
                        }
                        else
                            System.out.println("Error: This is the last page of tickets");
                        break;
                    }
                    case "prev":
                    {
                        if(prev && page>1)
                        {
                            page--;
                            pageNavigation(page);
                        }
                        else
                            System.out.println("Error: This is the first page of tickets");
                        break;
                    }
                    case "menu":
                    {
                        Main = true;
                        page = 1;
                        break;
                    }
                    case "exit":
                    {
                        System.out.println("Thank you for using Ticket Viewer");
                        return;
                    }
                    default:
                        System.out.println("ERROR: Wrong input");
                }
            }
            catch (IOException e) {
                System.out.println("ERROR: Internal error");
            }
        }
    }
}
