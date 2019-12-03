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

        getAlaskaInfo();
        getDeltaInfo();
        getSouthwestInfo();
        getAlaskaPassenger();
        getDeltaPassenger();
        getSouthwestPassenger();
    }

    public static ArrayList<String> getAlaskaInfo() {
        File file = new File("reservations.txt");

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
                        } else {
                            break;
                        }
                    }
                }
            }
            bfr.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
					"The file does not exist!", "File not found",
					JOptionPane.ERROR_MESSAGE);
        }
		for (int j = 0; j < Alaska.alaskaInfo.size(); j++) {
			if (Alaska.alaskaInfo.get(j).contains("/")) {
				Alaska.maxSeats = Integer.parseInt(Alaska.alaskaInfo.get
						(j).substring(Alaska.alaskaInfo.get(j).indexOf('/') + 1));
			}
		}
        return Alaska.alaskaInfo;
    }

    public static ArrayList<String> getSouthwestInfo() {
        File file = new File("reservations.txt");

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
                        if (t == null) {
                            break;
                        }
                        if (!t.equals("DELTA") || !t.equals("ALASKA") || !t.equals("EOF")) {
                            Southwest.southwestInfo.add(t);
                        } else {
                            break;
                        }
                    }
                }
            }
            bfr.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "The file does not exist!", "File not found", JOptionPane.ERROR_MESSAGE);
        }

		for (int j = 0; j < Southwest.southwestInfo.size(); j++) {
			if (Southwest.southwestInfo.get(j).contains("/")) {
				Southwest.maxSeats = Integer.parseInt(Southwest.southwestInfo.get(j).substring(Southwest.southwestInfo.get(j).indexOf('/') + 1));
			}
		}
        return Southwest.southwestInfo;
    }

    public static ArrayList<String> getDeltaInfo() {
        File file = new File("reservations.txt");

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
                        } else {
                            break;
                        }
                    }
                }
            }
            bfr.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "The file does not exist!", "File not found", JOptionPane.ERROR_MESSAGE);
        }

        for (int j = 0; j < Delta.deltaInfo.size(); j++) {
            if (Delta.deltaInfo.get(j).contains("/")) {
                Delta.maxSeats = Integer.parseInt(Delta.deltaInfo.get(j).substring(Delta.deltaInfo.get(j).indexOf('/') + 1));
            }
        }
        return Delta.deltaInfo;
    }

    public static void changeFile() {
        File file = new File("reservation.txt");

        try (PrintWriter pw = new PrintWriter(file);) {
            for (int i = 0; i < Alaska.alaskaInfo.size(); i++) {
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

    public static ArrayList<String> getAlaskaPassenger() {
        for (int i = 0; i < Alaska.alaskaInfo.size(); i++) {
            if (Alaska.alaskaInfo.get(i).contains(".")) {
                Alaska.passenger.add(Alaska.alaskaInfo.get(i));
                Alaska.aSeats--;
                Alaska.seatCount++;
            }
        }
        return Alaska.passenger;
    }

    public static ArrayList<String> getDeltaPassenger() {
        for (int i = 0; i < Delta.deltaInfo.size(); i++) {
            if (Delta.deltaInfo.get(i).contains(".")) {
                Delta.passenger.add(Delta.deltaInfo.get(i));
                Delta.dSeats--;
                Delta.seatCount++;
            }
        }
        return Delta.passenger;
    }

    public static ArrayList<String> getSouthwestPassenger() {
        for (int i = 0; i < Southwest.southwestInfo.size(); i++) {
            if (Southwest.southwestInfo.get(i).contains(".")) {
                Southwest.passenger.add(Southwest.southwestInfo.get(i));
                Southwest.swSeats--;
                Southwest.seatCount++;
            }
        }
        return Southwest.passenger;
    }

    public static void setAlaskaInfo() {
        ArrayList<String> temp = new ArrayList<>();
        String tempString = "";
        temp.add(Alaska.passenger.get(Alaska.passenger.size() - 1));
        temp.add("---------------------ALASKA");
        for (int i = 0; i < temp.size(); i++) {
            Alaska.alaskaInfo.add(temp.get(i));
        }

        for (int j = 0; j < Alaska.alaskaInfo.size(); j++) {
            if (Alaska.alaskaInfo.get(j).contains("/")) {
                tempString = Alaska.seatCount + Alaska.alaskaInfo.get(j).substring(Alaska.alaskaInfo.get(j).indexOf('/'));
                Alaska.maxSeats = Integer.parseInt(Alaska.alaskaInfo.get(j).substring(Alaska.alaskaInfo.get(j).indexOf('/') + 1));
                Alaska.numSeat = tempString;
                Alaska.alaskaInfo.set(j, tempString);
            }
        }
    }

    public static void setDeltaInfo() {
        ArrayList<String> temp = new ArrayList<>();
        String tempString = "";
        temp.add(Delta.passenger.get(Delta.passenger.size() - 1));
        temp.add("---------------------ALASKA");
        for (int i = 0; i < temp.size(); i++) {
            Delta.deltaInfo.add(temp.get(i));
        }

        for (int j = 0; j < Delta.deltaInfo.size(); j++) {
            if (Delta.deltaInfo.get(j).contains("/")) {
                tempString = Delta.seatCount + Delta.deltaInfo.get(j).substring(Delta.deltaInfo.get(j).indexOf('/'));
                Delta.numSeat = tempString;
                Delta.deltaInfo.set(j, tempString);
            }
        }
    }

    public static void setSouthwestInfo() {
        ArrayList<String> temp = new ArrayList<>();
        String tempString = "";
        temp.add(Southwest.passenger.get(Southwest.passenger.size() - 1));
        temp.add("---------------------ALASKA");
        for (int i = 0; i < temp.size(); i++) {
            Southwest.southwestInfo.add(temp.get(i));
        }

        for (int j = 0; j < Southwest.southwestInfo.size(); j++) {
            if (Southwest.southwestInfo.get(j).contains("/")) {
                tempString = Southwest.seatCount + Southwest.southwestInfo.get(j).substring(Southwest.southwestInfo.get(j).indexOf('/'));
                Southwest.numSeat = tempString;
                Southwest.southwestInfo.set(j, tempString);
            }
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
