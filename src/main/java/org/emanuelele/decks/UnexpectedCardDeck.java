package org.emanuelele.decks;

import org.emanuelele.cards.ProbabilityCard;
import org.emanuelele.cards.UnexpectedCard;

import java.util.Collections;
import java.util.List;

public class UnexpectedCardDeck implements ICardDeck {
    private List<UnexpectedCard> cards;
    private int currentIndex;

    public UnexpectedCardDeck() {
        try {
            this.cards = UnexpectedCard.getCards();
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
    public UnexpectedCard drawCard() {
        UnexpectedCard card = cards.get(currentIndex);
        currentIndex++;
        if(currentIndex == cards.size())
            currentIndex = 0;
        return card;
    }
}
