package com.cg.view;
import com.cg.model.Player;
import com.cg.model.Team;
import com.cg.service.PlayerService;
import com.cg.service.TeamService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TeamView {
    private final Scanner scanner;
    private static final String TEAM_FILE_PATH = "team.csv";
    private final PlayerService     playerService;

    public TeamView() {
        this.scanner = new Scanner(System.in);
        playerService = new PlayerService();
    }

    public void displayMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("Team Management Menu:");
            System.out.println("1. Display Team Information");
            System.out.println("2. Add Team");
            System.out.println("3. Edit Team");
            System.out.println("4. Delete Team");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayTeamInformationMenu();
                    break;
                case 2:
                    addTeam();
                    break;
                case 3:
                    editTeam();
                    break;
                case 4:
                    deleteTeam();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayTeamInformationMenu() {
        List<Team> teams = readTeamsFromFile();
        if (teams.isEmpty()) {
            System.out.println("No teams found.");
            return;
        }
        System.out.println("Team Information:");
        for (Team team : teams) {
            displayTeamInformation(team);
        }
    }

    private void displayTeamInformation(Team team) {
        System.out.println("Team ID: " + team.getId());
        System.out.println("Team Name: " + team.getName());
        System.out.println("Captain: " + team.getCaptain().getName());
        System.out.println("Players: " + "11");
        List<Player> players = team.getPlayers();
        for (Player player : players) {
            System.out.println("- ID: " + player.getId() + ", Name: " + player.getName() + ", DOB: " + player.getDob());
        }
        System.out.println();
    }

    private void addTeam() {
        Team team = createTeam();
        List<Team> teams = readTeamsFromFile();
        teams.add(team);
        writeTeamsToFile(teams);
        System.out.println("Team added successfully.");
    }

    private Team createTeam() {
        System.out.println("Enter team details:");
        System.out.print("Team ID: ");
        String id = scanner.nextLine();
        scanner.nextLine(); // Consume newline
        System.out.print("Team Name: ");
        String name = scanner.nextLine();
        System.out.print("Captain Name: ");
        String captainName = scanner.nextLine();

        // Create captain object
        Player captain = new Player(captainName);

        return new Team(id, name, captain);
    }

    private void editTeam() {
        List<Team> teams = readTeamsFromFile();
        System.out.print("Enter Team ID to edit: ");
        String id = scanner.nextLine();
        boolean found = false;
        for (Team team : teams) {
            if (team.getId().equals(id)) {
                found = true;
                Team updatedTeam = createTeam(); // Get updated team details
                team.setName(updatedTeam.getName());
                team.setCaptain(updatedTeam.getCaptain());
                writeTeamsToFile(teams);
                System.out.println("Team information updated successfully.");
                break;
            }
        }
        if (!found) {
            System.out.println("Team with ID " + id + " not found.");
        }
    }

    private void deleteTeam() {
        List<Team> teams = readTeamsFromFile();
        System.out.print("Enter Team ID to delete: ");
        String id = scanner.nextLine();
        TeamService teamService = new TeamService();
        Team teamToDelete = teamService.getTeamById(id);
        if (teamToDelete != null) {
            teams.remove(teamToDelete);
            writeTeamsToFile(teams);
            System.out.println("Team with ID " + id + " deleted successfully.");
        } else {
            System.out.println("Team with ID " + id + " not found.");
        }
    }


    private List<Team> readTeamsFromFile() {
        List<Team> teams = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(TEAM_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String id = data[0].trim();
                String name = data[1];
                String captainName = data[2];
                // Create captain object
                Player captain = new Player(captainName);
                Team team = new Team(id, name, captain);
                List<Player> players = playerService.getAllPlayers(team);
                team.setPlayers(players);
                teams.add(team);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return teams;
    }


    private void writeTeamsToFile(List<Team> teams) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEAM_FILE_PATH))) {
            for (Team team : teams) {
                writer.write(team.getId() + "," + team.getName() + "," + team.getCaptain().getName() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

