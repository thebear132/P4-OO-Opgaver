package opgave1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class TrainClient {

    public TrainClient() {
        // Create and start the keyboard input listener
        KeyboardInputListener keyboardInputListener = new KeyboardInputListener();
        keyboardInputListener.start();
    }

    public class KeyboardInputListener extends Thread {
        @Override
        public void run() {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                while (true) {
                    // Wait for user input
                    String input = reader.readLine();

                    // Send request when any key is pressed
                    new TrainClient().sendRequest();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendRequest() {
        try (Socket socket = new Socket("localhost", 12345);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            Random random = new Random();
            int randomAction = random.nextInt(3); // Generate random action index (0, 1, or 2)

            switch (randomAction) {
                case 0:
                    out.println("Gustav"); // Send request to add train
                    break;
                case 1:
                    out.println("list"); // Send request to list trains
                    break;
                case 2:
                    out.println("Carl"); // Send request to exit
                    break;
            }

            String response;
            while ((response = in.readLine()) != null) {
                System.out.println("Response -> " + response);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TrainClient();
    }
}
