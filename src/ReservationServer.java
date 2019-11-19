import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ReservationServer {

	public static int swSeats = 100;
	public static int dSeats = 100;
	public static int aSeats = 100;

	public static Passenger[] swPassengers;
	public static Passenger[] dPassengers;
	public static Passenger[] aPassengers;

	public static void main(String[] args) {
		try {
			ServerSocket socket = new ServerSocket(6679);

			while (true) {
				Socket s = socket.accept();

				Thread t = new Thread(new ReservationClient(s));

				t.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
