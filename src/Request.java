import java.io.OutputStream;
import java.net.Socket;
import java.util.Hashtable;
import java.util.Vector;



public class Request implements Runnable {

    private String method;
    private String URI;
    private String query;
    private String version;
    private Vector<String> tags = new Vector<String>();
    private static Hashtable<String, String> header = new Hashtable<String, String>();
    private String body;
    private Vector<String> envVars = new Vector<String>();
    private OutputStream out;
    private Hashtable<String, String> mimeTable;
    private Environment env;
    private Socket socket;
	
	
	public Request(){
	
	}
	
	  public void setRequest(OutputStream os, Hashtable<String, String> mime, Environment e, Socket s) {
		  out = os;
		  mimeTable = mime;
		  env = e;
		  socket = s;
}
	
	
	@Override
	public void run() {
		
	}

	
	
    public void parseAndStore(String requestStr) {

        String headers = requestStr.substring(0, requestStr.indexOf("\r\n\r\n"));       // Gets the 1st line.
        String uriTemp = "";
        String[] headersArray;

        method = headers.substring(0, headers.indexOf(" "));                            // Parses the method.

        uriTemp = headers.substring(headers.indexOf(" ") + 1,
                                    headers.indexOf(" ", headers.indexOf(" ") + 1));
        if (uriTemp.indexOf("?") == -1) {                                               // Checks for query.
            URI = uriTemp;
        } else {
            URI = uriTemp.substring(0, uriTemp.indexOf("?"));              // Divides URI part and query part.
            query = uriTemp.substring(uriTemp.indexOf("?" + 1));
        }

        version = headers.substring(headers.indexOf(" ", headers.indexOf(" ") + 1) + 1,  // Parses HTTP version.
                                    headers.indexOf("\r\n"));

        headersArray = headers.substring(headers.indexOf("\r\n") + 2).split("\r\n");    // Temporally stores all headers
                                                                                        // except 1st line into the array.
        for (String s : headersArray) {
            tags.add(s.substring(0, s.indexOf(": ")));                          // Stores each header entry
            header.put(tags.lastElement(), s.substring(s.indexOf(": ") + 2));   // and its content.
        }

        

        if (requestStr.indexOf("\r\n\r\n") == (requestStr.length() - 5)) {      // Checks if the request has a body.
            body = requestStr.substring(requestStr.indexOf("\r\n\r\n") + 4,     // Stores a body.
                                        requestStr.lastIndexOf("\r\n"));
        }
    }

	public String getVersion() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getMethod() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getQuery() {
		// TODO Auto-generated method stub
		return null;
	}
}
