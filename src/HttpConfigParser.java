import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HttpConfigParser {

	private static HttpConfigParser Config; 
	private static HashMap<String, String> map = new HashMap<String, String>(); 
	private String file = "httpd.conf";
	
	public HttpConfigParser() throws IOException{
		open();
	}

	private void open() throws IOException{
		 String line;
		 StringTokenizer currentLine;
			try {
				BufferedReader BR = new BufferedReader(new FileReader(file));
				
				while((line = BR.readLine()) != null){
					if(!line.startsWith("#") && !line.contentEquals("")){
						 currentLine = new StringTokenizer(line);
						 readAndStore(currentLine,line);
					}
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
		
			
		}
	
	public void readAndStore(StringTokenizer currentline, String line){
		String key,value;
		if(line.contains("Alias") || line.contains("ScriptAlias")){
			currentline.nextToken();
			key = currentline.nextToken();
			value = currentline.nextToken();
			if(value.contains("$SERVER")){
					map.put(key, changePath(value));
			}
			else{
					map.put(key, value);
			}
			
		}else{
			key = currentline.nextToken();
			value = currentline.nextToken();
			if(value.contains("$SERVER")){
				value = changePath(value);				
			}
			map.put(key, value);
		}
	}
	
	private String changePath(String path){
		path = path.replaceFirst(Pattern.quote("$SERVER"), Matcher.quoteReplacement(System.getProperty("user.dir")));
		path = path.substring(1,path.length()-1);
		return path;
	}

	public String getPort(){
		return map.get("Listen");
	}
	
	
	
	public void print(){
		for (Entry<String, String> entry : map.entrySet()) {
			System.out.println(entry.getKey()+": "+entry.getValue());
		}

	}

	public String getValue(String key) {
		return (String)map.get(key);
	}
	
}
