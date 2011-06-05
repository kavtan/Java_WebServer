import java.io.IOException;


public class main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		WebServer ws = WebServer.getServer();
		
		ws.run();
	}

}
