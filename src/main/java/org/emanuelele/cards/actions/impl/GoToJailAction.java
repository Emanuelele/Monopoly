package org.emanuelele.cards.actions.impl;

import org.emanuelele.cards.actions.ICardAction;
import org.emanuelele.player.Player;
import org.jetbrains.annotations.NotNull;

public class GoToJailAction implements ICardAction {

    @Override
    public void execute(@NotNull Player player){
        if(!player.hasExitFromPrisonCard())
            player.setInJail(true);
        else player.hasExitFromPrisonCard(false);
        player.setCurrentPosition(10);
    }
}
