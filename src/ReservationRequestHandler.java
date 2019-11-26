import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Array;
import java.net.Socket;
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
	private Airline airlineChoice;

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

		mainPanel.add(panel, "2");
		layout.show(mainPanel, "2");
	}

	public void stage_3() {

	}

	public void stage_4() {

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
