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

public class ReservationRequestHandler implements Runnable {
	/**
	 * The client socket of this request handler.
	 */
	private Socket clientSocket;
	private JFrame frame;
	private JPanel mainPanel;
	private CardLayout layout;
	private String whichAirline;
	private ArrayList<String> alaskaInfo;
	private ArrayList<String> southwestInfo;
	private ArrayList<String> deltaInfo;

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
	public ArrayList<String> getAlaskaInfo() {
		File file = new File("reservations.txt");
		String exception1 = "";
		String exception2 = "";

		try {
			FileReader fr = new FileReader(file);
			BufferedReader bfr = new BufferedReader(fr);
			String s = "";
			String t = "";
				while (true) {
					s = bfr.readLine();
					if (s == null) {
						break;
					}
					if (s.equals("ALASKA")) {
						this.alaskaInfo.add(s);
						while (true) {
							t = bfr.readLine();
							if (t == null) {
								break;
							}
							if (!t.equals("DELTA") || !t.equals("SOUTHWEST") || !t.equals("EOF")) {
								this.alaskaInfo.add(t);
							}
							else {
								break;
							}
						}
					}
				}
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "The file does not exist!", "File not found", JOptionPane.ERROR_MESSAGE);
			}
			return this.alaskaInfo;
		}

	public ArrayList<String> getSouthwestInfo() {
		File file = new File("reservations.txt");
		String exception1 = "";
		String exception2 = "";

		try {
			FileReader fr = new FileReader(file);
			BufferedReader bfr = new BufferedReader(fr);
			String s = "";
			String t = "";
			while (true) {
				s = bfr.readLine();
				if (s == null) {
					break;
				}
				if (s.equals("SOUTHWEST")) {
					this.alaskaInfo.add(s);
					while (true) {
						t = bfr.readLine();
						if(t == null)
						{
							break;
						}
						if (!t.equals("DELTA") || !t.equals("ALASKA") || !t.equals("EOF")) {
							this.alaskaInfo.add(t);
						}
						else {
							break;
						}
					}
				}
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "The file does not exist!", "File not found", JOptionPane.ERROR_MESSAGE);
		}
		return this.alaskaInfo;
	}

	public ArrayList<String> getDeltaInfo() {
		File file = new File("reservations.txt");
		String exception1 = "";
		String exception2 = "";

		try {
			FileReader fr = new FileReader(file);
			BufferedReader bfr = new BufferedReader(fr);
			String s = "";
			String t = "";
			while (true) {
				s = bfr.readLine();
				if (s == null) {
					break;
				}
				if (s.equals("DELTA")) {
					this.alaskaInfo.add(s);
					while (true) {
						t = bfr.readLine();
						if (t == null) {
							break;
						}
						if (!t.equals("ALASKA") || !t.equals("SOUTHWEST") || !t.equals("EOF")) {
							this.alaskaInfo.add(t);
						}
						else {
							break;
						}
					}
				}
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "The file does not exist!", "File not found", JOptionPane.ERROR_MESSAGE);
		}
		return this.alaskaInfo;
	}

	public void stage_0() {
		JPanel panel = new JPanel();
		panel.setSize(500, 500);
		JPanel panelStage0Text = new JPanel();
		panelStage0Text.setSize(new Dimension(500, 100));
		JPanel panelStage0Image = new JPanel();
		panelStage0Image.setSize(new Dimension(500, 300));
		JPanel panelStage0Buttons = new JPanel();
		panelStage0Buttons.setPreferredSize(new Dimension(500, 100));
		JLabel welcome = new JLabel("Welcome to Purdue University Airline Reservation Management System!");
		panelStage0Text.add(welcome);
		JLabel imageWelcome = new JLabel(new ImageIcon(
				(new ImageIcon("Images/Purdue_Boilermakers_logo.svg.png")).getImage().getScaledInstance
						(400, 300, 4)));
		panelStage0Image.add(imageWelcome);
		JButton exit = new JButton("Exit");
		panelStage0Buttons.add(exit);
		JButton next = new JButton("Book a Flight");
		panelStage0Buttons.add(next);
		panel.add(panelStage0Text, BorderLayout.CENTER);
		panel.add(panelStage0Image, BorderLayout.CENTER);
		panel.add(panelStage0Buttons, BorderLayout.CENTER);

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

		/*JFrame stage2 = new JFrame("Purdue Airline Reservation System");
		JPanel panelStage2Text = new JPanel();
		panelStage2Text.setSize(500, 100);
		JPanel panelStage2Image = new JPanel();
		panelStage2Image.setSize(500, 300);
		JPanel panelStage2Buttons = new JPanel();
		panelStage2Buttons.setSize(500, 100);
		JLabel welcome = new JLabel("Welcome to Purdue University Airline Reservation Management System!");
		welcome.setFont(new Font("Serif", 0, 15));
		JLabel imageWelcome = new JLabel(new ImageIcon((new ImageIcon("Images/Purdue_Boilermakers_logo.svg.png")).getImage().getScaledInstance(400, 300, 4)));
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
		frame.setResizable(false);
		mainPanel.add(panel, "1");
		layout.show(mainPanel, "1");
	}

	public void stage_2() {
		JPanel panel = new JPanel();
		JLabel title = new JLabel("Choose a Flight");
		panel.add(title);
		String[] airlineNames = {"Alaska", "Delta", "Southwest"}; //TODO maybe change to getName from Airline
		JComboBox airlines = new JComboBox(airlineNames);
		panel.add(airlines);
		JButton exit = new JButton("No");
		panel.add(exit);
		JButton next = new JButton("Choose this flight");
		panel.add(next);


		String[] paragraphs = new String[3];
		paragraphs[0] = "Gummy bears flying";
		paragraphs[1] = "How much food is this";
		paragraphs[2] = "I cannot believe you ate this much sushi";
		//JPanel wordPanel = new JPanel(); TODO shall i do this doe. IF needed when formatting the GUI
		JLabel mainParagaph = new JLabel(paragraphs[0]);
		panel.add(mainParagaph);
		airlines.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					mainParagaph.setText(paragraphs[airlines.getSelectedIndex()]);
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
				stage_3();
			}
		});
		frame.setResizable(false);
		mainPanel.add(panel, "2");
		layout.show(mainPanel, "2");
	}

	public void stage_3() {
		JPanel panel = new JPanel();
		JLabel title = new JLabel("Are you sure that you want to book a flight on ");
		panel.add(title);
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
				//TODO instantiate airlineChoice field here
				stage_3();
			}
		});

		mainPanel.add(panel, "2");
		layout.show(mainPanel, "2");
	}

	public void stage_4() {

	}

	public void stage_5() {

	}

	public void stage_6() {

	}

	public void stage_7() {
		JOptionPane.showMessageDialog(null, "Thank you for using Purdue University Airline Management System!",
				"THank you", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void run() {
		frame = new JFrame("Purdue University Flight Reservation System");
		layout = new CardLayout();
		mainPanel = new JPanel(layout);

		stage_0();

		frame.add(mainPanel);
		frame.setSize(500, 500);
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
