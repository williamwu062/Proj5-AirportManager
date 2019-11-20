import java.io.Serializable;

public interface Airline extends Serializable {
    public String getName();

    public void setName(String name) throws NullPointerException;
}
