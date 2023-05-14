package dataframe;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Data(Players players, MetaData metaData) {

    public Data(@JsonProperty("players") Players players,
                @JsonProperty("metadata") MetaData metaData) {
        this.players = players;
        this.metaData = metaData;
    }
}
