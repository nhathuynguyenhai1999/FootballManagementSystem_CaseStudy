package com.cg.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static com.cg.FootballApp.scanner;

public class Match {
    private String id;
    private String team1;
    private String team2;
    private LocalDate date;
    private String score;
    private Referee referee;

    public Match(String id, String team1, String team2, LocalDate date, String score, Referee referee) {
        this.id = id;
        this.team1 = team1;
        this.team2 = team2;
        this.date = date;
        this.score = score;
        this.referee = referee;
    }



    public Match(String datum, String datum1, String datum2, String datum3) {
        this.id = datum;
        this.team1 = datum1;
        this.team2 = datum2;
        setDateTime(datum3);
    }

    public Match(String matchID, String team1, String team2, LocalDate dataTime, String score) {

        this.id = matchID;
        this.team1 = team1;
        this.team2 = team2;
        this.date = dataTime;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Referee getReferee() {
        return referee;
    }

    public void setReferee(Referee referee) {
        this.referee = referee;
    }

    public void setDateTime(String dateTime) {
        try {
            // Parse the dateTime string and set the date field
            this.date = LocalDate.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (DateTimeParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
            // Handle the parsing exception accordingly, such as logging or displaying an error message
        }
    }

    public String getDateTime() {
        // Return a string representation of the date and time
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public String getTeamNames() {
        return getTeamNames();
    }

    public Object getHomeTeam() {
        return getHomeTeam();
    }

    public Object getAwayTeam() {
        return getAwayTeam();
    }
}


