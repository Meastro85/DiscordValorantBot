package main;

import commands.SlashCommandHandler;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.exceptions.InvalidTokenException;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import java.util.Objects;

public class ValorantBot {

    private final Dotenv config;

    private final JDA jda;

    public ValorantBot() throws InvalidTokenException, InterruptedException {
        config = Dotenv.configure().load();
        jda = JDABuilder.createDefault(config.get("TOKEN"))
                .setStatus(OnlineStatus.ONLINE)
                .enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES)
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .setChunkingFilter(ChunkingFilter.ALL)
                .enableCache(CacheFlag.ONLINE_STATUS)
                .setActivity(Activity.watching("Valorant stats"))
                .build();

        jda.awaitReady().addEventListener(new SlashCommandHandler(jda, Objects.requireNonNull(jda.getGuildById("725861294502314067"))));
    }

    public Dotenv getConfig(){
        return config;
    }

    public JDA getJda(){
        return jda;
    }

}
