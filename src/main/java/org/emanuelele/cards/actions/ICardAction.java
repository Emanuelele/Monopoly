package org.emanuelele.cards.actions;

import org.emanuelele.player.Player;
import org.jetbrains.annotations.NotNull;

public interface ICardAction {
    void execute(@NotNull Player player);
}
