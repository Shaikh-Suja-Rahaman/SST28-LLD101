import java.util.*;

public class CafeteriaSystem {
    private final FileStore store = new FileStore();
    private final InvoiceIdGenerator idGen = new InvoiceIdGenerator(); // one instance
    private final InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
    private final InvoiceFormatter formatter = new InvoiceFormatter();

    // Menu menu = new Menu();
    // private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    Menu menu;
    CafeteriaSystem(Menu menu){
        this.menu = menu;
    }

    // private int invoiceSeq = 1000;
    // public void addToMenu(MenuItem i) { menu.put(i.id, i); }

    // Intentionally SRP-violating: menu mgmt + tax + discount + format + persistence.
    public void checkout(String customerType, List<OrderLine> lines) {


        InvoiceGenerator invGen = invoiceGenerator;
        InvoiceIdGenerator idGenerator = idGen;
        InvoiceFormatter formatter = this.formatter;

        CalculatedInvoice invoice = invGen.generate(idGenerator.nextId(), customerType, lines, menu);

        String printable =  formatter.identityFormat(invoice);
        System.out.println(printable);
        store.save(invoice.invId, printable);
        System.out.println("Saved invoice: " + invoice.invId + " (lines=" + store.countLines(invoice.invId) + ")");
    }
}
