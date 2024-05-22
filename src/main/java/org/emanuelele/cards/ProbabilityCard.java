package org.emanuelele.cards;
import lombok.Getter;
import org.emanuelele.cards.actions.CardActionType;
import org.emanuelele.config.Config;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Getter
public class ProbabilityCard {
    private String key;
    private String title;
    private String description;
    private String image;
    private CardActionType action;


    ProbabilityCard(String key, Properties properties) {
        this.key = key;
        loadProperties(properties);
    }

    private void loadProperties(Properties properties) {
        this.title = properties.getProperty(key + ".title");
        this.description = properties.getProperty(key + ".description");
        this.image = properties.getProperty(key + ".image");
        this.action = CardActionType.valueOf(properties.getProperty(key + ".action"));
    }

    private static Properties loadProbabilityCards() throws IOException {
        Config config = new Config();
        Properties properties = new Properties();
        try (InputStream input = ProbabilityCard.class.getClassLoader().getResourceAsStream(config.getString("PROBABILITY_CARDS_PATH"))) {
            if (input == null) {
                throw new IOException("Sorry, unable to find " + config.getString("PROBABILITY_CARDS_PATH"));
            }
            properties.load(input);
        }
        return properties;
    }

    public static List<ProbabilityCard> getCards() throws IOException {
        Properties properties = loadProbabilityCards();
        List<ProbabilityCard> cards = new ArrayList<>();
        for (String key : properties.stringPropertyNames()) {
            if (key.startsWith("probability_") && key.endsWith(".title")) {
                String cardKey = key.substring(0, key.lastIndexOf("."));
                cards.add(new ProbabilityCard(cardKey, properties));
            }
        }
        return cards;
    }
}
