import java.io.Serializable;
import java.util.Random;

/**
 * The Gate class describes the terminal and number of a flight.
 */
public class Gate implements Serializable
{
	private String terminal;
	private int number;

	/**
	 * Initializes terminal to value, and sets number to random value between 1-18, inclusive.
	 *
	 * @param terminal
	 */
	public Gate(String terminal)
	{
		this.terminal = terminal;
		number = (new Random()).nextInt(18) + 1;
	}

	/**
	 * Gets the gate terminal and number.
	 * @return the gate
	 */
	public String getGate()
	{
		return terminal + number;
	}
}
