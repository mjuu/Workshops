package BlackJack.model.rules;


import BlackJack.model.Card;
import BlackJack.model.Player;

public class Soft17Strategy implements IHitStrategy {

    private final int g_hitLimit = 17;

    @Override
    public boolean DoHit(Player a_dealer) {

        if (a_dealer.CalcScore() == 17){

            for (Card c : a_dealer.GetHand()){
                if (c.GetValue() == Card.Value.Ace && a_dealer.CalcScore() == 17){
                    return true;
                }
            }
        }
        if (a_dealer.CalcScore() >= g_hitLimit){
            return false;
        }

        if (a_dealer.CalcScore() < g_hitLimit || a_dealer.CalcScore() < 21){
            return true;
        }
        return false;
    }
}
