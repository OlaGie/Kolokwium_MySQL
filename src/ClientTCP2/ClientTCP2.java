package ClientTCP2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;
 
public class ClientTCP2 {

	public static void main(String args[]) {
              Socket socket = null;
              BufferedReader input = null;
              PrintWriter output = null;
              String odp;
               
               Scanner scanner=new Scanner(System.in);
		if (args.length < 2)
			System.out.println("Wprowadź adres serwera TCP oraz numer portu");
		else{
			int port = 0;
			try {
				port = Integer.parseInt(args[1]);
			} catch (NumberFormatException e) {
				System.err.println("Wprowadź poprawny numer portu: " + e);
				return;
			}
			try {
                            System.out.println("Klient podlaczony");
				socket = new Socket(InetAddress.getByName(args[0]), port);
                                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                                output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
                                
                                    String inputFromServer =input.readLine();
                                    System.out.println(inputFromServer);
                                    String dane=scanner.nextLine();
                                    output.println(dane);   
				while (true){
                                    for(int i=0;i<6;i++){
                                    inputFromServer =input.readLine();
                                    System.out.println(inputFromServer);                                    
                                }
                                    Boolean poprawnaOdp=false;
                                    do{
                                    odp=scanner.nextLine();
                                    if(Integer.parseInt(odp)!=1 && Integer.parseInt(odp)!=2&&Integer.parseInt(odp)!=3&&Integer.parseInt(odp)!=4 ){
                                        System.out.println("Wprowadz odpowiedz z zakresu 1-4"); 
                                        poprawnaOdp=false;
                                    }
                                    else {
                                        poprawnaOdp=true;
                                        output.println(odp);  
                                    }        
                                    }while (poprawnaOdp=true);
                                                                  
                                }

				//socket.close();
			} catch (Exception e) {
				System.err.println(e);
			}
		}
	}
}