import java.net.Socket;

/**
 * Client class where the user can buy a ticket if one is still available.
 */
public class ReservationClient implements Runnable {

	private Socket socket;

	/**
	 * Initializes fields.
	 *
	 * @param socket the accepted socket
	 */
	public ReservationClient(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		//TODO book a ticket and add and subtract from fields in ReservationServer to find number of seats left
	}
}
