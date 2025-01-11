package SystemGlosowania;

import java.io.*;
import java.net.*;
import java.util.*;

public class PollingServer {
    private static Map<Integer, Poll> polls = new HashMap<>();
    private static int pollIdCounter = 1;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Polling server is running...");
            while (true) {
                // Akceptacja połączeń od klientów
                Socket clientSocket = serverSocket.accept();
                new PollingThread(clientSocket).start(); // Obsługuje każde połączenie w osobnym wątku
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class PollingThread extends Thread {
        private Socket socket;

        PollingThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)
            ) {
                String message;
                while ((message = reader.readLine()) != null) {
                    System.out.println("Received from client: " + message);

                    // Przetwarzanie wiadomości
                    String response = processMessage(message);
                    writer.println(response);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private String processMessage(String message) {
            String[] parts = message.split(":", 2);
            String command = parts[0];
            String argument = (parts.length > 1) ? parts[1] : "";

            switch (command) {
                case "createPoll":
                    return createPoll(argument);
                case "vote":
                    return vote(argument);
                case "getResults":
                    return getResults(Integer.parseInt(argument));
                default:
                    return "Unknown command";
            }
        }

        private String createPoll(String options) {
            String[] opts = options.split(",");
            Poll newPoll = new Poll("SystemGlosowania.Poll question?", Arrays.asList(opts));
            polls.put(pollIdCounter++, newPoll);
            return "SystemGlosowania.Poll created successfully!";
        }

        private String vote(String voteData) {
            String[] data = voteData.split(",");
            int pollId = Integer.parseInt(data[0]);
            String option = data[1];

            Poll poll = polls.get(pollId);
            if (poll != null) {
                poll.vote(option);
                return "Vote recorded for: " + option;
            }
            return "SystemGlosowania.Poll not found";
        }

        private String getResults(int pollId) {
            Poll poll = polls.get(pollId);
            if (poll != null) {
                return "Results: " + poll.getResults();
            }
            return "SystemGlosowania.Poll not found";
        }
    }
}
