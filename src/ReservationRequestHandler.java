import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Set;
import java.util.Objects;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class ReservationRequestHandler implements Runnable {
	/**
	 * The client socket of this request handler.
	 */
	private Socket clientSocket;
	private JFrame frame;
	private JPanel mainPanel;
	private CardLayout layout;
	private String airlineChoice;
	private Passenger passenger;

	//TODO need prompt if flight is full? Maybe. Yea we need to remove the option from stage 2

	/**
	 * Initializes fields.
	 *
	 * @param clientSocket the socket to be set
	 * @throws NullPointerException if clientSocket is null
	 */
	public ReservationRequestHandler(Socket clientSocket) throws NullPointerException {
		Objects.requireNonNull(clientSocket, "the specified client socket is null");
		this.clientSocket = clientSocket;
	} //CensoringRequestHandler

	/**
	 * Handles the requests of the client connected to this request handler's client socket.
	 */
	public void stage_0() {
		JPanel panel = new JPanel();
		JLabel welcome = new JLabel("Welcome to Purdue University Airline Reservation Management System!");
		panel.add(welcome);
		JButton exit = new JButton("Exit");
		panel.add(exit);
		JButton next = new JButton("Book a Flight");
		panel.add(next);

		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stage_7();
			}
		});

		next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stage_1();
			}
		});

		mainPanel.add(panel, "0");
		layout.show(mainPanel, "0");

		//JLabel imageWelcome = new JLabel(new ImageIcon("Images/Purdue_Boilermakers_logo.svg.png"));
		//panelStage2.add(imageWelcome, BorderLayout.CENTER);
	}

	public void stage_1() {
		JPanel panel = new JPanel();
		JLabel welcome = new JLabel("Are You Sure You Want to Book a Flight?");
		panel.add(welcome);
		JButton exit = new JButton("No");
		panel.add(exit);
		JButton next = new JButton("Yes");
		panel.add(next);

		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stage_7();
			}
		});

		next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stage_2();
			}
		});

		mainPanel.add(panel, "1");
		layout.show(mainPanel, "1");
	}

	public void stage_2() {
		JPanel panel = new JPanel();
		JLabel title = new JLabel("Choose a Flight");
		panel.add(title);
		String[] airlineNames = {Alaska.name, Delta.name, Southwest.name}; //TODO maybe change to getName from Airline
		JComboBox airlines = new JComboBox(airlineNames);
		panel.add(airlines);
		JButton exit = new JButton("No");
		panel.add(exit);
		JButton next = new JButton("Choose this flight");
		panel.add(next);

		String[] descriptions = {Alaska.description, Delta.description, Southwest.description};
		//JPanel wordPanel = new JPanel(); TODO shall i do this doe. IF needed when formatting the GUI
		JLabel mainParagraph = new JLabel(descriptions[0]);
		panel.add(mainParagraph);
		airlines.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					mainParagraph.setText(descriptions[airlines.getSelectedIndex()]);
				}
			}
		});

		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stage_7();
			}
		});

		next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO instantiate airlineChoice field here
				airlineChoice = airlines.getName();
				stage_3();
			}
		});

		mainPanel.add(panel, "2");
		layout.show(mainPanel, "2");
	}

	public void stage_3() {
		JPanel panel = new JPanel();
		JLabel title = new JLabel("Are you sure that you want to book a flight on " + airlineChoice);
		panel.add(title);
		JButton exit = new JButton("No");
		panel.add(exit);
		JButton differentFlight = new JButton("Different flight");
		panel.add(differentFlight);
		JButton next = new JButton("Choose this flight");
		panel.add(next);

		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stage_7();
			}
		});

		differentFlight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stage_2();
			}
		});

		next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stage_4();
			}
		});

		mainPanel.add(panel, "3");
		layout.show(mainPanel, "3");
	}

	public void stage_4() {
		JPanel panel = new JPanel();
		JLabel title = new JLabel("Input you information below");
		panel.add(title);

		JLabel name = new JLabel("What is your name");
		panel.add(name);
		JTextField nameField = new JTextField();
		panel.add(nameField);
		JLabel lname = new JLabel("What is your name");
		panel.add(lname);
		JTextField lnameField = new JTextField();
		panel.add(lnameField);
		JLabel age = new JLabel("What is your name");
		panel.add(age);
		JTextField ageField = new JTextField();
		panel.add(ageField);

		JButton exit = new JButton("No");
		panel.add(exit);
		JButton next = new JButton("Choose this flight");
		panel.add(next);

		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stage_7();
			}
		});

		next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int convertedAge = Integer.parseInt(ageField.getText());

					String passengerInfo = String.format("The passenger's name is %s %s and their age is %d.",
									nameField.getText(),
									lnameField.getText(), convertedAge);
					int option = JOptionPane.showConfirmDialog(null,
									"<html>Are all the details you entered correct?\n" + passengerInfo + "If all the " +
													"information shown is correct select the Yes button below, " +
													"otherwise, select the No button.<html>", "Confirm",
									JOptionPane.YES_NO_OPTION);
					if (option == JOptionPane.YES_OPTION) {
						passenger = new Passenger(nameField.getText(), lnameField.getText(), convertedAge);

						stage_5();
					}
				} catch(NumberFormatException a) {
					JOptionPane.showMessageDialog(null, "Please enter an integer for your age", "Error", JOptionPane.ERROR_MESSAGE);
					ageField.requestFocus();
				}
			}
		});

		mainPanel.add(panel, "4");
		layout.show(mainPanel, "4");
	}

	public void stage_5() {

	}

	public void stage_6() {

	}

	public void stage_7() {

	}

	@Override
	public void run() {
		frame = new JFrame("Purdue University Flight Reservation System");
		layout = new CardLayout();
		mainPanel = new JPanel(layout);

		stage_0();

		frame.add(mainPanel);
		frame.setSize(900, 600);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	} //run

	/**
	 * Returns the hash code of this request handler.
	 *
	 * @return the hash code of this request handler
	 */
	@Override
	public int hashCode() {
		int result = 23;

		result = 31 * result + Objects.hashCode(this.clientSocket);

		return result;
	} //hashCode

	/**
	 * Determines whether or not the specified object is equal to this request handler. {@code true} is returned if and
	 * only if the specified object is an instance of {@code CensoringRequestHandler} and its client socket and
	 * {@code Set} of bad words are equal to this request handler's.
	 *
	 * @param object the object to be used in the comparisons
	 * @return {@code true}, if the specified object is equal to this request handler and {@code false} otherwise
	 */
	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		} else if (object instanceof ReservationRequestHandler) {
			boolean equal;

			equal = Objects.equals(this.clientSocket, ((ReservationRequestHandler) object).clientSocket);

			return equal;
		} else {
			return false;
		} //end if
	} //equals

	@Override
	public String toString() {
		String format = "CensoringRequestHandler[%s]";

		return String.format(format, this.clientSocket);
	} //toString
}
