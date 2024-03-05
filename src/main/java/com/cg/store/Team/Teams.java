package com.cg.store.Team;

import java.util.List;

public class Teams {
    private String name;
    private List<String> players;
    private int points;
    private int matchesPlayed;

    public Teams(String name, List<String> players){
        this.name = name;
        this.players = players;
        this.points = 0;
        this.matchesPlayed = 0;
    }
    public String getName() {
        return name;
    }

    public List<String> getPlayers() {
        return players;
    }

    public int getPoints() {
        return points;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void incrementMatchesPlayed() {
        this.matchesPlayed++;
    }

    public void addPoints(int points) {
        this.points += points;
    }
}
