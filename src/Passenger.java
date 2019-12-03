import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

/**
 * The Passenger class defines a passenger in a plane.
 * @author williamwu
 * @version 1.0
 */
public class Passenger implements Serializable {
	private String firstName;
	private String lastName;
	private int age;
	private static BoardingPass pass;

	/**
	 * Initializes fields.
	 * @param firstName firstName parameter
	 * @param lastName lastName parameter
	 * @param age age parameter
	 */
	public Passenger(String firstName, String lastName, int age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	/**
	 * Add a boarding pass.
	 * @param pass the BoardingPass to be added
	 */
	public void addBoardingPass(BoardingPass pass) {
		this.pass = pass;
	}

	/**
	 * Gets the first name.
	 * @return first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Gets the last name.
	 * @return last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Gets the age.
	 * @return age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Gets the boarding pass
	 * @return boarding pass
	 */
	public static BoardingPass getPass() {
		return pass;
	}

	public String toString() {
		return getFirstName().substring(0, 1).toUpperCase() + ". " + getLastName().toUpperCase() + ", " + getAge();
	}

}
