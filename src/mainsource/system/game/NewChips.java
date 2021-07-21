package mainsource.system.game;

import java.util.Arrays;

public class NewChips {

    protected final int[] CHIP_COUNT;
    protected final int[] BET_COUNT;
    protected final int PLRS;
    protected int pot = 0;

    public NewChips() throws TableException {
        this(2, 500);
    }

    public NewChips(int plrs, int chip_count) throws TableException{
        if(plrs < 2 || plrs > 10){
            throw new TableException("players must be between 2 to 10");
        }
        if(chip_count < 0){
            throw new TableException("chip count must be positive");
        }
        CHIP_COUNT = new int[plrs];
        BET_COUNT = new int[plrs];
        PLRS = plrs;
        Arrays.fill(CHIP_COUNT, chip_count);
    }

    public void raise(int p, int val) throws TableException{
        if(p < 0 || p >= PLRS){
            throw new TableException("invalid number of players");
        }
        if(CHIP_COUNT[p] < val - BET_COUNT[p]){
            throw new TableException("invalid bet");
        }
        CHIP_COUNT[p] -= (val - BET_COUNT[p]);
        BET_COUNT[p] = val;
    }

    public void call(int p) throws TableException{
        if(p < 0 || p >= PLRS){
            throw new TableException("invalid number of players");
        }
        int bet_max = 0;
        for(int i=0;i<PLRS;++i){
            if(BET_COUNT[i] > bet_max){
                bet_max = BET_COUNT[i];
            }
        }
        if(bet_max == BET_COUNT[p]){
            throw new TableException("invalid call");
        }
        CHIP_COUNT[p] -= (bet_max - BET_COUNT[p]);
        BET_COUNT[p] = bet_max;
    }

    public void resume(){
        for(int i : BET_COUNT){
            pot += i;
        }
        Arrays.fill(BET_COUNT, 0);
    }

    public void winsPot(int p){
        CHIP_COUNT[p] += pot;
        pot = 0;
    }

    public void splitPot(int... p){
        int eachPot = pot / p.length;
        for(int i=0;i<p.length;i++){
            CHIP_COUNT[p[i]] += eachPot;
            if(pot % eachPot < i){
                ++CHIP_COUNT[p[i]];
            }
        }
        pot = 0;
    }

}