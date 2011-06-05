import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;


public class Authorization {
	   private String path = null;
	   private String pathHT = null;
	   private Boolean state = false;
	   private File file = null;
	   private static HashMap htfile = new HashMap();
	   
	   public Authorization(String path){
		   this.path = path;
	   }
	   
	   
	    /**
	     * This method checks for the htaccess files in the current directory and
	     * all directories before it.
	     * @return  boolean to see if the htaccess file is in one of the directory
	     * @throws java.io.FileNotFoundException
	     * @throws java.io.IOException
	     */
	    public boolean checkAccess() throws FileNotFoundException, IOException{

	        do {
	              File checker = new File(path + "/.htaccess");

	              if(checker.exists()){
	                 state = true;
	                 pathHT = checker.getAbsolutePath();
	                 createMap(pathHT);
	                 return state;
	              }
	              else if(!path.contentEquals(System.getProperty("user.dir")))
	              {
	                  path = path.substring(0,path.lastIndexOf('/'));
	              }



	          }while(!path.contentEquals(System.getProperty("user.dir")));

	        return state;

	    }
	   
	    /**
	     * This decrypths the user:password string. Then it looks for the user
	     * file that contains the user:password files. Reads the lines and compares
	     * them to the the decrypted user:password string. Returns true if it does
	     * find the user in the file and false if not.
	     *
	     * @param code the decypted user:password string.
	     * @return
	     * @throws webserver.Base64FormatException
	     * @throws java.io.FileNotFoundException
	     * @throws java.io.IOException
	     */ 
	    public boolean authorized(String code) throws Base64FormatException, FileNotFoundException, IOException{
	        boolean pass = false;
	        String temp = code;
	        temp = temp.substring(temp.indexOf(" "),temp.length());
	        Base64Decoder Decoder  = new Base64Decoder(temp);
	        temp = Decoder.processString();
	        String line = null;
	        String AuthUserFile = (String) htfile.get("AuthUserFile");


	        //Read the file

	        AuthUserFile = AuthUserFile.replace("$SERVER", System.getProperty("user.dir"));
	        BufferedReader passwordfile = new BufferedReader(new FileReader(AuthUserFile));

	        while((line = passwordfile.readLine()) != null){
	            if(line == temp){
	                pass = true;
	            }
	        }

	        return pass;
	    }
	    
	    
	    /**
	     * Takes the Htaccess file path and creates a hashmap for everything in it.
	     * @param pathHT The absolute path of the htaccess file
	     * @throws java.io.FileNotFoundException
	     * @throws java.io.IOException
	     */
	    public void createMap(String pathHT) throws FileNotFoundException, IOException{
	        BufferedReader access = new BufferedReader(new FileReader(pathHT));
	        String line;
	        while((line = access.readLine()) != null){
	            StringTokenizer token = new StringTokenizer(line);
	            htfile.put(token.nextToken(),token.nextToken());
	        }
	    }
}
