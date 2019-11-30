import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Alaska implements Airline {
	public static final String description = "<html>Alaska Airlines is proud to serve the strong and knowledgeable " +
					"Boilermakers from Purdue University.<br>We primarily fly westward and often have stops in " +
					"Alaska and California.<br>We have first class amenities, even in the Coach class.<br>We provide " +
					"fun snacks, such as pretzels and goldfish.<br>We also have comfortable seats, and free " +
					"WiFi.<br>We hope you choose Alaska Airlines for your next itinerary!</html>";
	public static final String name = "Alaska";
	public static int aSeats = 100;
	private ArrayList<Passenger> passengers;

	public Alaska(ArrayList<Passenger> passengers) throws NullPointerException {
		this.passengers = passengers;

		if (passengers == null) {
			throw new NullPointerException();
		}
	}

	public ArrayList<Passenger> getPassengers() {
		return passengers;
	}

	public void addPassenger(Passenger passenger) {
		passengers.add(passenger);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Alaska alaska = (Alaska) o;
		return Objects.equals(description, alaska.description) &&
						passengers.equals(alaska.passengers) &&
						Objects.equals(name, alaska.name);
	}

	public String toString() {
		return "";
	}
}
