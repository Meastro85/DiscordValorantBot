package dataframe;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Stats(int kills, int deaths, int assists){

    public Stats(@JsonProperty("kills") int kills,
                 @JsonProperty("deaths") int deaths,
                 @JsonProperty("assists") int assists){
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;
    }

}
