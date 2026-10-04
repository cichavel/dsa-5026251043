package lw03.prelab;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> playlist = new ArrayList<>();
        Scanner playlistScanner = new Scanner(Main.class.getResourceAsStream("playlist.txt"));

        Set<String> participants = new LinkedHashSet<>();
        Scanner participantScanner = new Scanner(Main.class.getResourceAsStream("participants.txt"));
        int duplicateRegistrations = 0;

        Map<String, Integer> inventory = new LinkedHashMap<>();
        Scanner inventoryScanner = new Scanner(Main.class.getResourceAsStream("inventory.txt"));
        int failedSales = 0;

        String line;
        String[] parts;
        String operation;
        String song;
        String name;
        String type;
        String product;
        int index;
        int quantity;
        int currentStock;
        int participantNumber;

        while (playlistScanner.hasNextLine()) {
            line = playlistScanner.nextLine();
            parts = line.split(" ", 2);
            operation = parts[0];

            if (operation.equals("ADD")) {
                song = parts[1];
                playlist.add(song);
            } else if (operation.equals("INSERT")) {
                parts = line.split(" ", 3);
                index = Integer.parseInt(parts[1]);
                song = parts[2];
                playlist.add(index, song);
            } else if (operation.equals("REMOVE")) {
                song = parts[1];
                playlist.remove(song);
            }
        }

        while (participantScanner.hasNextLine()) {
            name = participantScanner.nextLine();

            if (participants.contains(name)) {
                duplicateRegistrations++;
            } else {
                participants.add(name);
            }
        }

        while (inventoryScanner.hasNextLine()) {
            line = inventoryScanner.nextLine();
            parts = line.split(" ");
            type = parts[0];
            product = parts[1];
            quantity = Integer.parseInt(parts[2]);

            if (type.equals("ADD")) {
                if (inventory.containsKey(product)) {
                    currentStock = inventory.get(product);
                    inventory.put(product, currentStock + quantity);
                } else {
                    inventory.put(product, quantity);
                }
            } else if (type.equals("SELL")) {
                if (inventory.containsKey(product) && inventory.get(product) >= quantity) {
                    currentStock = inventory.get(product);
                    inventory.put(product, currentStock - quantity);
                } else {
                    failedSales++;
                }
            }
        }

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlist.size());
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }

        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + participants.size());
        participantNumber = 1;
        for (String participant : participants) {
            System.out.println(participantNumber + ". " + participant);
            participantNumber++;
        } System.out.println("Duplicate registrations: " + duplicateRegistrations);

        System.out.println("===== Problem 3 =====");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        } System.out.println("Failed sales: " + failedSales);

        playlistScanner.close();
        participantScanner.close();
        inventoryScanner.close();
    }
}
