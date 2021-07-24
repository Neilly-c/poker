package mainsource;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.image.ImageView;
import mainsource.gui.NewGameJavaFX;
import mainsource.system.evaluator.HoldemHandEvaluator;
import mainsource.system.game.TableException;
import mainsource.system.parser.StringHandParser;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public TextField text_chips_hero, text_chips_villain;
    public TextField text_bets_hero, text_bets_villain;
    public TextField text_pot;
    public TextArea area_log;
    public Button button_run;
    public ImageView img_hero1, img_hero2;
    public ImageView img_villain1, img_villain2;
    public ImageView img_board1, img_board2, img_board3, img_board4, img_board5;

    private StringHandParser stringHandParser;
    private HoldemHandEvaluator handEvaluator;
    private NewGameJavaFX newGameJavaFX;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stringHandParser = new StringHandParser();
        handEvaluator = new HoldemHandEvaluator();

        TextField[]
                txt_chip_count = new TextField[]{text_chips_hero, text_chips_villain},
                txt_bet_count = new TextField[]{text_bets_hero, text_bets_villain};
        ImageView[][] img_whole_cards = new ImageView[][]{{img_hero1, img_hero2}, {img_villain1, img_villain2}};
        ImageView[] img_board = new ImageView[]{img_board1, img_board2, img_board3, img_board4, img_board5};
        try {
            newGameJavaFX = new NewGameJavaFX(txt_chip_count, txt_bet_count, text_pot, img_whole_cards, img_board);
        } catch (TableException e) {
            e.printStackTrace();
        }
    }

    public void onClickRunParse(ActionEvent actionEvent) {
        newGameJavaFX.dealNewHand();
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
