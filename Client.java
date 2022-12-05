public class Client {

    private static int count = 1000;
    private int id;
    private String name;
    private String address;
    private String country;
    private int balance;

    /**
     * Constructor for Client
     * @param name
     * @param address
     * @param country
     */
    public Client(String name, String address, String country) {
        this.id = count++;
        this.name = name;
        this.address = address;
        this.country = country;
        this.balance = 0;
    }

    /**
     * Get client name
     * @return name
     */
    public int getId() {
        return id;
    }

    /**
     * Get client name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Get client address
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Get client country
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Set client balance
     * @param balance
     */
    public void setBalance(int balance) {

        this.balance = balance;
    }

    /**
     * Get client balance
     * @return balance
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Converts client balance to string
     * @return balance
     */
    public String balanceToString() {
        return balance + "€";
    }


    /**
     * Converts client information to string
     * @return string
     */
    @Override
    public String toString() {
        return "\nClient #" + id + ", Name: " + name  + ", Address: " + address + ", Country: " + country + ", Balance: " + balance;
    }



}
