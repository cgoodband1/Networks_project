import java.io.*;
//this class runs the requested commands and returns the information collected
public class RunCommand {

    public String command;

    public RunCommand(String command)
    {
         this.command = command;
    }

    //public static void main(String args[]) {

    public static String executeCommand(String command)
    {

        String s = null;

        try {
            
            
            Process p = Runtime.getRuntime().exec(command);
            
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            // read the output from the command
            
            //System.out.println("Here is the standard output of the command:\n");
            while ((s = stdInput.readLine()) != null) {
                //System.out.println(s);
                return s;    
            }
            
            // read any errors from the attempted command

            System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }
            
            System.exit(0);
        }
        catch (IOException e) {
            System.out.println("exception happened - here's what I know: ");
            e.printStackTrace();
            System.exit(-1);
        }
        return s;
    }// executeCommand()
}// runcommand()

