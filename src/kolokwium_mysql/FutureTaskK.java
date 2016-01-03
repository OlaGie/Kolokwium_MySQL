package kolokwium_mysql;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 *
 * @author Ola
 */
public class FutureTaskK<T> extends FutureTask<T>{

    public FutureTaskK(Callable clbl) {
        super(clbl);
    }
    public void done() {
        if(isCancelled()){
            System.out.println("Cancelled");
        }
        else{
            try {
                System.out.println("Zakończony wątek: "+Thread.currentThread().getName()+" "+get());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } catch (ExecutionException ex) {
                ex.printStackTrace();
            }
        }

    }
}