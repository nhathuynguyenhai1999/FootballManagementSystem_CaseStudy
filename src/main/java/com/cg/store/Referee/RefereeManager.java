package com.cg.store.Referee;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RefereeManager {
    private static final String REFEREE_FILE_PATH = "referee.csv";

    public static void writeRefereeToFile(Referees referees) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(REFEREE_FILE_PATH, true))) {
            writer.write(referees.getName() + "," + referees.getAge() + "," + referees.getNationality());
            writer.newLine();
        }
    }

    public static List<Referees> readRefereesFromFile(String filename) {
        List<Referees> referees = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String name = data[0];
                int age = Integer.parseInt(data[1]);
                String nationality = data[2];
                referees.add(new Referees(name, age, nationality));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return referees;
    }
}


