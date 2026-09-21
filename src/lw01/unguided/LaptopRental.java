public class LaptopRental extends Rental {

    public LaptopRental(String id, int days) {
        super(id, days);
    }

    public int calculateCharge() {
        return ((getDays() * 40000) + 10000); // Assuming getUnits() returns the number of laptops rented
    }

    public String label() {
        return "Laptop";
    }
}
