import javax.swing.*;
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
        JPanel panelStage2 = new JPanel();
        JLabel welcome = new JLabel("Welcome to Purdue University Airline Reservation Management System!");
<<<<<<< HEAD
        JLabel imageWelcome = new JLabel(new ImageIcon("C:/Users/Benjamin_Zhu/Desktop/School/CS180/CS180PJ/CS180PJ%-actual/Images/Purdue_Boilermakers_logo.svg.png"));
        JButton exit = new JButton("Exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                stage8();
            }
        });
        JButton BookAFlight = new JButton("Book a Flight");
        BookAFlight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                stage3();
            }
        });
=======
        JLabel imageWelcome = new JLabel(new ImageIcon("Images/Purdue_Boilermakers_logo.svg.png"));
>>>>>>> 7e5cd879224340e1cc9df16842a291569f718f11
        stage2.setSize(900,600);
        stage2.setLayout(new FlowLayout());
        panelStage2.add(welcome);
        panelStage2.add(imageWelcome, BorderLayout.CENTER);
        stage2.add(panelStage2);
        stage2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
