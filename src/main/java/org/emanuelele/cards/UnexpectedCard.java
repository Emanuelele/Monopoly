package org.emanuelele.cards;
import lombok.Getter;
import org.emanuelele.cards.actions.CardActionType;
import org.emanuelele.config.Config;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

@Getter
public class UnexpectedCard {
    private String key;
    private String title;
    private String description;
    private String image;
    private CardActionType action;

    UnexpectedCard(String key, Properties properties) {
        this.key = key;
        loadProperties(properties);
    }

    private void loadProperties(Properties properties) {
        this.title = properties.getProperty(key + ".title");
        this.description = properties.getProperty(key + ".description");
        this.image = properties.getProperty(key + ".image");
        this.action = CardActionType.valueOf(properties.getProperty(key + ".action"));
    }

    private static Properties loadUnexpectedCard() throws IOException {
        Config config = new Config();
        Properties properties = new Properties();
        try (InputStream input = UnexpectedCard.class.getClassLoader().getResourceAsStream(config.getString("UNEXPECTED_CARDS_PATH"))) {
            if (input == null) {
                throw new IOException("Sorry, unable to find " + config.getString("UNEXPECTED_CARDS_PATH"));
            }
            properties.load(input);
        }
        return properties;
    }

    public static List<UnexpectedCard> getCards() throws IOException {
        Properties properties = loadUnexpectedCard();
        List<UnexpectedCard> cards = new ArrayList<>();
        for (String key : properties.stringPropertyNames()) {
            if (key.startsWith("unexpected") && key.endsWith(".title")) {
                String cardKey = key.substring(0, key.lastIndexOf("."));
                cards.add(new UnexpectedCard(cardKey, properties));
            }
        }
        return cards;
    }
}
