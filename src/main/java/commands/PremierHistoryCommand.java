package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import valorantapi.MatchHistory;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class PremierHistoryCommand implements Slash {


    @Override
    public void onSlashCommandEvent(SlashCommandInteractionEvent event) throws IOException, InterruptedException {
        String requester = event.getOption("requester", OptionMapping::getAsString);
        String tag = event.getOption("tag", OptionMapping::getAsString);
        String filter = "premier";
        String matches = event.getOption("matches", OptionMapping::getAsString);
        int matchInt;
        if(matches != null && Integer.parseInt(matches) <= 5 && Integer.parseInt(matches) > 0){
            matchInt = Integer.parseInt(matches);
        } else {
            matchInt = 5;
        }

        List<List<String>> reply = MatchHistory.getMatchHistory(requester, tag, filter, matchInt);
        
        StringBuilder names = new StringBuilder();
        StringBuilder agents = new StringBuilder();
        StringBuilder maps = new StringBuilder();

        for (String name: reply.get(0)) {
            names.append(name).append("\t\n");
        }

        agents.append("\n");
        for(String agent: reply.get(1)){
            agents.append(agent).append("\n");
        }

        for(String map: reply.get(2)){
            maps.append(map).append(" ");
        }
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(Color.CYAN);
        eb.setTitle("Match stats of last " + matchInt + " matches");
        eb.addField("Maps played", maps.toString(), false);
        eb.addField("Team","```" +  names + "```", true);
        eb.addField("Agents","```" + agents + "```", true);

        event.replyEmbeds(eb.build()).queue();
    }

    @Override
    public String getName() {
        return "getpremierhistory";
    }

    @Override
    public String getDescription() {
        return "Get the stats of your # last played matches (max 5)";
    }

    @Override
    public boolean isSpecifiedGuild() {
        return false;
    }

    @Override
    public boolean isGuildOnly() {
        return false;
    }

    @Override
    public List<OptionData> getOptions() {
        return List.of(new OptionData(OptionType.STRING, "requester", "Name of the person requesting GET.", true),
                new OptionData(OptionType.STRING, "tag", "Tag of the person requesting GET", true),
                new OptionData(OptionType.STRING, "matches", "Amount of matches to get (max 5)", true));
    }
}
