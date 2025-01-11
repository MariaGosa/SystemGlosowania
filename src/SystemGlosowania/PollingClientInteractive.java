package SystemGlosowania;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class PollingClientInteractive {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Welcome to the Voting System!");
            boolean running = true;

            while (running) {
                showMenu();
                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        // Tworzenie głosowania
                        System.out.print("Enter poll options (comma separated): ");
                        String options = scanner.nextLine();
                        writer.println("createPoll:" + options);
                        System.out.println("Server response: " + reader.readLine());
                        break;
                    case "2":
                        // Oddawanie głosu
                        System.out.print("Enter poll ID to vote on: ");
                        int pollId = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter your vote (option): ");
                        String vote = scanner.nextLine();
                        writer.println("vote:" + pollId + "," + vote);
                        System.out.println("Server response: " + reader.readLine());
                        break;
                    case "3":
                        // Pobieranie wyników
                        System.out.print("Enter poll ID to get results: ");
                        int pollIdForResults = Integer.parseInt(scanner.nextLine());
                        writer.println("getResults:" + pollIdForResults);
                        System.out.println("Server response: " + reader.readLine());
                        break;
                    case "4":
                        // Zakończenie działania klienta
                        System.out.println("Goodbye!");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void showMenu() {
        System.out.println("\nPlease choose an option:");
        System.out.println("1. Create a new poll");
        System.out.println("2. Vote in an existing poll");
        System.out.println("3. Get results of a poll");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }
}
