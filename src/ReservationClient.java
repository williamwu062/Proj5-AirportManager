import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Client class where the user can buy a ticket if one is still available.
 *
 * @author williamwu
 * @version 1.0
 */
public final class ReservationClient {
	private static BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));
	private static String hostname;
	private static String portString;
	private static int port;
	private static Socket socket;
	private static BufferedWriter socketWriter = null;
	private static BufferedReader socketReader = null;
	private static String request;
	private static String response;

	private JFrame frame;
	private JPanel mainPanel;
	private CardLayout layout;
	private String airlineChoice;
	private Passenger passenger;

	private static boolean isParsable(String string) {
		return string.chars()
						.mapToObj(Character::isDigit)
						.reduce(Boolean::logicalAnd)
						.orElse(Boolean.FALSE);
	}

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

		JPanel helpPanel_image = new JPanel();
		JLabel imageWelcome = new JLabel(new ImageIcon((new ImageIcon("Images/Purdue_Boilermakers_logo.svg.png"))
						.getImage().getScaledInstance(400, 300, 4)));
		helpPanel_image.add(imageWelcome);
		panel_0.add(helpPanel_image);

		JPanel helpPanel_1 = new JPanel();
		helpPanel_1.setLayout(new BoxLayout(helpPanel_1, BoxLayout.X_AXIS));
		JButton exit = new JButton("Exit");
		helpPanel_1.add(exit);
		JButton next = new JButton("Book a Flight");
		helpPanel_1.add(next);

		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stage_6();
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
				stage_6();
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
		mainPanel.setFocusable(true);
		mainPanel.requestFocus();

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

		ArrayList<String> tempNames = new ArrayList<>();

		if (getaNumSeat()) {
			tempNames.add(Alaska.name);
		}
		if (getdNumSeat()) {
			tempNames.add(Delta.name);
		}
		if (getswNumSeat()) {
			tempNames.add(Southwest.name);
		}

		String[] airlineNames = new String[tempNames.size()]; //TODO maybe change to getName from Airline
		for (int i = 0; i < airlineNames.length; i++) {
			airlineNames[i] = tempNames.get(i);
		}
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
					mainPanel.requestFocus();
				}
			}
		});

		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stage_6();
			}
		});

		mainPanel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_BACK_SLASH) {
					String tempAirlineChoice = (String) airlines.getSelectedItem();
					airlinePassengers(tempAirlineChoice);
				}
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
		mainPanel.setFocusable(true);
		mainPanel.requestFocus();

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

		mainPanel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_BACK_SLASH) {
					airlinePassengers(airlineChoice);
				}
			}
		});

		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stage_6();
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
		JPanel panel_0 = new JPanel();
		panel_0.setLayout(new BoxLayout(panel_0, BoxLayout.Y_AXIS));
		mainPanel.setFocusable(true);
		mainPanel.requestFocus();

		JPanel helpPanel_0 = new JPanel();
		JLabel title = new JLabel("<html><b>Input you information below</b></html>");
		helpPanel_0.add(title);
		panel_0.add(helpPanel_0);

		JPanel helpPanel_2 = new JPanel();
		helpPanel_2.setLayout(new BoxLayout(helpPanel_2, BoxLayout.Y_AXIS));
		JLabel name = new JLabel("What is your name");
		helpPanel_2.add(name);
		JTextField nameField = new JTextField();
		helpPanel_2.add(nameField);
		JLabel lname = new JLabel("What is your last name");
		helpPanel_2.add(lname);
		JTextField lnameField = new JTextField();
		helpPanel_2.add(lnameField);
		JLabel age = new JLabel("What is your age");
		helpPanel_2.add(age);
		JTextField ageField = new JTextField();
		helpPanel_2.add(ageField);
		panel_0.add(helpPanel_2);

		JPanel helpPanel_1 = new JPanel();
		helpPanel_1.setLayout(new BoxLayout(helpPanel_1, BoxLayout.X_AXIS));
		JButton exit = new JButton("No");
		helpPanel_1.add(exit);
		JButton next = new JButton("Choose this flight");
		helpPanel_1.add(next);


		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stage_6();
			}
		});

		mainPanel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_BACK_SLASH) {
					airlinePassengers(airlineChoice);
				}
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
													"otherwise, select the No button.</html>", "Confirm",
									JOptionPane.YES_NO_OPTION);
					if (option == JOptionPane.YES_OPTION) {
						getaGate();
						getdGate();
						getswGate();

						Gate gate = null;
						if (airlineChoice.equals(Alaska.name)) {
							gate = Alaska.gate;
						} else if (airlineChoice.equals(Delta.name)) {
							gate = Delta.gate;
						} else if (airlineChoice.equals(Southwest.name)) {
							gate = Southwest.gate;
						}

						BoardingPass pass = new BoardingPass(nameField.getText(), lnameField.getText(), convertedAge,
										airlineChoice, gate);
						passenger = new Passenger(nameField.getText(), lnameField.getText(), convertedAge);
						passenger.addBoardingPass(pass);

						if (airlineChoice.equals(Alaska.name)) {
							add_aPassenger();
						} else if (airlineChoice.equals(Delta.name)) {
							add_dPassenger();
						} else if (airlineChoice.equals(Southwest.name)) {
							add_swPassenger();
						}

						stage_5();
					}
				} catch (NumberFormatException a) {
					JOptionPane.showMessageDialog(null, "Please enter an integer for your age", "Error",
									JOptionPane.ERROR_MESSAGE);
					ageField.requestFocus();
				}
			}
		});

		panel_0.add(helpPanel_1);
		mainPanel.add(panel_0, "4");
		layout.show(mainPanel, "4");
	}

	public void stage_5() {
		getaNumSeat();
		getdNumSeat();
		getswNumSeat();
		Alaska.passenger = getaPassengers();
		Delta.passenger = getdPassengers();
		Southwest.passenger = getswPassengers();

		JPanel panel_0 = new JPanel();
		panel_0.setLayout(new BoxLayout(panel_0, BoxLayout.Y_AXIS));
		passenger.getPass();
		passenger.getPass().getGate();
		passenger.getPass().getGate().getGate();

		JPanel helpPanel_0 = new JPanel();
		JLabel title = new JLabel("<html><b>Flight data displaying for " + airlineChoice + "Airlines<br>" +
						"Enjoy " +
						"your flight!<br>Flight is now boarding at Gate" + passenger.getPass().getGate().getGate() +
						"</b></html>");
		helpPanel_0.add(title);
		panel_0.add(helpPanel_0);

		JPanel helpPanel_2 = new JPanel();
		JLabel numOnFlight;
		if (airlineChoice.equalsIgnoreCase(Alaska.name)) {
			numOnFlight = new JLabel(Alaska.numSeat);
		} else if (airlineChoice.equalsIgnoreCase(Delta.name)) {
			numOnFlight = new JLabel(Delta.numSeat);
		} else if (airlineChoice.equalsIgnoreCase(Southwest.name)) {
			numOnFlight = new JLabel(Southwest.numSeat);
		}
		panel_0.add(helpPanel_2);

		if (airlineChoice.equals(Alaska.name)) {
			JTextArea result = new JTextArea();
			result.setEditable(false);
			String text = "";
			for (int i = 0; i < Alaska.passenger.size(); i++) {
				text = text + Alaska.passenger.get(i).toString() + "\n";
			}
			result.setText(text);
			panel_0.add(result);
		} else if (airlineChoice.equals(Delta.name)) {
			JTextArea result = new JTextArea();
			result.setEditable(false);
			String text = "";
			for (int i = 0; i < Delta.passenger.size(); i++) {
				text = text + Delta.passenger.get(i).toString() + "\n";
			}
			result.setText(text);
			panel_0.add(result);
		} else if (airlineChoice.equals(Southwest.name)) {
			JTextArea result = new JTextArea();
			result.setEditable(false);
			String text = "";
			for (int i = 0; i < Southwest.passenger.size(); i++) {
				text = text + Southwest.passenger.get(i).toString() + "\n";
			}
			result.setText(text);
			panel_0.add(result);
		}

		JPanel helpPanel_1 = new JPanel();
		helpPanel_1.setLayout(new BoxLayout(helpPanel_1, BoxLayout.X_AXIS));
		JButton exit = new JButton("Exit");
		helpPanel_1.add(exit);
		JButton refresh = new JButton("Refresh");
		helpPanel_1.add(refresh);

		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stage_6();
			}
		});

		refresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stage_5();
			}
		});

		panel_0.add(helpPanel_1);
		mainPanel.add(panel_0, "5");
		layout.show(mainPanel, "5");
	}

	public void stage_6() {
		int i = JOptionPane.showOptionDialog(null, "<html><b>Thank you for using Purdue University Airline " +
										"Management System!</b></html>",
						"Thank you", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

		if (i == 0) {
			frame.dispose();
		}
	}

	public boolean getaNumSeat() {
		try {
			socketWriter.write(Alaska.name + "_maxMin");
			socketWriter.newLine();
			socketWriter.flush();
			Alaska.numSeat = socketReader.readLine();
			String[] split = Alaska.numSeat.split("/");
			if (split[0].equals(split[1])) {
				return false;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;
	}

	public boolean getdNumSeat() {
		try {
			socketWriter.write(Delta.name + "_maxMin");
			socketWriter.newLine();
			socketWriter.flush();
			Delta.numSeat = socketReader.readLine();
			String[] split = Delta.numSeat.split("/");
			if (split[0].equals(split[1])) {
				return false;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;
	}

	public boolean getswNumSeat() {
		try {
			socketWriter.write(Southwest.name + "_maxMin");
			socketWriter.newLine();
			socketWriter.flush();
			Southwest.numSeat = socketReader.readLine();
			String[] split = Southwest.numSeat.split("/");
			if (split[0].equals(split[1])) {
				return false;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;
	}

	public void getaGate() {
		try {
			socketWriter.write(Alaska.name + "_gate");
			socketWriter.newLine();
			socketWriter.flush();
			ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
			Alaska.gate = (Gate) input.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void getdGate() {
		try {
			socketWriter.write(Delta.name + "_gate");
			socketWriter.newLine();
			socketWriter.flush();
			ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
			Delta.gate = (Gate) input.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void getswGate() {
		try {
			socketWriter.write(Southwest.name + "_gate");
			socketWriter.newLine();
			socketWriter.flush();
			ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
			Southwest.gate = (Gate) input.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void add_aPassenger() {
		try {
			socketWriter.write(Alaska.name + "_addPassenger");
			socketWriter.newLine();
			socketWriter.flush();
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(passenger);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void add_dPassenger() {
		try {
			socketWriter.write(Delta.name + "_addPassenger");
			socketWriter.newLine();
			socketWriter.flush();
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(passenger);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void add_swPassenger() {
		try {
			socketWriter.write(Delta.name + "_addPassenger");
			socketWriter.newLine();
			socketWriter.flush();
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(passenger);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Passenger> getaPassengers() {
		try {
			socketWriter.write(Alaska.name + "_Passengers");
			socketWriter.newLine();
			socketWriter.flush();
			ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
			return (ArrayList<Passenger>) input.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<Passenger> getdPassengers() {
		try {
			socketWriter.write(Delta.name + "_Passengers");
			socketWriter.newLine();
			socketWriter.flush();
			ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
			return (ArrayList<Passenger>) input.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<Passenger> getswPassengers() {
		try {
			socketWriter.write(Southwest.name + "_Passengers");
			socketWriter.newLine();
			socketWriter.flush();
			ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
			return (ArrayList<Passenger>) input.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void airlinePassengers(String airlineChoice) {
		Alaska.passenger = getaPassengers();
		Delta.passenger = getdPassengers();
		Southwest.passenger = getswPassengers();
		getaNumSeat();
		getdNumSeat();
		getswNumSeat();
		JFrame smallFrame = new JFrame();
		smallFrame.setSize(300, 300);
		smallFrame.setLayout(new FlowLayout());
		JPanel titlePanel = new JPanel();
		JPanel display = new JPanel();
		JPanel buttonPanel = new JPanel();
		if (airlineChoice.equals(Alaska.name)) {
			JLabel title = new JLabel("Alaska Airline." + Alaska.name);
			JLabel numOnFlight = new JLabel(Alaska.numSeat);

			JTextArea result = new JTextArea();
			result.setEditable(false);
			String text = "";
			titlePanel.add(title);
			for (int i = 0; i < Alaska.passenger.size(); i++) {
				text = text + Alaska.passenger.get(i).toString() + "\n";
			}
			result.setText(text);
			display.add(numOnFlight);
			display.add(result);
		} else if (airlineChoice.equals(Delta.name)) {
			JLabel title = new JLabel("Delta Airline." + Delta.name);
			JLabel numOnFlight = new JLabel(Delta.numSeat);
			JTextArea result = new JTextArea();
			result.setEditable(false);
			String text = "";
			titlePanel.add(title);
			for (int i = 0; i < Delta.passenger.size(); i++) {
				text = text + Delta.passenger.get(i).toString() + "\n";
			}
			result.setText(text);
			display.add(numOnFlight);
			display.add(result);
		} else if (airlineChoice.equals(Southwest.name)) {
			JLabel title = new JLabel("Southwest Airline." + Southwest.name);
			JLabel numOnFlight = new JLabel(Southwest.numSeat);
			JTextArea result = new JTextArea();
			result.setEditable(false);
			String text = "";
			titlePanel.add(title);
			for (int i = 0; i < Southwest.passenger.size(); i++) {
				text = text + Southwest.passenger.get(i).toString() + "\n";
			}
			result.setText(text);
			display.add(numOnFlight);
			display.add(result);
		}
		JButton exit = new JButton("Exit");
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				smallFrame.dispose();
			}
		});
		buttonPanel.add(exit);
		smallFrame.add(titlePanel);
		smallFrame.add(display);
		smallFrame.add(buttonPanel);
		smallFrame.setVisible(true);
	}


	public ReservationClient() {
		try {
			hostname = JOptionPane.showInputDialog(null, "What is the host name you'd like to connect to? ",
							"hostName?", JOptionPane.QUESTION_MESSAGE);

			if (hostname == null) {
				System.out.println();

				System.out.println("Goodbye!");
			} else {
				portString = JOptionPane.showInputDialog(null, "What is the port you'd like to connect to? ",
								"hostName?", JOptionPane.QUESTION_MESSAGE);

				if (portString == null) {
					System.out.println();

					System.out.println("Goodbye!");
				} else if (!isParsable(portString)) {
					System.out.println();

					System.out.println("The specified port must be a non-negative integer! Goodbye!");
				} else {
					port = Integer.parseInt(portString);

					socket = new Socket(hostname, port);

					socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

					socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					System.out.println("Connection successful");

					SwingUtilities.invokeLater(new Runnable() {
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
						}
					});
				} //end if
			} //end if
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ReservationClient client = new ReservationClient();
	}

}
