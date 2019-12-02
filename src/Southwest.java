import java.util.ArrayList;

/**
 * @author williamwu
 * @version 1.0
 */
public class Southwest implements Airline {
	public static final String description = "<html>Southwest Airlines is proud to offer flights to Purdue " +
					"University.<br>We are happy to offer free in flight WiFi, along with a wide assortment of " +
					"refreshments.<br>Additionally, Southwest flights offer two free checked bags and " +
					"predominately cheap flights.<br>We hope you choose Southwest for your next flight.</html>";
	public static final String name = "Southwest";
	public static int swSeats = 100;
	public static ArrayList<Passenger> passengers;
	public static Gate gate;
}
