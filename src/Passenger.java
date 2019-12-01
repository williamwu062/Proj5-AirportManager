/**
 * The Passenger class defines a passenger in a plane.
 */
public class Passenger {
	private String firstName;
	private String lastName;
	private int age;
	private BoardingPass pass;

	public Passenger(String firstName, String lastName, int age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	public void addBoardingPass(BoardingPass pass) {
		this.pass = pass;
	}
}
