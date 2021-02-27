import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
 
public class TimeClient {
     static long start;
    // first argument when running is Hostname 139.62.210.153 
    //second argument is the port that the server is running on
     public static void main(String[] args) {
        if (args.length < 2) return;
        
        String host = args[0];
        int port = Integer.parseInt(args[1]);

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of clients: ");
        String tempNu = sc.nextLine();
        int clientNu = Integer.parseInt(tempNu);
        
        System.out.println("Choose a request");
        System.out.println("1. Date");
        System.out.println("2. Uptime");
        System.out.println("3. Memory Use");
        System.out.println("4. Netstat");
        System.out.println("5. Current Users");
        System.out.println("6. Running Process");

        String request = sc.nextLine(); 


        cthread[] clist = new cthread[clientNu];
                for(int i=0; i<clientNu; i++)
                {
                    clist[i] = new cthread(host, port, request);
                }
    }

}
