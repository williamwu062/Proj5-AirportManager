import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The Passenger class defines a passenger in a plane.
 * @author williamwu
 * @version 1.0
 */
public class Passenger {
	private String firstName;
	private String lastName;
	private int age;
	private BoardingPass pass;
	private ArrayList<String> names;

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
		this.names = names;
	}

	public ArrayList<String> getEachPassenger(String string) {
		File file = new File("reservations.txt");
		String exception1 = "";
		String exception2= "";
		if(string.equals("ALASKA")) {
			exception1 = "DELTA";
			exception2 = "SOUTHWEST";
		}
		else if (string.equals("DELTA")) {
			exception1 = "ALASKA";
			exception2 = "SOUTHWEST";
		}
		else {
			exception1 = "ALASKA";
			exception2 = "DELTA";
		}
		try {
			FileReader fr = new FileReader(file);
			BufferedReader bfr = new BufferedReader(fr);
			String s ="";

			while(true) {
				if(!string.equals("")) {
					s = bfr.readLine();
					if(s == null) {
						break;
					}
					if(s.equals(string)) {
							this.names.add(s);
						if(s.equals(exception1) || s.equals(exception2)) {
							break;
						}
					}
				}

			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "The file does not exist!", "File not found", JOptionPane.ERROR_MESSAGE);
		}
		return this.names;
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
	public BoardingPass getPass() {
		return pass;
	}

}
