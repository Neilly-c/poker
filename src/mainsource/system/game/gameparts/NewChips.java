package mainsource.system.game.gameparts;

import mainsource.system.game.TableException;

import java.util.Arrays;

import static mainsource.system.game.NewGame.PLRS;

public class NewChips {

    private int[] chipCount;
    private int[] betCount;
    private int pot = 0;

    public NewChips() throws TableException {
        this(500);
    }

    public NewChips(int chip_count) throws TableException{
        if(PLRS < 2 || PLRS > 10){
            throw new TableException("players must be between 2 to 10");
        }
        if(chip_count < 0){
            throw new TableException("chip count must be positive");
        }
        chipCount = new int[PLRS];
        betCount = new int[PLRS];
        Arrays.fill(chipCount, chip_count);
    }

    public void raise(int p, int val) throws TableException{
        if(p < 0 || p >= PLRS){
            throw new TableException("invalid number of players");
        }
        if(chipCount[p] < val - betCount[p]){
            throw new TableException("invalid bet");
        }
        chipCount[p] -= (val - betCount[p]);
        betCount[p] = val;
    }

    public void call(int p) throws TableException{
        if(p < 0 || p >= PLRS){
            throw new TableException("invalid number of players");
        }
        int bet_max = 0;
        for(int i = 0; i< PLRS; ++i){
            if(betCount[i] > bet_max){
                bet_max = betCount[i];
            }
        }
        if(bet_max == betCount[p]){
            throw new TableException("invalid call");
        }
        chipCount[p] -= (bet_max - betCount[p]);
        betCount[p] = bet_max;
    }

    public void resume(){
        for(int i : betCount){
            pot += i;
        }
        Arrays.fill(betCount, 0);
    }

    public void winsPot(int p){
        chipCount[p] += pot;
        pot = 0;
    }

    public void splitPot(int... p){
        int eachPot = pot / p.length;
        for(int i=0;i<p.length;i++){
            chipCount[p[i]] += eachPot;
            if(pot % eachPot < i){
                ++chipCount[p[i]];
            }
        }
        pot = 0;
    }

    public int getChipCount(int p) {
        return chipCount[p];
    }

    public int getBetCount(int p) {
        return betCount[p];
    }

    public int getPot() {
        return pot;
    }
}