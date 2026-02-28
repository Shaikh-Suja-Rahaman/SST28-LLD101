import java.util.List;

public class CalculatedInvoice {
    String invId;
    List<String> lines;
    double subtotal;
    double taxPct;
    double tax;
    double discount;
    double total;
   CalculatedInvoice(String invId, List<String> lines,
    double subtotal, double taxPct, double tax, double discount, double total
   ){
    this.invId = invId;
    this.lines = lines;
    this.subtotal = subtotal;
    this.taxPct = taxPct;
    this.tax = tax;
    this.discount = discount;
    this.total = total;
   }
}
