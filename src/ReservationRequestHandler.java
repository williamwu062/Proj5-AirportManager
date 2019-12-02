import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Set;
import java.util.Objects;

/**
 * @author williamwu
 * @version 1.0
 */
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
	//TODO check if the textfields are empty in stage 6 I think

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
		JPanel panel_0 = new JPanel();
		panel_0.setLayout(new BoxLayout(panel_0, BoxLayout.Y_AXIS));

		JPanel helpPanel_0 = new JPanel();
		JLabel welcome = new JLabel("<html><b>Welcome to Purdue University Airline Reservation Management " +
						"System!</b></html>");
		helpPanel_0.add(welcome);
		panel_0.add(helpPanel_0);

		JPanel helpPanel_1 = new JPanel();
		helpPanel_1.setLayout(new BoxLayout(helpPanel_1, BoxLayout.X_AXIS));
		JButton exit = new JButton("Exit");
		helpPanel_1.add(exit);
		JButton next = new JButton("Book a Flight");
		helpPanel_1.add(next);

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

		panel_0.add(helpPanel_1);
		mainPanel.add(panel_0, "0");
		layout.show(mainPanel, "0");

		/*JFrame stage2 = new JFrame("Purdue Airline Reservation System");
		JPanel panelStage2Text = new JPanel();
		panelStage2Text.setSize(500, 100);
		JPanel panelStage2Image = new JPanel();
		panelStage2Image.setSize(500, 300);
		JPanel panelStage2Buttons = new JPanel();
		panelStage2Buttons.setSize(500, 100);
		JLabel welcome = new JLabel("Welcome to Purdue University Airline Reservation Management System!");
		welcome.setFont(new Font("Serif", 0, 15));
		JLabel imageWelcome = new JLabel(new ImageIcon((new ImageIcon("Images/Purdue_Boilermakers_logo.svg.png"))
		.getImage().getScaledInstance(400, 300, 4)));
		JButton exit = new JButton("Exit");
		exit.setSize(199, 50);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				ReservationRequestHandler.this.stage8();
			}
		});
		JButton bookAFlight = new JButton("Book a Flight");
		bookAFlight.setSize(100, 50);
		bookAFlight.addActionListener(new 2(this));
		stage2.setSize(500, 400);
		stage2.setLayout(new FlowLayout());
		panelStage2Text.add(welcome);
		panelStage2Image.add(imageWelcome, "Center");
		panelStage2Buttons.add(bookAFlight, "Center");
		panelStage2Buttons.add(exit, "Center");
		stage2.add(panelStage2Text);
		stage2.add(panelStage2Image);
		stage2.add(panelStage2Buttons);
		stage2.setDefaultCloseOperation(3);
		stage2.setVisible(true);*/
	}

	public void stage_1() {
		JPanel panel_0 = new JPanel();
		panel_0.setLayout(new BoxLayout(panel_0, BoxLayout.Y_AXIS));

		JPanel helpPanel_0 = new JPanel();
		JLabel welcome = new JLabel("<html><b>Are You Sure You Want to Book a Flight?</b></html>");
		helpPanel_0.add(welcome);
		panel_0.add(helpPanel_0);

		JPanel helpPanel_1 = new JPanel();
		helpPanel_1.setLayout(new BoxLayout(helpPanel_1, BoxLayout.X_AXIS));
		JButton exit = new JButton("No");
		helpPanel_1.add(exit);
		JButton next = new JButton("Yes");
		helpPanel_1.add(next);

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

		panel_0.add(helpPanel_1);
		mainPanel.add(panel_0, "1");
		layout.show(mainPanel, "1");
	}

	public void stage_2() {

		JPanel panel_0 = new JPanel();
		panel_0.setLayout(new BoxLayout(panel_0, BoxLayout.Y_AXIS));

		JPanel helpPanel_0 = new JPanel();
		JLabel title = new JLabel("<html><b>Choose a Flight</b></html>");
		helpPanel_0.add(title);
		panel_0.add(helpPanel_0);
		//TODO create a hashmap before creating an array of airline names. This so that we can find which planes are
		// full

		JPanel helpPanel_1 = new JPanel();
		helpPanel_1.setLayout(new BoxLayout(helpPanel_1, BoxLayout.X_AXIS));
		JButton exit = new JButton("No");
		helpPanel_1.add(exit);
		JButton next = new JButton("Choose this flight");
		helpPanel_1.add(next);

		String[] airlineNames = {Alaska.name, Delta.name, Southwest.name}; //TODO maybe change to getName from Airline
		JComboBox airlines = new JComboBox(airlineNames);
		panel_0.add(airlines);

		JPanel helpPanel_2 = new JPanel();
		String[] descriptions = {Alaska.description, Delta.description, Southwest.description};
		//JPanel wordPanel = new JPanel(); TODO shall i do this doe. IF needed when formatting the GUI
		JLabel mainParagraph = new JLabel(descriptions[0]);
		helpPanel_2.add(mainParagraph);
		panel_0.add(helpPanel_2);

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
				airlineChoice = (String) airlines.getSelectedItem();
				stage_3();
			}
		});

		panel_0.add(helpPanel_1);
		mainPanel.add(panel_0, "2");
		layout.show(mainPanel, "2");
	}

	public void stage_3() {
		JPanel panel_0 = new JPanel();
		panel_0.setLayout(new BoxLayout(panel_0, BoxLayout.Y_AXIS));

		JPanel helpPanel_0 = new JPanel();
		JLabel title = new JLabel("<html><b>Are you sure that you want to book a flight on " + airlineChoice +
						" Airlines?</b></html>");
		helpPanel_0.add(title);
		panel_0.add(helpPanel_0);

		JPanel helpPanel_1 = new JPanel();
		helpPanel_1.setLayout(new BoxLayout(helpPanel_1, BoxLayout.X_AXIS));

		JButton exit = new JButton("No");
		helpPanel_1.add(exit);
		JButton differentFlight = new JButton("Different flight");
		helpPanel_1.add(differentFlight);
		JButton next = new JButton("Choose this flight");
		helpPanel_1.add(next);

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

		panel_0.add(helpPanel_1);
		mainPanel.add(panel_0, "3");
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
									"<html>Are all the details you entered correct?\n" + passengerInfo + "If all the" +
													" " +
													"information shown is correct select the Yes button below, " +
													"otherwise, select the No button.<html>", "Confirm",
									JOptionPane.YES_NO_OPTION);
					if (option == JOptionPane.YES_OPTION) {
						Gate gate = null;

						BoardingPass pass = new BoardingPass(nameField.getText(), lnameField.getText(), convertedAge,
										airlineChoice, gate);
						passenger = new Passenger(nameField.getText(), lnameField.getText(), convertedAge);
						passenger.addBoardingPass(pass);

						stage_5();
					}
				} catch (NumberFormatException a) {
					JOptionPane.showMessageDialog(null, "Please enter an integer for your age", "Error",
									JOptionPane.ERROR_MESSAGE);
					ageField.requestFocus();
				}
			}
		});

		mainPanel.add(panel, "4");
		layout.show(mainPanel, "4");
	}

	public void stage_5() {
		JPanel panel = new JPanel();
		JLabel title = new JLabel("<html>Flight data displaying for " + airlineChoice + "Airlines<br>" +
						"Enjoy " +
						"your flight!<br>Flight is now boarding at Gate" + passenger.getPass().getGate().getGate() +
						"</html>");
		panel.add(title);

		mainPanel.add(panel, "5");
		layout.show(mainPanel, "5");
	}

	public void stage_6() {

	}

	public void stage_7() {
		JOptionPane.showMessageDialog(null, "Thank you for using Purdue University Airline Management System!",
				"Thank you", JOptionPane.INFORMATION_MESSAGE);
	}

	public synchronized void airlinePassengers() {

	}

	@Override
	public void run() {
		frame = new JFrame("Purdue University Flight Reservation System");
		layout = new CardLayout();
		mainPanel = new JPanel(layout);

		stage_0();

		frame.add(mainPanel);
		frame.setSize(900, 600);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
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
