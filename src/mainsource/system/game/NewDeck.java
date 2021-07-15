package mainsource.system.game;

import mainsource.system.card.Card;
import mainsource.system.card.CardValue;
import mainsource.system.card.Suit;

public class NewDeck {
  
  private final FULL_DECK_LEN = 52;
  private final int CARDVALUE_LEN = 13;
  private Card[] deck = new Card[FULL_DECK_LEN];
  
  public NewDeck(){
    initdeck();
  }
  
  private Card[] initdeck(){
    Card[] deck = new Card[FULL_DECK_LEN];
    for(int i=0;i<CARDVALUE_LEN;++i){
      deck[i*4+0] = new Card(getCardValueFromInt(i+1), Suit.CLUBS);
      deck[i*4+1] = new Card(getCardValueFromInt(i+1), Suit.DIAMONDS);
      deck[i*4+2] = new Card(getCardValueFromInt(i+1), Suit.HEARTS);
      deck[i*4+3] = new Card(getCardValueFromInt(i+1), Suit.SPADES);
    }
    for(int i=0;i<FULL_DECK_LEN;++i){
      int key = (int)(Math.floor(Math.random() * (FULL_DECK_LEN - i))) + i;
      Card temp = deck[key];
      deck[key] = deck[i];
      deck[i] = temp;
    }
    return deck;
  }
    
}
