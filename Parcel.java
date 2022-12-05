public class Parcel {

    private static int count = 100;
    private int parcelCode;
    private int clientId;
    private String origin;
    private String destination;
    private ParcelStatus parcelStatus;
    private ShippingMethod shippingMethod;
    private int fee;


    /**
     * Constructor for parcel
     * @param origin country of origin
     * @param destination destination country
     */
    public Parcel (int clientId, String origin, String destination)
    {
        this.parcelCode = count++;
        this.clientId = clientId;
        this.origin = origin;
        this.destination = destination;
        this.parcelStatus = ParcelStatus.PENDING;
        this.shippingMethod = setShippingMethod();
        this.fee = calculateShippingFee(this);
    }

    /**
     * Get client id code
     * @return clientId
     */
    public int getClientid()
    {
        return clientId;
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
     * Ge the parcel fee
     * @return fee
     */
    public int getFee()
    {
        return fee;
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
     * Get parcel status
     * @return parcelStatus
     */
    public ShippingMethod getShippingMethod()
    {
        return shippingMethod;
    }

    /**
     * Set parcel shipping method, according to the user's choice
     * @return
     */
    public ShippingMethod setShippingMethod() {
        if (ParcelDialog.menu.getSelectedItem().toString().equals("Standard")) return ShippingMethod.STANDARD;
        else if (ParcelDialog.menu.getSelectedItem().toString().equals("Express")) return ShippingMethod.EXPRESS;
        else if (ParcelDialog.menu.getSelectedItem().toString().equals("Next Day")) return ShippingMethod.NEXTDAY;

        return shippingMethod;
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
    @Override
    public String toString()
    {
        String s = "\n Parcel Code: "+parcelCode+", Country of origin: "+origin+", Destination: "+destination+ ", Shipping fee: " + fee +", Status: "+parcelStatus;

        return s;
    }

    /**
     * Calculates shipping fee of parcel as follows:
     * National standard shipping: 3 EUR,
     * National express shipping: 6 EUR,
     * National next day shipping: 10 EUR,
     * International standard shipping: 7 EUR,
     * International express shipping: 10 EUR,
     * International next day shipping: 13 EUR
     * @param pc parcel
     */
    public int calculateShippingFee(Parcel pc)
    {
        int fee = 0;
        String origin = pc.getOrigin();
        String destination = pc.getDestination();

        if (origin.equalsIgnoreCase(destination)) {
            if (pc.getShippingMethod()==ShippingMethod.STANDARD) fee = 3;
            else if (pc.getShippingMethod()==ShippingMethod.EXPRESS) fee = 6;
            else if (pc.getShippingMethod()==ShippingMethod.NEXTDAY) fee = 10;
        }
        else if (!origin.equalsIgnoreCase(destination)) {
            if (pc.getShippingMethod()==ShippingMethod.STANDARD) fee = 7;
            else if (pc.getShippingMethod()==ShippingMethod.EXPRESS) fee = 10;
            else if (pc.getShippingMethod()==ShippingMethod.NEXTDAY) fee = 13;
        }

        return fee;
    }


}
