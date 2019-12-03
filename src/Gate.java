import java.io.Serializable;
import java.util.Random;

/**
 * The Gate class describes the terminal and number of a flight.
 * @author williamwu, Benjamin Zhu
 * @version 1.0
 */
public class Gate implements Serializable {
	private String terminal;
	private int number;

	/**
	 * Initializes terminal to value, and sets number to random value between 1-18, inclusive.
	 */
	public Gate() {
		int randTerminal = (new Random()).nextInt(3);
		if (randTerminal == 0) {
			terminal = "A";
		}
		if (randTerminal == 1) {
			terminal = "B";
		}
		if (randTerminal == 2) {
			terminal = "C";
		}

		number = (new Random()).nextInt(18) + 1;
	}

	/**
	 * Gets the gate terminal and number.
	 *
	 * @return the gate
	 */
	public String getGate() {
		return terminal + number;
	}
}
