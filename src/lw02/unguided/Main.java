package lw02.unguided;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        final int MAX_BORROW = 2;

        LinkedList<String[]> requests = new LinkedList<>();

        InputStream input = Main.class.getResourceAsStream("/borrowing.txt");
        Scanner scanner = new Scanner(input);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] data = line.split(" ");
            requests.add(data);
        }

        scanner.close();

        LinkedList<String[]> books = new LinkedList<>();

        books.add(new String[]{"Kalkulus", "2"});
        books.add(new String[]{"Fisika", "1"});
        books.add(new String[]{"Statistika", "2"});

        LinkedList<String[]> members = new LinkedList<>();

        for (String[] request : requests) {

            boolean memberExists = false;

            for (String[] member : members) {
                if (member[0].equals(request[0])) {
                    memberExists = true;
                }
            }

            if (!memberExists) {
                members.add(new String[]{request[0], "0"});
            }
        }

        Queue<String[]> queue = new LinkedList<>();

        while (!requests.isEmpty()) {
            queue.add(requests.removeFirst());
        }

        LinkedList<String[]> successfulRequests = new LinkedList<>();

        Stack<String[]> failedRequests = new Stack<>();

        while (!queue.isEmpty()) {

            String[] request = queue.poll();

            String memberName = request[0];
            String bookTitle = request[1];

            String[] selectedBook = null;
            String[] selectedMember = null;

            for (String[] book : books) {
                if (book[0].equals(bookTitle)) {
                    selectedBook = book;
                }
            }

            for (String[] member : members) {
                if (member[0].equals(memberName)) {
                    selectedMember = member;
                }
            }

            int stock = Integer.parseInt(selectedBook[1]);
            int borrowed = Integer.parseInt(selectedMember[1]);

            if (stock > 0 && borrowed < MAX_BORROW) {

                stock = stock - 1;
                borrowed = borrowed + 1;

                selectedBook[1] = String.valueOf(stock);
                selectedMember[1] = String.valueOf(borrowed);

                successfulRequests.add(request);

            } else {

                failedRequests.push(request);
            }
        }
         //yg berhasil
        System.out.println("=== Successfully Processed Requests ===");
        for (String[] request : successfulRequests){
            System.out.println(" " + request[0] + " " + request[1]);
        }
        //sisa buku
        System.out.println("=== Remaining Book Stock ===");
        for (String[] book : books){
            System.out.println(" " + book[0] + " : " + book[1]);
        }
        //yg gagal
        System.out.println("=== Failed Requests ===");
        while (!failed.isEmpty()) {
            String[] request = failed.pop();
            System.out.println(" " + request[0] + " " + request[1]);
        } 
    }
}
