import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
 
public class Client {
     static long start;
    // first argument when running is Hostname 139.62.210.153 
    //second argument is the port that the server is running on
    public static void main(String[] args) {
        if (args.length < 2) return;
        
        String hostname = args[0];
        int port = Integer.parseInt(args[1]);

        //print out request option list and set request variable
        String request;
        Scanner obj = new Scanner(System.in); /* create a object */ 
        System.out.println("1. Date");
        System.out.println("2. Uptime");
        System.out.println("3. Memory Use");
        System.out.println("4. Netstat");
        System.out.println("5. Current Users");
        System.out.println("6. Running Process");
        //System.out.println("7. Exit");
        request = obj.nextLine(); 

        //print out request for the amount of servers spawned and set variable
        System.out.println("Enter how many clients to ");

        //create connection to the server
        try (Socket socket = new Socket(hostname, port)) {
                
                
                /*output stream and printwriter are the objects to send the
                request to the server*/
                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);

                //switch case to choose which request to send to the server
                switch(request){
                    case "1": 
                    //start is the start of the timer
                    start = System.currentTimeMillis();
                    //writer.println sends "date to the server"
                    writer.println("date");
                    case "2":
                    start = System.currentTimeMillis();
                    writer.println("uptime");
                    case "3":
                    start = System.currentTimeMillis();
                    writer.println("free");
                    case "4":
                    start = System.currentTimeMillis();
                    writer.println("netstat");
                    case "5":
                    start = System.currentTimeMillis();
                    writer.println("who");
                    case "6":
                    start = System.currentTimeMillis();
                    writer.println("ps");
                }
                //input and reader accept the information returned from the server
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                //ret is the string sent from the server
                String ret = reader.readLine();
                //end stops the timer 
                long end = System.currentTimeMillis(); 
                /*print out the information returned and the turn around time
                information returned does not need to be printed out based on 
                requiremnts*/
                System.out.println(ret);
                System.out.println((end - start) + "ms");

        //catch any errors
        } catch (UnknownHostException ex) {
 
            System.out.println("Server not found: " + ex.getMessage());
 
        } catch (IOException ex) {
 
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}