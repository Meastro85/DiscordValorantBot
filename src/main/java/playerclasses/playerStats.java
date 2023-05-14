package playerclasses;

public class playerStats {

    private int kills;
    private int deaths;
    private int assists;
    private String agents;

    public playerStats(int kills, int deaths, int assists, String agents) {
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;
        this.agents = agents;
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

    public String getAgents() {
        return agents;
    }

    public void addKills(int kills) {
        this.kills += kills;
    }

    public void addDeaths(int deaths) {
        this.deaths += deaths;
    }

    public void addAssists(int assists) {
        this.assists += assists;
    }

    public void addAgent(String agent){
        this.agents += " " + agent;
    }

}
