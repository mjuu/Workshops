package BlackJack.model.rules;

public class RulesFactory {

    public IHitStrategy GetHitRule() {
        return new Soft17Strategy();
    }

    public INewGameStrategy GetNewGameRule() {
        return new AmericanNewGameStrategy();
    }

    public TieStrategy GetTieRule() {
        return new TieStrategy();
    }

    public IWinStrategy GetWinnerRule(){
        return new HardStrategy();
    }

    public IHitStrategy GetSoft17Rule(){
        return new Soft17Strategy();
    }
}
