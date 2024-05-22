package org.emanuelele.decks;

import org.emanuelele.cards.ProbabilityCard;

import java.util.Collections;
import java.util.List;

public class ProbabilityCardDeck implements ICardDeck {
    private List<ProbabilityCard> cards;
    private int currentIndex;

    public ProbabilityCardDeck() {
        try {
            this.cards = ProbabilityCard.getCards();
            shuffle();
            currentIndex = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void shuffle() {
        Collections.shuffle(cards);
    }

    @Override
    public ProbabilityCard drawCard() {
        ProbabilityCard card = cards.get(currentIndex);
        currentIndex++;
        if(currentIndex == cards.size())
            currentIndex = 0;
        return card;
    }
}
