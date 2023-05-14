package dataframe;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Players(AllPlayers[] allPlayers) {

    public Players(@JsonProperty("all_players") AllPlayers[] allPlayers){
        this.allPlayers = allPlayers;
    }

    public AllPlayers[] getAllPlayers() {
        return allPlayers;
    }

    public String getRequesterTeam(String requester){
        for (AllPlayers player: allPlayers) {
            if(Objects.equals(player.name(), requester)){
                return player.team();
            }
        }
        return null;
    }

}
