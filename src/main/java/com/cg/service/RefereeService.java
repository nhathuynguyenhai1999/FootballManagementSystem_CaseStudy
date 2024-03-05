package com.cg.service;

import com.cg.model.Referee;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RefereeService {

    private static final String REFEREE_FILE_PATH = "referee.csv";

    // Method to read referee data from referee.csv file
    public List<Referee> readRefereesFromFile() {
        List<Referee> referees = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(REFEREE_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                Referee referee = new Referee(id, name);
                referees.add(referee);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return referees;
    }

    // Method to write referee data to referee.csv file
    public void writeRefereesToFile(List<Referee> referees) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(REFEREE_FILE_PATH))) {
            for (Referee referee : referees) {
                String line = referee.getId() + "," + referee.getName();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Method to get all referees
    public List<Referee> getAllReferees() {
        return readRefereesFromFile();
    }
    public void YourClass() {
        Scanner scanner = new Scanner(System.in);
        RefereeService refereeService = new RefereeService(); // Initialize your referee service
    }

    // Method to search for referees by name
    public List<Referee> searchRefereeByName(String name) {
        List<Referee> matchingReferees = new ArrayList<>();
        List<Referee> referees = readRefereesFromFile();
        for (Referee referee : referees) {
            if (referee.getName().equalsIgnoreCase(name)) {
                matchingReferees.add(referee);
            }
        }
        return matchingReferees;
    }

    // Method to get referee by ID
    public Referee getRefereeById(int id) {
        List<Referee> referees = readRefereesFromFile();
        for (Referee referee : referees) {
            if (referee.getId() == id) {
                return referee;
            }
        }
        return null;
    }

    // Method to delete a referee
    public void deleteReferee(Referee referee) {
        List<Referee> referees = readRefereesFromFile();
        int index = -1;
        for(int i = 0; i< referees.size();i++){
            if (referees.get(i).getId() == referee.getId()) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            referees.remove(index);
            writeRefereesToFile(referees);
        }

    }

    // Method to update referee information
    public void updateReferee(Referee referee) {
        List<Referee> referees = readRefereesFromFile();
        for (int i = 0; i < referees.size(); i++) {
            if (referees.get(i).getId() == referee.getId()) {
                referees.set(i, referee);
                break;
            }
        }
        writeRefereesToFile(referees);
    }

    // Method to add a new referee
    public void addReferee(Referee referee) {
        List<Referee> referees = readRefereesFromFile();
        referees.add(referee);
        writeRefereesToFile(referees);
    }
}


