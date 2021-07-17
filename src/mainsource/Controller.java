package mainsource;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import mainsource.system.card.Card;
import mainsource.system.evaluator.EvaluatorException;
import mainsource.system.evaluator.HandEvaluator;
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
    public TextArea area_log;
    public Button button_run;

    private StringHandParser stringHandParser;
    private HoldemHandEvaluator handEvaluator;
    private NewDeck newDeck;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stringHandParser = new StringHandParser();
        handEvaluator = new HoldemHandEvaluator();
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
            FinalHand finalHandHero = handEvaluator.evaluate(card_hero, card_board);
            FinalHand finalHandVillain = handEvaluator.evaluate(card_villain, card_board);
            insertTextLn(area_log, finalHandHero.toString(), finalHandVillain.toString());
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
