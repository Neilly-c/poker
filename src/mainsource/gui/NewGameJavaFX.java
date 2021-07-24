package mainsource.gui;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mainsource.system.card.Card;
import mainsource.system.game.NewGame;
import mainsource.system.game.TableException;

import java.io.File;
import java.util.Objects;

public class NewGameJavaFX extends NewGame {

    TextField[]
            txt_chip_count,
            txt_bet_count;
    TextField txt_pot;
    ImageView[][] img_whole_cards;
    ImageView[] img_board;

    Image img_back = new Image(new File("src/imgresource/back.png").toURI().toString());

    public NewGameJavaFX(TextField[] txt_chip_count, TextField[] txt_bet_count, TextField txt_pot,
                         ImageView[][] img_whole_cards, ImageView[] img_board) throws TableException {
        super();
        this.txt_chip_count = txt_chip_count;
        this.txt_bet_count = txt_bet_count;
        this.txt_pot = txt_pot;
        this.img_whole_cards = img_whole_cards;
        this.img_board = img_board;
    }

    @Override
    public void refreshCards() {
        for(int i=0;i<PLRS;++i){
            Card[] cards = newDeck.getWholeCards(i);
            for(int j=0;j< cards.length;++j){
                img_whole_cards[i][j].setImage(convertCardToImage(cards[j]));
            }
        }
        Card[] cards = newDeck.getBoard();
        for(int i=0;i<img_board.length;++i){
            if(Objects.nonNull(cards[i])) {
                img_board[i].setImage(convertCardToImage(cards[i]));
            }else{
                img_board[i].setImage(img_back);
            }
        }
    }

    @Override
    public void refreshChips() {
        for(int i=0;i<PLRS;++i){
            txt_chip_count[i].setText(String.valueOf(newChips.getChipCount(i)));
            txt_bet_count[i].setText(String.valueOf(newChips.getBetCount(i)));
        }
        txt_pot.setText(String.valueOf(newChips.getPot()));
    }

    private Image convertCardToImage(Card c){
        return new Image(new File("src/imgresource/" + c.toAbbreviateString() +  ".png").toURI().toString());
    }

    private Image[] convertCardToImage(Card[] c){
        Image[] images = new Image[c.length];
        for(int i=0;i<c.length;++i){
            images[i] = convertCardToImage(c[i]);
        }
        return images;
    }

}
