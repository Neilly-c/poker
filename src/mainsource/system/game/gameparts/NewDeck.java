package mainsource.system.game.gameparts;

import mainsource.system.card.Card;
import mainsource.system.card.Suit;

import java.util.*;

import static mainsource.system.card.CardValue.getCardValueFromInt;
import static mainsource.system.game.NewGame.PLRS;

public class NewDeck {

  private final int FULL_DECK_LEN = 52;
  private final int CARDVALUE_LEN = 13;
  private Queue<Card> deck = new ArrayDeque<>();
  private List<Card>[] whole_cards;
  private Card[] board = new Card[5];

  public NewDeck(){
    whole_cards = new ArrayList[PLRS];
    for(int i=0;i<PLRS;++i){
      whole_cards[i] = new ArrayList<Card>();
    }
    initDeck();
  }

  public void initDeck(){
    List<Card> deck_list = new ArrayList<>();
    for(int i=0;i<CARDVALUE_LEN;++i){
      deck_list.add(new Card(getCardValueFromInt(i+1), Suit.CLUBS));
      deck_list.add(new Card(getCardValueFromInt(i+1), Suit.DIAMONDS));
      deck_list.add(new Card(getCardValueFromInt(i+1), Suit.HEARTS));
      deck_list.add(new Card(getCardValueFromInt(i+1), Suit.SPADES));
    }
    Collections.shuffle(deck_list);
    deck = new ArrayDeque<>(deck_list);
    for(int i=0;i< PLRS;++i){
      whole_cards[i].clear();
    }
    Arrays.fill(board, null);
  }

  private void deal1(int p){
    whole_cards[p].add(deck.poll());
  }

  private void dealBoard(){
    for(int i=0;i<board.length;++i){
      if(Objects.nonNull(board[i])){
        continue;
      }else{
        board[i] = deck.poll();
      }
    }
  }

  public void dealPreflop() {
    for(int i=0;i<whole_cards.length;++i){
      deal1(i);
      deal1(i);
    }
  }

  public Card[] getWholeCards(int p){
    return (Card[]) whole_cards[p].toArray(new Card[]{});
  }

  public Card[] getBoard(){
    return board;
  }
}
