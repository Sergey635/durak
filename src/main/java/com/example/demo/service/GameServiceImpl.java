package com.example.demo.service;

import com.example.demo.data.DataDeck;
import com.example.demo.model.Card;
import com.example.demo.model.Game;
import com.example.demo.model.Player;
import com.example.demo.model.Suit;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Data
@NoArgsConstructor
@Service
public class GameServiceImpl implements  IGameService{

    private Game game;
    private Deque<Card> deck;
    List<Card> refill = new ArrayList<>();
    List<Card> refill1 = new ArrayList<>();


    @Autowired
    DataDeck data;


    @Override
    public void init() {
        deck = new LinkedList<>(data.getDeck());
        System.out.println(deck.size());
        refill.clear();
    }
    public Card giveCard(){
        return deck.poll();
    }
    public void shuffleDeck(){
        List<Card> list = new ArrayList<>(deck);
        Collections.shuffle(list);
        deck = new LinkedList<>(list);
    }
    public void removeCard(Card card){
        deck.removeFirstOccurrence(card);
    }
    public int countDeck(){
        if (deck==null){
            return 0;
        }
        return deck.size();
    }
    public List<Card> giveMeCards(){
        System.out.println(refill.size());
        int cardsAmount = 6 - refill.size();
        System.out.println(cardsAmount);
        for (int i = 0; i < cardsAmount; i++) {
                Card card = this.giveCard();
                refill.add(card);
            }
            return refill;

    }
    public List<Card> addCard(){
        if (refill.size()<6) {
            Card card = this.giveCard();
            refill.add(card);
        }
        return refill;
    }
}
