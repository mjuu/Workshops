package BlackJack;

import BlackJack.model.Event;
import BlackJack.model.Game;
import BlackJack.view.*;
import BlackJack.controller.*;

public class Program {

    public static void main(String[] a_args) {

        System.out.println("Press any key you want to begin the game!");

      /*
      Creates an Event source
        */
        Event event = new Event();

      /*
      Create the observer
       */
        PlayGame playGame = new PlayGame();

      /*
      Listens to the observer in the event class
       */
        event.addObserver(playGame);

        Thread thread = new Thread(event);
        thread.start();
    }
}