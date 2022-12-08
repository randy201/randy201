package classe;

import java.io.*;
import java.lang.management.*;
import java.net.*;
import java.time.LocalDateTime;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main_client {

    
public static void main(String[] args) {
    JFrame mafenetre=new JFrame("Parc info");
    mafenetre.setSize(800, 500);

    JPanel panel=new JPanel();
    panel.setLayout(null);
    panel.setSize(800, 500);
    mafenetre.add(panel);
    mafenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JLabel label0=new  JLabel("Parc info ");
    label0.setBounds(400,0,100,50);
    panel.add(label0);
    

    // Server Host
    // final String serverHost = "192.168.42.162";
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
        
        String Version_os =operatingSystemMXBean.getVersion();
        String processeur=operatingSystemMXBean.getAvailableProcessors()+"";
        String architecture=operatingSystemMXBean.getArch();
        String nom_win=operatingSystemMXBean.getName();

        JLabel label_Version_os=new JLabel("Version OS:"+Version_os);
        label_Version_os.setBounds(10,50,200,20);
        panel.add(label_Version_os);
        JLabel label_processeur=new JLabel("Processeur:"+processeur);
        label_processeur.setBounds(10,90,200,20);
        panel.add(label_processeur);
        JLabel label_architecture=new JLabel("Architecture:"+architecture);
        label_architecture.setBounds(10,130,200,20);
        panel.add(label_architecture);
        JLabel label_nom_win=new JLabel("Nom Win:"+nom_win);
        label_nom_win.setBounds(10,170,200,20);
        panel.add(label_nom_win);
        
        JLabel label_date=new JLabel("date:"+LocalDateTime.now());
        label_date.setBounds(10,210,250,20);
        panel.add(label_date);
        mafenetre.setVisible(true);

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
                mafenetre.dispose();
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
            return;
        }catch (IOException e) {
            System.err.println("IOException:  " + e);
            return;
        }
    }

}