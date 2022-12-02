package classe;

import java.io.*;
import java.lang.management.*;
import java.net.*;
import java.util.Scanner;

public class Main_client {

public static void main(String[] args) {

    // Server Host
    //    final String serverHost = "192.168.42.162";
    final String serverHost = "localhost";

       Socket socketOfClient = null;
       BufferedWriter os = null;
       BufferedReader is = null;

       try {
           // Send a request to connect to the server is listening
           // on machine 'localhost' port 9999.
           socketOfClient = new Socket(serverHost, 9999);

           // Create output stream at the client (to send data to the server)
           os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));


           // Input stream at Client (Receive data from the server).
           is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));

       } catch (UnknownHostException e) {
           System.err.println("Don't know about host " + serverHost);
           return;
       } catch (IOException e) {
           System.err.println("Couldn't get I/O for the connection to " + serverHost);
           return;
       }

       try {
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
		System.out.println(operatingSystemMXBean.toString());
        
        
        String Version_os =operatingSystemMXBean.getVersion()+"===version os";
        String processeur=operatingSystemMXBean.getAvailableProcessors()+"==processeur";
        String architecture=operatingSystemMXBean.getArch()+"==architecture";
        String nom_win=operatingSystemMXBean.getName()+"==nom windows";
        try {
            os.write("info Randy:");
            os.newLine();
            os.write(Version_os);
            os.newLine();
            os.write(processeur);
            os.newLine();
            os.write(architecture);
            os.newLine();
            os.write(nom_win);
            os.newLine();
            os.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        String ecriture;
        Scanner scan = new Scanner(System.in);
        while (0==0) {
            ecriture = scan.next();
            if (ecriture=="QUIT") {
                os.close();
                is.close();
                socketOfClient.close();
                break;
            }
            ecriture="RANDY: "+ecriture;
            os.write(ecriture);
            os.newLine();
            os.flush();
        }

            // Read data sent from the server.
            // By reading the input stream of the Client Socket.
            String responseLine;
            while ((responseLine = is.readLine()) != null) {
                System.out.println("RANDY: " + responseLine);
                if (responseLine.indexOf("OK") != -1) {
                    break;
                }
            }

            os.close();
            is.close();
            socketOfClient.close();
        }catch (UnknownHostException e) {
            System.err.println("Trying to connect to unknown host: " + e);
        } catch (IOException e) {
        System.err.println("IOException:  " + e);
        }
    
    }

}