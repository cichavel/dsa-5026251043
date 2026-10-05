package lw03.unguided;
import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(Main.class.getResourceAsStream("enrollment.txt"));
        Map<String, Integer> enrollmentMap = new LinkedHashMap<>();

        List<String> checkResults = new ArrayList<>();
        int reject = 0;

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String type = line.substring(0, line.indexOf(" "));
            String details = line.substring(line.indexOf(" ") + 1);
            //register
            if (type.equals("REGISTER")) {
                String cc = details.substring(0, details.indexOf(" "));
                int qty = Integer.parseInt(details.substring(details.indexOf(" ") + 1));
                if (qty <= 0) {
                    reject++;
                } else {
                    if (enrollmentMap.containsKey(cc)) {
                        int currentEnrollment = enrollmentMap.get(cc);
                        enrollmentMap.put(cc, currentEnrollment + qty);
                    } else {
                        enrollmentMap.put(cc, qty);
                    }
                }
            } 
            //withdraw
            else if (type.equals("WITHDRAW")) {
                String cc = details.substring(0, details.indexOf(" "));
                int qty = Integer.parseInt(details.substring(details.indexOf(" ") + 1));
                if (qty <= 0) {
                    reject++;
                } else {
                    if (enrollmentMap.containsKey(cc) && enrollmentMap.get(cc) >= qty) {
                        int currentEnrollment = enrollmentMap.get(cc);
                        enrollmentMap.put(cc, currentEnrollment - qty);
                    } else {
                        reject++;
                    }
                }
            } 
            //heck
            else if (type.equals("CHECK")) {
                String cc = details;
                if (enrollmentMap.containsKey(cc)) {
                    checkResults.add(cc + ": " + enrollmentMap.get(cc) + " students");
                } else {
                    checkResults.add(cc + ": Not found");
                }
            }
        }
        //mnampilkan output
        System.out.println("===== Enrollment Checks");
        for (String result : checkResults){
            System.out.println(result);
        }
        System.out.println("===== Final Enrollment =====");
        for (String p : enrollmentMap.keySet()){
            System.out.println(p + ": " + enrollmentMap.get(p) + " students");
        }
        System.out.println("Rejected operations: " + reject);
    }
}
