package BlackJack.model.rules;

import BlackJack.model.Player;


public class HardStrategy implements IWinStrategy{

    private int g_maxScore = 21;

    public boolean GetWinRule(Player a_dealer, Player a_player) {

        if (a_dealer.CalcScore() == a_player.CalcScore()) {

            return true;
        }

        if (a_dealer.CalcScore() > a_player.CalcScore() && a_dealer.CalcScore() < 21) {

            return true;
        }

        if (a_dealer.CalcScore() > g_maxScore) {

            return false;
        }
        else if (a_dealer.CalcScore() == 21) {

            return true;
        }

        if (a_player.CalcScore() > g_maxScore){

            return true;
        }
        else if (a_player.CalcScore() == 21){

            return false;
        }

        return false;
    }
}

