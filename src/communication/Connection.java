package communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

import api.commands.CommandTester;

import requests.Request;
import util.RequestException;

/**
 * Class representing a connection to a server and the
 * communication between server-client
 *
 * @author Desktop
 * @version 1.0
 *
 */
public class Connection {


    private static final int READ_TIMEOUT_MS = 2000;
    private static final int CONNECTION_TIMEOUT_MS = 3000;


    /** The IP-adress to the Server */
    private String ip;

    // TODO: skapa konstanter f�r response/status-code?
    /** HTML status code */
    private int responseCode;

    /** The response message of a request */
    private String responseBody;

    private HttpsURLConnection connection;

    /**
     * Constructs a new Connection object to a server with a given IP address,
     * and a given GUI.
     * @param ip The IP address
     */
    public Connection(String ip) {

        if(!ip.startsWith("https://")) {
            ip = "https://" + ip;
        }

        this.ip = ip;
        responseBody = "";
        responseCode = 0;
    }

    /**
     * Sends a REST-request to the connected server and processes the response.
     *
     * @param request The request to be sent
     * @param token A unique identifier of the user
     * @param type The type of request (JSON or PLAIN_TEXT)
     * @throws RequestException if an error occurs
     *              (i.e HTTP response code is > 201)
     */
    public void sendRequest(Request request, String token, String type)
            throws RequestException {
        try {

            connect(request, token, type);

            if (type.equals("application/json")) {
                PrintWriter outputStream = new PrintWriter(
                        connection.getOutputStream(), true);
                outputStream.println(request.toJson());
                outputStream.flush();
            }

            responseCode = connection.getResponseCode();
            fetchResponse(connection.getInputStream());

            if (responseCode >= 300) {

                connection.disconnect();
                throw new RequestException(responseCode, responseBody);
            }
            connection.disconnect();
        } catch (IOException e) {

            if(responseCode > 200){
                throw new RequestException(responseCode, responseBody + " - message: " + e.getMessage());
            }

            try {
                InputStream inputStream = connection.getErrorStream();
                if (inputStream != null) {
                    fetchResponse(connection.getErrorStream());

                    if(responseCode>=300){
                        connection.disconnect();
                        throw new RequestException(responseCode, responseBody);
                    }
                }
            } catch (IOException e1) {

                connection.disconnect();
                throw new RequestException(responseCode, responseBody);
            }
            connection.disconnect();
        }
    }

    private void connect(Request request, String token, String type)
            throws IOException {

        URL url = new URL(ip + request.url);


        connection = (HttpsURLConnection) url.openConnection();


        if (type.equals("application/json")) {
            connection.setDoOutput(true);
        }
        connection.setReadTimeout(READ_TIMEOUT_MS);
        connection.setConnectTimeout(CONNECTION_TIMEOUT_MS);
        connection.setRequestMethod(request.requestType);
        connection.setRequestProperty("Content-Type", type);

        if (!CommandTester.token.isEmpty()) {
            connection.setRequestProperty("Authorization", CommandTester.token);
        }

        connection.connect();

    }

    /**
     * Builds a response body from the connection input stream
     *
     * @param inputStream
     *            the connection input stream
     * @throws IOException
     *             If an I/O error occurs
     */
    private void fetchResponse(InputStream inputStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                inputStream));
        String buffer;
        StringBuilder output = new StringBuilder();
        while ((buffer = in.readLine()) != null) {
            output.append(buffer);
        }
        responseBody = output.toString();
    }

    /**
     * Returns the response code of a request
     *
     * @return The response code
     */
    public int getResponseCode() {
        return responseCode;
    }

    /**
     * Returns the response body of a request
     *
     * @return The response body
     */
    public String getResponseBody() {
        return responseBody;
    }


}

