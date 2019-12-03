import java.util.ArrayList;

/**
 * Class for the Delta airline.
 *
 * @author williamwu
 * @version 1.0
 */
public class Delta implements Airline {
	/**
	 * Describes the airline.
	 */
	public static final String description = "<html>Delta Airlines is proud to be one of the five premier " +
					"airlines at Purdue University.<br>We are extremely proud to offer exceptional services, " +
					"with free limited WiFi for all customers.<br>Passengers who use T-Mobile as a cell phone " +
					"carrier get additional benefits.<br>We are also happy to offer power outlets in each seat " +
					"for passenger use.<br>We hope you choose to fly Delta at your next airline.</html>";
	/**
	 * Name of the airline.
	 */
	public static final String name = "Delta";
	/**
	 * Number of seats on the airline.
	 */
	public static String numSeat;
	public static int dSeats = 100;
	public static int maxSeats = 0;
	public static int seatCount = 0;
	/**
	 * ArrayList of the passengers on the airplane.
	 */
	public static ArrayList<String> deltaInfo = new ArrayList<>();
	public static ArrayList<Passenger> passenger = new ArrayList<>();
	/**
	 * The gate the airline is on.
	 */
	public static Gate gate;
}
