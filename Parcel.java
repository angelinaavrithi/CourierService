public class Parcel {
    
    private int parcelCode;
    private String origin;
    private String destination;
    private ParcelStatus parcelStatus;
    private ShippingMethod shippingMethod;

    /**
     * Enumeration for parcel status
     */
    public enum ParcelStatus {
        PENDING,
        PROCESSED,
        SHIPPED, 
        ARRIVED;
        
        public static final ParcelStatus statuses[] = values();
    }

    /**
    * Enumeration for shipping method
    */
    public enum ShippingMethod 
    {
        STANDARD(7),
        EXPRESS(12),
        NEXTDAY(20);

        private int price;

        public int getPrice() {
            return price;
        }

        ShippingMethod(int price) 
        { 
            this.price = price; 
        }
    }

    /**
     * Constructor for Parcel
     * @param parcelCode parcel code
     * @param origin country of origin
     * @param destination destination country
     */
    public Parcel (int parcelCode, String origin, String destination)
    {
        this.parcelCode = parcelCode;
        this.origin = origin;
        this.destination = destination;
        this.parcelStatus = ParcelStatus.PENDING;
        this.shippingMethod = ShippingMethod.STANDARD;
    }

    /**
     * Get parcel code
     * @return parcelCode
     */
    public int getParcelCode()
    {
        return parcelCode;
    }

    /**
     * Get country of origin
     * @return origin
     */
    public String getOrigin()
    {
        return origin;
    }

    /**
     * Get destination country
     * @return destination
     */
    public String getDestination()
    {
        return destination;
    }

    /**
     * Get parcel status
     * @return parcelStatus
     */
    public ParcelStatus getParcelStatus()
    {
        return parcelStatus;
    }

    /**
     * Update the parcel status to the next phase
     */
    public void updateParcelStatus()
    {
        if (parcelStatus!=ParcelStatus.ARRIVED)
            parcelStatus = ParcelStatus.statuses[parcelStatus.ordinal()+1];
    }

    /**
     * Return parcel information as a string
     */
    public String toString()
    {
        String s = "\n Parcel Code: "+parcelCode+", Country of origin: "+origin+", Destination: "+destination+", Status: "+parcelStatus;

        return s;
    }

}
