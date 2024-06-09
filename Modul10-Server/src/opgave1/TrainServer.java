package opgave1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TrainServer {
    private List<String> trainList;
    private static final String FILENAME = "train_list.txt";

    public TrainServer() {
        this.trainList = new ArrayList<>();
        // Read train list from file if exists
        readTrainListFromFile();
        // Start listening for client connections
        startServer();
    }

    private void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(12345)) { // Using port 12345
            System.out.println("Server started. Listening on port 12345...");
            while (true) {
                Socket clientSocket = serverSocket.accept(); // Accept incoming connections
                new ClientHandler(clientSocket).start(); // Start a new thread to handle each client
            }
        } catch (IOException e) {
            System.err.println("Error starting server: " + e.getMessage());
        }
    }

    private synchronized void addTrain(String train) {
        trainList.add(train);
        System.out.println("Train added: " + train);
        // Write train list to file
        writeTrainListToFile();
    }

    private synchronized void printTrainList(PrintWriter out) {
        out.println("Train List:");
        for (String train : trainList) {
            out.println(train);
        }
    }

    private void readTrainListFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                trainList.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    private void writeTrainListToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME))) {
            for (String train : trainList) {
                bw.write(train);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    private class ClientHandler extends Thread {
        private Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    if (inputLine.equalsIgnoreCase("exit")) {
                        break;
                    } else if (inputLine.equalsIgnoreCase("list")) {
                        printTrainList(out);
                    } else {
                        addTrain(inputLine);
                        out.println("Train added!");
                    }
                }
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Error handling client request: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new TrainServer();
    }
}
