package com.cg.view;

import com.cg.model.Tournament;
import com.cg.service.TournamentService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TournamentView {
    private final Scanner scanner;
    private static final String TOURNAMENT_FILE_PATH = "tournamentLeague.csv";

    public TournamentView() {
        this.scanner = new Scanner(System.in);
        TournamentService tournamentService = new TournamentService();
    }

    public void showMenu() {
        System.out.println("Tournament Management System Menu:");
        System.out.println("1. Display Tournament Information");
        System.out.println("2. Add Tournament");
        System.out.println("3. Edit Tournament");
        System.out.println("4. Delete Tournament");
        System.out.println("5. Search Tournament");
        System.out.println("6. Exit");
    }

    public void runMenu() {
        showMenu();
        boolean exit = false;
        while (!exit) {
            showMenu();
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    displayTournamentInfo();
                    break;
                case 2:
                    addTournament();
                    break;
                case 3:
                    editTournament();
                    break;
                case 4:
                    deleteTournament();
                    break;
                case 5:
                    searchTournament();
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

    private void displayTournamentInfo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(TOURNAMENT_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line); // Simply print each line of the file
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addTournament() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TOURNAMENT_FILE_PATH, true))) {
            System.out.println("Enter tournament information:");
            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Location: ");
            String location = scanner.nextLine();

            String tournamentInfo = id + "," + name + "," + location;
            writer.write(tournamentInfo);
            writer.newLine();
            System.out.println("Tournament added successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void editTournament() {
        try {
            System.out.println("Enter the ID of the tournament you want to edit:");
            int tournamentId = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter the new information for the tournament:");
            System.out.print("Name: ");
            String newName = scanner.nextLine();
            System.out.print("Location: ");
            String newLocation = scanner.nextLine();

            // Update tournament information in file
            updateTournamentInFile(tournamentId, newName, newLocation);

            System.out.println("Tournament information updated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void deleteTournament() {
        try {
            System.out.println("Enter the ID of the tournament you want to delete:");
            int tournamentId = Integer.parseInt(scanner.nextLine());

            // Read all lines from the file, exclude the line containing the tournament's ID, and rewrite the file
            File file = new File(TOURNAMENT_FILE_PATH);
            File tempFile = getFile(file, tournamentId);
            // Delete the original file
            if (!file.delete()) {
                throw new IOException("Failed to delete the original tournament file.");
            }
            // Rename the temporary file to the original file name
            if (!tempFile.renameTo(file)) {
                throw new IOException("Failed to rename the temporary file.");
            }

            System.out.println("Tournament deleted successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static File getFile(File file, int tournamentId) throws IOException {
        File tempFile = new File("tempFile.csv");
        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                if (id != tournamentId) {
                    writer.write(line);
                    writer.newLine();
                }
            }
        }
        return tempFile;
    }


    private void searchTournament() {
        try {
            System.out.println("Enter the name of the tournament you want to search for:");
            String tournamentName = scanner.nextLine();

            // Search for the tournament by name in the file and display its information if found
            try (BufferedReader reader = new BufferedReader(new FileReader(TOURNAMENT_FILE_PATH))) {
                String line;
                boolean found = false;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    String name = parts[1];
                    if (name.equalsIgnoreCase(tournamentName)) {
                        System.out.println("Tournament found:");
                        System.out.println("ID: " + parts[0]);
                        System.out.println("Name: " + parts[1]);
                        System.out.println("Location: " + parts[2]);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("Tournament not found.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void updateTournamentInFile(int tournamentId, String newName, String newLocation) throws IOException {
        File file = new File(TOURNAMENT_FILE_PATH);
        File tempFile = new File("temp.csv");

        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0].trim());
                if (id == tournamentId) {
                    writer.write(tournamentId + "," + newName + "," + newLocation);
                } else {
                    writer.write(line);
                }
                writer.newLine();
            }
        }

        // Replace the original file with the updated one
        if (!file.delete() || !tempFile.renameTo(file)) {
            throw new IOException("Error updating tournament information.");
        }
    }
}

