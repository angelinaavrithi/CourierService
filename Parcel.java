public class Parcel {
    
    private int parcelCode;
    private String origin;
    private String destination;
    private String status;

    /**
     * Constructor
     * @param parcelCode
     * @param origin
     * @param destination
     * @param status
     */
    public Parcel (int parcelCode, String origin, String destination, String status)
    {
        this.parcelCode = parcelCode;
        this.origin = origin;
        this.destination = destination;
        this.status = status;
    }

    /**
     * Getters / accesors
     */
    public int getParcelCode()
    {
        return parcelCode;
    }

    public String getOrigin()
    {
        return origin;
    }

    public String getDestination()
    {
        return destination;
    }

    public String getStatus()
    {
        return status;
    }

    /**
     * Returns parcel information as a String
     */
    public String toString()
    {
        String s = " Parcel Code: "+parcelCode+", Country of origin: "+origin+", Destination: "+destination+", Status: "+status;

        return s;
    }

}
