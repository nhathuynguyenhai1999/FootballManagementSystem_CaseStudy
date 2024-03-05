package com.cg.service;

import com.cg.model.Tournament;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class TournamentService {
    private final Scanner scanner;
    private final List<Tournament> tournaments;

    public TournamentService() {
        this.scanner = new Scanner(System.in);
        this.tournaments = new ArrayList<>();
    }

    public void displayTournamentInfo() {
        if (tournaments.isEmpty()) {
            System.out.println("No tournaments found.");
            return;
        }
        System.out.println("Tournament Information:");
        System.out.printf("%20s | %40s | %20s | %20s \n","ID","Name","Day Start","Day end");
        for (Tournament tournament : tournaments) {
            System.out.println(tournament);
        }
    }

    public void addTournament() {
        System.out.println("Enter tournament details:");
        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Name: ");
        String name = scanner.nextLine();
        Tournament tournament = new Tournament(id, name);
        tournaments.add(tournament);
        System.out.println("Tournament added successfully.");
    }

    public void editTournament() {
        System.out.print("Enter the ID of the tournament to edit: ");
        int id = Integer.parseInt(scanner.nextLine());
        Tournament tournament = getTournamentById(id);
        if (tournament != null) {
            System.out.print("Enter new name for the tournament: ");
            String newName = scanner.nextLine();
            tournament.setName(newName);
            System.out.println("Tournament updated successfully.");
        } else {
            System.out.println("Tournament not found with ID " + id);
        }
    }

    private Tournament getTournamentById(int id) {
        for (Tournament tournament : tournaments) {
            if (tournament.getId() == id) {
                return tournament;
            }
        }
        return null;
    }

    public void deleteTournament() {
        System.out.print("Enter the ID of the tournament to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        tournaments.removeIf(tournament -> tournament.getId() == id);
        System.out.println("Tournament deleted successfully.");
    }

    public void searchTournament() {
        System.out.print("Enter the name of the tournament to search: ");
        String name = scanner.nextLine();
        for (Tournament tournament : tournaments) {
            if (tournament.getName().equalsIgnoreCase(name)) {
                System.out.println("Tournament found: " + tournament);
                return;
            }
        }
        System.out.println("Tournament not found with name " + name);
    }
}




