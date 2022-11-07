import java.lang.reflect.Array;
import java.util.*;

public class Courier {

    /**
     * @param args
     * @return
     */
    public static void main(String[] args) {

        //All origin and destination countries stored in arrays
        String[] countriesOfDestination = {"GR", "IT", "DE", "DK", "IT", "CA", "US", "UY","ES", "UK", "UA", "JP", "FR", "PT", "SA"};
        String[] countriesOfOrigin = {"GR", "IT", "DE", "DK", "IT", "CA", "US", "UY", "ES", "UK", "UA", "JP", "FR", "PT", "SA"};

        Parcel p1 = new Parcel(1, getRandom(countriesOfDestination), getRandom(countriesOfOrigin));
        Parcel p2 = new Parcel(2, getRandom(countriesOfDestination), getRandom(countriesOfOrigin));
        Parcel p3 = new Parcel(3, getRandom(countriesOfDestination), getRandom(countriesOfOrigin));
        Parcel p4 = new Parcel(4, getRandom(countriesOfDestination), getRandom(countriesOfOrigin));
        Parcel p5 = new Parcel(5, getRandom(countriesOfDestination), getRandom(countriesOfOrigin));

        PriorityQueue parcels = new PriorityQueue<>();
        parcels.add(p1);
        parcels.add(p2);
        parcels.add(p3);
        parcels.add(p4);
        parcels.remove(p4);


        System.out.println(parcels);

    }

    /**
     * Returns a random element from an array
     * @param arr
     * @return element
     */
    public static String getRandom(String[] arr) 
    {
        int rnd = new Random().nextInt(arr.length);
        String element = arr[rnd];

        return element;
    }

}