import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        List<Rental> rentals = new ArrayList<>();

        InputStream input = Main.class.getResourceAsStream("/rentals.txt");
        Scanner scanner = new Scanner(input);

        while (scanner.hasNext()) {

            String type = scanner.next();
            String id = scanner.next();
            int days = scanner.nextInt();
            int units = scanner.nextInt();

            Rental rental;

            if (type.equals("LAPTOP")) {
                rental = new LaptopRental(id, days);
            } else {
                rental = new ProjectorRental(id, days);
            }

            rentals.add(rental);
        }

        scanner.close();

        for (Rental rental : rentals) {
            System.out.println(rental.summary());
        }
    }
}

