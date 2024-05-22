package org.emanuelele.decks;

public interface ICardDeck<T> {
    void shuffle();
    T drawCard();
}
