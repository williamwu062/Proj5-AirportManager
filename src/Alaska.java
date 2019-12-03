import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Class for Alaskan Airlines.
 *
 * @author williamwu
 * @version 1.0
 */
public class Alaska implements Airline {
	/**
	 * Describes the airline.
	 */
	public static final String description = "<html>Alaska Airlines is proud to serve the strong and knowledgeable " +
					"Boilermakers from Purdue University.<br>We primarily fly westward and often have stops in " +
					"Alaska and California.<br>We have first class amenities, even in the Coach class.<br>We provide" +
					" " +
					"fun snacks, such as pretzels and goldfish.<br>We also have comfortable seats, and free " +
					"WiFi.<br>We hope you choose Alaska Airlines for your next itinerary!</html>";
	/**
	 * Name of the airline.
	 */
	public static final String name = "Alaska";
	public static String numSeat;
	/**
	 * Number of seats on the airline.
	 */
	public static int aSeats = 100;
	public static int maxSeats = 0;
	public static int seatCount = 0;
	/**
	 * ArrayList of the passengers on the airplane.
	 */
	public static ArrayList<Passenger> passengers;
	public static ArrayList<String> alaskaInfo;
	public static ArrayList<Passenger> passenger;
	/**
	 * The gate the airline is on.
	 */
	public static Gate gate;
}
