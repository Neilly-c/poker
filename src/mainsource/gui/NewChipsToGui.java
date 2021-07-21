package mainsource.gui;

public class NewChipsToGui extends NewChips{

    public NewChipsToGui() throws TableException {
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

}