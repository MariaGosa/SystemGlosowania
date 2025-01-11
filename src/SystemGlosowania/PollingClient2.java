package SystemGlosowania;

import java.io.*;
import java.net.*;

public class PollingClient2 {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345);  // Łączymy się z serwerem na porcie 12345
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

            // Przykład 1: Tworzenie głosowania
            String pollOptions = "OptionA,OptionB,OptionC";
            writer.println("createPoll:" + pollOptions);
            System.out.println("Response from server (creating poll): " + reader.readLine());

            // Przykład 2: Oddawanie głosu
            writer.println("vote:2,OptionB");
            System.out.println("Response from server (voting): " + reader.readLine());

            // Przykład 3: Pobieranie wyników
            writer.println("getResults:2");
            System.out.println("Response from server (getting results): " + reader.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
