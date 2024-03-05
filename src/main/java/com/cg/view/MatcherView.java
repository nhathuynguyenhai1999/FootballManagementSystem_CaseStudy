package com.cg.view;

import com.cg.model.Match;
import com.cg.service.MatchService;
import com.cg.utils.ParseUtils;

import java.io.*;
import java.util.*;

public class MatcherView {
    private final Scanner scanner;
    private final MatchService matchService = new MatchService();
    public static final String MATCH_FILE_PATH = "match.csv";

    public MatcherView() {
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("Match Management System Menu:");
        System.out.println("1. Display Match Information");
        System.out.println("2. Add Match");
        System.out.println("3. Edit Match");
        System.out.println("4. Delete Match");
        System.out.println("5. Search Match by Team");
        System.out.println("6. Exit");
    }

    public void runMenu() {
        boolean exit = false;
        while (!exit) {
            displayMenu();
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    displayMatchView();
                    break;
                case 2:
                    addMatchView();
                    break;
                case 3:
                    editMatchView();
                    break;
                case 4:
                    removeMatchView();
                    break;
                case 5:
                    searchMatchViewByTeam();
                    break;
                case 6:
                    exit = true;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 6.");
            }
        }
    }

    private void searchMatchViewByTeam() {
        System.out.print("Enter team name to search matches: ");
        String teamName = scanner.nextLine();

        List<Match> matches = matchService.searchMatchesByTeam(teamName);
        if (!matches.isEmpty()) {
            System.out.println("Matches for team " + teamName + ":");
            for (Match match : matches) {
                System.out.println(match);
            }
        } else {
            System.out.println("No matches found for team " + teamName);
        }
    }



    private void removeMatchView() {
        System.out.print("Enter match ID to remove: ");
        String matchId = scanner.nextLine(); // Consume newline
        boolean removed = matchService.removeMatch(matchId);
        if (removed) {
            System.out.println("Match with ID " + matchId + " removed successfully.");
        } else {
            System.out.println("No match found with ID " + matchId);
        }
    }

    private void editMatchView() {
        System.out.print("Enter match ID to edit: ");
        String matchId = scanner.nextLine();
        try {
            Match match = matchService.getMatchById(matchId);
            if(match != null){
                // Display current match details
                System.out.println("Current match details:");
                System.out.println(match);
                // Prompt for new details
                System.out.println("Enter new details:");
                System.out.print("Team 1: ");
                String team1 = scanner.nextLine();
                System.out.print("Team 2: ");
                String team2 = scanner.nextLine();
                System.out.print("Score: ");
                String score = scanner.nextLine();
                System.out.print("Date (YYYY-MM-DD): ");
                String dateTime = scanner.nextLine();
                if(isValidDateTime(dateTime)){
                    System.out.println("Invalid date format. Please enter in the format YYYY-MM-DD.");
                    return;
                }
                // Update match details
                match.setTeam1(team1);
                match.setTeam2(team2);
                match.setDateTime(dateTime);
                match.setScore(score);
                matchService.updateMatch(match);
                System.out.println("Match with ID " + matchId + " updated successfully.");
            }
            else {
                System.out.println("No match found with ID " + matchId);
            }
        } catch (NumberFormatException e){
            System.out.println("Invalid match ID format. Please enter a valid integer.");
        }
    }

    private void addMatchView() {
        try {
            System.out.println("Enter match details:");
            System.out.print("Match ID: ");
            String matchId = scanner.nextLine();
            System.out.print("Team 1: ");
            String team1 = scanner.nextLine();
            System.out.print("Team 2: ");
            String team2 = scanner.nextLine();
            System.out.print("Score: ");
            String score = scanner.nextLine();
            String dateTime = null;

            System.out.print("Date and Time (YYYY-MM-DD): ");
            dateTime = scanner.nextLine();
            // Validate dateTime format before proceeding
            if (isValidDateTime(dateTime)) {
                System.out.println("Invalid date and time format. Please enter in the format YYYY-MM-DD.");
                return;
            }
            Match match = new Match(matchId, team1, team2, ParseUtils.parseDate(dateTime), score);
            matchService.addMatch(match);
            System.out.println("Match added successfully.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input format. Please enter valid data.");
        } catch (NumberFormatException e){
            System.out.println("Invalid match ID. Please enter a valid integer.");
        }
    }
    private boolean isValidDateTime(String dateTime) {
        // Validate dateTime format using a regular expression
        String pattern = "\\d{4}-\\d{2}-\\d{2}";
        return !dateTime.matches(pattern);
    }

    private void displayMatchView() {
        MatchService matchService = new MatchService();
        String MATCH_FILE_PATH = "match.csv"; // Assuming this is the correct file path
        List<Match> matches = matchService.getAllMatchesFromFile(MATCH_FILE_PATH);
        if (matches != null && !matches.isEmpty()) {
            System.out.println("All Matches:");
            System.out.printf("%10s | %20s | %20s | %10s | %20s \n", "ID", "TEAM1", "TEAM2", "SCORE", "DATE");
            for (Match match : matches) {
                System.out.printf("%10s | %20s | %20s | %10s | %20s \n", match.getId(), match.getTeam1(), match.getTeam2(),match.getScore(), match.getDate().toString());
            }
        } else {
            System.out.println("No matches found.");
        }
    }


    public List<Match> readMatchesFromFile(String MATCH_FILE_PATH) {
        List<Match> matches = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(MATCH_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String id = data[0].trim();
                String team1 = data[1];
                String team2 = data[2];
                String dateTime = data[3];
                String score = data[4];
                Match match = new Match(id, team1, team2, ParseUtils.parseDate(dateTime), score);
                matches.add(match);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matches;
    }
    public List<Match> getAllMatchesFromFile() {
        List<Match> matches = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(MATCH_FILE_PATH))){
            String line;
            BufferedReader reader = new BufferedReader(bufferedReader);
            while (true){
                assert false;
                if ((line = reader.readLine()) == null) break;
                String[] data = line.split(",");
                if (data.length == 5){
                    String matchID = data[0];
                    String team1 = data[1];
                    String team2 = data[2];
                    String dataTime = data[3];
                    String score = data[4];
                    Match match = new Match(matchID,team1,team2,ParseUtils.parseDate(dataTime),score);
                    matches.add(match);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return getMatches(MATCH_FILE_PATH);
    }

    public static List<Match> getMatches(String MATCH_FILE_PATH) {
        List<Match> matches = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(MATCH_FILE_PATH))) {
            String line;
            while (!Objects.equals(line = reader.readLine(), "")) {
                String[] data = line.split(",");
                // Assuming Match constructor takes appropriate arguments, adjust as needed
                if (data.length >= 4){
                    Match match = new Match(data[0], data[1], data[2], data[3]);
                    matches.add(match);
                }
                else {
                    System.err.println("Invalid data format:" + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matches;
    }

    public void writeMatchesToFile(String MATCH_FILE_PATH, List<Match> matches) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MATCH_FILE_PATH))) {
            for (Match match : matches) {
                // Write match information to the file
                writer.write(match.getId() + "," + match.getTeam1() + "," + match.getTeam2() + "," + match.getDateTime() + "," + match.getScore());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


