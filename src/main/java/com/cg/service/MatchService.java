package com.cg.service;

import com.cg.model.Match;
import com.cg.utils.ParseUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static com.cg.view.MatcherView.getMatches;

public class MatchService {
    private static final String MATCH_FILE_PATH = "match.csv";
    private final List<Match> matches;

    public MatchService() {
        this.matches = new ArrayList<>();
    }

    // Method to display match information
    public void displayMatchInfo(Match match) {
        System.out.println("Match ID: " + match.getId());
        System.out.println("Team 1: " + match.getTeam1());
        System.out.println("Team 2: " + match.getTeam2());
        System.out.println("Date: " + match.getDate());
        System.out.println("Score: " + match.getScore());
        if (match.getReferee() != null) {
            System.out.println("Referee ID: " + match.getReferee().getId());
        } else {
            System.out.println("No referee assigned to this match.");
        }
    }

    public MatchService(List<Match> matches) {
        this.matches = matches;
    }



    // Method to update match score
    public void updateMatchScore(Match match, String newScore) {
        match.setScore(newScore);
        System.out.println("Match score updated successfully.");
    }

    // Method to update referee ID
    public void updateRefereeId(Match match, int newRefereeId) {
        if (match.getReferee() != null) {
            match.getReferee().setId(newRefereeId);
            System.out.println("Referee ID updated successfully.");
        } else {
            System.out.println("No referee assigned to this match.");
        }
    }

    // Method to delete match
    public void deleteMatch(Match match) {
        if (matches.remove(match)) {
            System.out.println("Match deleted successfully.");
        } else {
            System.out.println("Match not found.");
        }
    }

    // Method to update match information
    public void updateMatch(Match match) {
        // Implement updating match information
        String newScore = null;
        match.setScore(newScore);
        System.out.println("Match information updated successfully.");
    }

    // Method to search matches by team name
    public List<Match> searchMatchesByTeam(String teamName) {
        // Implement searching matches by team name
//        return new ArrayList<>();
        List<Match> result = new ArrayList<>();
        for (Match match : matches) {
            if (match.getHomeTeam().equals(teamName) || match.getAwayTeam().equals(teamName)) {
                result.add(match);
            }
        }
        return result;
    }

    // Method to add a new match
    public void addMatch(Match match) {
        String matchInfo = "\n" + match.getId() + "," + match.getTeam1() + "," + match.getTeam2() + "," + match.getScore() + "," + match.getDate();
        try {
            Files.write(Paths.get(MATCH_FILE_PATH), Collections.singletonList(matchInfo), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    // Method to retrieve all matches
    public List<Match> getAllMatches() {
        // Implement retrieving all matches
        return matches;
    }

    // Method to remove a match by ID
    public boolean removeMatch(String matchId) {
        // Implement removing a match by ID
        return false;
    }

    // Method to retrieve a match by ID
    public Match getMatchById(String matchId) {
        // Implement retrieving a match by ID
        return null;
    }

    public List<Match> getAllMatchesFromFile(String matchFilePath) {
        List<Match> matches = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(matchFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                // data = m3,UBVP,TCU,2-2,2024-02-29
                //String matchID, String team1, String team2, LocalDate dataTime, String score)
                Match match = new Match(data[0], data[1], data[2], ParseUtils.parseDate(data[4]), data[3]);
                matches.add(match);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matches;
    }

    public List<Match> readMatchesFromFile(String matchFilePath) {
        return readMatchesFromFile(MATCH_FILE_PATH);
    }
}


