public abstract class Rental implements Chargeable {
    private String id;
    private int days;

    public Rental(String id, int days) {
        if (days <= 0) {
            throw new IllegalArgumentException();
        }
        this.id = id;
        this.days = days;
    }

    public String getId() {
        return id;
    }

    public int getDays() {
        return days;
    }

    @Override
    public abstract int calculateCharge(); //Abstract method to be implemented by subclasses

    public int calculateCharge(int units) { //Overloaded method to calculate charge for multiple copies
        if (units <= 0) {
            throw new IllegalArgumentException();
        }
        return units * calculateCharge();
    }

    public String label() {
        return "Rental";
    }

    public String summary() {
        return id + " | " + label() + " | " + calculateCharge();
    }
}
