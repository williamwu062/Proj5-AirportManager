import java.util.ArrayList;

public class Delta implements Airline {
	public static final String description = "<html>Delta Airlines is proud to be one of the five premier " +
					"airlines at Purdue University.<br>We are extremely proud to offer exceptional services, " +
					"with free limited WiFi for all customers.<br>Passengers who use T-Mobile as a cell phone " +
					"carrier get additional benefits.<br>We are also happy to offer power outlets in each seat " +
					"for passenger use.<br>We hope you choose to fly Delta at your next airline.</html>";
	public static final String name = "Delta";
	private ArrayList<Passenger> passengers;

	public Delta(ArrayList<Passenger> passengers) throws NullPointerException {
		this.passengers = passengers;

		if (passengers == null) {
			throw new NullPointerException();
		}
	}

	@Override
	public ArrayList<Passenger> getPassengers() {
		return passengers;
	}

	@Override
	public void addPassenger(Passenger passenger) {
		passengers.add(passenger);
	}
}
