package Code;

import java.net.Authenticator;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import javax.xml.bind.DatatypeConverter;

public class Authentication {
    public static final String username = System.getenv("user");
    public static final String password = System.getenv("password");
    public static final String subdomain = System.getenv("subdomain");
    public static int responseCode = 0;

    public String sendGet(String method, String urlParameters)
    {
        HttpURLConnection http = null;
        String targetUrl = subdomain  + method;

        try
        {
            URL object = new URL(targetUrl + urlParameters);
            http = (HttpURLConnection) object.openConnection();
            http.setRequestProperty("Accept", "application/json");
            Authenticator.setDefault (new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication (username, password.toCharArray());
                }
            });
            byte[] message = (username + ":" + password).getBytes(StandardCharsets.UTF_8);
            String encoding = DatatypeConverter.printBase64Binary(message);
            http.setRequestProperty("Authorization", "Basic " + encoding);
            http.setDoOutput(true);
            http.setRequestMethod("GET");

            responseCode = http.getResponseCode();
            if (responseCode < 300 && responseCode > 199)
            {
                BufferedReader reader = new BufferedReader(new InputStreamReader(http.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = reader.readLine()) != null)
                    response.append(inputLine);
                reader.close();

                return response.toString();
            }
            else
                return printErrorMessage(responseCode);
        }

        catch (Exception e) {
            return "ERROR: Unable to connect to the URL";
        }
        finally
        {
            if (http != null)
                http.disconnect();
        }
    }

    public String printErrorMessage(int code)
    {
        switch(code)
        {
            case 400:
                return "Bad Request - the HTTP request that was sent to the server has invalid syntax";
            case 401:
                return "Unauthorized - the user trying to access the resource has not been authenticated";
            case 403:
                return "Forbidden - the user made a valid request but the server is refusing to serve the request";
            case 404:
                return "Not Found: ticket does not exist or unable to access";
            case 500:
                return "Internal Server Error - the server cannot process the request for an unknown reason";
            case 503:
                return "Service Unavailable - the server is overloaded or under maintenance";
            case 504:
                return "Gateway Timeout - the server is a not receiving a response from the backend servers within the allowed time period";
            default:
                return "Unknown error occured";
        }
    }
}
