package opgave4;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TrainServer {
    private List<String> trainList;
    private static final String FILENAME = "train_list.txt";
    private static final int MAX_TRAINS_PER_RESPONSE = 100;

    public TrainServer() {
        this.trainList = new ArrayList<>();
        readTrainListFromFile();
        startServer();
    }

    private void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Server started. Listening on port 12345...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            System.err.println("Error starting server: " + e.getMessage());
        }
    }

    private synchronized void addTrain(String train) throws TooManyTrainsException {
        if (trainList.size() >= MAX_TRAINS_PER_RESPONSE) {
            throw new TooManyTrainsException("Too many trains. Cannot add more.");
        }
        trainList.add(train);
        System.out.println("Train added: " + train);
        writeTrainListToFile();
    }

    private synchronized void printTrainList(PrintWriter out) {
        out.println("Train List:");
        for (String train : trainList) {
            out.println(train);
        }
    }

    private synchronized void findTrain(String trainType, PrintWriter out) throws TrainNotFoundException {
        boolean found = false;
        for (String train : trainList) {
            if (train.equals(trainType)) {
                out.println("Found train: " + train);
                found = true;
                break;
            }
        }
        if (!found) {
            throw new TrainNotFoundException("Train not found: " + trainType);
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
                    try {
                        if (inputLine.equalsIgnoreCase("exit")) {
                            break;
                        } else if (inputLine.equalsIgnoreCase("list")) {
                            printTrainList(out);
                        } else if (inputLine.startsWith("find ")) {
                            findTrain(inputLine.substring(5), out);
                        } else {
                            addTrain(inputLine);
                        }
                    } catch (TrainNotFoundException | TooManyTrainsException e) {
                        out.println("Error: " + e.getMessage());
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
