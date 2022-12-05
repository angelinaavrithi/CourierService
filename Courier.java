import javax.swing.text.StyleConstants;
import java.io.Serializable;
import java.util.*;

public class Courier implements Serializable {

    public static ArrayList<Parcel> parcels = new ArrayList<>();
    public static  ArrayList<Client> clients = new ArrayList<>();

    /**
     * Constructor for Courier object
     */
    public Courier() {
        parcels = new ArrayList<>();
        clients = new ArrayList<>();
    }

    /**
     * Finds existing client or registers new client
     * @param name client name
     * @param address client address
     * @param country client country code
     * @return existing or new client
     */
    public static Client registerClient(String name, String address, String country)
    {
        for (Client existingCl : clients) {
            if (existingCl.getName().equalsIgnoreCase(name) && existingCl.getAddress().equalsIgnoreCase(address) && existingCl.getCountry().equalsIgnoreCase(country)) {
            return existingCl;
            }
        }

        Client newCl = new Client(name, address, country);
        clients.add(newCl);

        return newCl;
    }

    /**
     * Registers new parcel
     * @param destination parcel destination
     * @return
     */
    public static void registerParcel(int clientId, String origin, String destination)
    {
        Parcel p = new Parcel(clientId, origin, destination);
        parcels.add(p);
    }


    /**
     * Calculates client balance, adding up all of their parcels' fee
     * @return balance
     */
    public static void calculateBalance() {

        int balance;

        for (Parcel p : Courier.parcels) {
            for (Client cl : Courier.clients) {
                if (p.getClientid() == cl.getId() && p.getParcelStatus()==ParcelStatus.PENDING) {
                    balance = cl.getBalance() + p.getFee();
                    cl.setBalance(balance);
                    p.updateParcelStatus();
                }
            }
        }
    }

    /**
     * Finds a client, using both their name and their address
     * This method is implemented using functional programming
     * @param name
     * @param address
     * @return
     */
    public static Client findClient(String name, String address) {

        return clients.stream()
                .filter(cl -> name.equals(cl.getName()) && address.equals(cl.getAddress()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Get parcels
     * @return parcels
     */
    public ArrayList<Parcel> getParcels() {
        return parcels;
    }


    /**
     * Get clients
     * @return clients
     */
    public ArrayList<Client> getClients() {
        return clients;
    }

}