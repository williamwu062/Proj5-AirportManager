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
    private String fake;

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

    public static void getAlaskaPassenger() {
		String tempFirst = "";
		String tempLast = "";
		int tempAge = 0;

        for (int i = 0; i < Alaska.alaskaInfo.size(); i++) {
            if (Alaska.alaskaInfo.get(i).contains(".")) {
				tempFirst = Alaska.alaskaInfo.get(i).substring(0, Alaska.alaskaInfo.get(i).indexOf('.'));
				tempLast = Alaska.alaskaInfo.get(i).substring(Alaska.alaskaInfo.get(i).indexOf('.') + 1,
						Alaska.alaskaInfo.get(i).indexOf(',')).trim();
				tempAge = Integer.parseInt(Alaska.alaskaInfo.get(i).substring(Alaska.alaskaInfo.get(i).indexOf(',')+1).trim());
				Alaska.passenger.add(new Passenger(tempFirst, tempLast, tempAge));
                Alaska.aSeats--;
                Alaska.seatCount++;
            }
        }
    }

    public static void getDeltaPassenger() {
		String tempFirst = "";
		String tempLast = "";
		int tempAge = 0;

        for (int i = 0; i < Delta.deltaInfo.size(); i++) {
            if (Delta.deltaInfo.get(i).contains(".")) {
				tempFirst = Delta.deltaInfo.get(i).substring(0, Delta.deltaInfo.get(i).indexOf('.'));
				tempLast = Delta.deltaInfo.get(i).substring(Delta.deltaInfo.get(i).indexOf('.') + 1,
						Delta.deltaInfo.get(i).indexOf(',')).trim();
				tempAge = Integer.parseInt(Delta.deltaInfo.get(i).substring(Delta.deltaInfo.get(i).indexOf(',')+1).trim());
				Delta.passenger.add(new Passenger(tempFirst, tempLast, tempAge));
                Delta.dSeats--;
                Delta.seatCount++;
            }
        }
    }

    public static void getSouthwestPassenger() {
    	String tempFirst = "";
    	String tempLast = "";
    	int tempAge = 0;
        for (int i = 0; i < Southwest.southwestInfo.size(); i++) {
            if (Southwest.southwestInfo.get(i).contains(".")) {
            	tempFirst = Southwest.southwestInfo.get(i).substring(0, Southwest.southwestInfo.get(i).indexOf('.'));
            	tempLast = Southwest.southwestInfo.get(i).substring(Southwest.southwestInfo.get(i).indexOf('.') + 1,
						Southwest.southwestInfo.get(i).indexOf(',')).trim();
            	tempAge = Integer.parseInt(Southwest.southwestInfo.get(i).substring(Southwest.southwestInfo.get(i).indexOf(',')+1).trim());
                Southwest.passenger.add(new Passenger(tempFirst, tempLast, tempAge));
                Southwest.swSeats--;
                Southwest.seatCount++;
            }
        }
    }

    public static void setAlaskaInfo() {
        ArrayList<String> temp = new ArrayList<>();
        String tempString = "";
        temp.add(Alaska.passenger.get(Alaska.passenger.size() - 1).toString());
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
        temp.add(Delta.passenger.get(Delta.passenger.size() - 1).toString());
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
        temp.add(Southwest.passenger.get(Southwest.passenger.size() - 1).toString());
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
