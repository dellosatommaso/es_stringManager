package client.clientserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnknownHostException, IOException {
        System.out.println("client partito");
        Socket s0 = new Socket("localhost", 3000);
        BufferedReader in = new BufferedReader(new InputStreamReader(s0.getInputStream()));
        DataOutputStream out = new DataOutputStream(s0.getOutputStream());
        Scanner input = new Scanner(System.in);
        String noperazione;
        String s;

        do{
            System.out.println("Inserire l'operazione che si vuole fare \n1=>stringa in maiuscolo\n2=>stringa in minuscolo\n3=>inverti stringa\n4=>conta lungheza stringa\n!=>chiusura onnessione\n");
            noperazione = input.nextLine();
            if (noperazione.equals("!")) {
                out.writeBytes("!");;
                break;
            }
            System.out.println("Inserire stringa da inviare:\n");
            s = input.nextLine();            
            out.writeBytes(s + noperazione + "\n"); 
            String sM = in.readLine();            
            System.out.println("Invio dati a server");            
            System.out.println("Risposta: " + sM);
        }while (!noperazione.equals("!"));
        
        input.close();
        s0.close();
        System.out.println("Client chiuso");
    }
}