package dataframe;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Players(AllPlayers[] allPlayers) {

    public Players(@JsonProperty("all_players") AllPlayers[] allPlayers){
        this.allPlayers = allPlayers;
    }

    public String getRequesterTeam(String requester){
        for (AllPlayers player: allPlayers) {
            String name = player.name();
            if(name.equalsIgnoreCase(requester)){
                return player.team();
            }
        }
        return null;
    }

}
