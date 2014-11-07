package BlackJack.model.rules;

import BlackJack.model.Player;


public interface IWinStrategy {
    boolean GetWinRule(Player a_dealer, Player a_player);
}
