import java.util.ArrayList;

/**
 * Class describing the Southwest airline.
 *
 * @author williamwu
 * @version 1.0
 */
public class Southwest implements Airline {
	/**
	 * Describes the airline.
	 */
	public static final String description = "<html>Southwest Airlines is proud to offer flights to Purdue " +
					"University.<br>We are happy to offer free in flight WiFi, along with a wide assortment of " +
					"refreshments.<br>Additionally, Southwest flights offer two free checked bags and " +
					"predominately cheap flights.<br>We hope you choose Southwest for your next flight.</html>";
	/**
	 * Name of the airline.
	 */
	public static final String name = "Southwest";
	public static String numSeat;
	/**
	 * Number of seats on the airline.
	 */
	public static int maxSeats = 0;
	public static int swSeats = 100;
	public static int seatCount = 0;
	/**
	 * ArrayList of the passengers on the airplane.
	 */
	public static ArrayList<Passenger> passengers;
	public static ArrayList<String> southwestInfo;
	public static ArrayList<String> passenger;
	/**
	 * The gate the airline is on.
	 */
	public static Gate gate;
}
