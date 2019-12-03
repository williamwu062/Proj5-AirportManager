import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Client class where the user can buy a ticket if one is still available.
 *
 * @author williamwu
 * @version 1.0
 */
public final class ReservationClient {
	private String fake;
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
		panel_0.setFocusable(true);

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
				stage_6();
			}
		});

		panel_0.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_BACK_SLASH) {
					JOptionPane.showMessageDialog(null, "Hi", "title", JOptionPane.ERROR_MESSAGE);
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
				stage_6();
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

	public synchronized void stage_5() {
		JPanel panel_0 = new JPanel();
		panel_0.setLayout(new BoxLayout(panel_0, BoxLayout.Y_AXIS));

		JPanel helpPanel_0 = new JPanel();
		JLabel title = new JLabel("<html>Flight data displaying for " + airlineChoice + "Airlines<br>" +
						"Enjoy " +
						"your flight!<br>Flight is now boarding at Gate" + passenger.getPass().getGate().getGate() +
						"</html>");
		helpPanel_0.add(title);
		panel_0.add(helpPanel_0);

		JPanel helpPanel_2 = new JPanel();
		JTextArea passengerList = new JTextArea();
		passengerList.setEditable(false);


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

			}
		});

		panel_0.add(helpPanel_1);
		mainPanel.add(panel_0, "5");
		layout.show(mainPanel, "5");
	}

	public void stage_6() {
		JOptionPane.showMessageDialog(null, "Thank you for using Purdue University Airline Management System!",
						"Thank you", JOptionPane.INFORMATION_MESSAGE);
	}

	public void addaPassenger(){
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

	public void adddPassenger(){
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

	public void addswPassenger(){
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
				} //end if
			} //end if
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				userInputReader.close();

				if (socketWriter != null) {
					socketWriter.close();
				} //end if

				if (socketReader != null) {
					socketReader.close();
				} //end if
			} catch (IOException e) {
				e.printStackTrace();
			} //end try catch
		} //end try catch finally

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
	}

	public static void main(String[] args) {
		ReservationClient client = new ReservationClient();
	}

}
