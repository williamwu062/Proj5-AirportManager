import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author williamwu
 * @version 1.0
 */
public class ReservationServer {

	public ReservationServer() {
		Alaska.gate = new Gate();
		Delta.gate = new Gate();
		Southwest.gate = new Gate();

		while (Alaska.gate.getGate().equals(Delta.gate.getGate()) || Alaska.gate.getGate()
						.equals(Southwest.gate.getGate()) || Delta.gate.getGate().equals(Southwest.gate.getGate())) {
			Alaska.gate = new Gate();
			Delta.gate = new Gate();
			Southwest.gate = new Gate();
		}
	}

	public static void main(String[] args) {

		try {
			ServerSocket socket = new ServerSocket(6679);

			while (true) {
				Socket s = socket.accept();

				Thread t = new Thread(new ReservationRequestHandler(s));

				t.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
