import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.Set;
import java.util.Objects;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException; 

public class ReservationRequestHandler implements Runnable{
    /**
     * The client socket of this request handler.
     */
    private Socket clientSocket;

    public ReservationRequestHandler(Socket clientSocket) throws NullPointerException {
        Objects.requireNonNull(clientSocket, "the specified client socket is null");

        this.clientSocket = clientSocket;
    } //CensoringRequestHandler

    /**
     * Handles the requests of the client connected to this request handler's client socket.
     */
    public void stage2() {
        JFrame stage2 = new JFrame("Purdue Airline Reservation System");
        JPanel panelStage2Text = new JPanel();
        panelStage2Text.setSize(500,100);
        JPanel panelStage2Image = new JPanel();
        panelStage2Image.setSize(500,300);
        JPanel panelStage2Buttons = new JPanel();
        panelStage2Buttons.setSize(500,100);
        JLabel welcome = new JLabel("Welcome to Purdue University Airline Reservation Management System!");
        welcome.setFont(new Font("Serif", Font.PLAIN, 15));
        JLabel imageWelcome = new JLabel(new ImageIcon(((new ImageIcon
                ("Images/Purdue_Boilermakers_logo.svg.png")).getImage()).getScaledInstance(400,300 , Image.SCALE_SMOOTH)));
        JButton exit = new JButton("Exit");
        exit.setSize(199,50);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                stage8();
            }
        });
        JButton bookAFlight = new JButton("Book a Flight");
        bookAFlight.setSize(100, 50);
        bookAFlight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                stage3();
            }
        });
        stage2.setSize(500,500);
        stage2.setLayout(new FlowLayout());
        panelStage2Text.add(welcome);
        panelStage2Image.add(imageWelcome, BorderLayout.CENTER);
        panelStage2Buttons.add(bookAFlight, BorderLayout.CENTER);
        panelStage2Buttons.add(exit, BorderLayout.CENTER);
        stage2.add(panelStage2Text);
        stage2.add(panelStage2Image);
        stage2.add(panelStage2Buttons);
        stage2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        stage2.setResizable(false);
        stage2.setVisible(true);
    }

    public void stage3() {

    }

    public void stage4() {

    }

    public void stage5() {

    }

    public void stage6() {

    }

    public void stage7() {

    }

    public void stage8() {

    }
    @Override
    public void run() {
        stage2();
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
