package BlackJack.model;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Observable;

public class Event extends Observable implements Runnable{

    @Override
    public void run() {
        try{
            final InputStreamReader inputReader = new InputStreamReader(System.in);
            final BufferedReader buffReader = new BufferedReader(inputReader);

            while (true){
                String resp = buffReader.readLine();
                setChanged();
                notifyObservers(resp);
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
