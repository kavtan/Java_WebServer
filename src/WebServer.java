import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;


public class WebServer {

	private static WebServer server;
	private static boolean isAvailable = true;
	private static HttpConfigParser Config;
	private static String port;
	private static mimeType mimeTable;
	
	private WebServer() throws IOException{
	  Config = new HttpConfigParser();
	  port = Config.getPort();
	  mimeTable = new mimeType();
	  
	}
	
	public static WebServer getServer() throws IOException{
		if(isAvailable){
			if(server == null){
				server = new WebServer();
			}
			isAvailable = false;
			return server;
		}else{
			return null;
		}
		

	}
	
	public void run(){
	    ServerSocket sSocket = null;
	    Socket socket;
	    InputStream in;
	    OutputStream out;
	    boolean getMoreRequest = true;
		
		if(Config == null){
			System.exit(0);
		}
		
		mimeTable.parseMIME();
			

	    
	   
	    /*
	    try {
	        sSocket = new ServerSocket(Integer.parseInt(Config.getPort()));
	        System.out.println("Listening to Port: " + Config.getPort());
	    } catch (IOException e) {
	        System.out.println("Exception: " + e.getMessage());
	    }
	    
	    System.out.println("Waiting for a Request from Client");
		while (getMoreRequest) {
	        try {
	            socket = sSocket.accept();
	            in = socket.getInputStream();
	            out = socket.getOutputStream();

	            Request req = new Request();
	            req.parse(in);


	            if (req.getURI().equals(shutDown)) {    // Checks for shutdown.
	                getMoreRequest = false;
	                System.out.println("Shutting Down Webserver!");
	                continue;
	            }

	            res = new Response(out);

	            env = new Environment(Config, req, socket, sSocket);

	            req.setRequest(out, mimeTable, env, socket);

//	            res.processRequest(req, mimeTable, env);
	            Thread thered = new Thread(req);
	            thered.start();

//	            socket.close();
	            System.out.println("Receiving Request");
	            res.writeToLog(socket.getInetAddress().getHostAddress(),
	                           socket.getInetAddress().getHostName(),
	                           Config.getLog(),req); // Passes information to the Response class so that It can write


	        } catch (IOException e) {
	            System.out.println("Exception: " + e.getMessage());
	        }
		}*/
		
		
	}
	
	public void getAvailable(){
		System.out.print(isAvailable);
	}

}