package mainsource;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import mainsource.system.card.Card;
import mainsource.system.evaluator.EvaluatorException;
import mainsource.system.evaluator.HandEvaluator;
import mainsource.system.handvalue.FinalHand;
import mainsource.system.parser.StringHandParser;
import mainsource.system.parser.StringHandParserException;

import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public TextField text_hand_input;
    public TextField text_hand_output;
    public Button button_run_parse;

    StringHandParser stringHandParser;
    HandEvaluator handEvaluator;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stringHandParser = new StringHandParser();
        handEvaluator = new HandEvaluator();
    }

    public void onClickRunParse(ActionEvent actionEvent) {
        try {
            Card[] cards = stringHandParser.parse(text_hand_input.getText());
            Arrays.sort(cards, Collections.reverseOrder());
            System.out.println(Arrays.asList(cards).toString());
            FinalHand finalHand = handEvaluator.evaluate(cards);
            System.out.println(finalHand.toString());
        } catch (StringHandParserException | EvaluatorException e) {
            e.printStackTrace();
        }
        text_hand_output.setText(text_hand_input.getText());
    }
}
