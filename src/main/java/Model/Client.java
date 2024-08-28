package Model;

public class Client {
    private int id;
    private String name;
    private String lastName;
    private String address;
    private String email;
    private int MoneySum;
    private String paymentMethod;
    private String password;
    private boolean sessionOpen = false;

    public Client(int id, String name, String lastName, String address, String email, int moneySum, String paymentMethod, String password) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        MoneySum = moneySum;
        this.paymentMethod = paymentMethod;
        this.password = password;
    }

    public Client(int id, String password, boolean sessionOpen) {
        this.id = id;
        this.password = password;
        this.sessionOpen = sessionOpen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setSessionOpen(boolean sessionOpen) {
        this.sessionOpen = sessionOpen;
    }

    public boolean isSessionOpen() {
        return sessionOpen;
    }
}
