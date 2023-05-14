package main;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.exceptions.InvalidTokenException;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

public class ValorantBot {

    private final ShardManager shardManager;

    public ValorantBot() throws InvalidTokenException {
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault("");
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.watching("Valorant stats"));
        shardManager = builder.build();
    }

    public ShardManager getShardManager(){
        return shardManager;
    }

}
