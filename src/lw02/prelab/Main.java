import java.io.InputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {

        LinkedList<String[]> transactions = new LinkedList<>();
        LinkedList<String[]> customers = new LinkedList<>();

        InputStream input = Main.class.getResourceAsStream("/transactions.txt");
        Scanner scanner = new Scanner(input);

        while (scanner.hasNextLine()) {

            String line = scanner.nextLine();
            String[] data = line.split(" ");

            transactions.add(data);

            boolean customerExists = false;

            for (String[] customer : customers) {

                if (customer[0].equals(data[0])) {
                    customerExists = true;
                }
            }

            if (!customerExists) {
                customers.add(new String[]{data[0], "0"});
            }
        }

        scanner.close();

        Queue<String[]> queue = new LinkedList<>();

        while (!transactions.isEmpty()) {
            queue.add(transactions.removeFirst());
        }

        Stack<String[]> failedTransactions = new Stack<>();

        while (!queue.isEmpty()) {

            String[] transaction = queue.poll();

            String customerName = transaction[0];
            String type = transaction[1];
            int amount = Integer.parseInt(transaction[2]);

            for (String[] customer : customers) {

                if (customer[0].equals(customerName)) {

                    if (type.equals("DEPOSIT")) {

                        int balance = Integer.parseInt(customer[1]);
                        balance = balance + amount;
                        customer[1] = String.valueOf(balance);

                    } else if (type.equals("WITHDRAW")) {

                        int balance = Integer.parseInt(customer[1]);

                        if (amount > balance) {
                            failedTransactions.push(transaction);
                        } else {
                            balance = balance - amount;
                            customer[1] = String.valueOf(balance);
                        }
                    }
                }
            }
        }

        System.out.println("=== Final Balances ===");

        for (String[] customer : customers) {
            System.out.println(customer[0] + " : " + customer[1]);
        }

        System.out.println("=== Failed Transactions ===");

        while (!failedTransactions.isEmpty()) {

            String[] transaction = failedTransactions.pop();

            System.out.println(
                transaction[0] + " " +
                transaction[1] + " " +
                transaction[2]
            );
        }
    }
}