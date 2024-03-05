package com.cg.view;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.cg.model.Player;
import com.cg.service.PlayerService;

public class PlayerView {
    private final Scanner scanner;
    private final PlayerService playerService; // Assuming you have PlayerService
    public static final String PLAYER_FILE_PATH = "players.csv";

    public PlayerView() {
        this.scanner = new Scanner(System.in);
        this.playerService = new PlayerService(); // Initialize PlayerService
    }

    public void showMenu() {
        System.out.println("Quản lý cầu thủ");
        System.out.println("1. Thêm cầu thủ");
        System.out.println("2. Sửa cầu thủ");
        System.out.println("3. Tìm kiếm cầu thủ theo ID");
        System.out.println("4. Tìm kiếm cầu thủ theo tên");
        System.out.println("5. Tìm kiếm cầu thủ theo chữ cái đầu tiên");
        System.out.println("6. Hiển thị danh sách cầu thủ");
        System.out.println("7. Exit");

        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                addPlayerView();
                break;
            case 2:
                editPlayerView();
                break;
            case 3:
                searchPlayerById();
                break;
            case 4:
                searchPlayerByName();
                break;
            case 5:
                searchPlayerByFirstLetter();
                break;
            case 6:
                displayPlayersToFile(); // Add this option to display players and save to file
                break;
            case 7:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    private void addPlayerView() {
        System.out.println("Nhập thông tin cầu thủ:");
        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Tên: ");
        String name = scanner.nextLine(); // Use next() instead of nextLine() to avoid skipping input
        LocalDate dob = null;

        boolean validDate = false;
        while (!validDate) {
            System.out.print("Ngày sinh (yyyy-MM-dd): ");
            String dobString = scanner.nextLine(); // Use next() instead of nextLine() to avoid skipping input
            try {
                dob = LocalDate.parse(dobString);
                validDate = true;
            } catch (DateTimeParseException e) {
                System.out.println("Ngày sinh không hợp lệ. Vui lòng nhập lại theo định dạng yyyy-MM-dd.");
            }
        }
        Player player = new Player(id,name,dob);
        System.out.print("Ngày sinh (yyyy-MM-dd): ");
        playerService.addPlayer(player);

        System.out.println("Cầu thủ đã được thêm vào hệ thống.");
    }

    private void searchPlayerByFirstLetter() {
        System.out.println("Nhập chữ cái đầu tiên của tên cầu thủ cần tìm:");
        String letter = scanner.next(); // Use next() instead of nextLine() to avoid skipping input
        List<Player> players = playerService.searchPlayerByFirstLetter(letter);
        if (!players.isEmpty()) {
            System.out.println("Cầu thủ có tên bắt đầu bằng '" + letter + "':");
            for (Player player : players) {
                System.out.println("ID: " + player.getId() + ", Tên: " + player.getName() + ", Ngày sinh: " + player.getDob());
            }
        } else {
            System.out.println("Không tìm thấy cầu thủ với tên bắt đầu bằng '" + letter + "'.");
        }
    }

    // Method to search player by name
    private void searchPlayerByName() {
        System.out.println("Nhập tên cầu thủ cần tìm:");
        String name = scanner.next(); // Use next() instead of nextLine() to avoid skipping input
        List<Player> players = playerService.searchPlayerByName(name);
        if (!players.isEmpty()) {
            System.out.println("Cầu thủ có tên '" + name + "':");
            for (Player player : players) {
                System.out.println("ID: " + player.getId() + ", Tên: " + player.getName() + ", Ngày sinh: " + player.getDob());
            }
        } else {
            System.out.println("Không tìm thấy cầu thủ với tên '" + name + "'.");
        }
    }

    private void searchPlayerById() {
        System.out.println("Nhập ID cầu thủ cần tìm:");
        int id = Integer.parseInt(scanner.nextLine());
        Player player = playerService.getPlayerById(id);
        if (player != null){
            System.out.println("Thông tin cầu thủ:");
            System.out.println("ID: " + player.getId());
            System.out.println("Tên: " + player.getName());
            System.out.println("Ngày sinh: " + player.getDob());
        }
        else {
            System.out.println("Không tìm thấy cầu thủ với ID đã nhập.");
        }
    }



    public void editPlayerView() {
        System.out.println("Nhập ID của cầu thủ cần chỉnh sửa:");
        int id = Integer.parseInt(scanner.nextLine());
        Player player = playerService.getPlayerById(id);
        if (player == null) {
            System.out.println("Không tìm thấy cầu thủ với ID đã nhập.");
            return;
        }
        System.out.println("Nhập thông tin mới:");
        System.out.print("Tên mới: ");
        String newName = scanner.nextLine(); // Use next() instead of nextLine() to avoid skipping input
        System.out.print("Ngày sinh mới (yyyy-MM-dd): ");
        String newDobString = scanner.nextLine(); // Use next() instead of nextLine() to avoid skipping input
        LocalDate newDob = LocalDate.parse(newDobString);
        player.setName(newName);
        player.setDob(newDob);
        playerService.updatePlayer(player);
        System.out.println("Thông tin cầu thủ đã được cập nhật.");
    }

    public List<Player> readPlayersFromFile() {
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
        } catch (IOException | DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            // Xử lý ngoại lệ và in thông báo lỗi nếu cần
            System.err.println("Đã xảy ra lỗi khi đọc tệp " + PLAYER_FILE_PATH + ": " + e.getMessage());
        }
        return players;
    }

    public void writePlayersToFile(List<Player> players) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PLAYER_FILE_PATH))) {
            for (Player player : players) {
                writer.write(player.getId() + "," + player.getName() + "," + player.getDob());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void displayPlayersToFile() {
        List<Player> players = readPlayersFromFile();
        if (players != null && !players.isEmpty()) {
            for (Player player : players) {
                System.out.println("ID: " + player.getId() + ", Tên: " + player.getName() + ", Ngày sinh: " + player.getDob());
            }
            writePlayersToFile(players);
            System.out.println("Danh sách cầu thủ đã được lưu vào tệp " + PLAYER_FILE_PATH);
        } else {
            System.out.println("Không có cầu thủ nào để hiển thị.");
        }
    }
}



