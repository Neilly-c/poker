package mainsource.gui;

import javafx.scene.control.TextField;
import mainsource.system.game.NewChips;
import mainsource.system.game.TableException;

import java.util.Arrays;

public class NewChipsToGui extends NewChips {

    private final TextField[] TEXT_CHIP_COUNT, TEXT_BET_COUNT;
    private final TextField TEXT_POT;

    public NewChipsToGui() throws TableException {
        this(2, 500);
    }

    public NewChipsToGui(int plrs, int chip_count) throws TableException{
        this(plrs, chip_count, new TextField[0], new TextField[0], new TextField());
    }

    public NewChipsToGui(int plrs, int chip_count, TextField[] textChips, TextField[] textBets, TextField textPot) throws TableException{
        if(plrs < 2 || plrs > 10){
            throw new TableException("players must be between 2 to 10");
        }
        if(chip_count < 0){
            throw new TableException("chip count must be positive");
        }
        chipCount = new int[plrs];
        betCount = new int[plrs];
        this.plrs = plrs;
        Arrays.fill(chipCount, chip_count);
        TEXT_CHIP_COUNT = textChips;
        TEXT_BET_COUNT = textBets;
        TEXT_POT = textPot;
    }

    public void refresh(){
        for(int i=0;i<plrs;++i){
            TEXT_CHIP_COUNT[i].setText(String.valueOf(chipCount[i]));
            TEXT_BET_COUNT[i].setText(String.valueOf(betCount[i]));
        }
        TEXT_POT.setText(String.valueOf(pot));
    }

    @Override
    public void raise(int p, int val) throws TableException{
        super.raise(p, val);
        refresh();
    }

    @Override
    public void call(int p) throws TableException{
        super.call(p);
        refresh();
    }

    @Override
    public void resume(){
        super.resume();
        refresh();
    }

    @Override
    public void winsPot(int p){
        super.winsPot(p);
        refresh();
    }

    @Override
    public void splitPot(int... p){
        super.splitPot(p);
        refresh();
    }

}