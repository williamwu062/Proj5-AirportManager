import java.net.PasswordAuthentication;
import java.util.Arrays;
import java.util.Objects;

public class Alaska implements Airline {
    private String description;
    private Passenger[] passengers;
    private String name;

    public Alaska(String name, Passenger[] passengers, String description) throws NullPointerException {
        if(name == null || passengers == null || description == null) {
            throw new NullPointerException();
        }
        this.name = name;
        this.passengers = passengers;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Passenger[] getPassengers() {
        return passengers;
    }

    @Override
    public String getName() {
        return name;
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alaska alaska = (Alaska) o;
        return Objects.equals(description, alaska.description) &&
                Arrays.equals(passengers, alaska.passengers) &&
                Objects.equals(name, alaska.name);
    }

    public String toString() {
        return "";
    }
}
