package opgave4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class TrainClient {

    private static final String SERVER_ADDRESS = "localhost"; // Change to server IP address

    public TrainClient() {
        KeyboardInputListener keyboardInputListener = new KeyboardInputListener();
        keyboardInputListener.start();
    }

    public class KeyboardInputListener extends Thread {
        @Override
        public void run() {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                while (true) {
                    String input = reader.readLine();
                    new TrainClient().sendRequest(input);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendRequest(String input) {
        try (Socket socket = new Socket(SERVER_ADDRESS, 12345);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.println(input);

            String response;
            while ((response = in.readLine()) != null) {
                System.out.println(response);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TrainClient();
    }
}
