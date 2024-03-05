package com.cg.LiveMatch;

import com.cg.store.Match.Matches;
import com.cg.store.Team.Teams;
import com.cg.store.League.Leagues;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class Test {
    public static void playRandomMatch(Teams team1, Teams team2, Leagues leagues) {
        Random random = new Random();
        int team1Goals = random.nextInt(4);
        int team2Goals = random.nextInt(4);
        // Ghi nhận kết quả của trận đấu
        Matches randomMatch = new Matches(team1, team2, team1Goals, team2Goals);
        Leagues.playMatch(randomMatch);
    }
    public static void main(String[] args) throws IOException {
        // Assume 'teams' and 'leagues' are defined somewhere in your code
        List<Teams> teams = null; // You need to define this
        Leagues leagues = null; // You need to define this
        System.out.println("Teams or leagues are not properly initialized.");
    }
}


