package kolokwium_mysql;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Ola
 */
public class Kolokwium {

    private String nazwaPliku;
    private int iloscPytan;
    private String niu;
    static ReentrantLock lock=new ReentrantLock();
    
    public Kolokwium(String nazwaPliku, int iloscPytan,String niu){
        this.nazwaPliku=nazwaPliku;
        this.iloscPytan=iloscPytan;
        this.niu=niu;
    }
    public String wyswietl(int nrPytania, int nrOdpowiedzi){
        FileReader fr = null;
        BufferedReader bfr=null;
       // OTWIERANIE PLIKU:
       try {
         fr = new FileReader(nazwaPliku);
       } catch (FileNotFoundException e) {
           System.out.println("BLAD PRZY OTWIERANIU PLIKU!");
           System.exit(1);
       }
        bfr = new BufferedReader(fr);
       // ODCZYT KOLEJNYCH LINII Z PLIKU:
       try {
             for(int i=0;i<iloscPytan;i++){
                 if(i==nrPytania){
                     String pytanie="Pytanie: "+(i+1)+". "+bfr.readLine();
                     String odp1="1) "+bfr.readLine();
                     String odp2="2) "+bfr.readLine();
                     String odp3="3) "+bfr.readLine();;
                     String odp4="4) "+bfr.readLine();
                     String odpPoprawna=bfr.readLine();

                     switch(nrOdpowiedzi){
                         case 0: return pytanie;
                         case 1: return odp1;
                         case 2: return odp2;
                         case 3: return odp3;
                         case 4: return odp4; 
                         case 5: return odpPoprawna;    
                     }
                 }
                 else{
                	 for(int k=0;k<6;k++)
                		 bfr.readLine();
                 }
             }
        } catch (IOException e) {
            System.out.println("BLAD ODCZYTU Z PLIKU!");
            System.exit(2);
       }
       // ZAMYKANIE PLIKU
       try {
         fr.close();
         bfr.close();
        } catch (IOException e) {
             System.out.println("BLAD PRZY ZAMYKANIU PLIKU!");
             System.exit(3);
            }
       return ";";
        }
    
        public void wczytajOdp(int nrPytania, int nrOdpowiedzi, String niu) throws IOException{
            FileReader fr=null;
            try {
                fr=new FileReader(".//bazaOdpowiedzi.txt");
                  } catch (IOException e) {
                e.printStackTrace();
             }
            BufferedReader bfr = new BufferedReader(fr);
            
            String linia;
            StringBuffer sb = new StringBuffer("");
            try {
                    linia=bfr.readLine();
                      do {
                        sb.append("\n"+linia);
                        linia = bfr.readLine();
                      } while(linia != null);
            
             } catch (IOException e) {
                e.printStackTrace();
             }
             try {
                bfr.close();
                fr.close();
             } catch (IOException e) {
                   e.printStackTrace();
             }
             
             lock.lock();
                FileWriter fw = null;
          
            try {
                fw = new FileWriter(".//bazaOdpowiedzi.txt");
               
                  } catch (IOException e) {
                e.printStackTrace();
             }
            BufferedWriter bw = new BufferedWriter(fw);
            try {
                   //bw.write("Odpowiedzi studenta o NIU: "+niu+" na pytanie nr "+(nrPytania+1)+": "+nrOdpowiedzi);
                bw.append(sb+" \nOdpowiedzi studenta o NIU: "+niu+" na pytanie nr "+(nrPytania+1)+": "+nrOdpowiedzi);
             
             } catch (IOException e) {
                e.printStackTrace();
             }
             try {
                bw.close();
                fw.close();
             } catch (IOException e) {
                   e.printStackTrace();
             }  
             lock.unlock();
            return;
        }        

        
        public void zapiszWynik(String wynikDoPliku) throws IOException{

                        FileReader fr=null;
            try {
                fr=new FileReader(".//wyniki.txt");
                  } catch (IOException e) {
                e.printStackTrace();
             }
            BufferedReader bfr = new BufferedReader(fr);
            
            String linia;
            StringBuffer sb = new StringBuffer("");
            try {
                    linia=bfr.readLine();
                      do {
                        sb.append("\n"+linia);
                        linia = bfr.readLine();
                      } while(linia != null);
            
             } catch (IOException e) {
                e.printStackTrace();
             }
             try {
                bfr.close();
                fr.close();
             } catch (IOException e) {
                   e.printStackTrace();
             }
             lock.lock();
                             FileWriter fw = null;
          
            try {
                fw = new FileWriter(".//wyniki.txt");
                  } catch (IOException e) {
                e.printStackTrace();
             }
            BufferedWriter bw = new BufferedWriter(fw);
            try {
                   bw.append(sb+"\n"+wynikDoPliku);
             } catch (IOException e) {
                e.printStackTrace();
             }
             try {
                bw.close();
                fw.close();
             } catch (IOException e) {
                   e.printStackTrace();
             }  
             lock.unlock();
            return;
        }        
        
//        public static void main(String[] args) throws IOException{
//            Kolokwium kolokwium=new Kolokwium("bazaPytan.txt",3,"agd");
//            
//            kolokwium.wczytajOdp(1,2,"agd");
//            kolokwium.wczytajOdp(2,2,"agd");
//            kolokwium.wczytajOdp(3,2,"agd");
//        }
}
