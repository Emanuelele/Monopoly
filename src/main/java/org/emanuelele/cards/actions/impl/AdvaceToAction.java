package org.emanuelele.cards.actions.impl;

import org.emanuelele.cards.actions.ICardAction;
import org.emanuelele.player.Player;
import org.jetbrains.annotations.NotNull;

public class AdvaceToAction implements ICardAction {
    private final int destination;
    private final boolean canPassThroughGo;

    public AdvaceToAction(int destination, boolean canPassThroughGo) {
        this.destination = destination;
        this.canPassThroughGo = canPassThroughGo;
    }

    @Override
    public void execute(@NotNull Player player) {
        player.setCurrentPosition(this.destination, canPassThroughGo);
    }
}
