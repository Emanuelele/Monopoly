package org.emanuelele.cards.actions.impl;

import org.emanuelele.cards.actions.ICardAction;
import org.emanuelele.player.Player;
import org.jetbrains.annotations.NotNull;

public class PayToOthersAction implements ICardAction {
    private final int amount;

    public PayToOthersAction(int amount) {
        this.amount = amount;
    }

    @Override
    public void execute(@NotNull Player player){
        player.addMoney(amount); //TO-DO
    }
}
