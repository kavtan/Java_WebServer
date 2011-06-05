import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


/*Creates a mimetype table to return to the webserver.
**
*/
public class mimeType {

	private HashMap <String, String> mimeTable = new HashMap<String,String>();
	private static boolean runOnce = false;
	
    public mimeType(){
    }
	
	
	public HashMap<String, String> getTable(){
		if(runOnce == false){
			return mimeTable;
		}else{
			return null;
		}
	}
	
	
	
	
	
	public void parseMIME() {
		if(runOnce == false){
	      BufferedReader in = null;

	      String line = "";
	      String type = "";
	      String extention = "";
	      
	      try {
	        in = new BufferedReader(new FileReader("MIME.types"));
	      } catch (FileNotFoundException e) {
	        System.out.println("MIME.types file not found");
	        System.exit(0);
	      }

	      while (true) {
	          try {
	            line = in.readLine();
	          } catch (IOException e) {
	              System.out.println("Exception: " + e.getMessage());
	          }

	          if (line == null) {
	              break;
	          } else if (!line.startsWith("#") && (line.indexOf('\t') != -1)) {
	              type = line.substring(0, line.indexOf('/'));
	              extention = line.substring(line.lastIndexOf('\t') + 1);

	              if (extention.indexOf(' ') == -1) {
	                  mimeTable.put(extention, type);
	              } else {
	                  String[] extentionArray = extention.split(" ");
	                  for (int i = 0; i < extentionArray.length; i++) {
	                      mimeTable.put(extentionArray[i], type);
	                  }
	              }
	          }
	      }
		}
	  }
		
	
	
	
	
	
	
	
	
}
