package mainsource;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mainsource.gui.ImageViewAdaptor;
import mainsource.system.card.Card;
import mainsource.system.evaluator.EvaluatorException;
import mainsource.system.evaluator.HoldemHandEvaluator;
import mainsource.system.game.NewDeck;
import mainsource.system.handvalue.FinalHand;
import mainsource.system.parser.StringHandParser;
import mainsource.system.parser.StringHandParserException;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public TextField text_hand_hero;
    public TextField text_hand_villain;
    public TextField text_hand_board;
    public TextField text_chips_hero, text_chips_villain;
    public TextField text_bets_hero, text_bets_villain;
    public TextField text_pot;
    public TextArea area_log;
    public Button button_run;
    public ImageView img_hero1, img_hero2;
    public ImageView img_villain1, img_villain2;
    public ImageView img_board1, img_board2, img_board3, img_board4, img_board5;
    private ImageView[] imgs_hero, imgs_villain, imgs_board;

    private StringHandParser stringHandParser;
    private HoldemHandEvaluator handEvaluator;
    private NewDeck newDeck;
    private ImageViewAdaptor imageViewAdaptor;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stringHandParser = new StringHandParser();
        handEvaluator = new HoldemHandEvaluator();
        imageViewAdaptor = new ImageViewAdaptor();
    }

    public void onClickRunParse(ActionEvent actionEvent) {
        text_hand_hero.setText("");
        text_hand_villain.setText("");
        text_hand_board.setText("");
        newDeck = new NewDeck();
        addText(text_hand_hero, newDeck.deal1().toAbbreviateString());
        addText(text_hand_villain, newDeck.deal1().toAbbreviateString());
        addText(text_hand_hero, newDeck.deal1().toAbbreviateString());
        addText(text_hand_villain, newDeck.deal1().toAbbreviateString());
        addText(text_hand_board, newDeck.deal1().toAbbreviateString());
        addText(text_hand_board, newDeck.deal1().toAbbreviateString());
        addText(text_hand_board, newDeck.deal1().toAbbreviateString());
        addText(text_hand_board, newDeck.deal1().toAbbreviateString());
        addText(text_hand_board, newDeck.deal1().toAbbreviateString());
        try {
            Card[] card_hero = stringHandParser.parse(text_hand_hero.getText());
            Card[] card_villain = stringHandParser.parse(text_hand_villain.getText());
            Card[] card_board = stringHandParser.parse(text_hand_board.getText());

            imgs_hero = new ImageView[]{img_hero1, img_hero2};
            imgs_villain = new ImageView[]{img_villain1, img_villain2};
            imgs_board = new ImageView[]{img_board1, img_board2, img_board3, img_board4, img_board5};
            Image[] images_hero = imageViewAdaptor.convertCardToImage(card_hero);
            Image[] images_villain = imageViewAdaptor.convertCardToImage(card_villain);
            Image[] images_board = imageViewAdaptor.convertCardToImage(card_board);
            for(int i=0;i<5;++i){       //hard coding
                if(i<2){
                    imgs_hero[i].setImage(images_hero[i]);
                    imgs_villain[i].setImage(images_villain[i]);
                }
                imgs_board[i].setImage(images_board[i]);
            }

            FinalHand finalHandHero = handEvaluator.evaluate(card_hero, card_board);
            FinalHand finalHandVillain = handEvaluator.evaluate(card_villain, card_board);
            insertTextLn(area_log, finalHandHero.toString(), finalHandVillain.toString());
            if(finalHandHero.compareTo(finalHandVillain) > 0){
                insertTextLn(area_log, "Hero wins the pot");
            }else if(finalHandHero.compareTo(finalHandVillain) < 0){
                insertTextLn(area_log, "Villain wins the pot");
            }else{
                insertTextLn(area_log, "split pot");
            }
        } catch (StringHandParserException | EvaluatorException e) {
            e.printStackTrace();
        }
    }

    private void addText(TextInputControl t, String... s){
        for(int i=0;i<s.length;++i){
            t.setText(t.getText() + "" + s[i]);        //きたない
        }
    }

    private void insertTextLn(TextInputControl t, String... s){
        for(int i=0;i<s.length;++i){
            t.setText(s[i] + "\n" + t.getText());
        }
    }

}
