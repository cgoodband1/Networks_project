import java.io.*;
import java.net.*;
import java.util.Date;
 
public class Server {
    String request; 


    //port number(1025-4998) is the only argument needed when running server
    public static void main(String[] args) {
        if (args.length < 1) return;
 
        int port = Integer.parseInt(args[0]);
        //create server connection
        try (ServerSocket serverSocket = new ServerSocket(port)) {
 
            System.out.println("Server is listening on port " + port);
 
            while (true) {
                //socket accepts any client connections
                Socket socket = serverSocket.accept();
 
                System.out.println("New client connected");
                /*
                input and reader read in the request sent in from the client
                variable text is the string request sent from the client 
                */
                String text;
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                text = reader.readLine();
                //print statement used to debug the request being received from client
                //System.out.println(text);

                String date = "date";
                String uptime = "uptime";
                String netstat = "netstat";
                String free = "free";
                String who = "who";
                String ps = "ps";
                /*output and writer are the objects to send the
                request to the server*/
                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);
                //variable for command output
                String out = null;
                

                //compare text(request from client to accepted request)
                if((text.compareTo(date))== 0)
                {
                    //writer sends data from server to client
                    writer.println(new Date().toString());  
                }
                if((text.compareTo(uptime))== 0)
                {
                    /*create runCommand object and set variabl out to the output 
                    returned from executeCommand
                    */
                    RunCommand u = new RunCommand(uptime);
                    out = u.executeCommand(uptime);
                    //writer sends data from server to client
                    writer.println(out);
                }
                if((text.compareTo(netstat))== 0)
                {
                    /*create runCommand object and set variabl out to the output 
                    returned from executeCommand
                    */
                    RunCommand n = new RunCommand(netstat);
                    out = n.executeCommand(netstat);
                    //writer sends data from server to client
                    writer.println(out);
                }
                if((text.compareTo(free))== 0)
                {
                    /*create runCommand object and set variabl out to the output 
                    returned from executeCommand
                    */
                    RunCommand f = new RunCommand(free);
                    out = f.executeCommand(free);
                    //writer sends data from server to client
                    writer.println(out);
                }
                if((text.compareTo(who))== 0)
                {
                    /*create runCommand object and set variabl out to the output 
                    returned from executeCommand
                    */
                    RunCommand w = new RunCommand(who);
                    out = w.executeCommand(who);
                    //writer sends data from server to client
                    writer.println(out);
                }
                if((text.compareTo(ps))== 0)
                {
                    /*create runCommand object and set variabl out to the output 
                    returned from executeCommand
                    */
                    RunCommand p = new RunCommand(ps);
                    out = p.executeCommand(ps);
                    //writer sends data from server to client
                    writer.println(out);
                }  
            }
        //Catch errors
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
        //socket.close();
    }
}