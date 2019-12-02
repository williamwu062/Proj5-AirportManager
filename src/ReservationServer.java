import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

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
	public static ArrayList<String> getAlaskaInfo() {
		File file = new File("reservations.txt");
		String exception1 = "";
		String exception2 = "";

		try {
			FileReader fr = new FileReader(file);
			BufferedReader bfr = new BufferedReader(fr);
			String s = "";
			String t = "";
			while (true) {
				s = bfr.readLine();
				if (s == null) {
					break;
				}
				if (s.equals("ALASKA")) {
					Alaska.alaskaInfo.add(s);
					while (true) {
						t = bfr.readLine();
						if (t == null) {
							break;
						}
						if (!t.equals("DELTA") || !t.equals("SOUTHWEST") || !t.equals("EOF")) {
							Alaska.alaskaInfo.add(t);
						}
						else {
							break;
						}
					}
				}
			}
			bfr.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "The file does not exist!", "File not found", JOptionPane.ERROR_MESSAGE);
		}
		return Alaska.alaskaInfo;
	}

	public  static ArrayList<String> getSouthwestInfo() {
		File file = new File("reservations.txt");
		String exception1 = "";
		String exception2 = "";

		try {
			FileReader fr = new FileReader(file);
			BufferedReader bfr = new BufferedReader(fr);
			String s = "";
			String t = "";
			while (true) {
				s = bfr.readLine();
				if (s == null) {
					break;
				}
				if (s.equals("SOUTHWEST")) {
					Southwest.southwestInfo.add(s);
					while (true) {
						t = bfr.readLine();
						if(t == null)
						{
							break;
						}
						if (!t.equals("DELTA") || !t.equals("ALASKA") || !t.equals("EOF")) {
							Southwest.southwestInfo.add(t);
						}
						else {
							break;
						}
					}
				}
			}
			bfr.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "The file does not exist!", "File not found", JOptionPane.ERROR_MESSAGE);
		}
		return Southwest.southwestInfo;
	}

	public static ArrayList<String> getDeltaInfo() {
		File file = new File("reservations.txt");
		String exception1 = "";
		String exception2 = "";

		try {
			FileReader fr = new FileReader(file);
			BufferedReader bfr = new BufferedReader(fr);
			String s = "";
			String t = "";
			while (true) {
				s = bfr.readLine();
				if (s == null) {
					break;
				}
				if (s.equals("DELTA")) {
					Delta.deltaInfo.add(s);
					while (true) {
						t = bfr.readLine();
						if (t == null) {
							break;
						}
						if (!t.equals("ALASKA") || !t.equals("SOUTHWEST") || !t.equals("EOF")) {
							Delta.deltaInfo.add(t);
						}
						else {
							break;
						}
					}
				}
			}
			bfr.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "The file does not exist!", "File not found", JOptionPane.ERROR_MESSAGE);
		}
		return Delta.deltaInfo;
	}

	public static void changeFile() {
		File file = new File("reservation.txt");

		try (PrintWriter pw = new PrintWriter(file);){
			for(int i = 0; i < Alaska.alaskaInfo.size();i++) {
				pw.println(Alaska.alaskaInfo.get(i));
			}

			for (int j = 0; j < Delta.deltaInfo.size(); j++) {
				pw.println(Delta.deltaInfo.get(j));
			}

			for (int k = 0; k < Southwest.southwestInfo.size(); k++) {
				pw.println(Southwest.southwestInfo.get(k));
			}
		} catch (IOException e) {

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
