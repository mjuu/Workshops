package BlackJack.model.rules;


import BlackJack.model.Card;
import BlackJack.model.Player;

public class Soft17Strategy implements IHitStrategy {

    private final int g_hitLimit = 17;

    @Override
    public boolean DoHit(Player a_dealer) {
        int score = a_dealer.CalcScore();

        if (score == g_hitLimit){
            Iterable<Card> hand = a_dealer.GetHand();

            for (Card c : hand){

                if (c.GetValue() == Card.Value.Ace){
                    return true;
                }
            }
        }
        return false;
    }
}
