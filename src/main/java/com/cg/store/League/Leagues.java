package com.cg.store.League;

import com.cg.store.Match.Matches;
import com.cg.store.Team.Teams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leagues {
    private String name;
    private List<Teams> teams; // Sửa tên biến team thành teams và import ArrayList

    public Leagues(String name) {
        this.name = name;
        this.teams = new ArrayList<>();
    }

    public void addTeam(Teams teams) {
        this.teams.add(teams);
    }

    public static void playMatch(Matches matches) {
        matches.getHomeTeam().incrementMatchesPlayed();
        matches.getAwayTeam().incrementMatchesPlayed();
        if (matches.getHomeGoals() > matches.getAwayGoals()) {
            matches.getHomeTeam().addPoints(3);
        } else if (matches.getHomeGoals() < matches.getAwayGoals()) {
            matches.getAwayTeam().addPoints(3);
        } else {
            matches.getHomeTeam().addPoints(1);
            matches.getAwayTeam().addPoints(1);
        }
    }

    public void showTable() {
        System.out.println("Leagues Table - " + name);
        System.out.printf("%-20s %-10s %-10s%n", "Teams", "Played", "Points");
        Collections.sort(teams, (a, b) -> b.getPoints() - a.getPoints());
        for (Teams teams : this.teams) {
            System.out.printf("%-20s %-10d %-10d%n", teams.getName(), teams.getMatchesPlayed(), teams.getPoints());
        }
    }
}

