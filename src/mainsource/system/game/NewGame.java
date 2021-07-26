package mainsource.system.game;

import mainsource.system.game.gameparts.ActionInput;
import mainsource.system.game.gameparts.NewChips;
import mainsource.system.game.gameparts.NewDeck;

import java.util.Arrays;

public abstract class NewGame {

    protected NewChips newChips;
    protected NewDeck newDeck;
    protected ActionInput[] actionInputs;
    protected Status[] status;

    public static final int PLRS = 2;
    protected int blind = 20;
    protected int activePlr = 0;

    enum Status {
        FOLDED(0),
        IN_PLAY(1),
        OPTION(2),
        COMPLETE(9);

        private int id;

        Status(int id){
            this.id = id;
        }
    }


    public NewGame() throws TableException {
        newChips = new NewChips(500);
        newDeck = new NewDeck();
        status = new Status[PLRS];
        Arrays.fill(status, Status.FOLDED);
    }

    public void dealNewHand(){
        newDeck.initDeck();
        postBlinds(0, 1, blind);
        newDeck.dealPreflop();
        Arrays.fill(status, Status.IN_PLAY);
        status[1] = Status.OPTION;
        refresh();
        waitForAction();
    }

    private void waitForAction() {
        while(!isAllActionEnded()){
            int tmp = activePlr;
            ++activePlr;
            for(int i=activePlr;activePlr!=tmp;++i){
                if(activePlr >= PLRS){
                    activePlr %= PLRS;
                }
                if(status[activePlr] != Status.FOLDED && status[activePlr] != Status.COMPLETE){
                    break;
                }
            }
            int totalAmountBet = actionInputs[activePlr].getAction();
            if(totalAmountBet == 0){
                if(status[activePlr].equals(Status.OPTION)){
                    status[activePlr] = Status.COMPLETE;
                }else {
                    status[activePlr] = Status.FOLDED;
                }
            }else{
                int max_bet = 0;
                for(int i=0;i<PLRS;++i){
                    max_bet = Math.max(max_bet, newChips.getBetCount(i));
                }
                if(max_bet < totalAmountBet){
                    for(int i=0;i<PLRS;++i){
                        if(status[i] == Status.OPTION || status[i] == Status.COMPLETE){
                            status[i] = Status.IN_PLAY;
                        }
                    }
                }
                status[activePlr] = Status.COMPLETE;
            }
        }
    }

    private boolean isAllActionEnded(){
        for(int i=0;i<PLRS;++i){
            if(status[i] != Status.FOLDED && status[i] != Status.COMPLETE){
                return false;
            }
        }
        return true;
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
