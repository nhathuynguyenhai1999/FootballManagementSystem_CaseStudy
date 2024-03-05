package com.cg.store.main;

import com.cg.store.League.Leagues;
import com.cg.store.Match.Matches;
import com.cg.store.Referee.Referees;
import com.cg.store.Team.Teams;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static List<Teams> readTeamsFromFile(String filename) throws IOException {
        List<Teams> teams = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            String name = data[0];
            List<String> players = new ArrayList<>();
            players.addAll(Arrays.asList(data).subList(1, data.length));
            teams.add(new Teams(name, players));
        }
        reader.close();
        return teams;
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
        } catch (IOException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Error reading referees file: " + e.getMessage());
        }
        return referees;
    }


    public static void main(String[] args) throws IOException {
        try {
            // Đọc danh sách đội bóng từ file
            List<Teams> teams = readTeamsFromFile("teams.txt");

            // Đọc thông tin trọng tài từ file
            List<Referees> referees = readRefereesFromFile("referee.csv");

            // Tạo một giải đấu mới
            Leagues leagues = new Leagues("Nhat Huy Chaien Super Leagues");

            // Thêm các đội bóng vào giải đấu
            for (Teams team : teams) {
                leagues.addTeam(team);
            }

            // Tạo và chơi các trận đấu
            for (int i = 0; i < teams.size(); i++) {
                for (int j = i + 1; j < teams.size(); j++) {
                    Matches matches1 = new Matches(teams.get(i), teams.get(j), 2, 1); // Lượt đi
                    Matches matches2 = new Matches(teams.get(j), teams.get(i), 1, 2); // Lượt về
                    leagues.playMatch(matches1);
                    leagues.playMatch(matches2);
                }
            }

            // Hiển thị bảng xếp hạng
            leagues.showTable();
        } catch (IOException e) {
            System.out.println("Error reading files: " + e.getMessage());
        }
    }
}

