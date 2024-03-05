package com.cg.service;

import com.cg.model.Player;
import com.cg.model.Team;

import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlayerService {

    private static final String PLAYER_FILE_PATH = "Players.csv";

    // Method to display player information for a team
    public void displayPlayers(Team team) {
        System.out.println("Players for Team " + team.getName() + ":");
        List<Player> players = team.getPlayers();
        for (Player player : players) {
            System.out.println("Name: " + player.getName() + ", Age: " + player.getAge() + ", Position: " + player.getPosition());
        }
    }

    // Method to update player information
    public void updatePlayer(Player player, String newName, int newAge, String newPosition) {
        player.setName(newName);
        player.setAge(newAge);
        player.setPosition(newPosition);
        System.out.println("Player information updated successfully.");
    }

    // Method to delete player from a team
    public void deletePlayer(Team team, Player player) {
        team.removePlayer(player);
        System.out.println("Player deleted successfully.");
    }

    // Method to get player by ID
    public Player getPlayerById(int id) {
        PlayerService team = null;
        List<Player> players = getPlayers(PLAYER_FILE_PATH);
        for (Player player : players) {
            if (player.getId() == id) {
                return player;
            }
        }
        return null; // Return null if player with given ID is not found
    }

    // Method to read player data from Player.csv file and populate the team
    public List<Player> getAllPlayers(Team team) {
        List<Player> allPlayers = readPlayersFromFile(); // Đọc danh sách cầu thủ từ tệp
        List<Player> teamPlayers = new ArrayList<>(); // Danh sách cầu thủ thuộc đội bóng
        for (Player player : allPlayers) {
            if (player.getTeam() != null && player.getTeam().equals(team.getName())) {
                teamPlayers.add(player); // Thêm cầu thủ vào danh sách của đội bóng
            }
        }
        return teamPlayers;
    }


    // Method to search players by the first letter of their name
    public List<Player> searchPlayerByFirstLetter(String letter) {
        List<Player> matchingPlayers = new ArrayList<>();
        List<Player> players = readPlayersFromFile();
        for (Player player : players) {
            if (player.getName().startsWith(letter)) {
                matchingPlayers.add(player);
            }
        }
        return matchingPlayers;
    }

    // Method to search players by their exact name
    public List<Player> searchPlayerByName(String name) {
        List<Player> matchingPlayers = new ArrayList<>();
        List<Player> players = readPlayersFromFile();
        for (Player player : players) {
            if (player.getName().equalsIgnoreCase(name)) {
                matchingPlayers.add(player);
            }
        }
        return matchingPlayers;
    }

    // Method to add a player
    public void addPlayer(Player player) {
        writePlayerToFile(player);
    }

    // Method to read player data from Player.csv file
    private List<Player> readPlayersFromFile() {
        return getPlayers(PLAYER_FILE_PATH);
    }

    public static List<Player> getPlayers(String playerFilePath) {
        List<Player> players = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PLAYER_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                LocalDate dob = LocalDate.parse(data[2]);
                Player player = new Player(id, name, dob);
                players.add(player);
            }
        } catch (DateTimeException | IOException e) {
            e.printStackTrace();
        }
        return players;
    }

    // Method to write player data to Player.csv file
    private void writePlayerToFile(Player player) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PLAYER_FILE_PATH, true))) {
            String line = player.getId() + "," + player.getName() + "," + player.getDob();
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void writeAllPlayersToFile(List<Player> allPlayers) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PLAYER_FILE_PATH))) {
            for (Player p : allPlayers) {
                writer.write(p.toString() + "\n");
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Stub methods
    public void addPlayer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập thông tin cầu thủ:");
        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Tên: ");
        String name = scanner.nextLine(); // Read the player's name
        System.out.print("Ngày sinh (yyyy-MM-dd): ");
        String dobString = scanner.nextLine(); // Read the date of birth
        try {
            LocalDate dob = LocalDate.parse(dobString);
            Player player = new Player(id, name, dob);
            writePlayerToFile(player);
            System.out.println("Cầu thủ đã được thêm vào hệ thống.");
        } catch (DateTimeParseException e) {
            System.out.println("Ngày sinh không hợp lệ. Vui lòng nhập lại theo định dạng yyyy-MM-dd.");
            // Handle the invalid date of birth input
        }
    }

    public void updatePlayer(Player player) {
        List<Player> allPlayers = readPlayersFromFile();
        for (int i = 0; i<allPlayers.size() ;i++) {
            if (allPlayers.get(i).getId() == player.getId()) {
                allPlayers.get(i).setAge(player.getAge());
                allPlayers.get(i).setDob(player.getDob());
                allPlayers.get(i).setPosition(player.getPosition());
                allPlayers.get(i).setTeam(player.getTeam());
                allPlayers.get(i).setName(player.getName());
            }
        }
        writeAllPlayersToFile(allPlayers);

    }


}



