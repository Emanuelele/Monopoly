package org.emanuelele.cards.actions;

import org.emanuelele.cards.actions.impl.*;
import org.emanuelele.player.Player;
import org.jetbrains.annotations.NotNull;

import static org.emanuelele.utils.MathFunc.calcClosestStation;

public enum CardActionType implements ICardAction {

    /* Community Cards */
    ESCAPE_FROM_PRISON {
        @Override
        public void execute(@NotNull Player player) {
            new EscapeFromPrisonAction().execute(player);
        }
    },

    ADVANCE_TO_GO {
        @Override
        public void execute(@NotNull Player player) {
            new AdvaceToAction(0, true).execute(player);
        }
    },

    GO_TO_PRISON {
        @Override
        public void execute(@NotNull Player player) {
            new GoToJailAction().execute(player);
        }
    },

    GO_TO_CLOSEST_STATION {
        @Override
        public void execute(@NotNull Player player) {
            int destination = calcClosestStation(player.getCurrentPosition());
            new AdvaceToAction(destination, true).execute(player);
        }
    },
    
    /* Probability Cards */
    ELECTION_DAY {
        @Override
        public void execute(@NotNull Player player) {
            new PayToOthersAction(50).execute(player);
        }
    },
    
    GO_TO_NORTH_STATION {
        @Override
        public void execute(@NotNull Player player) {
            new AdvaceToAction(25, true).execute(player);
        }
    },

    GO_BACK_3_TILES {
        @Override
        public void execute(@NotNull Player player) {
            int destination = player.getCurrentPosition() - 3;
            new AdvaceToAction(destination, false).execute(player);
        }
    },

    GO_TO_VIA_ACCADEMIA {
        @Override
        public void execute(@NotNull Player player) {
            new AdvaceToAction(11, true).execute(player);
        }
    },

    GO_TO_VICOLO_CORTO {
        @Override
        public void execute(@NotNull Player player) {
            new AdvaceToAction(1, true).execute(player);
        }
    },

    YOU_WON_BEAUTY_CONTEST {
        @Override
        public void execute(@NotNull Player player) {
            new GiftDollarsAction(10).execute(player);
        }
    },

    DIVIDENDS {
        @Override
        public void execute(@NotNull Player player) {
            new GiftDollarsAction(50).execute(player);
        }
    },

    LIFE_INSURANCE_EXPIRING {
        @Override
        public void execute(@NotNull Player player) {
            new GiftDollarsAction(100).execute(player);
        }
    },

    GO_TO_LARGO_COLOMBO {
        @Override
        public void execute(@NotNull Player player) {
            new AdvaceToAction(24, true).execute(player);
        }
    },

    DOCTOR_BILL {
        @Override
        public void execute(@NotNull Player player) {
            new PayDollarsAction(50).execute(player);
        }
    },

    PROPERTY_FEE {
        @Override
        public void execute(@NotNull Player player) {
            new PayDollarsAction(150).execute(player);
        }
    },

    /* Unexpected cards */
    AUNT_GIFT {
        @Override
        public void execute(@NotNull Player player) {
            new GiftDollarsAction(100).execute(player);
        }
    },

    SPEED_TICKET {
        @Override
        public void execute(@NotNull Player player) {
            new PayDollarsAction(15).execute(player);
        }
    },

    GO_TO_VICOLO_STRETTO {
        @Override
        public void execute(@NotNull Player player) {
            new AdvaceToAction(3, true).execute(player);
        }
    },

    GO_TO_PIAZZA_GIULIO_CESARE {
        @Override
        public void execute(@NotNull Player player) {
            new AdvaceToAction(29, true).execute(player);
        }
    },

    REAL_ESTATE {
        @Override
        public void execute(@NotNull Player player) {
            new GiftDollarsAction(150).execute(player);
        }
    },

    GENERIC_TAX {
        @Override
        public void execute(@NotNull Player player) {
            new PayDollarsAction(25).execute(player);
        }
    },

    INPS_ERROR {
        @Override
        public void execute(@NotNull Player player) {
            new PayDollarsAction(100).execute(player);
        }
    },

    GO_TO_EAST_STATION {
        @Override
        public void execute(@NotNull Player player) {
            new AdvaceToAction(25, true).execute(player);
        }
    },

    GO_TO_CORSO_ATENEO {
        @Override
        public void execute(@NotNull Player player) {
            new AdvaceToAction(13, true).execute(player);
        }
    },

    REPAIRS {
        @Override
        public void execute(@NotNull Player player) {
            new PayDollarsAction(0).execute(player); //TO-DO
        }
    },
}
