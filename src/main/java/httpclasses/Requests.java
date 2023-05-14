package httpclasses;

import jsonclasses.JsonBodyHandler;
import dataframe.MatchInfo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.function.Supplier;

public class Requests {

    private static final String HISTORYURL = "https://api.henrikdev.xyz/valorant/v3/matches/eu/";

    public static HttpResponse<Supplier<MatchInfo>> matchHistory(Map<String, String> parameters) throws IOException, InterruptedException {

        String params = String.format("%s/%s?filter=%s", parameters.get("requester"), parameters.get("tag"), parameters.get("filter"));

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(HISTORYURL + params)).build();

        return client.send(request, new JsonBodyHandler<>(MatchInfo.class));
    }

}
