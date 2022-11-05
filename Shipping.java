public enum Shipping {
    STANDARD(20),
    EXPRESS(15),
    NEXTDAY(10);

    private int price;
 
    Shipping(int price) 
    { 
        this.price = price; 
    }
}
