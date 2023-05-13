package jsonclasses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonToken;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MatchInfo(Data[] data, int status, String name, String character, int kills, int deaths, int assists) {

    public MatchInfo(@JsonProperty("data") Data[] data,
                     @JsonProperty("status") int status,
                     @JsonProperty("name") String name,
                     @JsonProperty("character") String character,
                     @JsonProperty("kills") int kills,
                     @JsonProperty("deaths") int deaths,
                     @JsonProperty("assists") int assists) {
        this.data = data;
        this.status = status;
        this.name = name;
        this.character = character;
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;
    }

    public int getStatus(){
        return status;
    }

    public Data[] getData(){
        return data;
    }

}
