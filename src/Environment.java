
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 */
public class Environment {

    Vector<String> variables = new Vector<String>();

    public Environment(HttpConfigParser c, Request d, Socket e,ServerSocket f) throws UnknownHostException{
        String[] env;
        
        
        variables.addElement("SERVER_ADDR=" + e.getLocalAddress().getHostAddress());
        System.out.println("SERVER_ADDR " + e.getLocalAddress().getHostAddress());//Gets the Server IP by checking local address. Which is the server IP
        variables.addElement("SERVER_ADMIN=" + c.getValue("ServerAdmin"));
        variables.addElement("SERVER_NAME=" + f.getInetAddress().getHostName());
        variables.addElement("GATEWAY_INTERFACE=CGI/1.1");
        variables.addElement("DOCUMENT_ROOT=" + c.getValue("ServerRoot"));
        variables.addElement("SERVER_PROTOCOL=" + d.getVersion());
        variables.addElement("SERVER_PORT=" + c.getPort());
        variables.addElement("REQUEST_METHOD=" + d.getMethod());
        variables.addElement("PATH_TRANSLATED=" );
        variables.addElement("PATH_INFO=" );
        variables.addElement("QUERY_STRING=" + d.getQuery());
        variables.addElement("AUTH_TYPE=");
        variables.addElement("CONTENT_TYPE=");
        variables.addElement("CONTENT_LENGTH=");


    }

//    public String findPath(){

//    }

    public Vector<String> getVariables() {
        return variables;
    }
}
