package com.cg;

import com.cg.view.*;

import java.util.Scanner;

public class FootballApp {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        PlayerView playerView = new PlayerView();
        RefereeView refereeView = new RefereeView();
        TournamentView tournamentView = new TournamentView();
        TeamView teamView = new TeamView();
        MatcherView matcherView = new MatcherView();
        // Run the menu for each view
        System.out.println("Football Management System Menu:");
        System.out.println("1. Player Management");
        System.out.println("2. Referee Management");
        System.out.println("3. Tournament Management");
        System.out.println("4. Team Management");
        System.out.println("5. Match management");
        System.out.println("6. Exit");

        // Let the user choose which management system to access
        int choice;
        do {
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    playerView.showMenu();
                    break;
                case 2:
                    refereeView.runMenu();
                    break;
                case 3:
                    tournamentView.runMenu();
                    break;
                case 4:
                    teamView.displayMenu();
                    break;
                case 5:
                    matcherView.runMenu();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 6.");
            }
        } while (choice != 6);
    }
}



