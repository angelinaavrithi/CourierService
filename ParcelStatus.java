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