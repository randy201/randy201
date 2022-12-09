package classe;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class Main{
    public static void main(String[] args) {
        ServerSocket serveur = null;
        Socket socketOfServer = null;
        try {
            serveur = new ServerSocket(9999);
            
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }
        
        try {
            System.out.println("Server is waiting to accept user...");
            while (true) { 
                socketOfServer = serveur.accept();
                try {
                    Serveur sser=new Serveur(socketOfServer); 
                    sser.start();
                    
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            // Accept client connection request
            // Get new Socket at Server.    
        }catch (IOException e) {
            System.out.println(e);
         //    e.printStackTrace();
        }
        System.out.println("Sever stopped!");
    }
}