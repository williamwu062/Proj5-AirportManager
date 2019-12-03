import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author williamwu
 * @version 1.0
 */
public class ReservationRequestHandler implements Runnable {
	/**
	 * The client socket of this request handler.
	 */
	private Socket clientSocket;


	//TODO need prompt if flight is full? Maybe. Yea we need to remove the option from stage 2
	//TODO check if the text fields are empty in stage 6 I think
	//TODO Remember to check to write to the file when creating a new passenger

	/**
	 * Initializes fields.
	 *
	 * @param clientSocket the socket to be set
	 * @throws NullPointerException if clientSocket is null
	 */
	public ReservationRequestHandler(Socket clientSocket) throws NullPointerException {
		Objects.requireNonNull(clientSocket, "the specified client socket is null");
		this.clientSocket = clientSocket;
	} //CensoringRequestHandler



	public synchronized void airlinePassengers() {

	}

	@Override
	public void run() {

		PrintWriter writer = null;
		Scanner reader = null;
		try {
			reader = new Scanner(clientSocket.getInputStream());
			writer = new PrintWriter(clientSocket.getOutputStream());

			ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());

			while (reader.hasNextLine()) {
				String s = reader.nextLine();
				String[] s1  = s.split("_");
				if (s1[1].equals("Passengers")) {
					if(s1[0].equals(Alaska.name)) {
						out.writeObject(Alaska.passenger);
					} else if (s1[0].equals(Delta.name)) {
						out.writeObject(Delta.passenger);
					} else if (s1[0].equals(Southwest.name)) {
						out.writeObject(Southwest.passenger);
					}
 				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
			if (reader != null) {
				reader.close();
			}
		}
	} //run

	/**
	 * Returns the hash code of this request handler.
	 *
	 * @return the hash code of this request handler
	 */
	@Override
	public int hashCode() {
		int result = 23;

		result = 31 * result + Objects.hashCode(this.clientSocket);

		return result;
	} //hashCode

	/**
	 * Determines whether or not the specified object is equal to this request handler. {@code true} is returned if and
	 * only if the specified object is an instance of {@code CensoringRequestHandler} and its client socket and
	 * {@code Set} of bad words are equal to this request handler's.
	 *
	 * @param object the object to be used in the comparisons
	 * @return {@code true}, if the specified object is equal to this request handler and {@code false} otherwise
	 */
	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		} else if (object instanceof ReservationRequestHandler) {
			boolean equal;

			equal = Objects.equals(this.clientSocket, ((ReservationRequestHandler) object).clientSocket);

			return equal;
		} else {
			return false;
		} //end if
	} //equals

	@Override
	public String toString() {
		String format = "CensoringRequestHandler[%s]";

		return String.format(format, this.clientSocket);
	} //toString
}
