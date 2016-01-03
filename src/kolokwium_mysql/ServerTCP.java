package kolokwium_mysql;

import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ServerTCP {
	public static void main(String args[]) {
            Database db;
            AnswerDatabase adb;
            ResultsDatabase rdb;
		if (args.length == 0)
			System.out.println("Wprowadz numer portu, na ktorym serwer bedzie oczekiwac na klientow");
		else {
			int port = 0;
			try {
				port = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				System.err.println("Wprowadz poprawny numer portu: " + e);
				return;
			}
			try {
                                     db=new Database();
                                     db.createDatabase(); 
                                     db.createTable();
                                     adb=new AnswerDatabase();
                                     adb.createAnswerDatabase();
                                     rdb=new ResultsDatabase();
                                     rdb.createResultsTable();
				// tworzymy socket
				ServerSocket serverSocket = new ServerSocket(port);
				System.out.println("Serwer oczekuje na porcie: "+args[0]);
                                int threadPool=100;
				while (true) {
				       Socket socket = serverSocket.accept();
				       System.out.println("Connection established");
				       
				       ExecutorService exec = Executors.newFixedThreadPool(threadPool);
				      
				       exec.execute(new FutureTaskK((new ServerTCPThread(socket, db, adb, rdb))));
				}
                                //zamkniecie strumieni i polaczenia
			} catch (Exception e) {
				System.err.println(e);
			}
		}
	}
}