package mainsource.system.evaluator;

import mainsource.system.card.Card;
import mainsource.system.card.CardValue;
import mainsource.system.handvalue.FinalHand;
import mainsource.system.handvalue.HandName;
import mainsource.system.handvalue.OptionValue;

public class HoldemHandEvaluator extends HandEvaluator{

    static final int HOLDEM_HAND_CARDS = 7;

    @Override
    public FinalHand evaluate(Card[] cards) throws EvaluatorException {
        if(cards.length != HOLDEM_HAND_CARDS){
            throw new EvaluatorException("evaluating HOLDEM hand length is illegal");
        }
        for(int i=0;i<HOLDEM_HAND_CARDS;++i){
            for(int j=i+1;j<HOLDEM_HAND_CARDS;++j){
                if(cards[i].getNumber() == cards[j].getNumber()){
                    throw new EvaluatorException("Array cards have two or more same cards");
                }
            }
        }
        FinalHand result = new FinalHand(HandName.HIGH_CARD, new OptionValue(CardValue.TWO));
        Card[] cards_picked = new Card[5];
        for(int i=0;i<HOLDEM_HAND_CARDS;++i){
            for(int j=i;j<HOLDEM_HAND_CARDS;++j){
                int count = 0;
                for(int k=0;k<HOLDEM_HAND_CARDS;++k){
                    if(k != i && k != j){
                        cards_picked[count] = cards[k];
                        ++count;
                    }
                }
                FinalHand temp_result = super.evaluate(cards_picked);
                if(temp_result.compareTo(result) > 0){
                    result = temp_result;
                }
            }
        }
        return result;
    }

}