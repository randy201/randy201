package classe;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Serveur extends  Thread{
    int x=10;
    int y=50;
    int longueur=0;
    int largeur=0;
    
    BufferedReader is;
    Socket socketOfServer = null;
    public Serveur(Socket socketOfServer) {
        this.socketOfServer=socketOfServer;
    }
    @Override
    public void run() {
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

        mafenetre.setVisible(true);

        System.out.println("Accept a client!");
        // Open input 
        try {
            is = new BufferedReader(new InputStreamReader(this.socketOfServer.getInputStream()));
            while(socketOfServer.isConnected()){
                String huhu=is.readLine();
                String table[]=huhu.split(":");
                if(table.length >1){
                    String mot=table[1];
                    if(mot.equals(" QUIT")){
                        break;
                    }
                }
                JLabel afficher=new JLabel(huhu);
                afficher.setBounds(x,y,200,20);
                panel.add(afficher);
                System.out.println(huhu);
                y=y+40;
            }
        } 
        catch(SocketException e){
            System.out.println("deco");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
    
}
