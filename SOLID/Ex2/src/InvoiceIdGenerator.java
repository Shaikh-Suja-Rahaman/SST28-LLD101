public class InvoiceIdGenerator {
    private int seq = 1000;

    public String nextId() {
        return "INV-" + (++seq);
    }
}