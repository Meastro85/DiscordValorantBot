package playerclasses;

public class playerStats {

    private int kills;
    private int deaths;
    private int assists;

    public playerStats(int kills, int deaths, int assists){
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;
    }

    public int getKills() {
        return kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getAssists() {
        return assists;
    }

    public void addKills(int kills){
        this.kills += kills;
    }

    public void addDeaths(int deaths){
        this.deaths += deaths;
    }

    public void addAssists(int assists){
        this.assists += assists;
    }

}
