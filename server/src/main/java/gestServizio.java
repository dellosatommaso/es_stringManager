package server.clientserver;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class gestioneServizio extends Thread{
    Socket s0;

    gestioneServizio(Socket s0) {
        this.s0 = s0;
    }

    @Override
    public void run() {
        try{
            System.out.println("Un client si è collegato alla porta "+ s0.getPort() +" dove risiede il thread " + Thread.currentThread().getName());
            BufferedReader in = new BufferedReader(new InputStreamReader(s0.getInputStream()));
            DataOutputStream out = new DataOutputStream(s0.getOutputStream());
            char nop;
            String dati;

            do{
                dati = in.readLine();
                if (dati.equals("!")){
                    stringaIn = "!";
                    break;
                }
                nop = dati.charAt(dati.length() - 1);
                stringaIn = dati.substring(0, dati.length() - 1);
                System.out.println("Stringa ricevuta: " + stringaIn + " operazione: " + nop);
                String sMod;

                switch (nop){
                    case '1':
                        sMod = stringaIn.toUpperCase();
                        break;
                    
                    case '2':
                        sMod = stringaIn.toLowerCase();
                        break;

                    case '3':
                        StringBuilder sB = new StringBuilder(stringaIn);
                        sMod = sB.reverse().toString();
                        break;

                    case '4':
                        sMod = "Lunghezza stringa: " + stringaIn.length();
                        break;
                
                    default:
                        sMod = "Inserire solo numeri da 1 a 4";
                        break;
                }
                out.writeBytes(sMod + "\n");

            }while(!stringaIn.equals("!"));

            System.out.println("Ciao client!");
            s0.close();    

        }catch (Exception e){} 
    }
}