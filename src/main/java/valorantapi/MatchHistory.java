package valorantapi;

import com.iwebpp.crypto.TweetNaclFast;
import dataframe.AllPlayers;
import dataframe.Data;
import httpclasses.Requests;
import playerclasses.playerStats;

import java.io.IOException;
import java.io.StringBufferInputStream;
import java.util.*;

public class MatchHistory {

    static Map<String, String> parameters;
    static Map<String, playerStats> requesterTeam;
    static Map<String, playerStats> sortedMap;
    static Map<Integer, List<String>> requesterTeamAgents;

    public static List<List<String>> getMatchHistory(String requester, String tag, String filter, int matches) throws IOException, InterruptedException {

        parameters = new HashMap<>();
        requesterTeam = new HashMap<>();
        sortedMap = new LinkedHashMap<>();
        requesterTeamAgents = new HashMap<>();


        parameters.put("requester", requester);
        parameters.put("tag", tag);
        parameters.put("filter", filter);
        List<Data> data = Requests.matchHistory(parameters).body().get().getLastNumberOfData(matches);

        String requesterTeamColor;
        List<String> maps = new ArrayList<>();

        for (Data dt : data) {
            requesterTeamColor = dt.players().getRequesterTeam(requester);
            for (AllPlayers players : dt.players().allPlayers()) {
                if (Objects.equals(players.team(), requesterTeamColor)) {
                    if (requesterTeam.containsKey(players.name())) {
                        playerStats oldStats = requesterTeam.get(players.name());
                        oldStats.addKills(players.stats().kills());
                        oldStats.addDeaths(players.stats().deaths());
                        oldStats.addAssists(players.stats().assists());
                        if(!oldStats.getAgents().contains(players.character())){
                            oldStats.addAgent(players.character());
                        }
                        requesterTeam.put(players.name(), oldStats);
                    } else {
                        requesterTeam.put(players.name(), new playerStats(players.stats().kills(), players.stats().deaths(), players.stats().assists(), players.character()));
                    }
                }
            }
            if(!maps.contains(dt.metaData().map())){
                maps.add(dt.metaData().map());
            }
        }
        List<Map.Entry<String, playerStats>> list = new ArrayList<>(requesterTeam.entrySet());
        list.sort((entry1, entry2) -> Integer.compare(entry2.getValue().getKills(), entry1.getValue().getKills()));
        for (Map.Entry<String, playerStats> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        List<List<String>> items = new ArrayList<>();
        List<String> names = new ArrayList<>();
        List<String> agents = new ArrayList<>();

        for (Map.Entry<String, playerStats> player : sortedMap.entrySet()) {
            names.add(String.format("%-16s %d/%d/%-2d (%.2f)",player.getKey(), player.getValue().getKills(), player.getValue().getDeaths(), player.getValue().getAssists(), (double) player.getValue().getKills() / player.getValue().getDeaths()));
            agents.add(player.getValue().getAgents());
        }


        items.add(names);
        items.add(agents);
        items.add(maps);

        return items;
    }
}
