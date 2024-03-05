package com.cg.view;

import com.cg.model.Referee;
import com.cg.service.RefereeService;

import java.util.List;
import java.util.Scanner;

public class RefereeView {

    private final Scanner scanner;
    private final RefereeService refereeService;

    public RefereeView() {
        this.scanner = new Scanner(System.in);
        this.refereeService = new RefereeService();
    }

    // Display menu for referee management system
    public void displayMenu() {
        System.out.println("Referee Management System Menu:");
        System.out.println("1. Add Referee");
        System.out.println("2. Update Referee");
        System.out.println("3. Delete Referee");
        System.out.println("4. Search Referee");
        System.out.println("5. View All Referees");
        System.out.println("6. Exit");
    }

    // Run the menu
    public void runMenu() {
        boolean exit = false;
        while (!exit) {
            displayMenu();
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    addReferee();
                    break;
                case 2:
                    updateReferee();
                    break;
                case 3:
                    deleteReferee();
                    break;
                case 4:
                    searchReferee();
                    break;
                case 5:
                    viewAllReferees();
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

    // Method to add a referee
    private void addReferee() {
        System.out.println("Enter referee details:");
        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Name: ");
        String name = scanner.nextLine();
        Referee referee = new Referee(id, name);
        refereeService.addReferee(referee);
        System.out.println("Referee added successfully.");
    }

    // Method to update a referee
    private void updateReferee() {
        System.out.print("Enter referee ID to update: ");
        int id = Integer.parseInt(scanner.nextLine());
        Referee referee = refereeService.getRefereeById(id);
        if (referee != null) {
            System.out.println("Enter new details for referee with ID " + id + ":");
            System.out.print("New Name: ");
            String newName = scanner.nextLine();
            referee.setName(newName);
            refereeService.updateReferee(referee);
            System.out.println("Referee updated successfully.");
        } else {
            System.out.println("Referee not found with ID " + id);
        }
    }

    // Method to delete a referee
    private void deleteReferee() {
        System.out.print("Enter referee ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        Referee referee = refereeService.getRefereeById(id);
        if (referee != null) {
            refereeService.deleteReferee(referee);
            System.out.println("Referee deleted successfully.");
        } else {
            System.out.println("Referee not found with ID " + id);
        }
    }

    // Method to search for a referee
    private void searchReferee() {
        System.out.print("Enter referee name to search: ");
        String name = scanner.nextLine();
        List<Referee> matchingReferees = refereeService.searchRefereeByName(name);
        if (!matchingReferees.isEmpty()) {
            System.out.println("Referees found with name '" + name + "':");
            for (Referee referee : matchingReferees) {
                System.out.println("ID: " + referee.getId() + ", Name: " + referee.getName());
            }
        } else {
            System.out.println("No referees found with name '" + name + "'");
        }
    }

    // Method to view all referees
    private void viewAllReferees() {
        List<Referee> allReferees = refereeService.getAllReferees();
        if (!allReferees.isEmpty()) {
            System.out.println("All Referees:");
            for (Referee referee : allReferees) {
                System.out.println("ID: " + referee.getId() + ", Name: " + referee.getName());
            }
        } else {
            System.out.println("No referees found.");
        }
    }
}

