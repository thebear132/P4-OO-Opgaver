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
				System.out.print("(Add Train by name / [list] trains / [exit] program): ");
				while (true) {
					// Wait for user input
					String input = reader.readLine();
					new TrainClient().sendRequest(input);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void sendRequest(String message) {
		try (Socket socket = new Socket("localhost", 12345);
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

			out.println(message); // Send message to the server

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
