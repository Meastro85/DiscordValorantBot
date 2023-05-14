package org.example;

import httpclasses.Requests;
import dataframe.AllPlayers;
import dataframe.Data;
import playerclasses.playerStats;
import utilclasses.MapUtil;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("username", "Meastro");
        parameters.put("tag", "3696");
        parameters.put("filter", "premier");

        String requester = "Meastro";
        String requesterTeamColor;
        Map<String, playerStats> requesterTeam = new HashMap<>();

        Map<String, Map<String,playerStats>> teams = new HashMap<>();

        List<Data> data = Requests.premierHistory(parameters).body().get().getLastNumberOfData(2);

        for (Data dt : data) {
            requesterTeamColor = dt.players().getRequesterTeam(requester);
            for (AllPlayers players : dt.players().allPlayers()) {
                if (Objects.equals(players.team(), requesterTeamColor)) {
                    if(requesterTeam.containsKey(players.name())){
                        playerStats oldStats = requesterTeam.get(players.name());
                        oldStats.addKills(players.stats().kills());
                        oldStats.addDeaths(players.stats().deaths());
                        oldStats.addAssists(players.stats().assists());
                        requesterTeam.put(players.name(), oldStats);
                    } else {
                        requesterTeam.put(players.name(), new playerStats(players.stats().kills(), players.stats().deaths(), players.stats().assists()));
                    }
                }
            }
            List<Map.Entry<String, playerStats>> list = new ArrayList<>(requesterTeam.entrySet());
            list.sort((entry1, entry2) -> Integer.compare(entry2.getValue().getKills(), entry1.getValue().getKills()));
            Map<String, playerStats> sortedMap = new LinkedHashMap<>();
            for (Map.Entry<String, playerStats> entry : list) {
                sortedMap.put(entry.getKey(), entry.getValue());
            }
            teams.put(requesterTeamColor, sortedMap);
        }



        for (Map.Entry<String, Map<String, playerStats>> team : teams.entrySet()) {
            for (Map.Entry<String, playerStats> player: team.getValue().entrySet()) {
                System.out.println(player.getKey() + " " + player.getValue().getKills() + "/" + player.getValue().getDeaths() + "/" + player.getValue().getAssists());
            }
        }
    }

}
