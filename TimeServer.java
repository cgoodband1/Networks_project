import java.io.*;
import java.net.*;
import java.util.Date;
 
public class TimeServer {
    //String request; 


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
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                String request = reader.readLine();
                //print statement used to debug the request being received from client
                //System.out.println(request);

                /*output and writer are the objects to send the
                request to the server*/
                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);
                //variable for command output
                String out = null;

                switch(request){
                    case "1": 
                    writer.println(new Date().toString()); 
                    writer.flush();
                    case "2":
                    RunCommand u = new RunCommand("uptime");
                    out = u.executeCommand("uptime");
                    //writer sends data from server to client
                    writer.println(out);
                    writer.flush();
                    case "3":
                    RunCommand n = new RunCommand("free");
                    out = n.executeCommand("free");
                    //writer sends data from server to client
                    writer.println(out);
                    writer.flush();
                    case "4":
                    RunCommand f = new RunCommand("netstat");
                    out = f.executeCommand("netstat");
                    //writer sends data from server to client
                    writer.println(out);
                    writer.flush();
                    case "5":
                    RunCommand w = new RunCommand("who");
                    out = w.executeCommand("who");
                    //writer sends data from server to client
                    writer.println(out);
                    writer.flush();
                    case "6":
                    RunCommand p = new RunCommand("ps");
                    out = p.executeCommand("ps");
                    //writer sends data from server to client
                    writer.println(out);
                    writer.flush();
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