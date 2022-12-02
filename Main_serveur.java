package classe;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main_serveur {

   public static void main(String args[]) {

       ServerSocket serveur = null;
       String line;
       BufferedReader is;
       BufferedWriter os;
       Socket socketOfServer = null;

       // Try to open a server socket on port 9999
       // Note that we can't choose a port less than 1023 if we are not
       // privileged users (root)

 
       try {
           serveur = new ServerSocket(9999);
       } catch (IOException e) {
           System.out.println(e);
           System.exit(1);
       }
       
       try {
           System.out.println("Server is waiting to accept user...");

           // Accept client connection request
           // Get new Socket at Server.    
           socketOfServer = serveur.accept();

           
           System.out.println("Accept a client!");

           // Open input and output streams
           is = new BufferedReader(new InputStreamReader(socketOfServer.getInputStream()));
        //    os = new BufferedWriter(new OutputStreamWriter(socketOfServer.getOutputStream()));

                while(socketOfServer.isConnected()){
                    String huhu=is.readLine();
                    String table[]=huhu.split(":");
                    if(table.length >1){
                        String mot=table[1];
                        if(mot.equals(" QUIT")){
                            break;
                        }
                    }
                    System.out.println(huhu);
                }
                

       } catch (IOException e) {
           System.out.println(e);
        //    e.printStackTrace();
       }
       System.out.println("Sever stopped!");
   }
}