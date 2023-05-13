package jsonclasses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Data(Players players){

    public Data(@JsonProperty("players") Players players){
        this.players = players;
    }
    public Players getPlayers() {
        return players;
    }
}
