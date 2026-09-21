public class ProjectorRental extends Rental {
   public ProjectorRental(String id, int days) {
        super(id, days);
    }

    public int calculateCharge() {
        int days = getDays();
        int charge;

        if (days <= 3) {
            charge = ((days * 60000)+20000); // Assuming getUnits() returns the number of projectors rented
        } else {
            charge = ((3 * 60000) + ((days - 3) * 45000) + 20000); // Assuming getUnits() returns the number of projectors rented
        }
        return charge;
    }

    public String label() {
        return "Projector";
    } 
}
