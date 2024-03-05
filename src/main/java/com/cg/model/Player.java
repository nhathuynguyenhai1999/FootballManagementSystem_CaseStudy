package com.cg.model;

import java.time.LocalDate;
import java.time.Period;

public class Player {
    private int id;
    private String name;
    private LocalDate dob;
    private String team;
    private String position;

    public Player(int id, String name, LocalDate dob, String team, String position) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.team = team;
        this.position = position;
    }

    // Constructor for a player with ID, name, and date of birth
    public Player(int id, String name, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.dob = dob;
    }

    // Constructor for a player with only a name (assuming this is used for a captain)
    public Player(String captainName) {
        this.name = captainName;
    }

    // Getter and setter methods for ID, name, and team
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    // Getter and setter methods for player position
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    // Method to calculate and return player's age
    public int getAge() {
        LocalDate currentDate = LocalDate.now();
        return Period.between(dob, currentDate).getYears();
    }

    // Method to set player's age (not sure if it's needed)
    public void setAge(int newAge) {
        // This method doesn't need to be implemented as age is derived from date of birth
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s", this.id, this.name, this.dob);
    }
}



