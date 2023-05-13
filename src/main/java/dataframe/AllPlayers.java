package jsonclasses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AllPlayers(String name, String character, String team) {

    public AllPlayers(@JsonProperty("name") String name,
                      @JsonProperty("character") String character,
                      @JsonProperty("team") String team) {
        this.team = team;
        this.name = name;
        this.character = character;
    }

}
