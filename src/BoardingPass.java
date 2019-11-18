/**
 * BoardingPass class that contains information on the passenger and flight.
 */
public class BoardingPass {
	private String firstName;
	private String lastName;
	private int age;

	/**
	 * Initializes fields
	 * @param firstName the firstName of the passenger
	 * @param lastName the lastName of the passenger
	 * @param age the age of the passenger
	 */
	public BoardingPass(String firstName, String lastName, int age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	//TODO need to add flight and flight number
}
