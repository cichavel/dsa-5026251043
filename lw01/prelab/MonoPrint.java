public class MonoPrint extends PrintJob {
    public MonoPrint(String id, int pages) {
        super(id, pages);
    }

    public int calculateCharge() { //Implementing the abstract method from PrintJob
        return getPages() * 500;
    }
    
    public String label() { //Overriding the label method from PrintJob
        return "Mono";
    }
}
