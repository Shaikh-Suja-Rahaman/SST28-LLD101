import java.util.ArrayList;
import java.util.List;

public class InvoiceGenerator {
  public CalculatedInvoice generate(String invId, String customerType, List<OrderLine> lines, Menu menu) {
    // your logic here
        // String invId = "INV-" + (InvId);
        // StringBuilder out = new StringBuilder();
        // out.append("Invoice# ").append(invId).append("\n");

        double subtotal = 0.0;
        List<String> lineStrings = new ArrayList<>();
        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            subtotal += lineTotal;
            lineStrings.add(String.format("- %s x%d = %.2f", item.name, l.qty, lineTotal));
        }

        double taxPct = TaxRules.taxPercent(customerType);
        double tax = subtotal * (taxPct / 100.0);

        double discount = DiscountRules.discountAmount(customerType, subtotal, lines.size());

        double total = subtotal + tax - discount;

    CalculatedInvoice invoice = new CalculatedInvoice(invId, lineStrings , subtotal, taxPct, tax, discount, total);
    //this is just an object, which would hold how my data looks like, so that i can pass it to my
    //invoice formatter
    return invoice;
  }
}