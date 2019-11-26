import java.io.Serializable;
import java.util.ArrayList;

public interface Airline extends Serializable {
	public ArrayList<Passenger> getPassengers();

	public void addPassenger(Passenger passenger);
}

