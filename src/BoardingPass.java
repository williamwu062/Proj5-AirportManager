import java.io.Serializable;

/**
 * BoardingPass class that contains information on the passenger and flight.
 *
 * @author williamwu
 * @version 1.0
 */
public class BoardingPass implements Serializable {
	private String firstName;
	private String lastName;
	private int age;
	private String airlineName;
	private Gate gate;

	/**
	 * Initializes fields
	 *
	 * @param firstName the firstName of the passenger
	 * @param lastName  the lastName of the passenger
	 * @param age       the age of the passenger
	 */
	public BoardingPass(String firstName, String lastName, int age, String airlineName, Gate gate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.airlineName = airlineName;
		this.gate = gate;
	}

	/**
	 * Gets first name.
	 *
	 * @return first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Gets last name.
	 *
	 * @return last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Gets age.
	 *
	 * @return age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Gets airline name.
	 *
	 * @return airline name
	 */
	public String getAirlineName() {
		return airlineName;
	}

	/**
	 * Gets gate.
	 *
	 * @return gate
	 */
	public Gate getGate() {
		return gate;
	}

	public String toString() {
		return String.format("BOARDING PASS FOR FLIGHT 18000 WITH %s Airlines%n" +
						"PASSENGER FIRST NAME: %s&n" +
						"PASSENGER LAST NAME: %s%n" +
						"PASSENGER AGE: %d%n" +
						"You can now begin boarding at gate %s", airlineName, firstName, lastName, age, gate.getGate());
	}
}
