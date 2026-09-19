public class ColourPrint extends PrintJob {

    public ColourPrint(String id, int pages) {
        super(id, pages);
    }

    public int calculateCharge() { //Implementing the abstract method from PrintJob
        int pages = getPages();
        int charge;

        if (pages <= 10) {
            charge = pages * 1500;
        } else {
            charge = (10 * 1500) + ((pages - 10) * 1000);
        }
        return charge + 2000;
    }

    public String label() { //Overriding the label method from PrintJob
        return "Colour";
    }
    
}
