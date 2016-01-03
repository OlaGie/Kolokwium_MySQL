package kolokwium_mysql;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.Callable;

public class ServerTCPThread implements Callable<String> {

	  private Socket socket = null;
	  private BufferedReader input = null;
	  private PrintWriter output = null;
          String niu=null;
          int wynik=0;
          private Database db;
          private AnswerDatabase adb;
          private ResultsDatabase rdb;
          int liczbaPytan=3;
          String nrOdpowiedzi;
        
	  public ServerTCPThread(Socket socket, Database db, AnswerDatabase adb, ResultsDatabase rdb) {
	    this.socket = socket;  
            this.db=db;
            this.adb=adb;
            this.rdb=rdb;
	  }
	    @Override
	    public String call() throws Exception {
                
              try {
                  //inputFromClient
                  input=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                  // outputToClient
                  output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

                  output.println("Podaj swój numer NIU");
                  niu=input.readLine();
                  System.out.println("NIU podłączonego uzytkownika: "+niu);

	        for(int i=0;i<liczbaPytan;i++){
                    for(int k=0;k<6;k++){ //6- laczna ilosc lini w pliku dotyczaca 1 pytania(1-pytanie, 2-5- odpowiedzi, 6=poprawna odp.
                        String trescPytan=db.showQuestions(i,k);  
                        if(k!=5)
                        output.println(trescPytan);
                        else output.println("Udziel odpowiedzi");
                    }

                    Integer odpPoprawna=Integer.parseInt(db.showQuestions(i,5));
                    nrOdpowiedzi=input.readLine();
                    System.out.println("NIU uzytkownika: "+niu+" Pytanie "+(i+1)+" odp: "+nrOdpowiedzi);
                        adb.addAnswers(niu,(i+1),nrOdpowiedzi);  
                        if(odpPoprawna==Integer.parseInt(nrOdpowiedzi)){
                            wynik++;
                        }
                        //System.out.println("Wynik po :"+(i+1)+" pytaniu: "+wynik);
                }
                
                output.println("Test zakonczony");
                output.println("Twoj wynik wynosi:  "+wynik);
                rdb.addResult(niu, wynik);
                //System.out.println("Wynik uzytkownika o NIU: "+niu+" wynosi  "+wynik);
                } catch (Exception exc) {
                    exc.printStackTrace();
                    try { socket.close(); } catch(Exception e) {}
                    return "";
                }

	        return "Wynik uzytkownika o NIU: "+niu+" wynosi  "+wynik;
	    }

	}
