import java.util.*;

public class Courier {

    public static HashMap<Integer, Integer> parcels = new HashMap<>();
    private static ArrayList<Client> clients = new ArrayList<>();


    /**
     * @param args
     * @return
     */
    public static void main(String[] args) {

        registerClient("Angelina Avrithi", "Miltiadou 12 Voula", "GR");
        registerClient("Marianna Avrithi", "Proodou 2 Voula", "GR");

        registerParcel("GR", registerClient("Giorgos Zervoleas", "Passov 44 Galatsi", "GR"));
        registerParcel("UY", registerClient("Angelina Avrithi", "Miltiadou 12 Voula", "GR"));

        System.out.println(calculateBalance(registerClient("Angelina Avrithi", "Miltiadou 12 Voula", "GR")));



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
        Client cl = new Client(name,address,country);
        for (Client existingCl : clients) {
            if (existingCl.getName().equalsIgnoreCase(name) && existingCl.getAddress().equalsIgnoreCase(address) && existingCl.getCountry().equalsIgnoreCase(country)){
                cl = existingCl;
            }
            else {
                Client newCl = new Client(name, address, country);
                clients.add(newCl);
                cl = newCl;
            }
        }
        return cl;
    }

    /**
     * Registers new parcel
     * @param destination parcel destination
     * @param cl client
     */
    public static void registerParcel(String destination, Client cl)
    {
        Parcel pc = new Parcel(cl.getCountry(), destination);
        parcels.put(cl.getId(), pc.getParcelCode());
    }

    /**
     * Calculates client balance
     * @param cl client
     * @return balance
     */
    public static double calculateBalance(Client cl) {

        double balance = 0.0;

        Iterator it = parcels.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println(pair.getKey());

            //if (pair.getKey() == cl.getId()) {
                //balance = 1;
            //}
        }
        return balance;
    }

}