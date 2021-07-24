package mainsource.system.game;

import mainsource.system.game.gameparts.ActionInput;
import mainsource.system.game.gameparts.NewChips;
import mainsource.system.game.gameparts.NewDeck;

public abstract class NewGame {

    protected NewChips newChips;
    protected NewDeck newDeck;
    protected ActionInput[] actionInputs;
    protected Status status;

    public static final int PLRS = 2;
    protected int blind = 20;

    enum Status {
        SLEEP(0),
        WAIT(1);

        private int id;

        Status(int id){
            this.id = id;
        }
    }


    public NewGame() throws TableException {
        newChips = new NewChips(500);
        newDeck = new NewDeck();
        status = Status.SLEEP;
    }

    public void dealNewHand(){
        newDeck.initDeck();
        postBlinds(0, 1, blind);
        newDeck.dealPreflop();
        refresh();
    }

    private void postBlinds(int SB, int BB, int bb_val) {
        try {
            newChips.raise(SB, bb_val/2);
            newChips.raise(BB, bb_val);
        } catch (TableException e) {
            e.printStackTrace();
        }
    }

    public void refresh(){
        refreshCards();
        refreshChips();
    }

    public abstract void refreshCards();
    public abstract void refreshChips();

}
