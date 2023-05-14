package commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.internal.interactions.CommandDataImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface Slash {
    void onSlashCommandEvent(SlashCommandInteractionEvent event) throws IOException, InterruptedException;

    String getName();
    String getDescription();
    boolean isSpecifiedGuild();
    boolean isGuildOnly();
    default List<OptionData> getOptions(){
        return new ArrayList<>();
    }
    default CommandData getcommandData(){
        return new CommandDataImpl(getName(),getDescription())
                .setGuildOnly(isGuildOnly())
                .addOptions(getOptions());
    }
}
