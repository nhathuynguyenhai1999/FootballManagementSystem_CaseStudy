package com.cg.service;

import com.cg.model.Team;
import java.util.List;
import java.util.Scanner;

public class TeamService {
    private List<Team> teams; // Assume this list contains all teams

    public void displayTeams() {
        // Code to display all teams
        for (Team team : teams) {
            System.out.println(team);
        }
    }

    public void addTeam(Team team) {
        // Code to add a team
        teams.add(team);
        System.out.println("Team added successfully.");
    }

    public void updateTeam(Team team) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Current details of the team:");
        System.out.println(team);

        System.out.println("Enter the new details (press Enter to skip):");

        System.out.print("New Team Name: ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            team.setName(newName);
        }

        // You can add similar logic for other fields such as coach, captain, etc.

        System.out.println("Team updated successfully.");
    }


    public void deleteTeam(Team team) {
        if (teams.contains(team)) {
            teams.remove(team);
            System.out.println("Team deleted successfully.");
        } else {
            System.out.println("Team not found.");
        }
    }


    public Team getTeamById(String id) {
        // Code to get team by ID
        for (Team team : teams) {
            if (team.getId().equals(id)) {
                return team;
            }
        }
        return null; // Return null if team with given ID is not found
    }
}
