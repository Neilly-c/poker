package mainsource.system.game;

import mainsource.system.card.Card;
import mainsource.system.card.Suit;

import java.util.*;

import static mainsource.system.card.CardValue.getCardValueFromInt;

public class NewDeck {

  private final int FULL_DECK_LEN = 52;
  private final int CARDVALUE_LEN = 13;
  private Queue<Card> deck = new ArrayDeque<>();

  public NewDeck(){
    initdeck();
  }

  private Queue<Card> initdeck(){
    List<Card> deck_list = new ArrayList<>();
    for(int i=0;i<CARDVALUE_LEN;++i){
      deck_list.add(new Card(getCardValueFromInt(i+1), Suit.CLUBS));
      deck_list.add(new Card(getCardValueFromInt(i+1), Suit.DIAMONDS));
      deck_list.add(new Card(getCardValueFromInt(i+1), Suit.HEARTS));
      deck_list.add(new Card(getCardValueFromInt(i+1), Suit.SPADES));
    }
    Collections.shuffle(deck_list);
    deck = new ArrayDeque<>(deck_list);
    return deck;
  }

  public Card deal1(){
    return deck.poll();
  }

}
