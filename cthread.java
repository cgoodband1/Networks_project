import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class cthread extends Thread{
    private String hostname;
    private int port;
    private String request;
    private Socket socket;
    private long start;
    private long end;
    private long total;

    public cthread(String hostname, int port, String request) {
            this.hostname = hostname;
            this.port = port;
            this.request = request;
            start();
        }


        public void run() {
            try (Socket socket = new Socket(hostname, port)) {
                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);

                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                //start timer
                start = System.currentTimeMillis();
                writer.println(request);

                String ret = reader.readLine();
                //stop timer
                long end = System.currentTimeMillis();
                total = (end - start);
                System.out.println(ret);
                System.out.println("Client Turn-around time: " + total + "ms");

                socket.close();

            }catch (IOException ex) {
                System.out.println("Server exception: " + ex.getMessage());
                ex.printStackTrace();
            }//catch(InterruptedException e){
            //System.out.println("my thread interrupted");
            //}
        }

         public long gettime(){
            return total;
        }
    }