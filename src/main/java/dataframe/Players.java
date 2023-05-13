package jsonclasses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Players(AllPlayers[] allPlayers) {

    public Players(@JsonProperty("all_players") AllPlayers[] allPlayers){
        this.allPlayers = allPlayers;
    }

    public AllPlayers[] getAllPlayers() {
        return allPlayers;
    }
}
