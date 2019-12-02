import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.time.format.DateTimeParseException;

/**
 * Client class where the user can buy a ticket if one is still available.
 * @author williamwu
 * @version 1.0
 */
public final class ReservationClient {

	private static boolean isParsable(String string) {
		return string.chars()
				.mapToObj(Character::isDigit)
				.reduce(Boolean::logicalAnd)
				.orElse(Boolean.FALSE);
	}

	public static void main(String[] args) {
		BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));
		String hostname;
		String portString;
		int port;
		Socket socket;
		BufferedWriter socketWriter = null;
		BufferedReader socketReader = null;
		String request;
		String response;

		try {
			hostname = JOptionPane.showInputDialog(null, "What is the host name you'd like to connect to? ",
					"hostName?", JOptionPane.QUESTION_MESSAGE);

			if (hostname == null) {
				System.out.println();

				System.out.println("Goodbye!");
			} else {
				System.out.print("Enter the sever's port: ");

				portString = JOptionPane.showInputDialog(null, "What is the port you'd like to connect to? ",
						"hostName?", JOptionPane.QUESTION_MESSAGE);

				if (portString == null) {
					System.out.println();

					System.out.println("Goodbye!");
				} else if (!isParsable(portString)) {
					System.out.println();

					System.out.println("The specified port must be a non-negative integer! Goodbye!");
				} else {
					port = Integer.parseInt(portString);

					socket = new Socket(hostname, port);

					socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

					socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));


					System.out.println();

					System.out.println("Goodbye!");
				} //end if
			} //end if
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				userInputReader.close();

				if (socketWriter != null) {
					socketWriter.close();
				} //end if

				if (socketReader != null) {
					socketReader.close();
				} //end if
			} catch (IOException e) {
				e.printStackTrace();
			} //end try catch
		} //end try catch finally
	}
}
