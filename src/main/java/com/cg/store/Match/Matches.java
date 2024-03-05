package com.cg.store.Match;

import com.cg.store.Team.Teams;

public class Matches {
    private Teams homeTeams;
    private Teams awayTeams;
    private int homeGoals;
    private int awayGoals;
    public Matches(Teams homeTeams, Teams awayTeams, int homeGoals, int awayGoals) {
        this.homeTeams = homeTeams;
        this.awayTeams = awayTeams;
        this.homeGoals = homeGoals;
        this.awayGoals = awayGoals;
    }

    public Teams getHomeTeam() {
        return homeTeams;
    }

    public Teams getAwayTeam() {
        return awayTeams;
    }

    public int getHomeGoals() {
        return homeGoals;
    }

    public int getAwayGoals() {
        return awayGoals;
    }
}
