package org.emanuelele.cards.actions.impl;

import org.emanuelele.cards.actions.ICardAction;
import org.emanuelele.player.Player;
import org.jetbrains.annotations.NotNull;

public class PayDollarsAction implements ICardAction {
    private final int amount;

    public PayDollarsAction(int amount) {
        this.amount = amount;
    }

    @Override
    public void execute(@NotNull Player player){
        player.removeMoney(this.amount);
    }
}
